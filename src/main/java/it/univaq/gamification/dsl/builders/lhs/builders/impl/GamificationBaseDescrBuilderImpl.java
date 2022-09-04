package it.univaq.gamification.dsl.builders.lhs.builders.impl;

import it.univaq.gamification.dsl.builders.lhs.ConstraintHelper;
import it.univaq.gamification.dsl.builders.lhs.builders.GamificationBaseDescrBuilder;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.BaseDescr;
import org.drools.compiler.lang.descr.PatternDescr;

@SuppressWarnings("unchecked")
public class GamificationBaseDescrBuilderImpl<P extends DescrBuilder<?, ? extends BaseDescr>, T>
        extends BaseDescrBuilderImpl<P, PatternDescr>
        implements GamificationBaseDescrBuilder<T> {

    protected GamificationBaseDescrBuilderImpl(P parent, PatternDescr descr) {
        super(parent, descr);
    }

    @Override
    public T bind(Bind bind, String value) {
        ConstraintHelper.addBindConstraint(this.descr, value, bind);
        return (T) this;
    }

    @Override
    public <T1> T declare(Bind bind, T1 value) {
        ConstraintHelper.addDeclareConstraint(this.descr, value, bind);
        return (T) this;
    }

    @Override
    public T constraint(String constraint) {
        ConstraintHelper.addConstraint(this.descr, constraint);
        return (T) this;
    }
}
