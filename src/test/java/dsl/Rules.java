package dsl;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.PackageDescr;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;

import static it.univaq.gamification.dsl.ConstraintType.EQ;
import static it.univaq.gamification.dsl.ConstraintType.GTE;

public class Rules {

    public static PackageDescr getAddBadgeRule() {
        final String BADGE_NAME = "Verona";
        final BindName BADGE_COLLECTION_REF = new BindName("$bc");
        final BindName GAME_ID_REF = new BindName("$gameId");

        return new PackageDescrBuilderImpl()
                .name("dsl")
                .newImport("eu.trentorise.game.model.PointConcept")
                .newImport("eu.trentorise.game.model.BadgeCollectionConcept")
                .newImport("eu.trentorise.game.model.Game")
                .newImport("eu.trentorise.game.model.Player")
                .newRule()
                    .name("R-add-badge Verona")
                    .when()
                        .point().name(EQ, "total_distance").score(GTE, 10.0).end()
                        .badgeCollection(BADGE_COLLECTION_REF).name(EQ, "silver_collection").badgeEarnedNotContains(BADGE_NAME).end()
                        .game().bindId(GAME_ID_REF).end()
                        .player().team(false).end()
                    .end()
                    .then()
                        .addBadge(BADGE_COLLECTION_REF, BADGE_NAME)
                    .end()
                .end()
                .getDescr();
    }

    public static PackageDescr getAddBadgeRuleWithNotification() {
        final String BADGE_NAME = "Verona";
        final BindName BADGE_COLLECTION_REF = new BindName("$bc");
        final BindName TEAM_ID_REF = new BindName("$teamId");
        final BindName GAME_ID_REF = new BindName("$gameId");
        final String GLOBAL_VERONA_DISTANCE = "Verona_distance";
        final String GLOBAL_SCHOOL_NAME = "const_school_name";

        return new PackageDescrBuilderImpl()
                .name("package_name")
                .newImport("eu.trentorise.game.notification.BadgeNotification")
                .newGlobal(Double.class.getSimpleName(), GLOBAL_VERONA_DISTANCE)
                .newGlobal(String.class.getSimpleName(), GLOBAL_SCHOOL_NAME)
                .newRule()
                    .name("R-add-badge Verona")
                    .attribute("salience", "-1000")
                    .when()
                        .point().name(EQ, "total_distance").score(GTE, GLOBAL_VERONA_DISTANCE).end()
                        .badgeCollection(BADGE_COLLECTION_REF).name(EQ, "silver_collection").badgeEarnedNotContains(BADGE_NAME).end()
                        .game().bindId(GAME_ID_REF).end()
                        .player().bindId(TEAM_ID_REF).constraint(TEAM_ID_REF.getValue() + EQ.getValue() + GLOBAL_SCHOOL_NAME).team(true).end()
                    .end()
                    .then()
                        // .addBadge(BADGE_COLLECTION_REF, BADGE_NAME)
                        .addBadgeWithNotification(BADGE_COLLECTION_REF, GAME_ID_REF, TEAM_ID_REF, BADGE_NAME)
                    .end()
                .end()
                .getDescr();
    }

}
