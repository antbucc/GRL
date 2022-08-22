package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.impl.lhs.CEDescrBuilderImpl;
import it.univaq.gamification.dsl.builders.impl.rhs.ConsequenceBuilderImpl;
import it.univaq.gamification.dsl.builders.lhs.CEDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import it.univaq.gamification.dsl.builders.RuleDescrBuilder;
import it.univaq.gamification.dsl.builders.rhs.ConsequenceBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.AndDescr;
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


}
