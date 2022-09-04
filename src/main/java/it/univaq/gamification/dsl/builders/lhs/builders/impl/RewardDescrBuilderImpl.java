package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.Reward;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.RewardDescrBuilder;
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

    protected RewardDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage) {
        ConstraintHelper.addConstraint(this.descr, constraintType, PERCENTAGE, percentage, null);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> percentage(ConstraintType constraintType, String percentage, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, PERCENTAGE, percentage, bind);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindPercentage(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, PERCENTAGE, bind);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold) {
        ConstraintHelper.addConstraint(this.descr, constraintType, THRESHOLD, threshold, null);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> threshold(ConstraintType constraintType, String threshold, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, THRESHOLD, threshold, bind);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindThreshold(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, THRESHOLD, bind);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindCalculationPointConcept(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, CALCULATION_POINT_CONCEPT, bind);
        return this;
    }

    @Override
    public RewardDescrBuilder<P> bindTargetPointConcept(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, TARGET_POINT_CONCEPT, bind);
        return this;
    }
}
