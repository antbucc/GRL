package it.univaq.gamification.dsl.builders.lhs;

public interface GamificationBaseDescrBuilder<P> {

    P bind(String bindName, String value);

    <T> P declare(String bindName, T value);

    P constraint(String constraint);

}
