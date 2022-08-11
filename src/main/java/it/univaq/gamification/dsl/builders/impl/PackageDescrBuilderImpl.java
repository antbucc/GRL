package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import it.univaq.gamification.dsl.builders.RuleDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.BaseDescr;
import org.drools.compiler.lang.descr.PackageDescr;

public class PackageDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, PackageDescr> implements PackageDescrBuilder {

    public PackageDescrBuilderImpl() {
        super(null, new PackageDescr());
    }

    @Override
    public PackageDescrBuilder name(String name) {
        this.descr.setNamespace( name );
        return this;
    }

    public RuleDescrBuilder newRule() {
        RuleDescrBuilder rule = new RuleDescrBuilderImpl( this );
        descr.addRule(initDescr(rule));
        rule.getDescr().setUnit(descr.getUnit());
        return rule;
    }

    private <T extends BaseDescr> T initDescr(DescrBuilder<PackageDescrBuilder, T> builder) {
        T descr = builder.getDescr();
        descr.setNamespace(this.descr.getNamespace());
        return descr;
    }

    public PackageDescrBuilder end() {
        return this;
    }
}
