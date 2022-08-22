package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.task.Classification;
import it.univaq.gamification.dsl.builders.lhs.ClassificationDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
import it.univaq.gamification.dsl.utils.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class ClassificationDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, ClassificationDescrBuilder<P>>
        implements ClassificationDescrBuilder<P> {

    private final String NAME = "name";
    private final String POSITION = "position";
    private final String SCORE = "score";
    private final String SCORE_TYPE = "scoreType";

    protected ClassificationDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(Classification.class.getSimpleName()));
        this.parent = parent;
    }

    protected ClassificationDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null, true);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bindName, true);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindName(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bindName);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position) {
        ConstraintHelper.addConstraint(this.descr, constraintType, POSITION, position, null, false);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> position(ConstraintType constraintType, Integer position, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, POSITION, position, bindName, false);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindPosition(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, POSITION, bindName);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, null, false);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> score(ConstraintType constraintType, Double score, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, null, false);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindScore(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, SCORE, bindName);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE_TYPE, scoreType, null, true);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> scoreType(ConstraintType constraintType, String scoreType, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE_TYPE, scoreType, bindName, true);
        return this;
    }

    @Override
    public ClassificationDescrBuilder<P> bindScoreType(String scoreType) {
        ConstraintHelper.addBindConstraint(this.descr, SCORE_TYPE, scoreType);
        return this;
    }
}
