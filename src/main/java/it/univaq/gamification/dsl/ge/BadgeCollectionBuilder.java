package it.univaq.gamification.dsl.ge;

import it.univaq.gamification.dsl.DescrBuilder;
import it.univaq.gamification.dsl.Operator;

public interface BadgeCollectionBuilder<P> extends DescrBuilder<P> {

    BadgeCollectionBuilder<P> name(Operator operator, String name);

    BadgeCollectionBuilder<P> earned(Operator operator, String badge);

}
