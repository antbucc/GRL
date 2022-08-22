import it.univaq.gamification.dsl.builders.impl.PackageDescrBuilderImpl;
import org.drools.compiler.lang.descr.PackageDescr;
import org.junit.Test;

public class RuleThenTest {

    PackageDescr pkg;

    @Test
    public void TestAddBadge() {
        pkg = new PackageDescrBuilderImpl()
                .name("it.gamification.something")
                .newRule()
                    .name("input_data_test")
                    .when()
                        // when
                    .end()
                    .then()
                        .addBadge("$bc", "Verona")
                    .end()
                .end()
                .getDescr();

        System.out.println(new DrlDumper().dump(pkg));
    }

}
