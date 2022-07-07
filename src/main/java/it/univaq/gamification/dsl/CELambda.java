package it.univaq.gamification.dsl;

import java.util.ArrayList;

@FunctionalInterface
public interface CELambda<T> {

    boolean execute(ArrayList<T> expressions);

}
