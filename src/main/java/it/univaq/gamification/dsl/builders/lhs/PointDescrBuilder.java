package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PointDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PointDescrBuilder<P>> {

    PointDescrBuilder<P> name(ConstraintType constraintType, String name);

    PointDescrBuilder<P> name(ConstraintType constraintType, String name, BindName bindName);

    PointDescrBuilder<P> bindName(BindName bindName);

    PointDescrBuilder<P> score(ConstraintType constraintType, double score);

    PointDescrBuilder<P> score(ConstraintType constraintType, String globalName);

    PointDescrBuilder<P> score(ConstraintType constraintType, double score, BindName bindName);

    PointDescrBuilder<P> bindScore(BindName bindName);

}
