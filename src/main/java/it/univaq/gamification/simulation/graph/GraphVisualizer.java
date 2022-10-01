package it.univaq.gamification.simulation.graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import eu.trentorise.game.model.*;
import eu.trentorise.game.task.Classification;
import it.univaq.gamification.simulation.Constants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import javax.swing.Action;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphVisualizer  {
    private final Graph<String, RelationshipEdge> graph;
    private final Set<String> selectedElements;
    private final JTextField searchField;
    private final List<AssertionError> expectationErrors;

    public GraphVisualizer(Graph<String, RelationshipEdge> graph, List<AssertionError> expectationErrors) throws HeadlessException {
        this.graph = graph;
        this.expectationErrors = expectationErrors;
        this.selectedElements = new HashSet<>();
        this.searchField = new JTextField();
    }

    private void applyFilters(JGraphXAdapter<String, RelationshipEdge> graphAdapter) {
        graphAdapter.getModel().beginUpdate();
        graphAdapter.getCellToVertexMap().forEach((cell, value) -> {
            String searchText = searchField.getText();
            if (selectedElements.isEmpty() && searchText.length() < 4) {
                cell.setValue(value);
            } else {
                cell.setValue(Arrays.stream(value.split(Constants.FACT_GRAPH_DELIMITER)).filter(elem -> {
                    if (!selectedElements.isEmpty() && searchText.length() >= 4) {
                        return elem.contains(searchField.getText()) && selectedElements.stream().anyMatch(elem::contains);
                    } else if (searchText.length() >= 4) {
                        return elem.contains(searchField.getText());
                    } else {
                        return selectedElements.stream().anyMatch(elem::contains);
                    }
                }).collect(Collectors.joining(Constants.FACT_GRAPH_DELIMITER)));
            }
        });
        graphAdapter.getModel().endUpdate();
        graphAdapter.refresh();
    }

    public void visualize() {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JGraphXAdapter<String, RelationshipEdge> graphAdapter = new JGraphXAdapter<>(graph);
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        graphComponent.setConnectable(false);
        graphComponent.getGraph().setAllowDanglingEdges(false);
        graphComponent.getGraph().setCellsEditable(false);
        graphComponent.getGraph().setEdgeLabelsMovable(false);
        graphComponent.getVerticalScrollBar().setUnitIncrement(16);

        String[] gameElements = {
                Action.class.getSimpleName(),
                BadgeCollectionConcept.class.getSimpleName(),
                ChallengeConcept.class.getSimpleName(),
                Classification.class.getSimpleName(),
                CustomData.class.getSimpleName(),
                Game.class.getSimpleName(),
                InputData.class.getSimpleName(),
                Player.class.getSimpleName(),
                PointConcept.class.getSimpleName(),
                Propagation.class.getSimpleName(),
                Reward.class.getSimpleName()
        };
        JPanel checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new GridLayout(2, 8));

        for (String gameElement : gameElements) {
            JCheckBox checkBox = new JCheckBox(gameElement);
            checkBox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedElements.add(gameElement);
                } else {
                    selectedElements.remove(gameElement);
                }

                this.applyFilters(graphAdapter);
            });
            checkboxesPanel.add(checkBox);
        }

        // Filters container
        JPanel topSection = new JPanel();
        topSection.setLayout(new GridLayout(1, 2));
        topSection.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Checkboxes and label
        JPanel checkboxesContainer = new JPanel();
        checkboxesContainer.setLayout(new GridLayout(2, 1));
        JLabel checkboxLabel = new JLabel("Filtra per elementi di gioco:");
        checkboxLabel.setMinimumSize(new Dimension(200, 200));
        checkboxLabel.setFont(new Font(javax.swing.UIManager.getDefaults().getFont("Label.font").getName(), Font.BOLD, 20));
        checkboxesContainer.add(checkboxLabel);
        checkboxesContainer.add(checkboxesPanel);

        // Input and label
        JPanel searchContainer = new JPanel();
        searchContainer.setLayout(new GridLayout(2, 1));
        JLabel searchLabel = new JLabel("Filtra per valore:");
        searchLabel.setMinimumSize(new Dimension(200, 200));
        searchLabel.setFont(new Font(javax.swing.UIManager.getDefaults().getFont("Label.font").getName(), Font.BOLD, 20));
        searchContainer.add(searchLabel);
        searchField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
            this.applyFilters(graphAdapter);
        });
        searchContainer.add(searchField);

        topSection.add(checkboxesContainer);
        topSection.add(searchContainer);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(graphComponent);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(.8d);

        // Add errors if present
        if (expectationErrors.size() > 0) {
            DefaultListModel<String> errors = new DefaultListModel<>();
            expectationErrors.forEach(e -> errors.addElement(String.format("Asserzione fallita: %s", e.getMessage())));
            JList<String> errorsList = new JList<>(errors);
            errorsList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            errorsList.setFont(new Font(javax.swing.UIManager.getDefaults().getFont("Label.font").getName(), Font.BOLD, 14));
            errorsList.setBackground(new Color(235, 216, 208));
            errorsList.setForeground(Color.RED);
            errorsList.setSelectionForeground(Color.RED);
            splitPane.add(errorsList);
        }

        frame.add(topSection, BorderLayout.NORTH);
        frame.add(splitPane, BorderLayout.CENTER);
        frame.setVisible(true);

        Object lock = new Object();
        // The main thread will wait for the frame to close before moving on
        Thread t = new Thread(() -> {
            synchronized(lock) {
                while (frame.isVisible())
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
        t.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                synchronized (lock) {
                    frame.setVisible(false);
                    lock.notify();
                }
            }
        });

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
