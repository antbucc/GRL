package it.univaq.gamification.dsl.impl;

import it.univaq.gamification.dsl.CEBuilder;
import it.univaq.gamification.dsl.RuleBuilder;

public class RuleBuilderImpl implements RuleBuilder {

    @Override
    public RuleBuilder name(String name) {
        System.out.println("Rule name: " + name + "\n");
        return new RuleBuilderImpl();
    }

    @Override
    public CEBuilder<RuleBuilder> when() {
        System.out.println("WHEN (");
        return new CEBuilderImpl<>();
    }

    @Override
    public RuleBuilder then() {
        return new RuleBuilderImpl();
    }

    @Override
    public RuleBuilder end() {
        return null;
    }
}
