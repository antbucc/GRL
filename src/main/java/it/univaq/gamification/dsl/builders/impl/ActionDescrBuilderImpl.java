package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.Action;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.ActionDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.PatternDescr;

    public class ActionDescrBuilderImpl<P extends DescrBuilder<?, ?>>
            extends BaseDescrBuilderImpl<P, PatternDescr>
            implements ActionDescrBuilder<P> {

    private final String NAME = "name";

    protected ActionDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Action.class.getName()));
        this.parent = parent;
    }

    protected ActionDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public ActionDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null, true);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bindName, true);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> bindName(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bindName);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> declare(String bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, value, bindName);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> constraint(String constraint) {
        ConstraintHelper.addConstraint(this.descr, constraint);
        return this;
    }
}