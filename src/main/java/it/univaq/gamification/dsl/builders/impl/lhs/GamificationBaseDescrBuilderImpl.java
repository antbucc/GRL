package it.univaq.gamification.dsl.builders.impl.lhs;

import it.univaq.gamification.dsl.utils.BindName;
import it.univaq.gamification.dsl.builders.lhs.GamificationBaseDescrBuilder;
import it.univaq.gamification.dsl.utils.ConstraintHelper;
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
    public T bind(BindName bindName, String value) {
        ConstraintHelper.addBindConstraint(this.descr, value, bindName);
        return (T) this;
    }

    @Override
    public <T1> T declare(BindName bindName, T1 value) {
        ConstraintHelper.addDeclareConstraint(this.descr, value, bindName);
        return (T) this;
    }

    @Override
    public T constraint(String constraint) {
        ConstraintHelper.addConstraint(this.descr, constraint);
        return (T) this;
    }
}
