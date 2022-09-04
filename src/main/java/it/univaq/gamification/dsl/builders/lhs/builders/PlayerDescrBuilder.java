package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PlayerDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PlayerDescrBuilder<P>> {

    PlayerDescrBuilder<P> team(Boolean team);

    PlayerDescrBuilder<P> bindId(Bind bind);

    PlayerDescrBuilder<P> bindTeam(Bind bind);

}
