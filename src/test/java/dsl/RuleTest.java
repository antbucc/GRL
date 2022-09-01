package dsl;

import eu.trentorise.game.notification.BadgeNotification;
import eu.trentorise.game.task.Classification;
import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.PackageDescr;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import it.univaq.gamification.utils.DrlDumper;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import static it.univaq.gamification.dsl.ConstraintType.EQ;

public class RuleTest {

    @Test
    public void testAddBadge() {
        String expectedResult = "packagepackage_nameimporteu.trentorise.game.notification.BadgeNotificationglobalDoubleVerona_distanceglobalStringconst_school_namerule\"R-add-badgeVerona\"salience-1000whenPointConcept(name==\"total_distance\",score>=Verona_distance)$bc:BadgeCollectionConcept(name==\"silver_collection\",badgeEarnednotcontains\"Verona\")Game($gameId:id)Player($teamId:id,$teamId==const_school_name,team==true)then$bc.getBadgeEarned().add(\"Verona\");insert(newBadgeNotification($gameId,$teamId,$bc.getName(),\"Verona\"));update($bc);end";
        String drl = new DrlDumper().dump(Rules.getAddBadgeRuleWithNotification());
        System.out.println(drl);
        Assert.assertEquals(StringUtils.deleteWhitespace(drl), StringUtils.deleteWhitespace(expectedResult));
    }

    // Play and Go Ferrara tests

    @Test
    public void finalClassificationBadge() {
        final BindName CLASSIFICATION_REF = new BindName("c");
        final BindName GAME_ID_REF = new BindName("gameId");
        final BindName PLAYER_ID_REF = new BindName("playerId");
        final BindName BADGE_COLLECTION_REF = new BindName("bc");

        PackageDescr pkg = new PackageDescrBuilderImpl()
                .name("package eu.trentorise.game.model")
                .newImport(BadgeNotification.class.getName())
                .newImport(Classification.class.getName())
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
                    .end()
                    .then()
                        .addBadgeWithNotification(BADGE_COLLECTION_REF, GAME_ID_REF, PLAYER_ID_REF, "gold-medal-green-1")
                    .end()
                .end()
                .getDescr();

        System.out.println(new DrlDumper().dump(pkg));
    }

}
