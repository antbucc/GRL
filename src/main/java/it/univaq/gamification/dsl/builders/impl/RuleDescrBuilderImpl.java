package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.CEDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import it.univaq.gamification.dsl.builders.RuleDescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.AndDescr;
import org.drools.compiler.lang.descr.RuleDescr;

public class RuleDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, RuleDescr> implements RuleDescrBuilder {

    protected RuleDescrBuilderImpl(PackageDescrBuilder parent) {
        super(parent, new RuleDescr());
    }

    public RuleDescrBuilder name(String name) {
        descr.setName(name);
        return this;
    }

    public CEDescrBuilder<RuleDescrBuilder, AndDescr> when() {
        CEDescrBuilder<RuleDescrBuilder, AndDescr> ce = new CEDescrBuilderImpl<>(this, new AndDescr());
        descr.setLhs(ce.getDescr());
        return ce;
    }


}
