package it.univaq.gamification.dsl.builders.impl;


import it.univaq.gamification.dsl.builders.AbstractClassTypeDeclarationBuilder;
import it.univaq.gamification.dsl.builders.FieldDescrBuilder;
import it.univaq.gamification.dsl.builders.PackageDescrBuilder;
import it.univaq.gamification.dsl.builders.TypeDeclarationDescrBuilder;
import org.drools.compiler.lang.api.AnnotationDescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.TypeDeclarationDescr;

public class TypeDeclarationDescrBuilderImpl extends BaseDescrBuilderImpl<PackageDescrBuilder, TypeDeclarationDescr>
        implements
        TypeDeclarationDescrBuilder {

    protected TypeDeclarationDescrBuilderImpl(PackageDescrBuilder parent) {
        super(parent, new TypeDeclarationDescr());
    }

    public TypeDeclarationDescrBuilder name(Class<?> type) {
        descr.setTypeName(type.getSimpleName());
        return this;
    }


    public TypeDeclarationDescrBuilder superType(String type) {
        descr.addSuperType(type);
        return this;
    }

    public TypeDeclarationDescrBuilder setTrait(boolean trait) {
        descr.setTrait(trait);
        return this;
    }

    public AnnotationDescrBuilder<TypeDeclarationDescrBuilder> newAnnotation(String name) {
        AnnotationDescrBuilder<TypeDeclarationDescrBuilder> annotation = new AnnotationDescrBuilderImpl<>(this, name);
        descr.addAnnotation(annotation.getDescr());
        return annotation;
    }

    public FieldDescrBuilder<AbstractClassTypeDeclarationBuilder<TypeDeclarationDescr>> newField(String name) {
        FieldDescrBuilder<AbstractClassTypeDeclarationBuilder<TypeDeclarationDescr>> field = new FieldDescrBuilderImpl<>(this, name);
        descr.addField(field.getDescr());
        return field;
    }

}
