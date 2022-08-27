package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.ChallengeConcept;
import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.ChallengeDescrBuilder;
import it.univaq.gamification.dsl.ConstraintHelper;
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

    protected ChallengeDescrBuilderImpl(P parent, BindName bindName) {
        this(parent);
        this.descr.setIdentifier(bindName.getValue());
    }

    @Override
    public ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, MODEL_NAME, modelName, null, true);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> modelName(ConstraintType constraintType, String modelName, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, MODEL_NAME, modelName, bindName, true);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindModelName(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, MODEL_NAME, bindName);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> isCompleted(Boolean completed) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, IS_COMPLETED, completed, null, false);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> isCompleted(Boolean completed, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, ConstraintType.EQ, IS_COMPLETED, completed, bindName, false);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindIsCompleted(BindName bindName) {
        ConstraintHelper.addBindConstraint(this.descr, IS_COMPLETED, bindName);
        return this;
    }

    @Override
    public <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, FIELDS + "[\"" +key + "\"]", value, null, value instanceof String);
        return this;
    }

    @Override
    public <T> ChallengeDescrBuilder<P> fromFields(ConstraintType constraintType, String key, T value, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, FIELDS + "[\"" +key + "\"]", value, bindName, value instanceof String);
        return this;
    }

    @Override
    public ChallengeDescrBuilder<P> bindFromField(BindName bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, FIELDS + "[\"" + value + "\"]", bindName);
        return this;
    }

}
