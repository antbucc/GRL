package it.univaq.gamification.simulation.builders;

public interface GameFactBuilder<P, T> {

    P build();

    T asOriginalPojo();

}
