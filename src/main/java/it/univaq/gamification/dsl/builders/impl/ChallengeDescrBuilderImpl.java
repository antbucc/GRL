package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.ChallengeConcept;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.ChallengeDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.PatternDescr;

public class ChallengeDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends BaseDescrBuilderImpl<P, PatternDescr>
        implements ChallengeDescrBuilder<P> {

    private final String MODEL_NAME = "modelName";
    private final String IS_COMPLETED = "isCompleted()";
    private final String FIELDS = "fields";
    private final String START = "start";
    private final String END = "end";

    protected ChallengeDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(ChallengeConcept.class.getName()));
        this.parent = parent;
    }

    protected ChallengeDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, MODEL_NAME, modelName, null, true);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, MODEL_NAME, modelName, bindName, true);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindModelName(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, MODEL_NAME, bindName);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> isCompleted(Boolean completed) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, IS_COMPLETED, completed, null, false);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> isCompleted(Boolean completed, String bindName) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, IS_COMPLETED, completed, bindName, false);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindIsCompleted(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, IS_COMPLETED, bindName);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> fieldsEntry(ConstraintType constraintType, String key, String value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, FIELDS + "[\"" +key + "\"]", value, null, true);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> fieldsEntry(ConstraintType constraintType, String key, String value, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, FIELDS + "[\"" +key + "\"]", value, bindName, true);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> declareFromField(String bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, FIELDS + "[\"" + value + "\"]", bindName);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> declare(String bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, value, bindName);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> constraint(String constraint) {
        ConstraintHelper.addConstraint(this.descr, constraint);
        return this;
    }
}