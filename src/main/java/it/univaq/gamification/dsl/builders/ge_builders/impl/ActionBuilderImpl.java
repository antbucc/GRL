package it.univaq.gamification.dsl.builders.ge_builders.impl;

import eu.trentorise.game.model.Action;
import it.univaq.gamification.dsl.CELambda;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.ge_builders.ActionBuilder;
import it.univaq.gamification.dsl.builders.impl.GRLDescrBuilderImpl;
import it.univaq.gamification.dsl.utils.Helpers;
import org.drools.model.PatternDSL;
import org.drools.model.view.ViewItemBuilder;

import static org.drools.model.PatternDSL.alphaIndexedBy;
import static org.drools.model.DSL.declarationOf;
import static org.drools.model.PatternDSL.pattern;

public class ActionBuilderImpl<P> extends GRLDescrBuilderImpl<P> implements ActionBuilder<P> {

    private PatternDSL.PatternDef<Action> actionPattern;

    public ActionBuilderImpl(P parent, CELambda<ViewItemBuilder<?>> ceLambda) {
        super(parent, ceLambda);
        this.actionPattern = pattern(declarationOf(Action.class));
    }

    public ActionBuilderImpl(P parent, String bindName, CELambda<ViewItemBuilder<?>> ceLambda) {
        super(parent, ceLambda);
        // TODO: Add binding to the Action
        // this.actionPattern = pattern(declarationOf(Action.class));
    }

    @Override
    public ActionBuilder<P> name(ConstraintType constraintType, String name) {
        this.actionPattern.expr(
                Helpers.getExprFromConstraint(Action.class, constraintType, name),
                // TODO: handle equals, !equals and so on
                action -> action.getId().equals(name),
                alphaIndexedBy(String.class, constraintType.getConstraintType(), 0, Action::getId, name));

        return this;
    }

    @Override
    public ActionBuilder<P> name(ConstraintType constraintType, String name, String bindName) {
        return this;
    }

    @Override
    public ActionBuilder<P> bindName(String bindName) {
        return null;
    }

    @Override
    public P end() {
        this.viewItemBuilders.add(this.actionPattern);
        return super.end();
    }

}
