package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ChallengeDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ChallengeDescrBuilder<P>> {

    ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName);

    ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName, String bindName);

    ChallengeDescrBuilder<P> bindModelName(String bindName);

    ChallengeDescrBuilder<P> isCompleted(Boolean completed);

    ChallengeDescrBuilder<P> isCompleted(Boolean completed, String bindName);

    ChallengeDescrBuilder<P> bindIsCompleted(String bindName);

    <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value);

    <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value, String bindName);

    ChallengeDescrBuilder<P> declareFromField(String bindName, String value);

}
