package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.BadgeCollectionConcept;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.BadgeCollectionDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
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

    protected BadgeCollectionDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null, true);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bindName, true);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> bindName(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bindName);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge) {
        ConstraintHelper.addConstraint(this.descr, constraintType, BADGE_EARNED, badge, null, false);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, BADGE_EARNED, badge, bindName, false);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.CONTAINS, BADGE_EARNED, badge, null, false);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge, String bindName) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.CONTAINS, BADGE_EARNED, badge, bindName, false);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.NOT_CONTAINS, BADGE_EARNED, badge, null, false);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge, String bindName) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.NOT_CONTAINS, BADGE_EARNED, badge, bindName, false);
        return this;
    }

    @Override
    public BadgeCollectionDescrBuilder<P> bindBadgeEarned(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, BADGE_EARNED, bindName);
        return this;
    }

}
