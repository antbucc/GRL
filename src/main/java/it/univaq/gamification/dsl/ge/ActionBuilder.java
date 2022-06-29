package it.univaq.gamification.dsl.ge;

import it.univaq.gamification.dsl.DescrBuilder;
import it.univaq.gamification.dsl.Operator;

public interface ActionBuilder<P> extends DescrBuilder<P> {

    ActionBuilder<P> name(Operator operator, String name);

    ActionBuilder<P> name(Operator operator, String name, String bindName);

    ActionBuilder<P> bindName(String bindName);

}
