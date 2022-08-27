package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ChallengeDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ChallengeDescrBuilder<P>> {

    ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName);

    ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName, BindName bindName);

    ChallengeDescrBuilder<P> bindModelName(BindName bindName);

    ChallengeDescrBuilder<P> isCompleted(Boolean completed);

    ChallengeDescrBuilder<P> isCompleted(Boolean completed, BindName bindName);

    ChallengeDescrBuilder<P> bindIsCompleted(BindName bindName);

    <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value);

    <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value, BindName bindName);

    ChallengeDescrBuilder<P> bindFromField(BindName bindName, String value);

}
