package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.utils.BindName;

public interface GamificationBaseDescrBuilder<P> {

    P bind(BindName bindName, String value);

    <T> P declare(BindName bindName, T value);

    P constraint(String constraint);

}
