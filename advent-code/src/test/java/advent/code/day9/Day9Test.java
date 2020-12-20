package advent.code.day9;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Day9Test {

    private static final Logger logger = LoggerFactory.getLogger(Day9Test.class);

    private static final String PARTIAL_INPUT_PATH = "src/main/java/advent/code/day9/resource/input.partial.txt";

    private static final String INPUT_PATH = "src/main/java/advent/code/day9/resource/input.txt";

    @Test
    public void part1_partial() throws IOException {
        List<String> rawInp = Files.readAllLines(Path.of(PARTIAL_INPUT_PATH));
        List<Long> inp = rawInp.stream().map(Long::valueOf).collect(Collectors.toList());
        logger.info("Input sequence: " + inp.toString());
        XMASParser parser = new XMASParser(5);
        parser.loadSequence(inp);
        Long firstInvalidNumber = parser.findFirstInvalid();
        assertEquals(127, firstInvalidNumber);
    }

    @Test
    public void part1_full() throws IOException {
        List<String> rawInp = Files.readAllLines(Path.of(INPUT_PATH));
        List<Long> inp = rawInp.stream().map(Long::valueOf).collect(Collectors.toList());
        XMASParser parser = new XMASParser(25);
        parser.loadSequence(inp);
        Long firstInvalidNumber = parser.findFirstInvalid();
        logger.info("part1_full: First invalid number: " + firstInvalidNumber);
    }

    @Test
    public void part2_partial() throws IOException {
        List<String> rawInp = Files.readAllLines(Path.of(PARTIAL_INPUT_PATH));
        List<Long> inp = rawInp.stream().map(Long::valueOf).collect(Collectors.toList());
        logger.info("Input sequence: " + inp.toString());
        XMASParser parser = new XMASParser(5);
        parser.loadSequence(inp);
        Long firstInvalidNumber = parser.findFirstInvalid();
        List<Long> contiguousSet = parser.findContiguousSet(firstInvalidNumber);
        assertEquals(127, firstInvalidNumber);
        assertEquals(Arrays.asList(15L, 25L, 47L, 40L), contiguousSet);
        long min = contiguousSet.stream().mapToLong(v -> v).min().orElseThrow(NoSuchElementException::new);
        long max = contiguousSet.stream().mapToLong(v -> v).max().orElseThrow(NoSuchElementException::new);
        assertEquals(62, min + max);
    }

    @Test
    public void part2_full() throws IOException {
        List<String> rawInp = Files.readAllLines(Path.of(INPUT_PATH));
        List<Long> inp = rawInp.stream().map(Long::valueOf).collect(Collectors.toList());
        logger.info("Input sequence: " + inp.toString());
        XMASParser parser = new XMASParser(25);
        parser.loadSequence(inp);
        Long firstInvalidNumber = parser.findFirstInvalid();
        List<Long> contiguousSet = parser.findContiguousSet(firstInvalidNumber);
        long min = contiguousSet.stream().mapToLong(v -> v).min().orElseThrow(NoSuchElementException::new);
        long max = contiguousSet.stream().mapToLong(v -> v).max().orElseThrow(NoSuchElementException::new);
        logger.info("Contiguous set: " + contiguousSet);
        logger.info("Min: " + min);
        logger.info("Max: " + max);
        logger.info("Part2_full: min + max: " + (min + max));
    }

}
