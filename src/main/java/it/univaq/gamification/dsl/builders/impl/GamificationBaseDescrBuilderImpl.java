package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.GamificationBaseDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.BaseDescr;
import org.drools.compiler.lang.descr.PatternDescr;

public class GamificationBaseDescrBuilderImpl<P extends DescrBuilder<?, ? extends BaseDescr>, T>
        extends BaseDescrBuilderImpl<P, PatternDescr>
        implements GamificationBaseDescrBuilder<T> {

    protected GamificationBaseDescrBuilderImpl(P parent, PatternDescr descr) {
        super(parent, descr);
    }

    @Override
    public T bind(String bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, value, bindName);
        return (T) this;
    }

    @Override
    public <T1> T declare(String bindName, T1 value) {
        ConstraintHelper.addDeclareConstraint(this.descr, value, bindName);
        return (T) this;
    }

    @Override
    public T constraint(String constraint) {
        ConstraintHelper.addConstraint(this.descr, constraint);
        return (T) this;
    }
}
