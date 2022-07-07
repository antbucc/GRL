import it.univaq.gamification.dsl.ConstraintType;
import it.univaq.gamification.dsl.builders.impl.GRLRuleBuilderImpl;
import org.drools.model.Rule;
import org.junit.Test;


public class RuleTest {

    Rule rule;

    @Test
    public void TestExecutableModelSimpleGeneration() {
        rule = new GRLRuleBuilderImpl()
                .name("simple_rule")
                .when()
                    .action().name(ConstraintType.EQ, "Walk").end()
                    .point().name(ConstraintType.EQ, "Robert").score(ConstraintType.EQ, 20).end()
                .end()
                .build();

        System.out.println(rule);
    }

    @Test
    public void TestExecutableModelGeneration() {
        rule = new GRLRuleBuilderImpl()
                .name("ce_rule")
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
                .build();

        System.out.println(rule);
    }

}
