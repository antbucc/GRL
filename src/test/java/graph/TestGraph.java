package graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import eu.trentorise.game.model.*;
import eu.trentorise.game.model.Action;
import eu.trentorise.game.task.Classification;
import it.univaq.gamification.simulation.graph.RelationshipEdge;
import it.univaq.gamification.simulation.graph.SimpleDocumentListener;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestGraph {

    private final Set<String> selectedElements;
    private final JTextField searchField;

    public TestGraph() {
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
                cell.setValue(Arrays.stream(value.split("---")).filter(elem -> {
                    if (!selectedElements.isEmpty() && searchText.length() >= 4) {
                        return elem.contains(searchField.getText()) && selectedElements.stream().anyMatch(elem::contains);
                    } else if (searchText.length() >= 4) {
                        return elem.contains(searchField.getText());
                    } else {
                        return selectedElements.stream().anyMatch(elem::contains);
                    }
                }).collect(Collectors.joining("---")));
            }
        });
        graphAdapter.getModel().endUpdate();
        graphAdapter.refresh();
    }

    public void init() {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Graph<String, RelationshipEdge> graph = new DefaultDirectedGraph<>(RelationshipEdge.class);
        graph.addVertex("1 - PointConcept{name=''} --- BadgeCollectionConcept{earnedBadges=[], name='bronze_collection'}");
        graph.addVertex("2 - PointConcept{name=''} --- BadgeCollectionConcept{earnedBadges=[], name='silver_collection'}");
        graph.addVertex("3 - PointConcept{name=''} --- BadgeCollectionConcept{earnedBadges=[], name='gold_collection'}");
        graph.addEdge("1 - PointConcept{name=''} --- BadgeCollectionConcept{earnedBadges=[], name='bronze_collection'}", "2 - PointConcept{name=''} --- BadgeCollectionConcept{earnedBadges=[], name='silver_collection'}", new RelationshipEdge("Edge1"));
        graph.addEdge("2 - PointConcept{name=''} --- BadgeCollectionConcept{earnedBadges=[], name='silver_collection'}", "3 - PointConcept{name=''} --- BadgeCollectionConcept{earnedBadges=[], name='gold_collection'}", new RelationshipEdge("Edge2"));

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
        searchField.getDocument().addDocumentListener((SimpleDocumentListener) e -> this.applyFilters(graphAdapter));
        searchContainer.add(searchField);

        // BOTTOM SECTION

        DefaultListModel<String> errors = new DefaultListModel<>();
        errors.addElement("Ciao");
        errors.addElement("Ciao");
        errors.addElement("Ciao");
        errors.addElement("Ciao");
        JList<String> errorsList = new JList<>(errors);
        errorsList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        errorsList.setFont(new Font(javax.swing.UIManager.getDefaults().getFont("Label.font").getName(), Font.BOLD, 14));
        errorsList.setBackground(new Color(235, 216, 208));
        errorsList.setForeground(Color.RED);
        errorsList.setSelectionForeground(Color.RED);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(graphComponent);
        splitPane.setBottomComponent(errorsList);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(.8d);

        topSection.add(checkboxesContainer);
        topSection.add(searchContainer);

        frame.add(topSection, BorderLayout.NORTH);
        frame.add(splitPane, BorderLayout.CENTER);
        // frame.add(bottomSection, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
