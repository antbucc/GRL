package it.univaq.gamification.dsl.builders.ge_builders;

import it.univaq.gamification.dsl.builders.GRLDescrBuilder;
import it.univaq.gamification.dsl.ConstraintType;

public interface PointBuilder<P> extends GRLDescrBuilder<P> {

    PointBuilder<P> name(ConstraintType constraintType, String name);

    PointBuilder<P> score(ConstraintType constraintType, double score);

}
