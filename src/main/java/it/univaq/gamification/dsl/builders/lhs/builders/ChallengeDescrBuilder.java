package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ChallengeDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ChallengeDescrBuilder<P>> {

    ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName);

    ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName, Bind bind);

    ChallengeDescrBuilder<P> bindModelName(Bind bind);

    ChallengeDescrBuilder<P> isCompleted(Boolean completed);

    ChallengeDescrBuilder<P> isCompleted(Boolean completed, Bind bind);

    ChallengeDescrBuilder<P> bindIsCompleted(Bind bind);

    <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value);

    <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value, Bind bind);

    ChallengeDescrBuilder<P> bindFromField(Bind bind, String value);

}
