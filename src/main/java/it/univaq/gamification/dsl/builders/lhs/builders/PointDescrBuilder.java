package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.Global;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PointDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PointDescrBuilder<P>> {

    PointDescrBuilder<P> name(ConstraintType constraintType, String name);

    PointDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind);

    PointDescrBuilder<P> bindName(Bind bind);

    PointDescrBuilder<P> score(ConstraintType constraintType, double score);

    PointDescrBuilder<P> score(ConstraintType constraintType, Global global);

    PointDescrBuilder<P> score(ConstraintType constraintType, double score, Bind bind);

    PointDescrBuilder<P> bindScore(Bind bind);

}
