package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.Player;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.PlayerDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class PlayerDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, PlayerDescrBuilder<P>>
        implements PlayerDescrBuilder<P> {

    private final String ID = "id";
    private final String TEAM = "team";

    protected PlayerDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Player.class.getSimpleName()));
        this.parent = parent;
    }

    protected PlayerDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public PlayerDescrBuilder<P> team(Boolean team) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, TEAM, team, null);
        return this;
    }

    @Override
    public PlayerDescrBuilder<P> bindId(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, ID, bind);
        return this;
    }

    @Override
    public PlayerDescrBuilder<P> bindTeam(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, TEAM, bind);
        return this;
    }

}
