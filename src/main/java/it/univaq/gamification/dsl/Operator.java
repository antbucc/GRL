package it.univaq.gamification.dsl;

public enum Operator {

    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVISION("/"),
    MODULO("%");

    private final String value;

    Operator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
