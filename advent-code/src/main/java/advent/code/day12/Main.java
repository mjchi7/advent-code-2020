package advent.code.day12;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String PARTIAL_PATH = "src/main/java/advent/code" +
            "/day12/resource/input.partial.txt";

    private static final String FULL_PATH = "src/main/java/advent/code" +
            "/day12/resource/input.txt";

    public static void main(String[] args) throws Exception {
        logger.info("Part 1, partial");
        // part1_partial();
        logger.info("Part 1, full");
        // part1_full();
        // logger.info("Part 2, partial");
        // part2_partial();
        logger.info("Part 2, full");
        part2_full();
    }

    public static void part1_partial() throws Exception {
        Ship ship = new Ship();
        List<String> rawInp = Files.readAllLines(Path.of(PARTIAL_PATH));
        List<Command> commands = loadCommand(rawInp);
        ship.printPositionAndDirection();
        commands.forEach(c -> {
            ship.move(c);
            logger.info("Command: " + c);
            ship.printPositionAndDirection();
        });
        logger.info("Ship's manhattan distance from origin: " + ship.getManhattanDistance());
    }

    public static void part1_full() throws Exception {
        Ship ship = new Ship();
        List<String> rawInp = Files.readAllLines(Path.of(FULL_PATH));
        List<Command> commands = loadCommand(rawInp);
        ship.printPositionAndDirection();
        commands.forEach(c -> {
            ship.move(c);
            logger.info("Command: " + c);
            ship.printPositionAndDirection();
        });
        logger.info("Ship's manhattan distance from origin: " + ship.getManhattanDistance());
    }

    public static void part2_partial() throws Exception {
        ShipWithWaypoint ship = new ShipWithWaypoint();
        List<String> rawInp = Files.readAllLines(Path.of(PARTIAL_PATH));
        List<Command> commands = loadCommand(rawInp);
        ship.printPositionAndDirection();
        commands.forEach(c -> {
            ship.move(c);
            logger.info("Command: " + c);
            ship.printPositionAndDirection();
        });
        logger.info("Ship's manhattan distance from origin: " + ship.getManhattanDistance());
    }

    public static void part2_full() throws Exception {
        ShipWithWaypoint ship = new ShipWithWaypoint();
        List<String> rawInp = Files.readAllLines(Path.of(FULL_PATH));
        List<Command> commands = loadCommand(rawInp);
        ship.printPositionAndDirection();
        commands.forEach(c -> {
            ship.move(c);
            logger.info("Command: " + c);
            ship.printPositionAndDirection();
        });
        logger.info("Ship's manhattan distance from origin: " + ship.getManhattanDistance());
    }

    public static List<Command> loadCommand(List<String> rawInp) {
        return rawInp.stream().map(raw -> {
            char direction = raw.substring(0, 1).charAt(0);
            int unit = Integer.parseInt(raw.substring(1));
            return new Command(direction, unit);
        }).collect(Collectors.toList());
    }
}
