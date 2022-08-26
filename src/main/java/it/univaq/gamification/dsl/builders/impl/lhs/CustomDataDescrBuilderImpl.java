package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.CustomData;
import it.univaq.gamification.dsl.utils.BindName;
import it.univaq.gamification.dsl.utils.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.CustomDataDescrBuilder;
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

    protected CustomDataDescrBuilderImpl(P parent, BindName bindName) {
        this(parent);
        this.descr.setIdentifier(bindName.getValue());
    }

    @Override
    public <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, null, value instanceof String);
        return this;
    }

    @Override
    public <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, bindName, value instanceof String);
        return this;
    }

    @Override
    public CustomDataDescrBuilder<P> bindFromData(BindName bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, DATA + "[\"" + value + "\"]", bindName);
        return this;
    }

}
