package it.univaq.gamification.simulation.graph;

import org.jgrapht.graph.DefaultEdge;

public class RelationshipEdge extends DefaultEdge {
    private final String label;

    public RelationshipEdge(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}