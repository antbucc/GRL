package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.utils.BindName;
import it.univaq.gamification.dsl.utils.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ActionDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ActionDescrBuilder<P>> {

    ActionDescrBuilder<P> id(ConstraintType constraintType, String name);

    ActionDescrBuilder<P> id(ConstraintType constraintType, String name, BindName bindName);

    ActionDescrBuilder<P> bindId(BindName bindName);

    ActionDescrBuilder<P> name(ConstraintType constraintType, String name);

    ActionDescrBuilder<P> name(ConstraintType constraintType, String name, BindName bindName);

    ActionDescrBuilder<P> bindName(BindName bindName);

}
