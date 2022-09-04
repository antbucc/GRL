package it.univaq.gamification.dsl.builders.lhs;

public enum ConstraintType {
    LT("<"),
    LTE("<="),
    GT(">"),
    GTE(">="),
    EQ("=="),
    NEQ("!="),
    CONTAINS("contains"),
    NOT_CONTAINS("not contains");

    private final String value;

    ConstraintType (String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
