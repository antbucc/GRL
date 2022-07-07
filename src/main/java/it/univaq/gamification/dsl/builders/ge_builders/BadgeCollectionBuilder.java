package it.univaq.gamification.dsl.builders.ge_builders;

import it.univaq.gamification.dsl.builders.GRLDescrBuilder;
import it.univaq.gamification.dsl.ConstraintType;

public interface BadgeCollectionBuilder<P> extends GRLDescrBuilder<P> {

    BadgeCollectionBuilder<P> name(ConstraintType constraintType, String name);

    BadgeCollectionBuilder<P> earned(ConstraintType constraintType, String badge);

}
