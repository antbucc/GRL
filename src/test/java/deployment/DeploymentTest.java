package deployment;

import dsl.Rules;
import it.univaq.gamification.deployment.services.impl.DeploymentServiceImpl;
import org.junit.Test;

import java.io.IOException;

public class DeploymentTest {

    @Test
    public void UpsertRule() throws IOException {

        final String gameId = "62df8ddc0055b0275113d374";

        DeploymentServiceImpl.builder()
                .protocol("http")
                .domain("localhost")
                .port(8010)
                .username("admin")
                .password("password")
                .build()
                .upsert(gameId, Rules.getAddBadgeRule());
    }

    @Test
    public void deleteRule() throws IOException {

        final String gameId = "62df8ddc0055b0275113d374";

        DeploymentServiceImpl.builder()
                .protocol("http")
                .domain("localhost")
                .port(8010)
                .username("admin")
                .password("password")
                .build()
                .delete(gameId, Rules.getAddBadgeRule());
    }

}
