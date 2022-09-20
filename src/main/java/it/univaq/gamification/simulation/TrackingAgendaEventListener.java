package it.univaq.gamification.simulation;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchEvent;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingAgendaEventListener extends DefaultAgendaEventListener  {

    private final Logger logger = LoggerFactory.getLogger(TrackingAgendaEventListener.class);

    private void logFacts(Boolean isBefore, MatchEvent event) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Match match = event.getMatch();

        stringBuilder.append(String.format("\n    - State %s \"%s\" rule fired:\n", isBefore ? "before" : "after", match.getRule().getName()));

        for (FactHandle fact : match.getFactHandles()) {
            stringBuilder.append(String.format("        - %s\n", fact.toString()));
        }

        logger.info(stringBuilder.toString());
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        this.logFacts(true, event);
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        this.logFacts(false, event);
    }
}
