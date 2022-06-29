package it.univaq.gamification.dsl.ge;

import it.univaq.gamification.dsl.DescrBuilder;
import it.univaq.gamification.dsl.Operator;

public interface ChallengeBuilder<P> extends DescrBuilder<P> {

    ChallengeBuilder<P> modelName(Operator operator, String modelName);

    ChallengeBuilder<P> completed(Boolean completed);

}
