package it.univaq.gamification.dsl.utils;

import it.univaq.gamification.dsl.ConstraintType;
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

    public static <T> void addConstraint(PatternDescr descr, ConstraintType constraintType, String field, T value, String bindName, Boolean quoted) {
        String fieldToInterpolate = field;
        String valueToInterpolate;

        if (value == null) {
            valueToInterpolate = "null";
        } else {
            valueToInterpolate = quoted ? "\"" + value + "\n" : value.toString();
        }

        // Checking if a binding is needed
        if (bindName != null) {
            fieldToInterpolate = bindName + ": " + field;
        }

        String constraint = fieldToInterpolate + " " + constraintType.getValue() + " " + valueToInterpolate;
        ConstraintHelper.addConstraint(descr, constraint);
    }

    public static void addBindConstraint(PatternDescr descr, String field, String bindName) {
        BindingDescr bindingConstraint = new BindingDescr(bindName, field, false);
        bindingConstraint.setResource(descr.getResource());
        descr.addConstraint(bindingConstraint);
    }

}
