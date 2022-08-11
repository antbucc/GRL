import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import org.drools.compiler.lang.descr.PackageDescr;
import org.junit.Test;


public class RuleTest {

    PackageDescr pkg;

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

        System.out.println(new DrlDumper().dump(pkg));
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

        System.out.println(new DrlDumper().dump(pkg));
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
                            .fieldsEntry(ConstraintType.EQ, "target", "aTarget")
                            .fieldsEntry(ConstraintType.EQ, "target", "aTarget", "$isTargetATarget")
                            .constraint("$endTime: end.getTime()")
                            .constraint("$startTime: start.getTime()")
                            .constraint("$now: System.currentTimeMillis()")
                            .isCompleted(false)
                        .end()
                    .end()
                    .end()
                .end()
                .getDescr();

        System.out.println(new DrlDumper().dump(pkg));
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

        System.out.println(new DrlDumper().dump(pkg));
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
                            .point().name(ConstraintType.EQ, "Sam").score(ConstraintType.EQ, 50).end()
                            .point().name(ConstraintType.EQ, "Ronny").score(ConstraintType.EQ, 60).end()
                        .end()
                    .end()
            .end()
            .getDescr();

        System.out.println(new DrlDumper().dump(pkg));
    }

}
