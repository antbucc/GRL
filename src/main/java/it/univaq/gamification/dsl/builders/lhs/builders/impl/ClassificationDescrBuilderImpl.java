package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.task.Classification;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.ClassificationDescrBuilder;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class ClassificationDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, ClassificationDescrBuilder<P>>
        implements ClassificationDescrBuilder<P> {

    private final String NAME = "name";
    private final String POSITION = "position";
    private final String CLASSIFICATION_RUN_NUMBER = "classificationRunNumber";
    private final String SCORE = "score";
    private final String SCORE_TYPE = "scoreType";

    protected ClassificationDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Classification.class.getSimpleName()));
        this.parent = parent;
    }

    protected ClassificationDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindName(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position) {
        ConstraintHelper.addConstraint(this.descr, constraintType, POSITION, position, null);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, POSITION, position, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> classificationRunNumber(ConstraintType constraintType, Integer classificationRunNumber) {
        ConstraintHelper.addConstraint(this.descr, constraintType, CLASSIFICATION_RUN_NUMBER, classificationRunNumber, null);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindClassificationRunNumber(ConstraintType constraintType, Integer classificationRunNumber, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, CLASSIFICATION_RUN_NUMBER, classificationRunNumber, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindPosition(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, POSITION, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, null);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindScore(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, SCORE, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE_TYPE, scoreType, null);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE_TYPE, scoreType, bind);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindScoreType(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, SCORE_TYPE, bind);
        return this;
    }
}
