package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PointDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PointDescrBuilder<P>> {

    PointDescrBuilder<P> name(ConstraintType constraintType, String name);

    PointDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName);

    PointDescrBuilder<P> bindName(String bindName);

    PointDescrBuilder<P> score(ConstraintType constraintType, double score);

    PointDescrBuilder<P> score(ConstraintType constraintType, double score, String bindName);

    PointDescrBuilder<P> bindScore(String bindName);

}
