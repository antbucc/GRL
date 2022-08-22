package it.univaq.gamification.dsl.builders.impl;


import it.univaq.gamification.dsl.builders.ImportDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.ImportDescr;

public class ImportDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, ImportDescr>
        implements
        ImportDescrBuilder {

    protected ImportDescrBuilderImpl(PackageDescrBuilder parent) {
        super(parent, new ImportDescr());
    }

    public ImportDescrBuilder target(String target) {
        descr.setTarget(target);
        return this;
    }

}
