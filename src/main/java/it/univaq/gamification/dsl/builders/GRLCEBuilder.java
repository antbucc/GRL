package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.builders.ge_builders.*;

public interface GRLCEBuilder<P> extends GRLDescrBuilder<P> {

    GRLCEBuilder<GRLCEBuilder<P>> and();

    GRLCEBuilder<GRLCEBuilder<P>> or();

    GRLCEBuilder<GRLCEBuilder<P>> not();

    GRLCEBuilder<GRLCEBuilder<P>> exists();

    // Gamification elements builders

    PointBuilder<GRLCEBuilder<P>> point();

    // PointBuilder<GRLCEBuilder<P>> point(String bindName);

    ActionBuilder<GRLCEBuilder<P>> action();

    // ActionBuilder<GRLCEBuilder<P>> action(String bindName);
    //
    // BadgeCollectionBuilder<GRLCEBuilder<P>> badgeCollection();
    //
    // BadgeCollectionBuilder<GRLCEBuilder<P>> badgeCollection(String bindName);
    //
    // ChallengeBuilder<GRLCEBuilder<P>> challenge();
    //
    // ChallengeBuilder<GRLCEBuilder<P>> challenge(String bindName);
    //
    // InputDataBuilder<GRLCEBuilder<P>> inputData();
    //
    // InputDataBuilder<GRLCEBuilder<P>> inputData(String bindName);

}
