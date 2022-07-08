package it.univaq.gamification.dsl.builders.ge_builders.impl;

import eu.trentorise.game.model.PointConcept;
import it.univaq.gamification.dsl.CELambda;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.ge_builders.PointBuilder;
import it.univaq.gamification.dsl.builders.impl.GRLDescrBuilderImpl;
import it.univaq.gamification.dsl.utils.Helpers;
import org.drools.model.PatternDSL;
import org.drools.model.view.ViewItemBuilder;

import static org.drools.model.DSL.declarationOf;
import static org.drools.model.PatternDSL.alphaIndexedBy;
import static org.drools.model.PatternDSL.pattern;

public class PointBuilderImpl<P> extends GRLDescrBuilderImpl<P> implements PointBuilder<P> {

    private PatternDSL.PatternDef<PointConcept> pointPattern;

    public PointBuilderImpl(P parent, CELambda<ViewItemBuilder<?>> ceLambda) {
        super(parent, ceLambda);
        this.pointPattern = pattern(declarationOf(PointConcept.class));
    }

    public PointBuilderImpl(P parent, String bindName, CELambda<ViewItemBuilder<?>> ceLambda) {
        super(parent ,ceLambda);
        // TODO: Add binding to the Point
        // this.pointPattern = pattern(declarationOf(PointConcept.class));
    }

    @Override
    public PointBuilder<P> name(ConstraintType constraintType, String name) {
        this.pointPattern.expr(
                Helpers.getExprFromConstraint(PointConcept.class, constraintType, name),
                // TODO: handle equals, !equals and so on [Check if it is possible to avoid arrow functions]
                point -> point.getName().equals(name),
                alphaIndexedBy(String.class, constraintType.getConstraintType(), 0, PointConcept::getId, name));

        return this;
    }

    @Override
    public PointBuilder<P> score(ConstraintType constraintType, double score) {
        this.pointPattern.expr(
                Helpers.getExprFromConstraint(PointConcept.class, constraintType, String.valueOf(score)),
                // TODO: handle equals, !equals and so on [Check if it is possible to avoid arrow functions]
                point -> point.getScore().equals(score),
                alphaIndexedBy(Double.class, constraintType.getConstraintType(), 0, PointConcept::getScore, score));

        return this;
    }

    @Override
    public P end() {
        this.viewItemBuilders.add(this.pointPattern);
        return super.end();
    }

}
