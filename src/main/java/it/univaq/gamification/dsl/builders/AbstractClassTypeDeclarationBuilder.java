package it.univaq.gamification.dsl.builders;


import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.BaseDescr;

public interface AbstractClassTypeDeclarationBuilder<T extends BaseDescr>
        extends
        DescrBuilder<PackageDescrBuilder, T> {

     FieldDescrBuilder<AbstractClassTypeDeclarationBuilder<T>> newField(String name);
}
