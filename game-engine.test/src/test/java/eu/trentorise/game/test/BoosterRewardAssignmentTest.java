package eu.trentorise.game.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import eu.trentorise.game.model.ChallengeModel;
import eu.trentorise.game.model.GroupChallenge.PointConceptRef;
import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.model.Reward;
import eu.trentorise.game.model.core.GameConcept;

public class BoosterRewardAssignmentTest extends GameTest {

    private static final String GAME = "challengeTest";
    private static final String ACTION = "save_itinerary";

    private static final String DOMAIN = "my-domain";

    private static final String PLAYER_1 = "Alice";

    // TODO to remove extending GameTest with operation on ChallengeModels
    @Autowired
    private MongoTemplate mongo;

    @Override
    public void initEnv() {
        List<GameConcept> concepts = new ArrayList<GameConcept>();
        PointConcept greenLeaves = new PointConcept("green leaves");
        PointConcept points = new PointConcept("points");
        PointConcept bikeTrips = new PointConcept("bikeTrips");

        long dayDurationInMillis = 24 * 60 * 60 * 1000;

        Date todayDate = new Date(); // today
        Date tomorrowDate = new Date(); // today
        tomorrowDate.setTime(todayDate.getTime() + dayDurationInMillis);
        Date yesterdayDate = new Date(); // yesterday
        yesterdayDate.setTime(todayDate.getTime() - dayDurationInMillis);
        Date dayBeforeYesterdayDate = new Date(); // the day before yesterday
        dayBeforeYesterdayDate.setTime(yesterdayDate.getTime() - dayDurationInMillis);

        greenLeaves.addPeriod("weekly", todayDate, dayDurationInMillis * 7);
        greenLeaves.increment(21d);
        concepts.add(greenLeaves);

        points.addPeriod("weekly", todayDate, dayDurationInMillis * 7);
        points.increment(1000d);
        concepts.add(points);

        bikeTrips.addPeriod("weekly", todayDate, dayDurationInMillis * 7);
        bikeTrips.increment(10d);
        concepts.add(bikeTrips);

        savePlayerState(GAME, PLAYER_1, concepts);

    }

    @Override
    public void defineGame() {
        mongo.dropCollection(ChallengeModel.class);

        List<GameConcept> concepts = new ArrayList<GameConcept>();
        concepts.add(new PointConcept("green leaves"));

        defineGameHelper(DOMAIN, GAME, Arrays.asList(ACTION), concepts);

        try {
            loadClasspathRules(GAME, "rules/" + GAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void defineExecData(List<ExecData> execList) {
        List<Object> list = new ArrayList<>();
        Reward r = new Reward(); // with threshold, and threshold isn't applied
        
        r.setPercentage(50);
        r.setThreshold(250);
        r.setCalculationPointConcept(new PointConceptRef("green leaves", "weekly"));
        r.setTargetPointConcept(new PointConceptRef("green leaves", "daily"));
        list.add(r);
        
        Reward r2 = new Reward(); // with threshold, and threshold is applied
        
        r2.setPercentage(50);
        r2.setThreshold(250);
        r2.setCalculationPointConcept(new PointConceptRef("points", "weekly"));
        r2.setTargetPointConcept(new PointConceptRef("points", "daily"));
        list.add(r2);
        
        Reward r3 = new Reward(); // without threshold

        r3.setPercentage(50);
        r3.setCalculationPointConcept(new PointConceptRef("points", "weekly"));
        r3.setTargetPointConcept(new PointConceptRef("points", "daily"));
        list.add(r3);

        Reward r4 = new Reward(); // fixed reward

        r4.getBonusScore().put("Alice", 15d);
        r4.setCalculationPointConcept(new PointConceptRef("bikeTrips", "weekly"));
        r4.setTargetPointConcept(new PointConceptRef("bikeTrips", "daily"));
        list.add(r4);

        execList.add(new ExecData(GAME, ACTION, PLAYER_1, new HashMap<>(), list));

    }

    @Override
    public void analyzeResult() {
        double newscore = 21 + 11; // increase current gl of the percentage
        assertionPoint(GAME, newscore, PLAYER_1, "green leaves"); // +50%
        assertionPoint(GAME, (1000.0 + 250 + 625), PLAYER_1, "points"); // +Threshold
        assertionPoint(GAME, (10D + 15D), PLAYER_1, "bikeTrips"); // fixed score
    }

}
