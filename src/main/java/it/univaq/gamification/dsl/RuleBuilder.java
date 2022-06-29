package it.univaq.gamification.dsl;

public interface RuleBuilder {

    RuleBuilder name(String name);

    CEBuilder<RuleBuilder> when();

    RuleBuilder then();

    RuleBuilder end();

}
