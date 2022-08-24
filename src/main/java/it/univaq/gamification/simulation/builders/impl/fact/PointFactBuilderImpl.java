package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.PointConcept;
import lombok.Builder;

public class PointFactBuilderImpl extends GameConceptFactBuilderImpl<PointConcept> {
    private final Double score;
    private PointConcept pointConcept;

    @Builder
    public PointFactBuilderImpl(String id, String name, Double score) {
        super(id, name);
        this.score = score;
    }

    @Override
    public PointConcept asOriginalPojo() {
        if (this.pointConcept == null) {
            this.pointConcept = new PointConcept(this.name);
            this.pointConcept.setId(this.id);
            this.pointConcept.setScore(this.score);
        }
        return this.pointConcept;
    }

    public Double getScore() {
        return this.asOriginalPojo().getScore();
    }

    public void setScore(Double score) {
        this.asOriginalPojo().setScore(score);
    }
}
