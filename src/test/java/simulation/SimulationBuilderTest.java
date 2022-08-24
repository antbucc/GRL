package simulation;

import it.univaq.gamification.simulation.builders.impl.fact.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SimulationBuilderTest {

    @Test
    public void TestBuilders() {
        GameFactBuilderImpl.builder().id("1").build();
        ActionFactBuilderImpl.builder().id("run").build();
        BadgeCollectionFactBuilderImpl.builder().id("badgeCollection").badgeEarned(new ArrayList<>(Collections.singleton("badge1"))).build();
        ChallengeFactBuilderImpl.builder().id("challenge").fields(new HashMap<>()).build();
        CustomDataFactBuilderImpl.builder().data(new HashMap<>()).build();
        InputDataFactBuilderImpl.builder().data(new HashMap<>()).build();
        PlayerFactBuilderImpl.builder().id("player").team(true).build();
        PointFactBuilderImpl.builder().id("point").score(1.0).build();
        PropagationFactBuilderImpl.builder().level(2).build();
        RewardFactBuilderImpl.builder().threshold(10.0).build();
    }

}
