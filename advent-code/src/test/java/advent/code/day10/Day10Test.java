package advent.code.day10;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Day10Test {

    private static final String PARTIAL_PATH = "src/main/java/advent/code/day10/resource/input.partial.txt";

    private static final String FULL_PATH = "src/main/java/advent/code/day10/resource/input.txt";

    private static final Logger logger = LoggerFactory.getLogger(Day10Test.class);

    private JoltSolver solver = new JoltSolver();

    private List<Integer> getRatings(String path) throws Exception {
        List<String> rawInp = Files.readAllLines(Path.of(path));
        return rawInp.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    @Test
    public void part1_partial() throws Exception {
        List<String> rawInp = Files.readAllLines(Path.of(PARTIAL_PATH));
        List<Integer> adapterRatings = rawInp.stream().map(Integer::valueOf).collect(Collectors.toList());
        Map<Integer, Integer> differences = solver.solve1(adapterRatings);
        assertEquals(22, differences.get(1));
        assertEquals(10, differences.get(3));
    }

    @Test
    public void part1_full() throws Exception {
        List<String> rawInp = Files.readAllLines(Path.of(FULL_PATH));
        List<Integer> adapterRatings = rawInp.stream().map(Integer::valueOf).collect(Collectors.toList());
        Map<Integer, Integer> differences = solver.solve1(adapterRatings);
        logger.info("Number of differences of 1: " + differences.get(1));
        logger.info("Number of differences of 3: " + differences.get(3));
        logger.info("Number of differences of 1 multiplied by number of differences of 3: " + (differences.get(3) * differences.get(1)));
    }

    @Test
    public void part2_partial() throws Exception {
        List<Integer> ratings = getRatings(PARTIAL_PATH);
        long counts = solver.solve2(ratings);
        assertEquals(19208L, counts);
    }

    @Test
    public void part2_full() throws Exception {
        List<Integer> ratings = getRatings(FULL_PATH);
        long counts = solver.solve2(ratings);
        logger.info("Counts: " + counts);
    }
}
