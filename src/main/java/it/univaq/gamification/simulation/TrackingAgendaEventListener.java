package it.univaq.gamification.simulation;

import it.univaq.gamification.simulation.graph.RelationshipEdge;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.drools.core.event.DefaultAgendaEventListener;
import org.drools.core.spi.Salience;
import org.jgrapht.Graph;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchEvent;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrackingAgendaEventListener extends DefaultAgendaEventListener  {

    private Integer ruleCounter = 0;
    // private final CSVPrinter csvPrinter;
    private final Logger logger = LoggerFactory.getLogger(TrackingAgendaEventListener.class);
    private final Graph<String, RelationshipEdge> stateGraph;
    private String before;
    private String after;
    private final Map<String, Boolean> rulesEnabledMapping;

    public TrackingAgendaEventListener(
            // CSVPrinter csvPrinter,
            Graph<String, RelationshipEdge> stateGraph, Map<String, Boolean> rulesEnabledMapping) {
        // this.csvPrinter = csvPrinter;
        this.stateGraph = stateGraph;
        this.rulesEnabledMapping = rulesEnabledMapping;
    }

    private void logFacts(Boolean isBefore, MatchEvent event) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Match match = event.getMatch();

        stringBuilder.append(String.format("\n    - State %s \"%s\" rule fired:\n", isBefore ? "before" : "after", match.getRule().getName()));

        for (FactHandle fact : match.getFactHandles()) {
            stringBuilder.append(String.format("        - %s\n", fact.toString()));
        }

        logger.info(stringBuilder.toString());
    }

    private String findMatchingPattern(String fact) {
        Pattern pattern = Pattern.compile("[A-Za-z]*\\*\\*\\*(.*?)\\*\\*\\*");
        Matcher patternMatch = pattern.matcher(fact);

        if (patternMatch.find()) {
            String matchingPattern = patternMatch.group(0).replaceAll("\\*\\*\\*", "");
            int firstBraceIndex = matchingPattern.indexOf("{");
            if (firstBraceIndex != -1) {
                String className = matchingPattern.substring(0, firstBraceIndex);
                String properties = matchingPattern.substring(firstBraceIndex);
                return "<strong style=\"color: blue\">" + className + "</strong>" + properties;
            }
        }

        return "";
    }

    private String buildFactString(MatchEvent event) {
        String result = "";
        List<? extends FactHandle> facts = event.getMatch().getFactHandles();
        for (int i = 0; i < facts.size(); i++) {
            result = result.concat((this.findMatchingPattern(facts.get(i).toString())).concat(i == facts.size() - 1 ? "" : " " + Constants.FACT_STRING_DELIMITER + " " ));
        }

        return result;
    }

    private String formatVertexLabel(String state) {
        return state.replaceAll(Constants.FACT_STRING_DELIMITER, Constants.FACT_GRAPH_DELIMITER);
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        this.logFacts(true, event);
        // Storing the value of the state inside `before` to use it later
        before = formatVertexLabel(this.buildFactString(event));

        // Add the node to the graph
        this.stateGraph.addVertex(before);

        // README: Remove the following if you want to have a forest instead of a path, make this available from DSL
        if (after != null && !after.equals(before)) {
            this.stateGraph.addEdge(after, before, new RelationshipEdge("Leads to"));
        }

    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        this.logFacts(false, event);


        after = formatVertexLabel(this.buildFactString(event));
        RuleImpl rule = (RuleImpl) event.getMatch().getRule();

        String ruleName = rule.getName();
        Salience ruleSalience = rule.getSalience();
        String ruleTimestamp = String.valueOf(Timestamp.from(Instant.now()));

        this.stateGraph.addVertex(after);
        this.stateGraph.addEdge(before, after, new RelationshipEdge(String.format(
                "#%s %s salience:%s  timestamp:%s",
                ruleCounter,
                ruleName,
                ruleSalience,
                ruleTimestamp))
        );

        // Increase rule counter value
        ruleCounter += 1;

        // Disable fired rule
        this.rulesEnabledMapping.put(ruleName, false);

        // try {
        //     csvPrinter.printRecord(ruleName, ruleSalience, ruleTimestamp, before, after);
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }
    }
}
