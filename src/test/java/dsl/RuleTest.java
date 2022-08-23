package dsl;

import it.univaq.gamification.utils.DrlDumper;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class RuleTest {

    @Test
    public void testAddBadge() {
        String expectedResult = "packagepackage_nameimporteu.trentorise.game.notification.BadgeNotificationglobalDoubleVerona_distanceglobalStringconst_school_namerule\"R-add-badgeVerona\"salience-1000whenPointConcept(name==\"total_distance\",score>=Verona_distance)$bc:BadgeCollectionConcept(name==\"silver_collection\",badgeEarnednotcontains\"Verona\")Game($gameId:id)Player($teamId:id,$teamId==const_school_name,team==true)then$bc.getBadgeEarned().add(\"Verona\");insert(newBadgeNotification($gameId,$teamId,$bc.getName(),\"Verona\"));update($bc);end";
        String drl = new DrlDumper().dump(Rules.getBadgeRule());
        System.out.println(drl);
        Assert.assertEquals(StringUtils.deleteWhitespace(drl), StringUtils.deleteWhitespace(expectedResult));

    }

}
