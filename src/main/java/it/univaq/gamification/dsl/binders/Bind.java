package it.univaq.gamification.dsl.binders;

public class Bind {

    private final String value;

    public Bind(String value) {
        this.value = value.startsWith("$") ? value : "$" + value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
