package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.*;
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
    public ActionDescrBuilder<CEDescrBuilder<P, T>> action(String bindName) {
        ActionDescrBuilder<CEDescrBuilder<P, T>> action = new ActionDescrBuilderImpl<>(this, bindName);
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
    public PointDescrBuilder<CEDescrBuilder<P, T>> point(String bindName) {
        PointDescrBuilder<CEDescrBuilder<P, T>> point = new PointDescrBuilderImpl<>(this, bindName);
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
    public BadgeCollectionDescrBuilder<CEDescrBuilder<P, T>> badgeCollection(String bindName) {
        BadgeCollectionDescrBuilder<CEDescrBuilder<P, T>> badgeCollection = new BadgeCollectionDescrBuilderImpl<>(this, bindName);
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
    public ChallengeDescrBuilder<CEDescrBuilder<P, T>> challenge(String bindName) {
        ChallengeDescrBuilder<CEDescrBuilder<P, T>> challenge = new ChallengeDescrBuilderImpl<>(this, bindName);
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
    public InputDataDescrBuilder<CEDescrBuilder<P, T>> inputData(String bindName) {
        InputDataDescrBuilder<CEDescrBuilder<P, T>> inputData = new InputDataDescrBuilderImpl<>(this, bindName);
        ((ConditionalElementDescr) descr).addDescr(inputData.getDescr());
        return inputData;
    }
}
