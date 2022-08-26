package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.utils.BindName;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.BaseDescr;

public interface GamificationConceptDescrBuilder<P extends DescrBuilder<? , ?>, T extends BaseDescr> {

    ActionDescrBuilder<P> action();

    ActionDescrBuilder<P> action(BindName bindName);

    PointDescrBuilder<P> point();

    PointDescrBuilder<P> point(BindName bindName);

    BadgeCollectionDescrBuilder<P> badgeCollection();

    BadgeCollectionDescrBuilder<P> badgeCollection(BindName bindName);

    ChallengeDescrBuilder<P> challenge();

    ChallengeDescrBuilder<P> challenge(BindName bindName);

    InputDataDescrBuilder<P> inputData();

    InputDataDescrBuilder<P> inputData(BindName bindName);

    CustomDataDescrBuilder<P> customData();

    CustomDataDescrBuilder<P> customData(BindName bindName);

    PlayerDescrBuilder<P> player();

    PlayerDescrBuilder<P> player(BindName bindName);

    GameDescrBuilder<P> game();

    GameDescrBuilder<P> game(BindName bindName);

    RewardDescrBuilder<P> reward();

    RewardDescrBuilder<P> reward(BindName bindName);

    PropagationDescrBuilder<P> propagation();

    PropagationDescrBuilder<P> propagation(BindName bindName);

    ClassificationDescrBuilder<P> classification();

    ClassificationDescrBuilder<P> classification(BindName bindName);

    T getDescr();

}
