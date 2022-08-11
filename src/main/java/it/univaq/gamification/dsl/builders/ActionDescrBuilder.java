package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ActionDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationElement<ActionDescrBuilder<P>> {

    ActionDescrBuilder<P> name(ConstraintType constraintType, String name);

    ActionDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName);

    ActionDescrBuilder<P> bindName(String bindName);

}
