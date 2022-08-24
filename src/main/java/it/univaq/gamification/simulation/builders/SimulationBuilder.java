package it.univaq.gamification.simulation.builders;


import it.univaq.gamification.simulation.utils.CheckExpectationLambda;
import org.drools.compiler.lang.descr.PackageDescr;

public interface SimulationBuilder {

    SimulationBuilder addFacts(GameFactBuilder<?, ?>... factBuilders);

    SimulationBuilder addFacts(Object... facts);

    SimulationBuilder addRules(PackageDescr... packageDescrs);

    SimulationBuilder addExpectations(CheckExpectationLambda... expectations);

    SimulationBuilder simulate();

}
