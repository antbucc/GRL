package it.univaq.gamification.dsl.builders.ge_builders;

import it.univaq.gamification.dsl.builders.GRLDescrBuilder;
import it.univaq.gamification.dsl.ConstraintType;

public interface ActionBuilder<P> extends GRLDescrBuilder<P> {

    ActionBuilder<P> name(ConstraintType constraintType, String name);

    ActionBuilder<P> name(ConstraintType constraintType, String name, String bindName);

    ActionBuilder<P> bindName(String bindName);

}
