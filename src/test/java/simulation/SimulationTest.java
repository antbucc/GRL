package simulation;

import dsl.Rules;
import eu.trentorise.game.model.BadgeCollectionConcept;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.Player;
import eu.trentorise.game.model.PointConcept;
import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import it.univaq.gamification.simulation.builders.impl.SimulationBuilderImpl;
import it.univaq.gamification.simulation.builders.impl.fact.*;
import it.univaq.gamification.simulation.builders.CheckExpectationLambda;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SimulationTest {

    @Test
    public void testSimulationWithFactBuilders() {
        // package eu.trentorise.game.model
        //
        // import eu.trentorise.game.model.PointConcept
        // import eu.trentorise.game.model.BadgeCollectionConcept
        // import eu.trentorise.game.model.Game
        // import eu.trentorise.game.model.Player
        // import eu.trentorise.game.notification.BadgeNotification
        //
        // rule "R-add-badge Verona"
        // when
        //     PointConcept( name == "total_distance", score >= 10.0 )
        //     $bc : BadgeCollectionConcept( name == "silver_collection", badgeEarned not contains "Verona" )
        //     Game( $gameId : id )
        //     Player( team == false )
        // then
        //     $bc.getBadgeEarned().add("Verona");
        //     update( $bc );
        // end

        // Facts
        PointFactBuilderImpl pointFact = PointFactBuilderImpl.builder().name("total_distance").score(1000.0).build();
        BadgeCollectionFactBuilderImpl badgeCollectionFact = BadgeCollectionFactBuilderImpl.builder().name("silver_collection").build();
        GameFactBuilderImpl gameFact = GameFactBuilderImpl.builder().id("1").build();
        PlayerFactBuilderImpl playerFact = PlayerFactBuilderImpl.builder().id("1").build();
        // Rules
        PackageDescr addBadgeRule1 = Rules.getAddBadgeRule1();
        PackageDescr addBadgeRule2 = Rules.getAddBadgeRule2();
        // Expectations
        CheckExpectationLambda doesNotHaveVeronaBadge = () -> Assert.assertFalse("Non contiene il badge 'Verona 1'", badgeCollectionFact.getBadgeEarned().contains("Verona 1"));
        CheckExpectationLambda hasVeronaBadge1 = () -> Assert.assertTrue("Contiene il badge 'Verona 1'", badgeCollectionFact.getBadgeEarned().contains("Verona 1"));
        CheckExpectationLambda hasVeronaBadge2 = () -> Assert.assertTrue("Contiene il badge 'Verona 2'", badgeCollectionFact.getBadgeEarned().contains("Verona 2"));

        new SimulationBuilderImpl()
                .addFacts(pointFact, badgeCollectionFact, gameFact)
                .addRules(addBadgeRule1, addBadgeRule2)
                .addExpectations(doesNotHaveVeronaBadge)
                .simulate()
                .addFacts(playerFact)
                .addExpectations(hasVeronaBadge1, hasVeronaBadge2)
                .simulateAndClose();
    }

    @Test
    public void testSimulationWithObjects() {
        // Facts
        PointConcept pointFact = new PointConcept("total_distance");
        pointFact.setScore(1000.0);
        BadgeCollectionConcept badgeCollectionFact = new BadgeCollectionConcept();
        badgeCollectionFact.setBadgeEarned(new ArrayList<>());
        badgeCollectionFact.setName("silver_collection");
        Game gameFact = new Game();
        gameFact.setId("1");
        Player playerFact = new Player("1");
        // Rules
        PackageDescr addBadgeRule = Rules.getAddBadgeRule1();
        // Expectations
        CheckExpectationLambda doesNotHaveVeronaBadge = () -> Assert.assertFalse(badgeCollectionFact.getBadgeEarned().contains("Verona"));
        CheckExpectationLambda hasVeronaBadge = () -> Assert.assertTrue(badgeCollectionFact.getBadgeEarned().contains("Verona"));

        new SimulationBuilderImpl()
                .addFacts(pointFact, badgeCollectionFact, gameFact)
                .addRules(addBadgeRule)
                .addExpectations(doesNotHaveVeronaBadge)
                .simulate()
                .addFacts(playerFact)
                .addExpectations(hasVeronaBadge)
                .simulateAndClose();
    }

}
