import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import org.apache.commons.lang.StringUtils;
import org.drools.compiler.lang.descr.PackageDescr;
import org.junit.Assert;
import org.junit.Test;

import static it.univaq.gamification.dsl.utils.ConstraintType.EQ;
import static it.univaq.gamification.dsl.utils.ConstraintType.GTE;

public class RuleTest {

    PackageDescr pkg;

    @Test
    public void testAddBadge() {

        final String BADGE_NAME = "Verona";
        final String BADGE_COLLECTION_REF = "$bc";
        final String TEAM_ID_REF = "$teamId";
        final String GAME_ID_REF = "$gameId";
        final String GLOBAL_VERONA_DISTANCE = "Verona_distance";
        final String GLOBAL_SCHOOL_NAME = "const_school_name";

        pkg = new PackageDescrBuilderImpl()
                .name("package_name")
                .newImport().target("eu.trentorise.game.notification.BadgeNotification").end()
                .newGlobal().type(Double.class.getSimpleName()).identifier(GLOBAL_VERONA_DISTANCE).end()
                .newGlobal().type(String.class.getSimpleName()).identifier(GLOBAL_SCHOOL_NAME).end()
                .newRule()
                    .name("R-add-badge Verona")
                    .attribute("salience", "-1000")
                    .when()
                        .point().name(EQ, "total_distance").score(GTE, GLOBAL_VERONA_DISTANCE).end()
                        .badgeCollection(BADGE_COLLECTION_REF).name(EQ, "silver_collection").badgeEarnedNotContains(BADGE_NAME).end()
                        .game().bindId(GAME_ID_REF).end()
                        .player().bindId(TEAM_ID_REF).constraint(TEAM_ID_REF + EQ.getValue() + GLOBAL_SCHOOL_NAME).team(true).end()
                    .end()
                    .then()
                        // .addBadge(BADGE_COLLECTION_REF, BADGE_NAME)
                        .addBadgeWithNotification(BADGE_COLLECTION_REF, GAME_ID_REF, TEAM_ID_REF, BADGE_NAME)
                    .end()
                .end()
                .getDescr();

        String expectedResult = "packagepackage_nameimporteu.trentorise.game.notification.BadgeNotificationglobalDoubleVerona_distanceglobalStringconst_school_namerule\"R-add-badgeVerona\"salience-1000whenPointConcept(name==\"total_distance\",score>=Verona_distance)$bc:BadgeCollectionConcept(name==\"silver_collection\",badgeEarnednotcontains\"Verona\")Game($gameId:id)Player($teamId:id,$teamId==const_school_name,team==true)then$bc.getBadgeEarned().add(Verona);insert(newBadgeNotification($gameId,$teamId,$bc.getName(),\"Verona\"));update($bc);end";

        String drl = new DrlDumper().dump(pkg);
        System.out.println(new DrlDumper().dump(pkg));
        Assert.assertEquals(StringUtils.deleteWhitespace(drl), StringUtils.deleteWhitespace(expectedResult));

    }

}
