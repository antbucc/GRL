package dsl;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.PackageDescr;
import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import it.univaq.gamification.utils.DrlDumper;
import org.junit.Test;

public class RuleThenTest {

    PackageDescr pkg;

    @Test
    public void testAddBadge() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("input_data_test")
                    .when()
                        // when
                    .end()
                    .then()
                        .addBadge(new BindName("$bc"), "Verona")
                    .end()
                .end()
                .getDescr();

        System.out.println(new DrlDumper().dump(pkg));
    }

}
