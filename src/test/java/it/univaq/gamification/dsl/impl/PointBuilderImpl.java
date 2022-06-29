package it.univaq.gamification.dsl.impl;

import it.univaq.gamification.dsl.Operator;
import it.univaq.gamification.dsl.ge.PointBuilder;

public class PointBuilderImpl<T> implements PointBuilder<T> {

    @Override
    public T end() {
        return (T) new CEBuilderImpl<T>();
    }

    @Override
    public PointBuilder<T> name(Operator operator, String name) {
        System.out.println("     - name " + operator + " " + name);
        return new PointBuilderImpl<>();
    }

    @Override
    public PointBuilder<T> score(Operator operator, double score) {
        System.out.println("     - score " + operator + " " + score);
        return new PointBuilderImpl<>();
    }
}
