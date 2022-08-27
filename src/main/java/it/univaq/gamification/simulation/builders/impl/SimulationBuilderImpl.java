package it.univaq.gamification.simulation.builders.impl;

import it.univaq.gamification.simulation.builders.GameFactBuilder;
import it.univaq.gamification.simulation.builders.SimulationBuilder;
import it.univaq.gamification.simulation.builders.CheckExpectationLambda;
import it.univaq.gamification.utils.DrlDumper;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.core.io.impl.ByteArrayResource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationBuilderImpl implements SimulationBuilder {

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
        expectations = new ArrayList<>();
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
            knowledgeBuilder.add(new ByteArrayResource(drlDumper.dump(packageDescr).getBytes()), ResourceType.DRL);
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
        kieSession.fireAllRules();
        this.expectations.forEach(CheckExpectationLambda::check);
        // TODO: Remove all expectations and rules in order to run other tests
        // TODO: assert knowledgeBuilder.getErrors()
        return this;
    }
}
