package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.Reward;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.RewardDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class RewardDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, RewardDescrBuilder<P>>
        implements RewardDescrBuilder<P> {

    private final String PERCENTAGE = "percentage";
    private final String THRESHOLD = "threshold";
    private final String CALCULATION_POINT_CONCEPT = "calculationPointConcept";
    private final String TARGET_POINT_CONCEPT = "targetPointConcept";

    protected RewardDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Reward.class.getSimpleName()));
        this.parent = parent;
    }

    protected RewardDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage) {
        ConstraintHelper.addConstraint(this.descr, constraintType, PERCENTAGE, percentage, null, false);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, PERCENTAGE, percentage, bindName, false);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindPercentage(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, PERCENTAGE, bindName);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold) {
        ConstraintHelper.addConstraint(this.descr, constraintType, THRESHOLD, threshold, null, false);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, THRESHOLD, threshold, bindName, false);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindThreshold(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, THRESHOLD, bindName);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindCalculationPointConcept(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, CALCULATION_POINT_CONCEPT, bindName);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindTargetPointConcept(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, TARGET_POINT_CONCEPT, bindName);
        return this;
    }
}
