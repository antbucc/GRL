package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.PointConcept;
import it.univaq.gamification.dsl.utils.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.PointDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
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

    protected PointDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }


    @Override
    public PointDescrBuilder<P> name(ConstraintType constraintType, String name) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, null, true);
        return this;
    }

    @Override
    public PointDescrBuilder<P> name(ConstraintType constraintType, String name, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, NAME, name, bindName, true);
        return this;
    }

    @Override
    public PointDescrBuilder<P> bindName(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, NAME, bindName);
        return this;
    }

    @Override
    public PointDescrBuilder<P> score(ConstraintType constraintType, double score) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, null, false);
        return this;
    }

    @Override
    public PointDescrBuilder<P> score(ConstraintType constraintType, String globalName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, globalName, null, false);
        return this;
    }

    @Override
    public PointDescrBuilder<P> score(ConstraintType constraintType, double score, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, SCORE, score, bindName, false);
        return this;
    }

    @Override
    public PointDescrBuilder<P> bindScore(String bindName) {
        ConstraintHelper.addBindConstraint(this.descr, SCORE, bindName);
        return this;
    }

}
