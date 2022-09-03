package it.univaq.gamification.dsl;

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

    public static <T> void addConstraint(PatternDescr descr, ConstraintType constraintType, String field, T value, BindName bindName, Boolean quoted) {
        String fieldToInterpolate = field;
        String valueToInterpolate;

        if (value == null) {
            valueToInterpolate = "null";
        } else {
            valueToInterpolate = quoted ? "\"" + value + "\"" : value.toString();
        }

        // Checking if a binding is needed
        if (bindName != null) {
            fieldToInterpolate = bindName.getValue() + ": " + field;
        }

        String constraint = fieldToInterpolate + " " + constraintType.getValue() + " " + valueToInterpolate;
        ConstraintHelper.addConstraint(descr, constraint);
    }

    public static void addBindConstraint(PatternDescr descr, String field, BindName bindName) {
        BindingDescr bindingConstraint = new BindingDescr(bindName.getValue(), field, false);
        bindingConstraint.setResource(descr.getResource());
        descr.addConstraint(bindingConstraint);
    }

    public static <T> void addDeclareConstraint(PatternDescr descr, T field, BindName bindName) {
        ConstraintHelper.addConstraint(descr, bindName + ": " + (field instanceof String ? "\"" + field + "\"" : field));
    }

}
