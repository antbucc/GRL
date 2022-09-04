package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.lhs.builders.impl.CEDescrBuilderImpl;
import it.univaq.gamification.dsl.builders.rhs.builders.impl.ConsequenceBuilderImpl;
import it.univaq.gamification.dsl.builders.lhs.builders.CEDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import it.univaq.gamification.dsl.builders.RuleDescrBuilder;
import it.univaq.gamification.dsl.builders.rhs.builders.ConsequenceBuilder;
import org.drools.compiler.lang.api.AttributeDescrBuilder;
import org.drools.compiler.lang.api.impl.AttributeDescrBuilderImpl;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.AndDescr;
import org.drools.compiler.lang.descr.AttributeDescr;
import org.drools.compiler.lang.descr.RuleDescr;

public class RuleDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, RuleDescr> implements RuleDescrBuilder {

    protected RuleDescrBuilderImpl(PackageDescrBuilder parent) {
        super(parent, new RuleDescr());
    }

    public RuleDescrBuilder name(String name) {
        this.descr.setName(name);
        return this;
    }

    public CEDescrBuilder<RuleDescrBuilder, AndDescr> when() {
        CEDescrBuilder<RuleDescrBuilder, AndDescr> conditionalElement = new CEDescrBuilderImpl<>(this, new AndDescr());
        this.descr.setLhs(conditionalElement.getDescr());
        return conditionalElement;
    }

    @Override
    public ConsequenceBuilder<RuleDescrBuilder> then() {
        ConsequenceBuilder<RuleDescrBuilder> consequenceBuilder = new ConsequenceBuilderImpl<>(this);
        this.descr.setConsequence(consequenceBuilder.getConsequence());
        return consequenceBuilder;
    }


    @Override
    public AttributeDescrBuilder<RuleDescrBuilder> attribute(String name) {
        AttributeDescrBuilder<RuleDescrBuilder> attribute = new AttributeDescrBuilderImpl<>(this, name);
        this.descr.addAttribute(attribute.getDescr());
        return attribute;
    }

    @Override
    public RuleDescrBuilder attribute(String name, String value) {
        this.descr.addAttribute(new AttributeDescr(name, value));
        return this;
    }

    @Override
    public RuleDescrBuilder attribute(String name, String value, AttributeDescr.Type type) {
        this.descr.addAttribute(new AttributeDescr(name, value, type));
        return this;
    }
}
