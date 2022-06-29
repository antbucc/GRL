package it.univaq.gamification.dsl.impl;

import it.univaq.gamification.dsl.Operator;
import it.univaq.gamification.dsl.ge.ActionBuilder;

public class ActionBuilderImpl<T> implements ActionBuilder<T> {
    @Override
    public T end() {
        return (T) new CEBuilderImpl<T>();
    }

    @Override
    public ActionBuilder<T> name(Operator operator, String name) {
        System.out.println("     - name " + operator + " " + name);
        return new ActionBuilderImpl<>();
    }

    @Override
    public ActionBuilder<T> name(Operator operator, String name, String bindName) {
        System.out.println("     - " + bindName + " : name " + operator + " " + name);
        return new ActionBuilderImpl<>();
    }

    @Override
    public ActionBuilder<T> bindName(String bindName) {
        System.out.println("     - " + bindName + " : name ");
        return new ActionBuilderImpl<>();
    }
}
