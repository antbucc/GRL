package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.Game;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

public class GameFactBuilderImpl implements GameFactBuilder<ActionFactBuilderImpl, Game> {
    private final String id;
    private Game game;

    @Builder
    public GameFactBuilderImpl(String id) {
        this.id = id;
    }

    @Override
    public Game asOriginalPojo() {
        if (this.game == null) {
            this.game = new Game();
            this.game.setId(this.id);
        }
        return this.game;
    }

    public String getId() {
        return this.asOriginalPojo().getId();
    }

    public void setId(String id) {
        this.asOriginalPojo().setId(id);
    }

}
