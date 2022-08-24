package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.core.GameConcept;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class GameConceptFactBuilderImpl<P> implements GameFactBuilder<GameConceptFactBuilderImpl<P>, GameConcept> {
    protected String id;
    protected String name;

    @Override
    public GameConcept asOriginalPojo() {
        return null;
    }

    public String getId() {
        return this.asOriginalPojo().getId();
    }

    public void setId(String id) {
        this.asOriginalPojo().setId(id);
    }

    public String getName() {
        return this.asOriginalPojo().getName();
    }

    public void setName(String name) {
        this.asOriginalPojo().setName(name);
    }
}
