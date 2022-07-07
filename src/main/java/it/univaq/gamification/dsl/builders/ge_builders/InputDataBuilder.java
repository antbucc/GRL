package it.univaq.gamification.dsl.builders.ge_builders;

import it.univaq.gamification.dsl.builders.GRLDescrBuilder;
import it.univaq.gamification.dsl.ConstraintType;

public interface InputDataBuilder<P> extends GRLDescrBuilder<P> {

    // TODO: value could be also a number, etc.
    InputDataBuilder<P> attribute(String key, ConstraintType constraintType, String value);

}
