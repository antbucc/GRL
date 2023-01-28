package it.univaq.gamification.dsl.builders.impl;


import it.univaq.gamification.dsl.builders.FieldDescrBuilder;
import org.drools.compiler.lang.api.AnnotationDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.PatternDescr;
import org.drools.compiler.lang.descr.TypeFieldDescr;

public class FieldDescrBuilderImpl<T extends DescrBuilder<?,?>> extends BaseDescrBuilderImpl<T, TypeFieldDescr>
        implements
        FieldDescrBuilder<T> {

    protected FieldDescrBuilderImpl(T parent, String name) {
        super(parent, new TypeFieldDescr(name));
    }

    public AnnotationDescrBuilder<FieldDescrBuilder<T>> newAnnotation(String name) {
        AnnotationDescrBuilder<FieldDescrBuilder<T>> annotation = new AnnotationDescrBuilderImpl<>(this, name);
        descr.addAnnotation(annotation.getDescr());
        return annotation;
    }

    public FieldDescrBuilder<T> index(int index) {
        descr.setIndex(index);
        return this;
    }

    public FieldDescrBuilder<T> name( String name) {
        descr.setFieldName(name);
        return this;
    }

    public FieldDescrBuilder<T> type(Class<?> type) {
        descr.setPattern(new PatternDescr(type.getSimpleName()));
        return this;
    }

    public FieldDescrBuilder<T> initialValue(String value) {
        descr.setInitExpr(value);
        return this;
    }
}
