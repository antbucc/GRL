package dsl;

import eu.trentorise.game.model.BadgeCollectionConcept;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.Player;
import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.notification.BadgeNotification;
import it.univaq.gamification.dsl.binders.BadgeCollectionBind;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.Global;
import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;

import static it.univaq.gamification.dsl.builders.lhs.ConstraintType.EQ;
import static it.univaq.gamification.dsl.builders.lhs.ConstraintType.GTE;

public class Rules {

    public static PackageDescr getAddBadgeRule1() {
        final String BADGE_NAME = "pro-badge";
        final BadgeCollectionBind BADGE_COLLECTION_REF = new BadgeCollectionBind("$bc");
        final Bind GAME_ID_REF = new Bind("$gameId");

        PackageDescr packageDescrBuilder = new PackageDescrBuilderImpl()
                .name("eu.trentorise.game.model")
                .newImport(PointConcept.class).end()
                .newImport(BadgeCollectionConcept.class).end()
                .newImport(Game.class).end()
                .newImport(Player.class).end()
                .newImport(BadgeNotification.class).end()
                .newRule()
                    .name("Add pro-badge")
                    .attribute("salience", "1000")
                    .when()
                        .point().name(EQ, "total_distance").score(GTE, 10.0).end()
                        .badgeCollection(BADGE_COLLECTION_REF).name(EQ, "badge_collection").badgeEarnedNotContains(BADGE_NAME).end()
                        .player().team(false).end()
                    .end()
                    .then()
                        .addBadge(BADGE_COLLECTION_REF, BADGE_NAME)
                    .end()
                .end()
                .getDescr();

        return packageDescrBuilder;
    }

    public static PackageDescr getAddBadgeRule2() {
        final String BADGE_NAME_1 = "pro-badge";
        final String BADGE_NAME_2 = "pro-badge-2";
        final BadgeCollectionBind BADGE_COLLECTION_REF = new BadgeCollectionBind("$bc");
        final Bind GAME_ID_REF = new Bind("$gameId");

        return new PackageDescrBuilderImpl()
                .name("eu.trentorise.game.model")
                .newImport(PointConcept.class).end()
                .newImport(BadgeCollectionConcept.class).end()
                .newImport(Game.class).end()
                // .newImport("eu.trentorise.game.model.Player").end()
                .newImport(BadgeNotification.class).end()
                .newRule()
                    .name("Add pro-badge-2")
                    .when()
                        .point().name(EQ, "total_distance").score(GTE, 10.0).end()
                        .badgeCollection(BADGE_COLLECTION_REF)
                            .name(EQ, "badge_collection")
                            .badgeEarnedContains(BADGE_NAME_1)
                            .badgeEarnedNotContains(BADGE_NAME_2)
                        .end()
                        .player().team(false).end()
                    .end()
                    .then()
                        .addBadge(BADGE_COLLECTION_REF, BADGE_NAME_2)
                    .end()
                .end()
                .getDescr();
    }

    public static PackageDescr getAddBadgeRuleWithNotification() {
        final String BADGE_NAME = "Verona";
        final BadgeCollectionBind BADGE_COLLECTION_REF = new BadgeCollectionBind("$bc");
        final Bind TEAM_ID_REF = new Bind("$teamId");
        final Bind GAME_ID_REF = new Bind("$gameId");
        final Global GLOBAL_VERONA_DISTANCE = new Global(Double.class, "Verona_distance");
        final Global GLOBAL_SCHOOL_NAME = new Global(String.class, "const_school_name");

        return new PackageDescrBuilderImpl()
                .name("eu.trentorise.game.model")
                .newImport(PointConcept.class).end()
                .newImport(BadgeCollectionConcept.class).end()
                .newImport(Game.class).end()
                .newImport(Player.class).end()
                .newImport(BadgeNotification.class).end()
                .newGlobal(GLOBAL_VERONA_DISTANCE).end()
                .newGlobal(GLOBAL_SCHOOL_NAME).end()
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
