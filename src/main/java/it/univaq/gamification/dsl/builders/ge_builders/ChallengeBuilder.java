package it.univaq.gamification.dsl.builders.ge_builders;

import it.univaq.gamification.dsl.builders.GRLDescrBuilder;
import it.univaq.gamification.dsl.ConstraintType;

public interface ChallengeBuilder<P> extends GRLDescrBuilder<P> {

    ChallengeBuilder<P> modelName(ConstraintType constraintType, String modelName);

    ChallengeBuilder<P> completed(Boolean completed);

}
