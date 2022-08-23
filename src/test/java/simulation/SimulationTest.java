package simulation;

import dsl.Rules;
import it.univaq.gamification.simulation.builders.impl.SimulationBuilderImpl;
import it.univaq.gamification.simulation.builders.impl.fact.BadgeCollectionFactBuilderImpl;
import it.univaq.gamification.simulation.builders.impl.fact.GameFactBuilderImpl;
import it.univaq.gamification.simulation.builders.impl.fact.PlayerFactBuilderImpl;
import it.univaq.gamification.simulation.builders.impl.fact.PointFactBuilderImpl;
import org.junit.Assert;
import org.junit.Test;

public class SimulationTest {

    @Test
    public void TestSimulation() {
        PointFactBuilderImpl pointFact = PointFactBuilderImpl.builder().name("total_distance").score(1000.0).build();
        BadgeCollectionFactBuilderImpl badgeCollectionFact = BadgeCollectionFactBuilderImpl.builder().name("silver_collection").build();
        GameFactBuilderImpl<?> gameFact = GameFactBuilderImpl.gameBuilder().id("1").build();
        PlayerFactBuilderImpl playerFact = PlayerFactBuilderImpl.builder().id("1").team(true).build();

        new SimulationBuilderImpl()
                .addFacts(
                        pointFact,
                        badgeCollectionFact,
                        gameFact,
                        playerFact
                )
                .addRules(
                        Rules.getBadgeSimplifiedRule()
                )
                .addExpectations(
                        () -> Assert.assertTrue(badgeCollectionFact.asOriginalPojo().getBadgeEarned().contains("Verona"))
                )
                .simulate();
    }

}
