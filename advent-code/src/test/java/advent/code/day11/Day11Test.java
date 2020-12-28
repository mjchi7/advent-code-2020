package advent.code.day11;

import advent.code.day10.Day10Test;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Day11Test {

    private static final String PARTIAL_PATH = "src/main/java/advent/code" +
            "/day11/resource/input.partial.txt";

    private static final String FULL_PATH = "src/main/java/advent/code/day11" +
            "/resource/input.txt";

    private static final Logger logger = LoggerFactory
            .getLogger(Day11Test.class);

    private SeatSolver solver = new SeatSolver();

    private String[][] loadAndConvert(String path) throws Exception {
        List<String> rawMap = Files.readAllLines(Path.of(path));
        // Get list of list of string from list of string by dicing up all
        // the characters
        List<List<String>> intermediateMap = rawMap.stream()
                .map(s -> s.chars().mapToObj(c -> String.valueOf((char) c))
                        .collect(Collectors
                                .toList())).collect(Collectors
                        .toList());
        return intermediateMap.stream()
                .map(listOfString -> listOfString.toArray(String[]::new))
                .toArray(String[][]::new);
    }

    @Test
    public void part1_partial() throws Exception {
        String[][] steadyState = solver.solve(loadAndConvert(PARTIAL_PATH));
        Arrays.stream(steadyState).forEach(row -> {
            logger.info(String.join("", row));
        });
        long nOccupied = solver.countOccupied(steadyState);
        assertEquals(37L, nOccupied);
    }

    @Test
    public void part1_full() throws Exception {
        String[][] steadyState = solver.solve(loadAndConvert(FULL_PATH));
        Arrays.stream(steadyState).forEach(row -> {
            logger.info(String.join("", row));
        });
        long nOccupied = solver.countOccupied(steadyState);
        logger.info("Number of occupied: " + nOccupied);
    }

    @Test
    public void part2_partial() throws Exception {
        String[][] steadyState = solver.solve2(loadAndConvert(PARTIAL_PATH));
        Arrays.stream(steadyState).forEach(row -> {
            logger.info(String.join("", row));
        });
        long nOccupied = solver.countOccupied(steadyState);
        logger.info("Number of occupied: " + nOccupied);
    }

    @Test
    public void part2_full() throws Exception {
        String[][] steadyState = solver.solve2(loadAndConvert(FULL_PATH));
        Arrays.stream(steadyState).forEach(row -> {
            logger.info(String.join("", row));
        });
        long nOccupied = solver.countOccupied(steadyState);
        logger.info("Number of occupied: " + nOccupied);
    }
}
