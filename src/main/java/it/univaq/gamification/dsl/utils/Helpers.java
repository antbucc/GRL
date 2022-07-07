package it.univaq.gamification.dsl.utils;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.model.view.ViewItemBuilder;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class Helpers {

    public static String getExprFromConstraint(Class<?> className, ConstraintType constraintType, String value) {
        return className.getName() + ":" + constraintType.name() + ":" + value;
    }

    public static <T extends ViewItemBuilder<?>> T getFirstExpressions(ArrayList<T> expressions) {
        Optional<T> optionalFirstExpression = expressions.stream().findFirst();

        // Handle exception
        return optionalFirstExpression.orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ViewItemBuilder<?>> T[] getExtraExpressions(ArrayList<T> expressions) {
        T[] extraExpressions = (T[]) new ViewItemBuilder[expressions.size() - 1];
        IntStream.range(1, expressions.size()).forEach(index -> extraExpressions[index - 1] = expressions.get(index));

        return extraExpressions;
    }

}
