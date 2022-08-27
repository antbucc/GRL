package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.Game;
import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.builders.lhs.GameDescrBuilder;
import it.univaq.gamification.dsl.ConstraintHelper;
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

    protected GameDescrBuilderImpl(P parent, BindName bindName) {
        this(parent);
        this.descr.setIdentifier(bindName.getValue());
    }

    @Override
    public GameDescrBuilder<P> bindId(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, ID, bindName);
        return this;
    }

}
