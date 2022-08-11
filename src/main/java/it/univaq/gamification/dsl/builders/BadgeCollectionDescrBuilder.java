package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface BadgeCollectionDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationElement<BadgeCollectionDescrBuilder<P>> {

    BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name);

    BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName);

    BadgeCollectionDescrBuilder<P> bindName(String bindName);

    BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge);

    BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge, String bindName);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge, String bindName);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge, String bindName);

    BadgeCollectionDescrBuilder<P> bindBadgeEarned(String bindName);

}
