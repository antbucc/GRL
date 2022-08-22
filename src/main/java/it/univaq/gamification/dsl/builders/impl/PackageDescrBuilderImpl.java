package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.GlobalDescrBuilder;
import it.univaq.gamification.dsl.builders.ImportDescrBuilder;
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
    public PackageDescrBuilder newImport(String target) {
        ImportDescrBuilder impl = new ImportDescrBuilderImpl(this, target);
        this.descr.addImport(initDescr(impl));
        return this;
    }

    @Override
    public PackageDescrBuilder newGlobal(String type, String identifier) {
        GlobalDescrBuilder global = new GlobalDescrBuilderImpl(this, type, identifier );
        this.descr.addGlobal(initDescr(global));
        return this;
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
