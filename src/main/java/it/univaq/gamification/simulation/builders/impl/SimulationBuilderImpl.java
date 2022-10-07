package it.univaq.gamification.simulation.builders.impl;

import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import it.univaq.gamification.simulation.SimulationError;
import it.univaq.gamification.simulation.TrackingAgendaEventListener;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import it.univaq.gamification.simulation.builders.SimulationBuilder;
import it.univaq.gamification.simulation.builders.CheckExpectationLambda;
import it.univaq.gamification.simulation.graph.GraphVisualizer;
import it.univaq.gamification.simulation.graph.RelationshipEdge;
import it.univaq.gamification.utils.DrlDumper;
import org.drools.compiler.lang.descr.RuleDescr;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.core.io.impl.ByteArrayResource;
import org.drools.verifier.Verifier;
import org.drools.verifier.builder.VerifierBuilderFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SimulationBuilderImpl implements SimulationBuilder {

    private final Logger logger = LoggerFactory.getLogger(SimulationBuilderImpl.class);
    private final Verifier verifier;
    private final DrlDumper drlDumper;
    private final KnowledgeBuilder knowledgeBuilder;
    private final InternalKnowledgeBase knowledgeBase;
    private final KieSession kieSession;
    private final List<CheckExpectationLambda> expectations;
    // private final CSVPrinter csvPrinter;
    private final Graph<String, RelationshipEdge> stateGraph;
    private final Map<AssertionError, List<String>> expectationErrors;
    private final Map<String, Boolean> rulesEnabledMapping;
    private final List<String> lastAddedRules;

    public SimulationBuilderImpl() {
        // try {
        //     FileWriter fileWriter = new FileWriter(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date()) + ".csv");
        //     this.csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
        //             .withHeader("Rule", "Salience", "Timestamp", "Starting state", "Final state")
        //             .withDelimiter(';'));
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }

        drlDumper = new DrlDumper();
        rulesEnabledMapping = new HashMap<>();
        knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        kieSession = knowledgeBase.newKieSession();
        stateGraph = new DefaultDirectedGraph<>(RelationshipEdge.class);
        // TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener(csvPrinter, stateGraph);
        expectationErrors = new LinkedHashMap<>();
        lastAddedRules = new ArrayList<>();
        expectations = new ArrayList<>();
        verifier = VerifierBuilderFactory.newVerifierBuilder().newVerifier();
        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener(stateGraph, rulesEnabledMapping);
        kieSession.addEventListener(agendaEventListener);
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
        // Remove previously added rules
        lastAddedRules.clear();

        for (PackageDescr packageDescr : packageDescrs) {
            ByteArrayResource packageDrlByteArrayResource = new ByteArrayResource(drlDumper.dump(packageDescr).getBytes());

            if (this.verifyRule(packageDrlByteArrayResource)) {
                knowledgeBuilder.add(packageDrlByteArrayResource, ResourceType.DRL);
            }

            for (RuleDescr rule : packageDescr.getRules()) {
                String ruleName = rule.getName();
                lastAddedRules.add(ruleName);
                rulesEnabledMapping.put(ruleName, true);
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

        // Firing all rules only if enabled
        int totalFiredRules = kieSession.fireAllRules(match -> rulesEnabledMapping.get(match.getRule().getName()));
        // List fired rules
        logger.info("Total fired rules: {}", totalFiredRules);

        // Check expectations
        try {
            this.expectations.forEach(CheckExpectationLambda::check);
        } catch (AssertionError e) {
            expectationErrors.put(e, lastAddedRules);
        }

        if (expectationErrors.size() > 0) {
            logger.error("Assertion failure");
        } else {
            logger.info("Expectations verified successfully");
        }

        // Remove all expectations
        this.expectations.clear();
        logger.info("Expectations cleared");

        // Eventually print errors
        if (knowledgeBuilder.hasErrors()) {
            knowledgeBuilder.getErrors().forEach(error -> logger.error(error.getMessage()));
            logger.error("Aborting...");
            throw new SimulationError("Error during simulation");
        }

        return this;
    }

    @Override
    public void simulateAndClose() {
        this.simulate();

        // Print csv
        // try {
        //     this.csvPrinter.flush();
        //     this.csvPrinter.close();
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }

        // Show graph
        new GraphVisualizer(stateGraph, expectationErrors).visualize();

        // Throw error if at least one
        if (expectationErrors.size() > 0) {
            throw expectationErrors.entrySet().iterator().next().getKey();
        }
    }
}
