package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import it.univaq.gamification.dsl.binders.*;
import it.univaq.gamification.dsl.builders.lhs.builders.*;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.*;

public class CEDescrBuilderImpl<P extends DescrBuilder<? , ?>, T extends AnnotatedBaseDescr>
        extends BaseDescrBuilderImpl<P, T>
        implements CEDescrBuilder<P, T> {

    public CEDescrBuilderImpl(P parent, T descr) {
        super(parent, descr);
    }

    public CEDescrBuilder<CEDescrBuilder<P, T>, AndDescr> and() {
        AndDescr andDescr = new AndDescr();
        ((ConditionalElementDescr) descr).addDescr(andDescr);
        return new CEDescrBuilderImpl<>( this, andDescr);
    }

    public CEDescrBuilder<CEDescrBuilder<P, T>, OrDescr> or() {
        OrDescr orDescr = new OrDescr();
        ((ConditionalElementDescr) descr).addDescr(orDescr);
        return new CEDescrBuilderImpl<>(this, orDescr);
    }

    public CEDescrBuilder<CEDescrBuilder<P, T>, NotDescr> not() {
        CEDescrBuilder<CEDescrBuilder<P, T>, NotDescr> not = new CEDescrBuilderImpl<>(this, new NotDescr());
        ((ConditionalElementDescr) descr).addDescr(not.getDescr());
        return not;
    }

    public CEDescrBuilder<CEDescrBuilder<P, T>, ExistsDescr> exists() {
        CEDescrBuilder<CEDescrBuilder<P, T>, ExistsDescr> exists = new CEDescrBuilderImpl<>(this, new ExistsDescr());
        ((ConditionalElementDescr) descr).addDescr(exists.getDescr());
        return exists;
    }

    // GAMIFICATION ELEMENT BUILDERS

    @Override
    public ActionDescrBuilder<CEDescrBuilder<P, T>> action() {
        ActionDescrBuilder<CEDescrBuilder<P, T>> action = new ActionDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(action.getDescr());
        return action;
    }

    @Override
    public ActionDescrBuilder<CEDescrBuilder<P, T>> action(ActionBind bind) {
        ActionDescrBuilder<CEDescrBuilder<P, T>> action = new ActionDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(action.getDescr());
        return action;
    }

    @Override
    public PointDescrBuilder<CEDescrBuilder<P, T>> point() {
        PointDescrBuilder<CEDescrBuilder<P, T>> point = new PointDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(point.getDescr());
        return point;
    }

    @Override
    public PointDescrBuilder<CEDescrBuilder<P, T>> point(PointBind bind) {
        PointDescrBuilder<CEDescrBuilder<P, T>> point = new PointDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(point.getDescr());
        return point;
    }

    @Override
    public BadgeCollectionDescrBuilder<CEDescrBuilder<P, T>> badgeCollection() {
        BadgeCollectionDescrBuilder<CEDescrBuilder<P, T>> badgeCollection = new BadgeCollectionDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(badgeCollection.getDescr());
        return badgeCollection;
    }

    @Override
    public BadgeCollectionDescrBuilder<CEDescrBuilder<P, T>> badgeCollection(BadgeCollectionBind bind) {
        BadgeCollectionDescrBuilder<CEDescrBuilder<P, T>> badgeCollection = new BadgeCollectionDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(badgeCollection.getDescr());
        return badgeCollection;
    }

    @Override
    public ChallengeDescrBuilder<CEDescrBuilder<P, T>> challenge() {
        ChallengeDescrBuilder<CEDescrBuilder<P, T>> challenge = new ChallengeDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(challenge.getDescr());
        return challenge;
    }

    @Override
    public ChallengeDescrBuilder<CEDescrBuilder<P, T>> challenge(ChallengeBind bind) {
        ChallengeDescrBuilder<CEDescrBuilder<P, T>> challenge = new ChallengeDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(challenge.getDescr());
        return challenge;
    }

    @Override
    public InputDataDescrBuilder<CEDescrBuilder<P, T>> inputData() {
        InputDataDescrBuilder<CEDescrBuilder<P, T>> inputData = new InputDataDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(inputData.getDescr());
        return inputData;
    }

    @Override
    public InputDataDescrBuilder<CEDescrBuilder<P, T>> inputData(InputDataBind bind) {
        InputDataDescrBuilder<CEDescrBuilder<P, T>> inputData = new InputDataDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(inputData.getDescr());
        return inputData;
    }

    @Override
    public CustomDataDescrBuilder<CEDescrBuilder<P, T>> customData() {
        CustomDataDescrBuilder<CEDescrBuilder<P, T>> customData = new CustomDataDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(customData.getDescr());
        return customData;
    }

    @Override
    public CustomDataDescrBuilder<CEDescrBuilder<P, T>> customData(CustomDataBind bind) {
        CustomDataDescrBuilder<CEDescrBuilder<P, T>> customData = new CustomDataDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(customData.getDescr());
        return customData;
    }

    @Override
    public PlayerDescrBuilder<CEDescrBuilder<P, T>> player() {
        PlayerDescrBuilder<CEDescrBuilder<P, T>> player = new PlayerDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(player.getDescr());
        return player;
    }

    @Override
    public PlayerDescrBuilder<CEDescrBuilder<P, T>> player(PlayerBind bind) {
        PlayerDescrBuilder<CEDescrBuilder<P, T>> player = new PlayerDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(player.getDescr());
        return player;
    }

    @Override
    public GameDescrBuilder<CEDescrBuilder<P, T>> game() {
        GameDescrBuilder<CEDescrBuilder<P, T>> game = new GameDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(game.getDescr());
        return game;
    }

    @Override
    public GameDescrBuilder<CEDescrBuilder<P, T>> game(GameBind bind) {
        GameDescrBuilder<CEDescrBuilder<P, T>> game = new GameDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(game.getDescr());
        return game;
    }

    @Override
    public RewardDescrBuilder<CEDescrBuilder<P, T>> reward() {
        RewardDescrBuilder<CEDescrBuilder<P, T>> reward = new RewardDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(reward.getDescr());
        return reward;
    }

    @Override
    public RewardDescrBuilder<CEDescrBuilder<P, T>> reward(RewardBind bind) {
        RewardDescrBuilder<CEDescrBuilder<P, T>> reward = new RewardDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(reward.getDescr());
        return reward;
    }

    @Override
    public PropagationDescrBuilder<CEDescrBuilder<P, T>> propagation() {
        PropagationDescrBuilder<CEDescrBuilder<P, T>> propagation = new PropagationDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(propagation.getDescr());
        return propagation;
    }

    @Override
    public PropagationDescrBuilder<CEDescrBuilder<P, T>> propagation(PropagationBind bind) {
        PropagationDescrBuilder<CEDescrBuilder<P, T>> propagation = new PropagationDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(propagation.getDescr());
        return propagation;
    }

    @Override
    public ClassificationDescrBuilder<CEDescrBuilder<P, T>> classification() {
        ClassificationDescrBuilder<CEDescrBuilder<P, T>> classification = new ClassificationDescrBuilderImpl<>(this);
        ((ConditionalElementDescr) descr).addDescr(classification.getDescr());
        return classification;
    }

    @Override
    public ClassificationDescrBuilder<CEDescrBuilder<P, T>> classification(ClassificationBind bind) {
        ClassificationDescrBuilder<CEDescrBuilder<P, T>> classification = new ClassificationDescrBuilderImpl<>(this, bind);
        ((ConditionalElementDescr) descr).addDescr(classification.getDescr());
        return classification;
    }
}
