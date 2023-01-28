package it.univaq.gamification.dsl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Global {

    private final Class<?> type;
    private final String identifier;

    @Override
    public String toString() {
        return this.identifier;
    }

}
