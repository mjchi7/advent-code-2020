package advent.code.day8;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day8Test {

    private static final Logger logger = LoggerFactory.getLogger(Day8Test.class);

    private static final String PARTIAL_INPUT_PATH = "src/main/java/advent/code/day8/resource/input.partial.txt";

    private static final String INPUT_PATH = "src/main/java/advent/code/day8/resource/input.partial.txt";

    @Test
    public void part1_testPartial() throws IOException {
        List<String> partialInput = Files.readAllLines(Path
                .of(PARTIAL_INPUT_PATH));
        OpInterpreter inp = new OpInterpreter(partialInput);
        inp.run();
        logger.info("part1_testPartial: Global Value: " + inp.getGlobalVal().toString());
    }

    @Test
    public void part1_testFull() throws IOException {
        List<String> sequences = Files.readAllLines(Path.of(INPUT_PATH));
        OpInterpreter inp = new OpInterpreter(sequences);
        inp.run();
        logger.info("part1_testFull: Global Value: " + inp.getGlobalVal().toString());
    }

    @Test
    public void part2_testPartial() throws IOException {
        List<String> partialInput = Files.readAllLines(Path.of(PARTIAL_INPUT_PATH));
        OpInterpreter inp = new OpInterpreter(partialInput);
        inp.runUntilSucceed();
        assertEquals(8, inp.getGlobalVal());
        logger.info("part2_testPartial: Global value: " + inp.getGlobalVal());
    }

    @Test
    public void part2_testFull() throws IOException {
        List<String> input = Files.readAllLines(Path.of(INPUT_PATH));
        OpInterpreter inp = new OpInterpreter(input);
        inp.runUntilSucceed();
        logger.info("part2_testFull: Global value: " + inp.getGlobalVal());
    }
}
