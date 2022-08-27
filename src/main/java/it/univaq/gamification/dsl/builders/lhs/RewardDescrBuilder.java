package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface RewardDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<RewardDescrBuilder<P>> {

    RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage);

    RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage, BindName bindName);

    RewardDescrBuilder<P> bindPercentage(BindName bindName);

    RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold);

    RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold, BindName bindName);

    RewardDescrBuilder<P> bindThreshold(BindName bindName);

    RewardDescrBuilder<P> bindCalculationPointConcept(BindName bindName);

    RewardDescrBuilder<P> bindTargetPointConcept(BindName bindName);

}
