package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.Player;
import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.PlayerDescrBuilder;
import it.univaq.gamification.dsl.ConstraintHelper;
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

    protected PlayerDescrBuilderImpl(P parent, BindName bindName) {
        this(parent);
        this.descr.setIdentifier(bindName.getValue());
    }

    @Override
    public PlayerDescrBuilder<P> team(Boolean team) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, TEAM, team, null, false);
        return this;
    }

    @Override
    public PlayerDescrBuilder<P> bindId(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, ID, bindName);
        return this;
    }

    @Override
    public PlayerDescrBuilder<P> bindTeam(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, TEAM, bindName);
        return this;
    }

}
