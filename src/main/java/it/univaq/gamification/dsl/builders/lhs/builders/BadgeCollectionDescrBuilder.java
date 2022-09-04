package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface BadgeCollectionDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<BadgeCollectionDescrBuilder<P>> {

    BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name);

    BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind);

    BadgeCollectionDescrBuilder<P> bindName(Bind bind);

    BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge);

    BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge, Bind bind);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(Bind badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge, Bind bind);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(Bind badge, Bind bind);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(Bind badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge, Bind bind);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(Bind badge, Bind bind);

    BadgeCollectionDescrBuilder<P> bindBadgeEarned(Bind bind);

}
