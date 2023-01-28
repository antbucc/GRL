package it.univaq.gamification.dsl.builders.impl;


import it.univaq.gamification.dsl.builders.ImportDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.ImportDescr;

public class ImportDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, ImportDescr> implements ImportDescrBuilder {

    protected ImportDescrBuilderImpl(PackageDescrBuilder parent, Class<?> target) {
        super(parent, new ImportDescr());
        this.descr.setTarget(target.getName());
    }

}
