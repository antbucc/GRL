package it.univaq.gamification.simulation.builders.impl.fact;


import eu.trentorise.game.model.BadgeCollectionConcept;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BadgeCollectionFactBuilderImpl extends GameFactBuilderImpl<BadgeCollectionConcept> {
    private List<String> badgeEarned;
    private BadgeCollectionConcept badgeCollectionConcept;

    @Builder
    public BadgeCollectionFactBuilderImpl(String id, String name, List<String> badgeEarned) {
        super(id, name);
        this.badgeEarned = badgeEarned != null ? badgeEarned : new ArrayList<>();
    }

    @Override
    public BadgeCollectionConcept asOriginalPojo() {
        if (this.badgeCollectionConcept == null) {
            this.badgeCollectionConcept = new BadgeCollectionConcept();
            this.badgeCollectionConcept.setId(this.id);
            this.badgeCollectionConcept.setName(this.name);
            this.badgeCollectionConcept.setBadgeEarned(this.badgeEarned);
        }
        return this.badgeCollectionConcept;
    }
}
