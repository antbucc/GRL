package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.Propagation;
import it.univaq.gamification.dsl.utils.BindName;
import it.univaq.gamification.dsl.utils.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.PropagationDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
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

    protected PropagationDescrBuilderImpl(P parent, BindName bindName) {
        this(parent);
        this.descr.setIdentifier(bindName.getValue());
    }

    @Override
    public PropagationDescrBuilder<P> action(ConstraintType constraintType, String action) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ACTION, action, null, true);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> action(ConstraintType constraintType, String action, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, ACTION, action, bindName, true);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> bindAction(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, ACTION, bindName);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level) {
        ConstraintHelper.addConstraint(this.descr, constraintType, LEVEL, level, null, true);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, LEVEL, level, bindName, true);
        return this;
    }

    @Override
    public PropagationDescrBuilder<P> bindLevel(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, LEVEL, bindName);
        return this;
    }
}
