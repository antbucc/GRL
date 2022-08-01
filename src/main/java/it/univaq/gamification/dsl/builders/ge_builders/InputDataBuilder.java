package it.univaq.gamification.dsl.builders.ge_builders;

import it.univaq.gamification.dsl.builders.GRLDescrBuilder;
import it.univaq.gamification.dsl.ConstraintType;

public interface InputDataBuilder<P> extends GRLDescrBuilder<P> {

    // TODO: Use method overload for int, double, etc
    InputDataBuilder<P> attribute(String key, ConstraintType constraintType, String value);

}
