package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.utils.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface ClassificationDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<ClassificationDescrBuilder<P>> {

    ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name);

    ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName);

    ClassificationDescrBuilder<P> bindName(String bindName);

    ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position);

    ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position, String bindName);

    ClassificationDescrBuilder<P> bindPosition(String bindName);

    ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score);

    ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score, String bindName);

    ClassificationDescrBuilder<P> bindScore(String bindName);

    ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType);

    ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType, String bindName);

    ClassificationDescrBuilder<P> bindScoreType(String scoreType);
}
