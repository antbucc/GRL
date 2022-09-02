package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.PackageDescr;
import it.univaq.gamification.dsl.builders.DeclareDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import it.univaq.gamification.dsl.builders.TypeDeclarationDescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;

public class DeclareDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, PackageDescr>
        implements
        DeclareDescrBuilder {

    protected DeclareDescrBuilderImpl(PackageDescrBuilder parent) {
        super(parent, parent.getDescr());
    }

    public TypeDeclarationDescrBuilder type() {
        TypeDeclarationDescrBuilder tddb = new TypeDeclarationDescrBuilderImpl(parent);
        descr.addTypeDeclaration(tddb.getDescr());
        return tddb;
    }

}
