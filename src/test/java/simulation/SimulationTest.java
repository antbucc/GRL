package simulation;

import dsl.Rules;
import eu.trentorise.game.model.BadgeCollectionConcept;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.Player;
import eu.trentorise.game.model.PointConcept;
import it.univaq.gamification.simulation.builders.impl.SimulationBuilderImpl;
import it.univaq.gamification.simulation.builders.impl.fact.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SimulationTest {

    @Test
    public void TestSimulationWithFactBuilders() {
        PointFactBuilderImpl pointFact = PointFactBuilderImpl.builder().name("total_distance").score(1000.0).build();
        BadgeCollectionFactBuilderImpl badgeCollectionFact = BadgeCollectionFactBuilderImpl.builder().name("silver_collection").build();
        GameFactBuilderImpl gameFact = GameFactBuilderImpl.builder().id("1").build();
        PlayerFactBuilderImpl playerFact = PlayerFactBuilderImpl.builder().id("1").build();

        new SimulationBuilderImpl()
                .addFacts(
                        pointFact,
                        badgeCollectionFact,
                        gameFact,
                        playerFact
                )
                .addRules(
                        Rules.getAddBadgeRule()
                )
                .addExpectations(
                        () -> Assert.assertTrue(badgeCollectionFact.getBadgeEarned().contains("Verona"))
                )
                .simulate();
    }

    @Test
    public void TestSimulationWithObjects() {
        PointConcept pointFact = new PointConcept("total_distance");
        pointFact.setScore(1000.0);

        BadgeCollectionConcept badgeCollectionFact = new BadgeCollectionConcept();
        badgeCollectionFact.setBadgeEarned(new ArrayList<>());
        badgeCollectionFact.setName("silver_collection");

        Game gameFact = new Game();
        gameFact.setId("1");

        Player playerFact = new Player("1");

        new SimulationBuilderImpl()
                .addFacts(
                        pointFact,
                        badgeCollectionFact,
                        gameFact,
                        playerFact
                )
                .addRules(
                        Rules.getAddBadgeRule()
                )
                .addExpectations(
                        () -> Assert.assertTrue(badgeCollectionFact.getBadgeEarned().contains("Verona"))
                )
                .simulate();
    }

}
