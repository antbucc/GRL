package it.univaq.gamification.dsl.impl;

import it.univaq.gamification.dsl.CEBuilder;
import it.univaq.gamification.dsl.ge.*;

public class CEBuilderImpl<T> implements CEBuilder<T> {
    @Override
    public CEBuilder<CEBuilder<T>> or() {
        System.out.println("    OR (");
        return new CEBuilderImpl<>();
    }

    @Override
    public CEBuilder<CEBuilder<T>> and() {
        System.out.println("    AND (");
        return new CEBuilderImpl<>();
    }

    @Override
    public CEBuilder<CEBuilder<T>> not() {
        return null;
    }

    @Override
    public CEBuilder<CEBuilder<T>> exists() {
        return null;
    }

    @Override
    public T end() {
        System.out.println(")");
        return (T) new CEBuilderImpl<>();
    }

    @Override
    public ActionBuilder<CEBuilder<T>> action() {
        System.out.println("    Action:");
        return new ActionBuilderImpl<>();
    }

    @Override
    public ActionBuilder<CEBuilder<T>> action(String bindName) {
        System.out.println("    " + bindName + " : Action:");
        return new ActionBuilderImpl<>();
    }

    @Override
    public PointBuilder<CEBuilder<T>> point() {
        System.out.println("    Point:");
        return new PointBuilderImpl<>();
    }

    @Override
    public PointBuilder<CEBuilder<T>> point(String bindName) {
        System.out.println("    " + bindName + " : Point:");
        return new PointBuilderImpl<>();
    }

    @Override
    public BadgeCollectionBuilder<CEBuilder<T>> badgeCollection() {
        System.out.println("    BadgeCollection:");
        return new BadgeCollectionBuilderImpl<>();
    }

    @Override
    public BadgeCollectionBuilder<CEBuilder<T>> badgeCollection(String bindName) {
        System.out.println("    " + bindName + " : BadgeCollection:");
        return new BadgeCollectionBuilderImpl<>();
    }

    @Override
    public ChallengeBuilder<CEBuilder<T>> challenge() {
        System.out.println("    Challenge:");
        return new ChallengeBuilderImpl<>();
    }

    @Override
    public ChallengeBuilder<CEBuilder<T>> challenge(String bindName) {
        System.out.println("    " + bindName + " : Challenge:");
        return new ChallengeBuilderImpl<>();
    }

    @Override
    public InputDataBuilder<CEBuilder<T>> inputData() {
        System.out.println("    InputData:");
        return new InputDataBuilderImpl<>();
    }

    @Override
    public InputDataBuilder<CEBuilder<T>> inputData(String bindName) {
        System.out.println("    " + bindName + " : InputData:");
        return new InputDataBuilderImpl<>();
    }
}
