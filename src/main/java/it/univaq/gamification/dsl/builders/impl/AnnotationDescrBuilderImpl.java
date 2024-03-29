package it.univaq.gamification.dsl.builders.impl;

import org.drools.compiler.lang.api.AnnotationDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.AnnotationDescr;

public class AnnotationDescrBuilderImpl<P extends DescrBuilder<? , ? >> extends BaseDescrBuilderImpl<P, AnnotationDescr>
        implements
        AnnotationDescrBuilder<P> {

    protected AnnotationDescrBuilderImpl(P parent, String name) {
        super(parent, new AnnotationDescr(name));
    }

    public AnnotationDescrBuilder<P> value(Object value) {
        descr.setValue(value);
        return this;
    }

    public AnnotationDescrBuilder<P> keyValue(String key, Object value) {
        descr.setKeyValue(key, value);
        return this;
    }

    @Override
    public AnnotationDescrBuilder<AnnotationDescrBuilder<P>> newAnnotation(String name) {
        return new AnnotationDescrBuilderImpl<>(this, name);
    }
}
