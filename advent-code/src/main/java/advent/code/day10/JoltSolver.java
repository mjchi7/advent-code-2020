package advent.code.day10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JoltSolver {

    private static final Logger logger = LoggerFactory.getLogger(JoltSolver.class);

    public Map<Integer, Integer> solve1(List<Integer> adapterRatings) {
        List<Integer> adapterRatingsSorted = adapterRatings.stream().sorted((a, b) -> a - b).collect(Collectors.toList());
        // There's a base level adapter that is 0 rated.
        adapterRatingsSorted.add(0, 0);

        // Preview the list
        logger.info(adapterRatingsSorted.toString());
        Map<Integer, Integer> differences = new HashMap<>();
        differences.put(1, 0);
        differences.put(2, 0);
        // the differences of 3 is 1 initially due to the fact that the built in adapters has a differences of 3.
        differences.put(3, 1);
        for (int i = 0; i < adapterRatingsSorted.size() - 1; i++ ) {
            int diff = adapterRatingsSorted.get(i + 1) - adapterRatingsSorted.get(i);
            differences.put(diff, differences.get(diff) + 1);
        }
        return differences;
    }

    public long solve2(List<Integer> adapterRatings) {
        List<Long> adapterRatingsSorted = adapterRatings.stream().sorted((a, b) -> a - b).map(Long::valueOf).collect(Collectors.toList());
        adapterRatingsSorted.add(0, 0L);
        long lastRating = adapterRatingsSorted.get(adapterRatingsSorted.size() - 1) + 3;
        adapterRatingsSorted.add(lastRating);
        Map<Long, Long> counts = new HashMap<>();
        counts.put(0L, 1L);
        for (int i = 1; i < adapterRatingsSorted.size(); i++ ) {
            long rating = adapterRatingsSorted.get(i);
            long sum = 0;
            sum =+ safeMapGet(counts, rating - 1) + safeMapGet(counts, rating - 2) + safeMapGet(counts, rating - 3);
            counts.put(rating, sum);
        }
        logger.info(counts.toString());
        return counts.get(lastRating);
    }

    private long safeMapGet(Map<Long, Long> map, Long key) {
        if (key < 0 || !map.containsKey(key)) {
            return 0;
        }
        return map.get(key);
    }
}
