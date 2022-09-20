package dsl;

import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.notification.BadgeNotification;
import eu.trentorise.game.task.Classification;
import it.univaq.gamification.dsl.binders.BadgeCollectionBind;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.binders.ClassificationBind;
import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import it.univaq.gamification.utils.DrlDumper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import static it.univaq.gamification.dsl.builders.lhs.ConstraintType.EQ;

public class RuleTest {

    @Test
    public void testAddBadge() {
        String expectedResult = "package eu.trentorise.game.model import eu.trentorise.game.model.PointConceptimport eu.trentorise.game.model.BadgeCollectionConceptimport eu.trentorise.game.model.Gameimport eu.trentorise.game.model.Playerimport eu.trentorise.game.notification.BadgeNotificationglobal Double Verona_distanceglobal String const_school_namerule \"R-add-badge Verona\"    salience -1000when    PointConcept( name == \"total_distance\", score >= Verona_distance )      $bc : BadgeCollectionConcept( name == \"silver_collection\", badgeEarned not contains \"Verona\" )      Game( $gameId : id )      Player( $teamId : id, $teamId==const_school_name, team == true )  then    $bc.getBadgeEarned().add(\"Verona\");            insert(new BadgeNotification($gameId, $teamId, \"Verona\"));    update( $bc );end";
        String drl = new DrlDumper().dump(Rules.getAddBadgeRuleWithNotification());
        System.out.println(drl);
        Assert.assertEquals(StringUtils.deleteWhitespace(expectedResult), StringUtils.deleteWhitespace(drl));
    }

    // Play and Go Ferrara tests

    @Test
    public void finalClassificationBadge() {
        final ClassificationBind CLASSIFICATION_REF = new ClassificationBind("c");
        final Bind GAME_ID_REF = new Bind("gameId");
        final Bind PLAYER_ID_REF = new Bind("playerId");
        final BadgeCollectionBind BADGE_COLLECTION_REF = new BadgeCollectionBind("bc");

        PackageDescr pkg = new PackageDescrBuilderImpl()
                .name("eu.trentorise.game.model")
                .newImport(BadgeNotification.class.getName()).end()
                .newImport(Classification.class.getName()).end()
                .newRule()
                    .name("final classification gold badge green d1")
                        .attribute("salience", "-1000")
                    .when()
                        .classification(CLASSIFICATION_REF)
                            .name(EQ, "final classification green")
                            .position(EQ,1)
                            .classificationRunNumber(EQ, 1)
                        .end()
                        .game().bindId(GAME_ID_REF).end()
                        .player().bindId(PLAYER_ID_REF).end()
                        .badgeCollection(BADGE_COLLECTION_REF)
                            .name(EQ, "green leaves")
                            .badgeEarnedNotContains("gold-medal-green-1")
                        .end()
                        .not()
                            .pattern("PRItinerary").end()
                        .end()
                    .end()
                    .then()
                        .addBadgeWithNotification(BADGE_COLLECTION_REF, GAME_ID_REF, PLAYER_ID_REF, "gold-medal-green-1")
                        .insert(PointConcept.class)
                        .insert(PointConcept.class, BADGE_COLLECTION_REF, 20, "string")
                    .end()
                .end()
                .getDescr();

        System.out.println(new DrlDumper().dump(pkg));
    }

}
