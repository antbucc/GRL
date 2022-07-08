package it.univaq.gamification.dsl.builders;

import org.drools.model.Rule;

public interface GRLRuleBuilder {

    GRLRuleBuilder name(String name);

    GRLCEBuilder<GRLRuleBuilder> when();

    GRLRuleBuilder then();

    Rule end();


}
