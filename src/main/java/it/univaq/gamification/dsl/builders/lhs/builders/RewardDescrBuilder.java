package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface RewardDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<RewardDescrBuilder<P>> {

    RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage);

    RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage, Bind bind);

    RewardDescrBuilder<P> bindPercentage(Bind bind);

    RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold);

    RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold, Bind bind);

    RewardDescrBuilder<P> bindThreshold(Bind bind);

    RewardDescrBuilder<P> bindCalculationPointConcept(Bind bind);

    RewardDescrBuilder<P> bindTargetPointConcept(Bind bind);

}
