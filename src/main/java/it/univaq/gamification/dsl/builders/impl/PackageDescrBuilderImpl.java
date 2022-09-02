package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.PackageDescr;
import it.univaq.gamification.dsl.builders.*;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.BaseDescr;

public class PackageDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, PackageDescr> implements PackageDescrBuilder {

    public PackageDescrBuilderImpl() {
        super(null, new PackageDescr());
    }

    private <T extends BaseDescr> T initDescr(DescrBuilder<PackageDescrBuilder, T> builder) {
        T descr = builder.getDescr();
        descr.setNamespace(this.descr.getNamespace());
        return descr;
    }

    @Override
    public PackageDescrBuilder name(String name) {
        this.descr.setNamespace(name);
        return this;
    }

    @Override
    public ImportDescrBuilder newImport(String target) {
        ImportDescrBuilder importz = new ImportDescrBuilderImpl(this, target);
        this.descr.addImport(initDescr(importz));
        return importz;
    }

    @Override
    public GlobalDescrBuilder newGlobal(String type, String identifier) {
        GlobalDescrBuilder global = new GlobalDescrBuilderImpl(this, type, identifier );
        this.descr.addGlobal(initDescr(global));
        return global;
    }

    @Override
    public DeclareDescrBuilder newDeclare() {
        return new DeclareDescrBuilderImpl(this);
    }

    public RuleDescrBuilder newRule() {
        RuleDescrBuilder rule = new RuleDescrBuilderImpl( this );
        this.descr.addRule(initDescr(rule));
        rule.getDescr().setUnit(this.descr.getUnit());
        return rule;
    }

    public PackageDescrBuilder end() {
        return this;
    }
}
