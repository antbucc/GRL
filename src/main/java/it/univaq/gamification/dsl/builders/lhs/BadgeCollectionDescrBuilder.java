package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface BadgeCollectionDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<BadgeCollectionDescrBuilder<P>> {

    BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name);

    BadgeCollectionDescrBuilder<P> name(ConstraintType constraintType, String name, BindName bindName);

    BadgeCollectionDescrBuilder<P> bindName(BindName bindName);

    BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge);

    BadgeCollectionDescrBuilder<P> badgeEarned(ConstraintType constraintType, String badge, BindName bindName);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(BindName badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(String badge, BindName bindName);

    BadgeCollectionDescrBuilder<P> badgeEarnedContains(BindName badge, BindName bindName);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(BindName badge);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(String badge, BindName bindName);

    BadgeCollectionDescrBuilder<P> badgeEarnedNotContains(BindName badge, BindName bindName);

    BadgeCollectionDescrBuilder<P> bindBadgeEarned(BindName bindName);

}
