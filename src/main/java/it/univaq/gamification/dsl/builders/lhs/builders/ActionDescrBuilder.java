package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ActionDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ActionDescrBuilder<P>> {

    ActionDescrBuilder<P> id(ConstraintType constraintType, String name);

    ActionDescrBuilder<P> id(ConstraintType constraintType, String name, Bind bind);

    ActionDescrBuilder<P> bindId(Bind bind);

    ActionDescrBuilder<P> name(ConstraintType constraintType, String name);

    ActionDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind);

    ActionDescrBuilder<P> bindName(Bind bind);

}
