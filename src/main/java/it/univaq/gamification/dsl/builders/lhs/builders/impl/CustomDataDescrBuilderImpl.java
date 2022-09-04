package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import eu.trentorise.game.model.CustomData;
import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.builders.lhs.builders.CustomDataDescrBuilder;
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

    protected CustomDataDescrBuilderImpl(P parent, Bind bind) {
        this(parent);
        this.descr.setIdentifier(bind.getValue());
    }

    @Override
    public <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, null);
        return this;
    }

    @Override
    public <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, Bind bind) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, bind);
        return this;
    }

    @Override
    public CustomDataDescrBuilder<P> bindFromData(Bind bind, String value) {
        ConstraintHelper.addBindConstraint(this.descr, DATA + "[\"" + value + "\"]", bind);
        return this;
    }

}
