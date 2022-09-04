package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.binders.*;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.BaseDescr;

public interface GamificationConceptDescrBuilder<P extends DescrBuilder<? , ?>, T extends BaseDescr> {

    ActionDescrBuilder<P> action();

    ActionDescrBuilder<P> action(ActionBind bind);

    PointDescrBuilder<P> point();

    PointDescrBuilder<P> point(PointBind bind);

    BadgeCollectionDescrBuilder<P> badgeCollection();

    BadgeCollectionDescrBuilder<P> badgeCollection(BadgeCollectionBind bind);

    ChallengeDescrBuilder<P> challenge();

    ChallengeDescrBuilder<P> challenge(ChallengeBind bind);

    InputDataDescrBuilder<P> inputData();

    InputDataDescrBuilder<P> inputData(InputDataBind bind);

    CustomDataDescrBuilder<P> customData();

    CustomDataDescrBuilder<P> customData(CustomDataBind bind);

    PlayerDescrBuilder<P> player();

    PlayerDescrBuilder<P> player(PlayerBind bind);

    GameDescrBuilder<P> game();

    GameDescrBuilder<P> game(GameBind bind);

    RewardDescrBuilder<P> reward();

    RewardDescrBuilder<P> reward(RewardBind bind);

    PropagationDescrBuilder<P> propagation();

    PropagationDescrBuilder<P> propagation(PropagationBind bind);

    ClassificationDescrBuilder<P> classification();

    ClassificationDescrBuilder<P> classification(ClassificationBind bind);

    T getDescr();

}
