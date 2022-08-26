package it.univaq.gamification.dsl.builders.impl.lhs;

import eu.trentorise.game.model.InputData;
import it.univaq.gamification.dsl.utils.BindName;
import it.univaq.gamification.dsl.utils.ConstraintType;
import it.univaq.gamification.dsl.builders.lhs.InputDataDescrBuilder;
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

    protected InputDataDescrBuilderImpl(P parent, BindName bindName) {
        this(parent);
        this.descr.setIdentifier(bindName.getValue());
    }

    @Override
    public <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, null, value instanceof String);
        return this;
    }

    @Override
    public <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, BindName bindName) {
        ConstraintHelper.addConstraint(this.descr, constraintType, DATA + "[\"" +key + "\"]", value, bindName, value instanceof String);
        return this;
    }

    @Override
    public InputDataDescrBuilder<P> bindFromData(BindName bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, DATA + "[\"" + value + "\"]", bindName);
        return this;
    }

}
