package dsl;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.PackageDescr;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import it.univaq.gamification.utils.DrlDumper;
import org.drools.core.io.impl.ByteArrayResource;
import org.drools.verifier.Verifier;
import org.drools.verifier.VerifierError;
import org.drools.verifier.builder.VerifierBuilder;
import org.drools.verifier.builder.VerifierBuilderFactory;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;


public class RuleWhenTest {

    PackageDescr pkg;

    private void verifyRule(PackageDescr packageDescr) {
        VerifierBuilder verifierBuilder = VerifierBuilderFactory.newVerifierBuilder();
        Verifier verifier = verifierBuilder.newVerifier();
        String rule = new DrlDumper().dump(packageDescr);
        System.out.println(rule);
        verifier.addResourcesToVerify(new ByteArrayResource(rule.getBytes()), ResourceType.DRL);
        try {
            Assert.assertTrue("The rule has some error", verifier.getErrors().isEmpty());
        } catch (AssertionError e) {
            for (VerifierError error : verifier.getErrors()) {
                System.out.println(error.getMessage());
            }
            throw e;
        }
    }

    @Test
    public void testInputData() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("input_data_test")
                    .when()
                        .inputData(new BindName("$inputData"))
                            .fromData(ConstraintType.NEQ, "mode", null, new BindName("$mode"))
                            .constraint("$mode != \"absent\"")
                            .fromData(ConstraintType.NEQ, "meteo", null, new BindName("$meteo"))
                            .fromData(ConstraintType.NEQ, "school-date", null, new BindName("$date"))
                        .end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void testBadgeCollection() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("badge_collection_test")
                    .when()
                        .badgeCollection(new BindName("$bc"))
                            .name(ConstraintType.EQ, "medaglie")
                            .declare(new BindName("$badge"), "Green Medal")
                            .badgeEarned(ConstraintType.NOT_CONTAINS, "$badge")
                        .end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void testChallenge() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("challenge_test")
                    .when()
                        .challenge()
                            .modelName(ConstraintType.EQ, "kmSettimanali")
                            .bindFromField(new BindName("$counter"), "counterName")
                            .bindFromField(new BindName("$target"), "target")
                            .bindFromField(new BindName("$vp"), "virtualPrice")
                            .fromFields(ConstraintType.EQ, "target", "aTarget")
                            .fromFields(ConstraintType.EQ, "target", "aTarget", new BindName("$isTargetATarget"))
                            .constraint("$endTime: end.getTime()")
                            .constraint("$startTime: start.getTime()")
                            .constraint("$now: System.currentTimeMillis()")
                            .isCompleted(false)
                        .end()
                    .end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void testSimpleRuleGeneration() {
         pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("simple_rule")
                    .when()
                        .action().name(ConstraintType.NEQ, "Walk").end()
                        .action(new BindName("$action2"))
                            .bindName(new BindName("$actionName"))
                            .name(ConstraintType.EQ, "Walk", new BindName("$isActionWalk"))
                        .end()
                        .point().name(ConstraintType.EQ, "Robert").score(ConstraintType.EQ, 20).end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void testRuleGeneration() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("rule")
                    .when()
                        .point().name(ConstraintType.EQ, "Walter").score(ConstraintType.EQ, 10).end()
                        .and()
                            .action().name(ConstraintType.EQ, "Eleven").end()
                            .point().name(ConstraintType.EQ, "Bart").score(ConstraintType.EQ, 20).end()
                            .or()
                                .point().name(ConstraintType.EQ, "Kate").score(ConstraintType.EQ, 30).end()
                                .point().name(ConstraintType.EQ, "Nick").score(ConstraintType.EQ, 40).end()
                            .end()
                        .end()
                        .not()
                            .action().name(ConstraintType.EQ, "Run").end()
                        .end()
                        .exists()
                            .point().name(ConstraintType.EQ, "Ronny").score(ConstraintType.EQ, 60).end()
                        .end()
                    .end()
            .end()
            .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void testRealRuleWhen() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("real_rule")
                        .when()
                            .challenge(new BindName("$challenge"))
                                .modelName(ConstraintType.EQ, "incentiveGroupChallengeReward")
                                .bindFromField(new BindName("$bonusScore"), "bonusScore")
                                .bindFromField(new BindName("$bonusPointType"), "bonusPointType")
                                .isCompleted(false)
                            .end()
                            .reward(new BindName("$reward")).end()
                            .point(new BindName("$pcBonus")).constraint("name == (String) $bonusPointType").end()
                        .end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void testRealRule2When() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("real_rule2")
                    .when()
                        .action().id(ConstraintType.EQ, "taskCompleted").end()
                        .inputData()
                            .fromData(ConstraintType.EQ, "moves", 5.0, new BindName("$moves"))
                            .fromData(ConstraintType.EQ, "errors", 0.0, new BindName("$errors"))
                        .end()
                        .point(new BindName("$movesScore")).name(ConstraintType.EQ, "moves").end()
                        .point(new BindName("$errorsScore")).name(ConstraintType.EQ, "errors").end()
                        .customData(new BindName("$customData")).constraint("this[\"level\"] == null").end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void testDeclaration() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newDeclare()
                    .type().name("PRItinerary")
                    .newField("length").type(Double.class.getSimpleName()).end()
                    .newField("name").type(String.class.getSimpleName()).end()
                .end()
                .newRule()
                .name("real_rule2")
                    .when()
                        .action().id(ConstraintType.EQ, "taskCompleted").end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

}
