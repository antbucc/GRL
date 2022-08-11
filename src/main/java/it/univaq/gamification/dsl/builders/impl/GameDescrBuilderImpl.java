package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.Game;
import it.univaq.gamification.dsl.builders.GameDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.PatternDescr;

public class GameDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends BaseDescrBuilderImpl<P, PatternDescr>
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

    @Override
    public GameDescrBuilder<P> declare(String bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, value, bindName);
        return this;
    }

    @Override
    public GameDescrBuilder<P> constraint(String constraint) {
        ConstraintHelper.addConstraint(this.descr, constraint);
        return this;
    }
}
