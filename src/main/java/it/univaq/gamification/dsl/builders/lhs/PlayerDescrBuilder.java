package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.utils.BindName;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PlayerDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PlayerDescrBuilder<P>> {

    PlayerDescrBuilder<P> team(Boolean team);

    PlayerDescrBuilder<P> bindId(BindName bindName);

    PlayerDescrBuilder<P> bindTeam(BindName bindName);

}
