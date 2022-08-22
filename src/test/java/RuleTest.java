import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import org.drools.compiler.lang.descr.PackageDescr;
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

        pkg = new PackageDescrBuilderImpl()
                .name("package_name")
                .newGlobal().type(Double.class.getSimpleName()).identifier(GLOBAL_VERONA_DISTANCE).end()
                .newRule()
                    .name("R-add-badge Verona")
                    .when()
                        .point().name(EQ, "total_distance").score(GTE, GLOBAL_VERONA_DISTANCE).end()
                        .badgeCollection(BADGE_COLLECTION_REF).name(EQ, "silver_collection").badgeEarnedNotContains(BADGE_NAME).end()
                        .game().bindId(GAME_ID_REF).end()
                        .player().bindId(TEAM_ID_REF).constraint(TEAM_ID_REF + EQ + "\"school_name\"").team(true).end()
                    .end()
                    .then()
                        // .addBadge(BADGE_COLLECTION_REF, BADGE_NAME)
                        .addBadgeWithNotification(BADGE_COLLECTION_REF, GAME_ID_REF, TEAM_ID_REF, BADGE_NAME)
                    .end()
                .end()
                .getDescr();

        System.out.println(new DrlDumper().dump(pkg));

    }

}
