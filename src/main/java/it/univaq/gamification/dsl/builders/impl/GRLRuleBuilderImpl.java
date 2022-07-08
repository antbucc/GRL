package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.GRLCEBuilder;
import it.univaq.gamification.dsl.builders.GRLRuleBuilder;
import org.drools.model.Rule;
import org.drools.model.RuleItemBuilder;
import org.drools.model.impl.RuleBuilder;
import org.drools.model.view.ViewItemBuilder;

import java.util.ArrayList;
import java.util.Collections;

import static org.drools.model.PatternDSL.rule;

public class GRLRuleBuilderImpl implements GRLRuleBuilder {

    private RuleBuilder rule;
    protected ArrayList<ViewItemBuilder<?>> viewItemBuilders;

    public GRLRuleBuilderImpl() {
        this.viewItemBuilders = new ArrayList<>(Collections.emptyList());
    }

    @Override
    public GRLRuleBuilder name(String name) {
        this.rule = rule(name);
        return this;
    }

    @Override
    public GRLCEBuilder<GRLRuleBuilder> when() {
        return new GRLCEBuilderImpl<>(this, ((expressions) -> this.viewItemBuilders.addAll(expressions)));
    }

    @Override
    public GRLRuleBuilder then() {
        return null;
    }

    @Override
    public Rule end() {
        return this.rule.build(this.viewItemBuilders.toArray(new RuleItemBuilder[0]));
    }

}
