package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.ValueHelper;
import org.drools.compiler.lang.descr.BindingDescr;
import org.drools.compiler.lang.descr.ExprConstraintDescr;
import org.drools.compiler.lang.descr.PatternDescr;

public class ConstraintHelper {

    public static void addConstraint(PatternDescr descr, String constraint) {
        ExprConstraintDescr exprConstraint = new ExprConstraintDescr(constraint);
        exprConstraint.setType(ExprConstraintDescr.Type.NAMED);
        exprConstraint.setPosition(descr.getConstraint().getDescrs().size());
        exprConstraint.setResource(descr.getResource());
        descr.addConstraint(exprConstraint);
    }

    public static <T> void addConstraint(PatternDescr descr, ConstraintType constraintType, String field, T value, Bind bind) {
        String fieldToInterpolate = field;
        String valueToInterpolate;

        if (value == null) {
            valueToInterpolate = "null";
        } else {
            valueToInterpolate = ValueHelper.processValue(value);
        }

        // Checking if a binding is needed
        if (bind != null) {
            fieldToInterpolate = bind.getValue() + ": " + field;
        }

        String constraint = fieldToInterpolate + " " + constraintType.getValue() + " " + valueToInterpolate;
        ConstraintHelper.addConstraint(descr, constraint);
    }

    public static void addBindConstraint(PatternDescr descr, String field, Bind bind) {
        BindingDescr bindingConstraint = new BindingDescr(bind.getValue(), field, false);
        bindingConstraint.setResource(descr.getResource());
        descr.addConstraint(bindingConstraint);
    }

    public static <T> void addDeclareConstraint(PatternDescr descr, T field, Bind bind) {
        ConstraintHelper.addConstraint(descr, bind + ": " + ValueHelper.processValue(field));
    }

}
