package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.Action;
import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.ActionDescrBuilder;
import it.univaq.gamification.dsl.ConstraintHelper;
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

    protected ActionDescrBuilderImpl(P parent, BindName bindName) {
        this(parent);
        this.descr.setIdentifier(bindName.getValue());
    }

    @Override
    public ActionDescrBuilder<P> id(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ID, name, null, true);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> id(ConstraintType constraintType, String name, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ID, name, bindName, true);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> bindId(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, ID, bindName);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null, true);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> name(ConstraintType constraintType, String name, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bindName, true);
        return this;
    }

    @Override
    public ActionDescrBuilder<P> bindName(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bindName);
        return this;
    }

}
