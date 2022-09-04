package it.univaq.gamification.dsl.builders;

import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PatternDescrBuilder<P extends DescrBuilder<?, ?>> extends DescrBuilder<P, PatternDescr> {
    PatternDescrBuilder<P> constraint(String constraint);

}