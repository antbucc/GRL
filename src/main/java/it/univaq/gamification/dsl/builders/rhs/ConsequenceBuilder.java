package it.univaq.gamification.dsl.builders.rhs;

public interface ConsequenceBuilder<P> {

    StringBuilder getConsequence();

    ConsequenceBuilder<P> addBadge(String badgeCollectionRef, String badge);

    ConsequenceBuilder<P> addBadgeWithNotification(String badgeCollectionRef, String gameIdRef, String teamIdRef, String badge);

    ConsequenceBuilder<P> levelError(String errorScoreRef, String errorsRef, String customDataRef, String level);

    P end();

}
