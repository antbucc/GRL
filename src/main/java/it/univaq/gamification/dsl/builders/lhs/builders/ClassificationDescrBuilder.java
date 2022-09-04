package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ClassificationDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ClassificationDescrBuilder<P>> {

    ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name);

    ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind);

    ClassificationDescrBuilder<P> bindName(Bind bind);

    ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position);

    ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position, Bind bind);

    ClassificationDescrBuilder<P> classificationRunNumber(ConstraintType constraintType, Integer classificationRunNumber);

    ClassificationDescrBuilder<P> bindClassificationRunNumber(ConstraintType constraintType, Integer classificationRunNumber, Bind bind);

    ClassificationDescrBuilder<P> bindPosition(Bind bind);

    ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score);

    ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score, Bind bind);

    ClassificationDescrBuilder<P> bindScore(Bind bind);

    ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType);

    ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType, Bind bind);

    ClassificationDescrBuilder<P> bindScoreType(Bind bind);
}
