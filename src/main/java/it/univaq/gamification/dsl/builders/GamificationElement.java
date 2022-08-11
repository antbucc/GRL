package it.univaq.gamification.dsl.builders;

public interface GamificationElement<P> {

    P declare(String bindName, String value);

    P constraint(String constraint);

}
