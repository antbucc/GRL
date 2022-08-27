package it.univaq.gamification.dsl;

public class BindName {

    private final String value;

    public BindName(String value) {
        this.value = value.startsWith("$") ? value : "$" + value;
    }

    public String getValue() {
        return this.value;
    }

}
