package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.Game;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.GameDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class GameDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, GameDescrBuilder<P>>
        implements GameDescrBuilder<P> {

    private final String ID = "id";

    protected GameDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Game.class.getSimpleName()));
        this.parent = parent;
    }

    protected GameDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public GameDescrBuilder<P> bindId(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, ID, bind);
        return this;
    }

}
