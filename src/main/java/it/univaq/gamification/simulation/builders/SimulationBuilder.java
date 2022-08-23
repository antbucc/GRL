package it.univaq.gamification.simulation.builders;


import it.univaq.gamification.simulation.utils.CheckExpectationLambda;
import org.drools.compiler.lang.descr.PackageDescr;

public interface SimulationBuilder {

    SimulationBuilder addFacts(GameFactBuilder<?, ?>... conceptBuilders);

    SimulationBuilder addRules(PackageDescr... packageDescrs);

    SimulationBuilder addExpectations(CheckExpectationLambda... expectations);

    void simulate();

}
