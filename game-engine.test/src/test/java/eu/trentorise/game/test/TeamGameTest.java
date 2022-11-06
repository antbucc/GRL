package eu.trentorise.game.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import eu.trentorise.game.managers.QueueGameWorkflow;
import eu.trentorise.game.model.BadgeCollectionConcept;
import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.model.core.GameConcept;
import eu.trentorise.game.services.PlayerService;
import eu.trentorise.game.services.Workflow;

@ContextConfiguration(classes = {TeamGameTestConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class TeamGameTest extends GameTest {

    @Autowired
    PlayerService playerSrv;

    private static final String GAME = "teams";
    private static final String ACTION = "save_itinerary";

    private static final String DOMAIN = "my-domain";

    @Override
    public void initEnv() {

        saveTeam(GAME, "fuorilegge", "fuorilegge",
                new HashSet<String>(Arrays.asList("prowler", "rocket racer")));

        saveTeam(GAME, "secret avengers", "secret avengers",
                new HashSet<String>(Arrays.asList("war machine", "moon knight")));

        saveTeam(GAME, "marvel", "marvel",
                new HashSet<String>(Arrays.asList("fuorilegge", "secret avengers")));

    }

    @Override
    public void defineGame() {
        List<GameConcept> concepts = new ArrayList<GameConcept>();
        concepts.add(new PointConcept("steps"));
        concepts.add(new BadgeCollectionConcept("itinerary"));
        concepts.add(new BadgeCollectionConcept("my-badges"));

        defineGameHelper(DOMAIN, GAME, Arrays.asList(ACTION), concepts);

        try {
            loadClasspathRules(GAME, "rules/" + GAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void defineExecData(List<ExecData> execList) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("meters-walked", 500d);
        ExecData input = new ExecData(GAME, ACTION, "prowler", data);
        execList.add(input);

        data = new HashMap<String, Object>();
        data.put("meters-walked", 1000d);
        input = new ExecData(GAME, ACTION, "fuorilegge", data);
        execList.add(input);

        data = new HashMap<String, Object>();
        data.put("meters-walked", 400d);
        input = new ExecData(GAME, ACTION, "prowler", data);
        execList.add(input);

        data = new HashMap<String, Object>();
        data.put("meters-walked", 100d);
        input = new ExecData(GAME, ACTION, "rocket racer", data);
        execList.add(input);

    }

    @Override
    public void analyzeResult() {
        assertionPoint(GAME, 1001d, "prowler", "steps");
        assertionPoint(GAME, 201d, "rocket racer", "steps");
        assertionPoint(GAME, 1531d, "fuorilegge", "steps");
        assertionPoint(GAME, 760d, "marvel", "steps");
        assertionPoint(GAME, 1d, "secret avengers", "steps");

        assertionBadge(GAME, Arrays.asList("poi_1", "poi_3", "team_1", "poi_2", "team_bump_1"),
                "fuorilegge", "itinerary");

    }

}


/**
 * TODO: not good. Override GameTest configuration using the QueueGameWorkflow. Some issues with
 * propagation without using execution queue..to investigate
 *
 */
@Configuration
class TeamGameTestConfiguration {

    @Bean
    @Primary
    public Workflow workflow() {
        return new QueueGameWorkflow();
    }

}
