package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.core.Clock;
import eu.trentorise.game.model.ChallengeConcept;
import lombok.Builder;

import java.util.*;

public class ChallengeFactBuilderImpl extends GameConceptFactBuilderImpl<ChallengeConcept> {
    private final Map<String, Object> fields;
    private final boolean completed;
    private final Map<ChallengeConcept.ChallengeState, Date> stateDate;
    private final int priority;
    private final Clock clock;
    private ChallengeConcept challengeConcept;

    @Builder
    public ChallengeFactBuilderImpl(
            String id,
            String name,
            Map<String, Object> fields,
            boolean completed,
            Map<ChallengeConcept.ChallengeState, Date> stateDate,
            int priority,
            Clock clock) {
        super(id, name);
        this.fields = fields != null ? fields : new HashMap<>();
        this.completed = completed;
        this.stateDate = stateDate != null ? stateDate : new HashMap<>();
        this.priority = priority;
        this.clock = clock;
    }

    @Override
    public ChallengeConcept asOriginalPojo() {
        if (this.challengeConcept == null) {
            this.challengeConcept = new ChallengeConcept();
            this.challengeConcept.setId(this.id);
            this.challengeConcept.setName(this.name);
            this.challengeConcept.setFields(this.fields);
            if (this.completed) {
                this.challengeConcept.completed();
            }
            this.challengeConcept.setStateDate(this.stateDate);
            this.challengeConcept.setPriority(this.priority);
            this.challengeConcept.setClock(this.clock);
        }
        return this.challengeConcept;
    }

    public Map<String, Object> getFields() {
        return this.asOriginalPojo().getFields();
    }

    public void setFields(Map<String, Object> fields) {
        this.asOriginalPojo().setFields(fields);
    }

    public boolean isCompleted() {
        return this.asOriginalPojo().isCompleted();
    }

    public Map<ChallengeConcept.ChallengeState, Date> getStateDate() {
        return this.asOriginalPojo().getStateDate();
    }

    public void setStateDate(Map<ChallengeConcept.ChallengeState, Date> stateDate) {
        this.asOriginalPojo().setStateDate(stateDate);
    }

    public int getPriority() {
        return this.asOriginalPojo().getPriority();
    }

    public void setPriority(int priority) {
        this.asOriginalPojo().setPriority(priority);
    }

    public Clock getClock() {
        return this.asOriginalPojo().getClock();
    }

    public void setClock(Clock clock) {
        this.asOriginalPojo().setClock(clock);
    }
}
