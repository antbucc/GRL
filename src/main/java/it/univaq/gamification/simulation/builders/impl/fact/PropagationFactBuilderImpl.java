package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.Propagation;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

public class PropagationFactBuilderImpl implements GameFactBuilder<PropagationFactBuilderImpl, Propagation> {
    private final String action;
    @Builder.Default
    private int level = 1;
    private Propagation propagation;

    @Builder
    public PropagationFactBuilderImpl(String action, int level) {
        this.action = action;
        this.level = level;
    }

    @Override
    public Propagation asOriginalPojo() {
        if (this.propagation == null) {
            this.propagation = new Propagation(this.action, this.level);
        }
        return this.propagation;
    }

    public String getAction() {
        return this.asOriginalPojo().getAction();
    }

    public void setAction(String action) {
        this.asOriginalPojo().setAction(action);
    }

    public int getLevel() {
        return this.asOriginalPojo().getLevel();
    }

    public void setLevel(int level) {
        this.asOriginalPojo().setLevel(level);
    }
}
