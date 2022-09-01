package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.task.Classification;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

public class ClassificationFactBuilderImpl implements GameFactBuilder<ClassificationFactBuilderImpl, Classification> {
    private final String name;
    private final int position;
    private final double score;
    private final String scoreType;
    private final int classificationRunNumber;
    private Classification classification;

    @Builder
    public ClassificationFactBuilderImpl(String name, int position, double score, String scoreType, int classificationRunNumber) {
        super();
        this.name = name;
        this.position = position;
        this.score = score;
        this.scoreType = scoreType;
        // TODO: Default -1 (?)
        this.classificationRunNumber = classificationRunNumber;
    }

    @Override
    public Classification asOriginalPojo() {
        if (this.classification == null) {
            this.classification = new Classification();
            this.classification.setName(this.name);
            this.classification.setPosition(this.position);
            this.classification.setScore(this.score);
            this.classification.setScoreType(this.scoreType);
            this.classification.setClassificationRunNumber(this.classificationRunNumber);
        }
        return this.classification;
    }

    public String getName() {
        return this.asOriginalPojo().getName();
    }

    public int getPosition() {
        return this.asOriginalPojo().getPosition();
    }

    public double getScore() {
        return this.asOriginalPojo().getScore();
    }

    public String getScoreType() {
        return this.asOriginalPojo().getScoreType();
    }

    public int getClassificationRunNumber() {
        return this.asOriginalPojo().getClassificationRunNumber();
    }

    public void setName(String name) {
        this.asOriginalPojo().setName(name);
    }

    public void setPosition(int position) {
        this.asOriginalPojo().setPosition(position);
    }

    public void setScore(double score) {
        this.asOriginalPojo().setScore(score);
    }

    public void setScoreType(String scoreType) {
        this.asOriginalPojo().setScoreType(scoreType);
    }

    public void setClassificationRunNumber(int classificationRunNumber) {
        this.asOriginalPojo().setClassificationRunNumber(classificationRunNumber);
    }

}
