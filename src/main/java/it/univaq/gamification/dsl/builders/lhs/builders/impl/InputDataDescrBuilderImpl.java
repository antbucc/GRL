package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.InputData;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.builders.InputDataDescrBuilder;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class InputDataDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, InputDataDescrBuilder<P>>
        implements InputDataDescrBuilder<P> {

    private final String DATA = "data";

    protected InputDataDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(InputData.class.getSimpleName()));
        this.parent = parent;
    }

    protected InputDataDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, null);
        return this;
    }

    @Override
    public <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, bind);
        return this;
    }

    @Override
    public InputDataDescrBuilder<P> bindFromData(Bind bind, String value) {
        ConstraintHelper.addBindConstraint(this.descr, DATA + "[\"" + value + "\"]", bind);
        return this;
    }

}
