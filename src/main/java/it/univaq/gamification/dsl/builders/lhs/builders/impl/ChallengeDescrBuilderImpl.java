package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.ChallengeConcept;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.ChallengeDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class ChallengeDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, ChallengeDescrBuilder<P>>
        implements ChallengeDescrBuilder<P> {

    private final String MODEL_NAME = "modelName";
    private final String IS_COMPLETED = "isCompleted()";
    private final String FIELDS = "fields";
    private final String START = "start";
    private final String END = "end";

    protected ChallengeDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(ChallengeConcept.class.getSimpleName()));
        this.parent = parent;
    }

    protected ChallengeDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, MODEL_NAME, modelName, null);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, MODEL_NAME, modelName, bind);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindModelName(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, MODEL_NAME, bind);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> isCompleted(Boolean completed) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, IS_COMPLETED, completed, null);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> isCompleted(Boolean completed, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, IS_COMPLETED, completed, bind);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindIsCompleted(Bind bind) {
        ConstraintHelper.addBindConstraint(this.descr, IS_COMPLETED, bind);
        return this;
    }

    @Override
    public <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, FIELDS + "[\"" +key + "\"]", value, null);
        return this;
    }

    @Override
    public <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, FIELDS + "[\"" +key + "\"]", value, bind);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindFromField(Bind bind, String value) {
        ConstraintHelper.addBindConstraint(this.descr, FIELDS + "[\"" + value + "\"]", bind);
        return this;
    }

}
