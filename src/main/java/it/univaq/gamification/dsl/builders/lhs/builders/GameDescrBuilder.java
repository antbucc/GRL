package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface GameDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<GameDescrBuilder<P>> {

    GameDescrBuilder<P> bindId(Bind bind);

}
