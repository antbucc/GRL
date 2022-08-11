package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.InputData;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.InputDataDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
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

    protected InputDataDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, String value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, null, true);
        return this;
    }

    @Override
    public InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, String value, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, bindName, true);
        return this;
    }

    @Override
    public InputDataDescrBuilder<P> declareFromData(String bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, DATA + "[\"" + value + "\"]", bindName);
        return this;
    }

}
