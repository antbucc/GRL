package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.core.Clock;
import eu.trentorise.game.model.ChallengeConcept;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ChallengeFactBuilderImpl extends GameFactBuilderImpl<ChallengeConcept> {
    private Map<String, Object> fields;
    private boolean completed;
    private Map<ChallengeConcept.ChallengeState, Date> stateDate;
    private int priority;
    private Date objectCreationDate;
    private Clock clock;

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
        this.objectCreationDate = new Date();
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
}
