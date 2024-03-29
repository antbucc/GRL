package it.univaq.gamification.simulation.builders.impl.fact;


import eu.trentorise.game.model.Reward;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

import java.util.Map;

public class RewardFactBuilderImpl implements GameFactBuilder<RewardFactBuilderImpl, Reward> {
    private final double percentage;
    private final double threshold;
    private Reward reward;

    @Builder
    public RewardFactBuilderImpl(double percentage, double threshold) {
        this.percentage = percentage;
        this.threshold = threshold;
    }

    @Override
    public Reward asOriginalPojo() {
        if (this.reward == null) {
            this.reward = new Reward();
            this.reward.setPercentage(this.percentage);
            this.reward.setThreshold(this.threshold);
        }
        return this.reward;
    }

    public double getPercentage() {
        return this.asOriginalPojo().getPercentage();
    }

    public void setPercentage(double percentage) {
        this.asOriginalPojo().setPercentage(percentage);
    }

    public double getThreshold() {
        return this.asOriginalPojo().getThreshold();
    }

    public void setThreshold(double threshold) {
        this.asOriginalPojo().setThreshold(threshold);
    }

    public Map<String, Double> getBonusScore() {
        return this.asOriginalPojo().getBonusScore();
    }

}
