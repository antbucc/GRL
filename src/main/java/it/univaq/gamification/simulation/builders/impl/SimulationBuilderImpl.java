package it.univaq.gamification.simulation.builders.impl;

import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import it.univaq.gamification.simulation.SimulationError;
import it.univaq.gamification.simulation.TrackingAgendaEventListener;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import it.univaq.gamification.simulation.builders.SimulationBuilder;
import it.univaq.gamification.simulation.builders.CheckExpectationLambda;
import it.univaq.gamification.utils.DrlDumper;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.core.io.impl.ByteArrayResource;
import org.drools.verifier.Verifier;
import org.drools.verifier.builder.VerifierBuilderFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationBuilderImpl implements SimulationBuilder {

    private final Logger logger = LoggerFactory.getLogger(SimulationBuilderImpl.class);
    private final Verifier verifier;
    private final DrlDumper drlDumper;
    private final KnowledgeBuilder knowledgeBuilder;
    private final InternalKnowledgeBase knowledgeBase;
    private final KieSession kieSession;
    private final List<CheckExpectationLambda> expectations;

    public SimulationBuilderImpl() {
        drlDumper = new DrlDumper();
        knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        kieSession = knowledgeBase.newKieSession();
        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);
        expectations = new ArrayList<>();
        verifier = VerifierBuilderFactory.newVerifierBuilder().newVerifier();
    }

    private boolean verifyRule(ByteArrayResource ruleDrlByteArrayResource) {
        verifier.addResourcesToVerify(ruleDrlByteArrayResource, ResourceType.DRL);

        if (!verifier.getErrors().isEmpty()) {
            verifier.getErrors().forEach(verifierError -> logger.error(verifierError.getMessage()));
            throw new SimulationError("Error during rule validation process");
        }

        return true;
    }

    @Override
    public SimulationBuilder addFacts(GameFactBuilder<?, ?>... factBuilders) {
        for (GameFactBuilder<?, ?> factBuilder : factBuilders) {
            kieSession.insert(factBuilder.asOriginalPojo());
        }
        return this;
    }

    @Override
    public SimulationBuilder addFacts(Object... facts) {
        for (Object fact : facts) {
            kieSession.insert(fact);
        }
        return this;
    }

    @Override
    public SimulationBuilder addRules(PackageDescr... packageDescrs) {
        for (PackageDescr packageDescr : packageDescrs) {
            ByteArrayResource ruleDrlByteArrayResource = new ByteArrayResource(drlDumper.dump(packageDescr).getBytes());

            if (this.verifyRule(ruleDrlByteArrayResource)) {
                knowledgeBuilder.add(ruleDrlByteArrayResource, ResourceType.DRL);
            }

        }
        return this;
    }

    @Override
    public SimulationBuilder addExpectations(CheckExpectationLambda... expectations) {
        this.expectations.addAll(Arrays.asList(expectations));
        return this;
    }

    @Override
    public SimulationBuilder simulate() {
        knowledgeBase.addPackages(knowledgeBuilder.getKnowledgePackages());

        // Firing all rules
        int totalFiredRules = kieSession.fireAllRules();
        // List fired rules
        logger.info("Total fired rules: {}", totalFiredRules);

        // Check expectations
        this.expectations.forEach(CheckExpectationLambda::check);
        logger.info("Expectations verified successfully");
        // Remove all expectations
        this.expectations.clear();
        logger.info("Expectations cleared");

        // Eventually print errors
        if (knowledgeBuilder.hasErrors()) {
            knowledgeBuilder.getErrors().forEach(error -> logger.error(error.getMessage()));
            logger.error("Aborting...");
            throw new SimulationError("Error during simulation");
        }

        // TODO: Remove rules in order to run other tests (?)
        return this;
    }
}
