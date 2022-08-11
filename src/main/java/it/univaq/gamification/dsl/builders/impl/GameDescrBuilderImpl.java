package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.Game;
import it.univaq.gamification.dsl.builders.GameDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
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

    protected GameDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public GameDescrBuilder<P> bindId(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, ID, bindName);
        return this;
    }

}
