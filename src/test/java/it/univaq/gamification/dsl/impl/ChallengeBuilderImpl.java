package it.univaq.gamification.dsl.impl;

import it.univaq.gamification.dsl.Operator;
import it.univaq.gamification.dsl.ge.ChallengeBuilder;

public class ChallengeBuilderImpl<T> implements ChallengeBuilder<T> {
    @Override
    public T end() {
        return (T) new CEBuilderImpl<T>();
    }

    @Override
    public ChallengeBuilder<T> modelName(Operator operator, String modelName) {
        System.out.println("     - modelName " + operator + " " + modelName);
        return new ChallengeBuilderImpl<>();
    }

    @Override
    public ChallengeBuilder<T> completed(Boolean completed) {
        System.out.println("     - completed " + completed);
        return new ChallengeBuilderImpl<>();
    }
}
