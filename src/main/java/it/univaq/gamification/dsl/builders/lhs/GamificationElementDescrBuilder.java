package it.univaq.gamification.dsl.builders.lhs;

import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.BaseDescr;

public interface GamificationElementDescrBuilder<P extends DescrBuilder<? , ?>, T extends BaseDescr> {

    ActionDescrBuilder<P> action();

    ActionDescrBuilder<P> action(String bindName);

    PointDescrBuilder<P> point();

    PointDescrBuilder<P> point(String bindName);

    BadgeCollectionDescrBuilder<P> badgeCollection();

    BadgeCollectionDescrBuilder<P> badgeCollection(String bindName);

    ChallengeDescrBuilder<P> challenge();

    ChallengeDescrBuilder<P> challenge(String bindName);

    InputDataDescrBuilder<P> inputData();

    InputDataDescrBuilder<P> inputData(String bindName);

    CustomDataDescrBuilder<P> customData();

    CustomDataDescrBuilder<P> customData(String bindName);

    PlayerDescrBuilder<P> player();

    PlayerDescrBuilder<P> player(String bindName);

    GameDescrBuilder<P> game();

    GameDescrBuilder<P> game(String bindName);

    RewardDescrBuilder<P> reward();

    RewardDescrBuilder<P> reward(String bindName);

    PropagationDescrBuilder<P> propagation();

    PropagationDescrBuilder<P> propagation(String bindName);

    T getDescr();

}
