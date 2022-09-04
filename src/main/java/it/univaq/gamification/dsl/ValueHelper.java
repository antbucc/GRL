package it.univaq.gamification.dsl;

public class ValueHelper {

    public static <T> String processValue(T value) {
        return value instanceof String ? String.format("\"%s\"", value) : value.toString();
    }

}
