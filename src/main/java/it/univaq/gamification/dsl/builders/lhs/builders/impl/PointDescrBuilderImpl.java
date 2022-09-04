package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.PointConcept;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.builders.PointDescrBuilder;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.Global;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class PointDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, PointDescrBuilder<P>>
        implements PointDescrBuilder<P> {

    private final String NAME = "name";
    private final String SCORE = "score";

    protected PointDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(PointConcept.class.getSimpleName()));
        this.parent = parent;
    }

    protected PointDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }


    @Override
    public PointDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null);
        return this;
    }

    @Override
    public PointDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bind);
        return this;
    }

    @Override
    public PointDescrBuilder<P> bindName(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bind);
        return this;
    }

    @Override
    public PointDescrBuilder<P> score(ConstraintType constraintType, double score) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, null);
        return this;
    }

    @Override
    public PointDescrBuilder<P> score(ConstraintType constraintType, Global global) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, global, null);
        return this;
    }

    @Override
    public PointDescrBuilder<P> score(ConstraintType constraintType, double score, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, bind);
        return this;
    }

    @Override
    public PointDescrBuilder<P> bindScore(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, SCORE, bind);
        return this;
    }

}
