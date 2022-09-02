package it.univaq.gamification.dsl.builders;

import org.drools.compiler.lang.api.AnnotatedDescrBuilder;
import org.drools.compiler.lang.descr.TypeDeclarationDescr;

public interface TypeDeclarationDescrBuilder
        extends
        AnnotatedDescrBuilder<TypeDeclarationDescrBuilder>,
        AbstractClassTypeDeclarationBuilder<TypeDeclarationDescr> {

    TypeDeclarationDescrBuilder name(String type);

    TypeDeclarationDescrBuilder superType(String type);

    TypeDeclarationDescrBuilder setTrait(boolean trait);
}
