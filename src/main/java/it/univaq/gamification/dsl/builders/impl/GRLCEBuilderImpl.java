package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.CELambda;
import it.univaq.gamification.dsl.builders.GRLCEBuilder;
import it.univaq.gamification.dsl.builders.ge_builders.*;
import it.univaq.gamification.dsl.builders.ge_builders.impl.ActionBuilderImpl;
import it.univaq.gamification.dsl.builders.ge_builders.impl.PointBuilderImpl;
import it.univaq.gamification.dsl.utils.Helpers;
import org.drools.model.view.ViewItemBuilder;

public class GRLCEBuilderImpl<P> extends GRLDescrBuilderImpl<P> implements GRLCEBuilder<P> {

    protected GRLCEBuilderImpl(P parent, CELambda<ViewItemBuilder<?>> ceLambda) {
        super(parent, ceLambda);
    }

    @Override
    public GRLCEBuilder<GRLCEBuilder<P>> and() {
        return new GRLCEBuilderImpl<>(this, (expressions) -> this.viewItemBuilders.add(org.drools.model.PatternDSL.and(
                Helpers.getFirstExpressions(expressions),
                Helpers.getExtraExpressions(expressions)
        )));
    }

    @Override
    public GRLCEBuilder<GRLCEBuilder<P>> or() {
        return new GRLCEBuilderImpl<>(this, (expressions) -> this.viewItemBuilders.add(org.drools.model.PatternDSL.or(
                Helpers.getFirstExpressions(expressions),
                Helpers.getExtraExpressions(expressions)
        )));
    }

    @Override
    public GRLCEBuilder<GRLCEBuilder<P>> not() {
        return new GRLCEBuilderImpl<>(this, (expressions) -> this.viewItemBuilders.add(org.drools.model.PatternDSL.not(
                Helpers.getFirstExpressions(expressions),
                Helpers.getExtraExpressions(expressions)
        )));
    }

    @Override
    public GRLCEBuilder<GRLCEBuilder<P>> exists() {
        return new GRLCEBuilderImpl<>(this, (expressions) -> this.viewItemBuilders.add(org.drools.model.PatternDSL.exists(
                Helpers.getFirstExpressions(expressions),
                Helpers.getExtraExpressions(expressions)
        )));
    }

    @Override
    public PointBuilder<GRLCEBuilder<P>> point() {
        return new PointBuilderImpl<>(this, (pattern) -> this.viewItemBuilders.addAll(pattern));
    }

    // @Override
    // public PointBuilder<GRLCEBuilder<P>> point(String bindName) {
    //     return null;
    // }

    @Override
    public ActionBuilder<GRLCEBuilder<P>> action() {
        return new ActionBuilderImpl<>(this, (pattern) -> this.viewItemBuilders.addAll(pattern));
    }

    // @Override
    // public ActionBuilder<GRLCEBuilder<P>> action(String bindName) {
    //     return null;
    // }
    //
    // @Override
    // public BadgeCollectionBuilder<GRLCEBuilder<P>> badgeCollection() {
    //     return null;
    // }
    //
    // @Override
    // public BadgeCollectionBuilder<GRLCEBuilder<P>> badgeCollection(String bindName) {
    //     return null;
    // }
    //
    // @Override
    // public ChallengeBuilder<GRLCEBuilder<P>> challenge() {
    //     return null;
    // }
    //
    // @Override
    // public ChallengeBuilder<GRLCEBuilder<P>> challenge(String bindName) {
    //     return null;
    // }
    //
    // @Override
    // public InputDataBuilder<GRLCEBuilder<P>> inputData() {
    //     return null;
    // }
    //
    // @Override
    // public InputDataBuilder<GRLCEBuilder<P>> inputData(String bindName) {
    //     return null;
    // }
}
