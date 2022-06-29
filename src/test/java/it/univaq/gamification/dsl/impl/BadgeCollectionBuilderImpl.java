package it.univaq.gamification.dsl.impl;

import it.univaq.gamification.dsl.Operator;
import it.univaq.gamification.dsl.ge.BadgeCollectionBuilder;

public class BadgeCollectionBuilderImpl<T> implements BadgeCollectionBuilder<T> {
    @Override
    public T end() {
        return (T) new CEBuilderImpl<T>();
    }

    @Override
    public BadgeCollectionBuilder<T> name(Operator operator, String name) {
        System.out.println("     - name " + operator + " " + name);
        return new BadgeCollectionBuilderImpl<>();
    }

    @Override
    public BadgeCollectionBuilder<T> earned(Operator operator, String badge) {
        System.out.println("     - earned " + operator + " " + badge);
        return new BadgeCollectionBuilderImpl<>();
    }
}
