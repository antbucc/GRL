package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.Action;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

public class ActionFactBuilderImpl implements GameFactBuilder<ActionFactBuilderImpl, Action> {
    private final String id;
    private Action action;

    @Builder
    public ActionFactBuilderImpl(String id) {
        this.id = id;
    }

    @Override
    public Action asOriginalPojo() {
        if (this.action == null) {
            this.action = new Action(this.id);
        }
        return this.action;
    }

    public String getId() {
        return this.asOriginalPojo().getId();
    }

    public void setId(String id) {
        this.asOriginalPojo().setId(id);
    }
}
