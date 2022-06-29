import it.univaq.gamification.dsl.Operator;
import it.univaq.gamification.dsl.impl.RuleBuilderImpl;
import it.univaq.gamification.dsl.utils.Action;
import it.univaq.gamification.dsl.utils.Badge;
import it.univaq.gamification.dsl.utils.Challenge;
import it.univaq.gamification.dsl.utils.Point;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.mvel.DrlDumper;
import org.junit.Test;

public class RuleTest {

    @Test
    public void testSimpleRuleDefinition() {
        new RuleBuilderImpl()
            .name("testSimpleRule")
            .when()
                .action().name(Operator.EQ, Action.MOVED).end()
                .point().name(Operator.EQ, Point.STEPS).score(Operator.GTE, 10000).end()
            .end();
    }

    @Test
    public void testRuleDefinition() {
        new RuleBuilderImpl()
            .name("ruleDefinition")
            .when()
                .or()
                    .and()
                        .action().name(Operator.EQ, Action.RUN).end()
                        .action().name(Operator.EQ, Action.USED_BIKE).end()
                    .end()
                    .action().name(Operator.EQ, Action.WALKED).end()
                .end()
                .point().name(Operator.EQ, Point.STEPS).score(Operator.GTE, 10000).end()
                .badgeCollection().name(Operator.EQ, Badge.BRONZE).earned(Operator.NOT_CONTAINS, Badge.SILVER).end()
                .challenge().modelName(Operator.EQ, Challenge.SCHOOL_WITHOUT_CAR).completed(true).end()
                .inputData().attribute("key1", Operator.EQ, null).attribute("key2", Operator.NEQ, "value").end()
            .end();
    }

    @Test
    public void testRuleBinding() {
        new RuleBuilderImpl()
            .name("ruleBinding")
            .when()
                // $runAction : Action (name == "RUN")
                .action("$runAction").name(Operator.EQ, Action.RUN).end()
                // Action ($actionName : name, name == "USED_BIKE")
                .action().bindName("$actionName").name(Operator.EQ, Action.USED_BIKE).end()
                // Action ($hasMoved : name == "MOVED")
                .action().name(Operator.EQ, Action.MOVED, "$hasMoved").end()
            .end();
    }

    @Test
    public void testDroolsFluentApi() {
        PackageDescr pkg = DescrFactory.newPackage()
                .name("org.drools")
                .newRule().name("test")
                .lhs()
                    .or()
                        .pattern("Action").bind("$name", "name", false).constraint("name == \"walked\"").end()
                        .pattern("Action").constraint("name == \"moved\"").end()
                    .end()
                    .pattern("Point").constraint("name == \"steps\"").end()
                .end()
                .rhs("// Do something")
                .namedRhs("c1", "// Do something else")
            .end()
            .getDescr();

        String drl = new DrlDumper().dump(pkg);
        System.out.println(drl);
    }

}
