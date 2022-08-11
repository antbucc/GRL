package it.univaq.gamification.dsl.builders.impl;

import eu.trentorise.game.model.CustomData;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.CustomDataDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public class CustomDataDescrBuilderImpl<P extends DescrBuilder<?, ?>>
        extends GamificationBaseDescrBuilderImpl<P, CustomDataDescrBuilder<P>>
        implements CustomDataDescrBuilder<P> {

    private final String DATA = "data";

    protected CustomDataDescrBuilderImpl(P parent) {
        super(parent, new PatternDescr(CustomData.class.getSimpleName()));
        this.parent = parent;
    }

    protected CustomDataDescrBuilderImpl(P parent, String bindName) {
        this(parent);
        this.descr.setIdentifier(bindName);
    }

    @Override
    public <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, null, value instanceof String);
        return this;
    }

    @Override
    public <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, String bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, bindName, value instanceof String);
        return this;
    }

    @Override
    public CustomDataDescrBuilder<P> declareFromData(String bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, DATA + "[\"" + value + "\"]", bindName);
        return this;
    }

}
