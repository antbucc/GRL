package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.Propagation;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.PropagationDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class PropagationDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, PropagationDescrBuilder<P>>
        implements PropagationDescrBuilder<P> {

    private final String ACTION = "action";
    private final String LEVEL = "level";

    protected PropagationDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Propagation.class.getSimpleName()));
        this.parent = parent;
    }

    protected PropagationDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public PropagationDescrBuilder<P> action(ConstraintType constraintType, String action) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ACTION, action, null);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> action(ConstraintType constraintType, String action, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ACTION, action, bind);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> bindAction(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, ACTION, bind);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level) {
        ConstraintHelper.addConstraint(this.descr, constraintType, LEVEL, level, null);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, LEVEL, level, bind);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> bindLevel(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, LEVEL, bind);
        return this;
    }
}
