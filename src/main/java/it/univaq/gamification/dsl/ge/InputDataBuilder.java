package it.univaq.gamification.dsl.ge;

import it.univaq.gamification.dsl.DescrBuilder;
import it.univaq.gamification.dsl.Operator;

public interface InputDataBuilder<P> extends DescrBuilder<P> {

    // TODO: value could be also a number, etc.
    InputDataBuilder<P> attribute(String key, Operator operator, String value);

}
