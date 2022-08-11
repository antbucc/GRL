package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface RewardDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<RewardDescrBuilder<P>> {

    RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage);

    RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage, String bindName);

    RewardDescrBuilder<P> bindPercentage(String bindName);

    RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold);

    RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold, String bindName);

    RewardDescrBuilder<P> bindThreshold(String bindName);

    RewardDescrBuilder<P> bindCalculationPointConcept(String bindName);

    RewardDescrBuilder<P> bindTargetPointConcept(String bindName);

}
