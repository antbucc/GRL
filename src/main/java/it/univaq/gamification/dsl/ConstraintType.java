package it.univaq.gamification.dsl;

import org.drools.model.Index;

public enum ConstraintType {
    LT(Index.ConstraintType.LESS_THAN),
    LTE(Index.ConstraintType.LESS_OR_EQUAL),
    GT(Index.ConstraintType.GREATER_THAN),
    GTE(Index.ConstraintType.GREATER_OR_EQUAL),
    EQ(Index.ConstraintType.EQUAL),
    NEQ(Index.ConstraintType.NOT_EQUAL);
    // CONTAINS,
    // NOT_CONTAINS;

    private final Index.ConstraintType constraintType;

    ConstraintType(Index.ConstraintType constraintType) {
        this.constraintType = constraintType;
    }

    public Index.ConstraintType getConstraintType() {
        return this.constraintType;
    }
}
