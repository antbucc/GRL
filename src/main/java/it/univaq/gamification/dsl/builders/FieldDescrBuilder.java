package it.univaq.gamification.dsl.builders;


import org.drools.compiler.lang.api.AnnotatedDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.TypeFieldDescr;

public interface FieldDescrBuilder<T extends DescrBuilder<?,?>>
        extends
        AnnotatedDescrBuilder<FieldDescrBuilder<T>>,
        DescrBuilder<T, TypeFieldDescr> {

    FieldDescrBuilder<T> index(int index);

    FieldDescrBuilder<T> name(String name);

    FieldDescrBuilder<T> type(String type);

    FieldDescrBuilder<T> initialValue(String value);
}
