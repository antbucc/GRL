package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ClassificationDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ClassificationDescrBuilder<P>> {

    ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name);

    ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name, BindName bindName);

    ClassificationDescrBuilder<P> bindName(BindName bindName);

    ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position);

    ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position, BindName bindName);

    ClassificationDescrBuilder<P> bindPosition(BindName bindName);

    ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score);

    ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score, BindName bindName);

    ClassificationDescrBuilder<P> bindScore(BindName bindName);

    ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType);

    ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType, BindName bindName);

    ClassificationDescrBuilder<P> bindScoreType(BindName bindName);
}
