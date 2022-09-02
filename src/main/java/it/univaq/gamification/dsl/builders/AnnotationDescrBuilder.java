package it.univaq.gamification.dsl.builders;

import org.drools.compiler.lang.api.AnnotatedDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.AnnotationDescr;

public interface AnnotationDescrBuilder<P extends DescrBuilder< ? , ? >>
        extends
        DescrBuilder<P, AnnotationDescr>,
        AnnotatedDescrBuilder<AnnotationDescrBuilder<P>> {

    org.drools.compiler.lang.api.AnnotationDescrBuilder<P> value(Object value);

    org.drools.compiler.lang.api.AnnotationDescrBuilder<P> keyValue(String key, Object value );

}
