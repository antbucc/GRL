package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.Global;
import it.univaq.gamification.dsl.builders.GlobalDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.GlobalDescr;

public class GlobalDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, GlobalDescr> implements GlobalDescrBuilder {
    protected GlobalDescrBuilderImpl(PackageDescrBuilder parent, String type, Global global) {
        super(parent, new GlobalDescr());
        this.descr.setType(type);
        this.descr.setIdentifier(global.getIdentifier());
    }

}
