import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.core.io.impl.ByteArrayResource;
import org.drools.verifier.Verifier;
import org.drools.verifier.VerifierError;
import org.drools.verifier.builder.VerifierBuilder;
import org.drools.verifier.builder.VerifierBuilderFactory;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;


public class RuleTest {

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
    public void TestInputData() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("input_data_test")
                    .when()
                        .inputData("$inputData")
                            .fromData(ConstraintType.NEQ, "mode", null, "$mode")
                            .constraint("$mode != \"absent\"")
                            .fromData(ConstraintType.NEQ, "meteo", null, "$meteo")
                            .fromData(ConstraintType.NEQ, "school-date", null, "$date")
                        .end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void TestBadgeCollection() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("badge_collection_test")
                    .when()
                        .badgeCollection("$bc")
                            .name(ConstraintType.EQ, "medaglie")
                            .declare("$badge", "Green Medal")
                            .badgeEarned(ConstraintType.NOT_CONTAINS, "$badge")
                        .end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void TestChallenge() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("challenge_test")
                    .when()
                        .challenge()
                            .modelName(ConstraintType.EQ, "kmSettimanali")
                            .declareFromField("$counter", "counterName")
                            .declareFromField("$target", "target")
                            .declareFromField("$vp", "virtualPrice")
                            .fromFields(ConstraintType.EQ, "target", "aTarget")
                            .fromFields(ConstraintType.EQ, "target", "aTarget", "$isTargetATarget")
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
    public void TestSimpleRuleGeneration() {
         pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("simple_rule")
                    .when()
                        .action().name(ConstraintType.NEQ, "Walk").end()
                        .action("$action2").bindName("$actionName").name(ConstraintType.EQ, "Walk", "$isActionWalk").end()
                        .point().name(ConstraintType.EQ, "Robert").score(ConstraintType.EQ, 20).end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void TestRuleGeneration() {
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
    public void TestRealRuleWhen() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("real_rule")
                        .when()
                            .challenge("$challenge")
                                .modelName(ConstraintType.EQ, "incentiveGroupChallengeReward")
                                .declareFromField("$bonusScore", "bonusScore")
                                .declareFromField("$bonusPointType", "bonusPointType")
                                .isCompleted(false)
                            .end()
                            .reward("$reward").end()
                            .point("$pcBonus").constraint("name == (String) $bonusPointType").end()
                        .end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

    @Test
    public void TestRealRule2When() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("real_rule2")
                    .when()
                        .action().id(ConstraintType.EQ, "taskCompleted").end()
                        .inputData()
                            .fromData(ConstraintType.EQ, "moves", 5.0, "$moves")
                            .fromData(ConstraintType.EQ, "errors", 0.0, "$errors")
                        .end()
                        .point("$movesScore").name(ConstraintType.EQ, "moves").end()
                        .point("$errorsScore").name(ConstraintType.EQ, "errors").end()
                        .customData("$customData").constraint("this[\"level\"] == null").end()
                    .end()
                .end()
                .getDescr();

        verifyRule(pkg);
    }

}
