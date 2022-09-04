package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.binders.Bind;

public interface GamificationBaseDescrBuilder<P> {

    P bind(Bind bind, String value);

    <T> P declare(Bind bind, T value);

    P constraint(String constraint);

}
