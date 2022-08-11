package it.univaq.gamification.dsl.builders;

import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PlayerDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationElement<PlayerDescrBuilder<P>> {

    PlayerDescrBuilder<P> team(Boolean team);

    PlayerDescrBuilder<P> bindTeam(String bindName);

}
