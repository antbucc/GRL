package dsl;

import it.univaq.gamification.dsl.binders.BadgeCollectionBind;
import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import it.univaq.gamification.utils.DrlDumper;
import org.junit.Test;

public class RuleThenTest {

    PackageDescr pkg;

    @Test
    public void testAddBadge() {
        final String BADGE_NAME = "green-badge";
        final BadgeCollectionBind BADGE_COLLECTION_BIND = new BadgeCollectionBind("badgeCollection");

        pkg = new PackageDescrBuilderImpl()
                .name("eu.trentorise.game.model")
                .newImport(BadgeCollectionBind.class).end()
                .newRule()
                    .name("my_amazing_rule")
                    .when()
                        .badgeCollection(BADGE_COLLECTION_BIND)
                            .badgeEarnedNotContains(BADGE_NAME)
                        .end()
                    .end()
                    .then()
                        .addBadge(BADGE_COLLECTION_BIND, BADGE_NAME)
                        .log(String.format("The %s badge has been assigned", BADGE_NAME))
                    .end()
                .end()
                .getDescr();

        System.out.println(new DrlDumper().dump(pkg));
    }

}
