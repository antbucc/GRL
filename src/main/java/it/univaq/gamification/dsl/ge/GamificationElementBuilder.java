package it.univaq.gamification.dsl.ge;

public interface GamificationElementBuilder<P> {

    ActionBuilder<P> action();

    ActionBuilder<P> action(String bindName);

    PointBuilder<P> point();

    PointBuilder<P> point(String bindName);

    BadgeCollectionBuilder<P> badgeCollection();

    BadgeCollectionBuilder<P> badgeCollection(String bindName);

    ChallengeBuilder<P> challenge();

    ChallengeBuilder<P> challenge(String bindName);

    InputDataBuilder<P> inputData();

    InputDataBuilder<P> inputData(String bindName);

}
