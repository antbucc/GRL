package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.Action;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.builders.ActionDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class ActionDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, ActionDescrBuilder<P>>
        implements ActionDescrBuilder<P> {

    private final String ID = "id";
    private final String NAME = "name";

    protected ActionDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Action.class.getSimpleName()));
        this.parent = parent;
    }

    protected ActionDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public ActionDescrBuilder<P> id(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ID, name, null);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> id(ConstraintType constraintType, String name, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ID, name, bind);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> bindId(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, ID, bind);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bind);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> bindName(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bind);
        return this;
    }

}
