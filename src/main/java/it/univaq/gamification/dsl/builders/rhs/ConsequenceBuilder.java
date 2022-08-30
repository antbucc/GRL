package it.univaq.gamification.dsl.builders.rhs;

import it.univaq.gamification.dsl.BindName;

public interface ConsequenceBuilder<P> {

    StringBuilder getConsequence();

    ConsequenceBuilder<P> addBadge(BindName badgeCollectionRef, String badge);

    ConsequenceBuilder<P> addBadgeWithNotification(BindName badgeCollectionRef, BindName gameIdRef, BindName teamIdRef, String badge);

    ConsequenceBuilder<P> gainLevel(BindName errorScoreRef, BindName errorsRef, BindName customDataRef, String level);

    P end();

}
