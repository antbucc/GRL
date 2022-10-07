package graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main (String[] args) {
        // new TestGraph().init();

        String myString = "BadgeCollectionConcept{earnedBadges=[], name='silver_collection'}";

        LinkedHashMap<String, List<String>> a = new LinkedHashMap<>();
        a.put("a", new ArrayList<>(List.of(new String[]{"1", "2", "3"})));
        a.put("b", new ArrayList<>(List.of(new String[]{"4", "5", "5"})));

        a.forEach((key, value) -> System.out.println(String.join(", ", value)));

    }

}
