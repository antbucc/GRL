package it.univaq.gamification.dsl.builders.rhs.builders;

import it.univaq.gamification.dsl.Global;
import it.univaq.gamification.dsl.binders.*;

public interface ConsequenceBuilder<P> {

    StringBuilder getConsequence();

    ConsequenceBuilder<P> addBadge(BadgeCollectionBind badgeCollectionBind, String badge);

    ConsequenceBuilder<P> addBadge(BadgeCollectionBind badgeCollectionBind, Bind badge);

    ConsequenceBuilder<P> addBadgeWithNotification(BadgeCollectionBind badgeCollectionBind, Bind gameIdBind, Bind teamIdBind, String badge);

    ConsequenceBuilder<P> addBadgeWithNotification(BadgeCollectionBind badgeCollectionBind, Bind gameIdBind, Bind teamIdBind, Bind badge);

    ConsequenceBuilder<P> gainLevel(Bind errorScoreBind, Bind errorsBind, CustomDataBind customDataBind, String level);

    ConsequenceBuilder<P> gainLevel(Bind errorScoreBind, Bind errorsBind, CustomDataBind customDataBind, Bind level);

    ConsequenceBuilder<P> increaseScore(PointBind pointBind, Double amount);

    ConsequenceBuilder<P> increaseScore(PointBind pointBind, Bind amount);

    ConsequenceBuilder<P> increaseScore(PointBind pointBind, Global amount);

    ConsequenceBuilder<P> completeChallenge(ChallengeBind challengeBind);

    ConsequenceBuilder<P> insert(Class<?> clazz);

    ConsequenceBuilder<P> insert(Class<?> clazz, Object... parameters);

    P end();

}
