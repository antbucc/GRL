package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.Player;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

import java.util.Map;

public class PlayerFactBuilderImpl implements GameFactBuilder<PlayerFactBuilderImpl, Player> {
    private final String id;
    private final boolean team;
    private final int totalMembers;
    private Player player;

    @Builder
    public PlayerFactBuilderImpl(String id, boolean team, int totalMembers) {
        this.id = id;
        this.team = team;
        this.totalMembers = totalMembers;
    }

    @Override
    public Player asOriginalPojo() {
        if (this.player == null) {
            this.player = new Player(this.id, this.team, this.totalMembers);
        }
        return this.player;
    }

    public String getId() {
        return this.asOriginalPojo().getId();
    }

    public boolean isTeam() {
        return this.asOriginalPojo().isTeam();
    }

    public int getTotalMembers() {
        return this.asOriginalPojo().getTotalMembers();
    }

    public Map<String, String> getLevels() {
        return this.asOriginalPojo().getLevels();
    }

}
