package it.univaq.gamification.dsl.builders;

import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.BaseDescr;

public interface GamificationElementBuilder<P extends DescrBuilder<? , ?>, T extends BaseDescr> {

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

    T getDescr();

}