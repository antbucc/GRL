package it.univaq.gamification.dsl.builders.impl;

import it.univaq.gamification.dsl.builders.PatternDescrBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
import org.drools.compiler.lang.descr.ExprConstraintDescr;
import org.drools.compiler.lang.descr.PatternDescr;

public class PatternDescrBuilderImpl<P extends DescrBuilder< ?, ? >> extends BaseDescrBuilderImpl<P, PatternDescr>
        implements
        PatternDescrBuilder<P> {

    public PatternDescrBuilderImpl(P parent, String type) {
        super(parent, new PatternDescr(type));
        this.parent = parent;
    }

    public PatternDescrBuilder<P> constraint( String constraint ) {
        ExprConstraintDescr constr = new ExprConstraintDescr(constraint);
        constr.setType(ExprConstraintDescr.Type.NAMED);
        constr.setPosition(descr.getConstraint().getDescrs().size());
        constr.setResource(descr.getResource());
        descr.addConstraint(constr);
        return this;
    }

}
