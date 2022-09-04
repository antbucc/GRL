package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.BadgeCollectionConcept;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.BadgeCollectionDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class BadgeCollectionDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, BadgeCollectionDescrBuilder<P>>
        implements BadgeCollectionDescrBuilder<P> {

    private final String NAME = "name";
    private final String BADGE_EARNED = "badgeEarned";

    protected BadgeCollectionDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(BadgeCollectionConcept.class.getSimpleName()));
        this.parent = parent;
    }

    protected BadgeCollectionDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bind);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> bindName(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bind);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge) {
        ConstraintHelper.addConstraint(this.descr, constraintType, BADGE_EARNED, badge, null);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, BADGE_EARNED, badge, bind);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.CONTAINS, BADGE_EARNED, badge, null);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedContains(Bind badge) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.CONTAINS, BADGE_EARNED, badge, null);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.CONTAINS, BADGE_EARNED, badge, bind);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedContains(Bind badge, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.CONTAINS, BADGE_EARNED, badge, bind);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.NOT_CONTAINS, BADGE_EARNED, badge, null);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(Bind badge) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.NOT_CONTAINS, BADGE_EARNED, badge, null);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.NOT_CONTAINS, BADGE_EARNED, badge, bind);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(Bind badge, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.NOT_CONTAINS, BADGE_EARNED, badge, bind);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> bindBadgeEarned(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, BADGE_EARNED, bind);
        return this;
    }

}
