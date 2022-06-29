package it.univaq.gamification.dsl.impl;

import it.univaq.gamification.dsl.Operator;
import it.univaq.gamification.dsl.ge.InputDataBuilder;

public class InputDataBuilderImpl<T> implements InputDataBuilder<T> {
    @Override
    public T end() {
        return (T) new CEBuilderImpl<T>();
    }

    @Override
    public InputDataBuilder<T> attribute(String key, Operator operator, String value) {
        System.out.println("     - data[" + key + "] " + operator + " " + value);
        return new InputDataBuilderImpl<>();
    }
}
