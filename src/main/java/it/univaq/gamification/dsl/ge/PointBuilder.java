package it.univaq.gamification.dsl.ge;

import it.univaq.gamification.dsl.DescrBuilder;
import it.univaq.gamification.dsl.Operator;

public interface PointBuilder<P> extends DescrBuilder<P> {

    PointBuilder<P> name(Operator operator, String name);

    PointBuilder<P> score(Operator operator, double score);

}
