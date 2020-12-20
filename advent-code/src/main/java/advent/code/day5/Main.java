package advent.code.day5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        logger.info("The current execution path: " + System.getProperty("user.dir"));
        List<String> inps = Files.readAllLines(Path.of("./src/main/java/advent/code/day5/resource/input.partial.txt"));
        BoardingPassScanner scanner = new BoardingPassScanner();
        List<Integer> seatIds = new ArrayList<>();
        for (String inp : inps) {
            scanner.load(inp);
            seatIds.add(scanner.getSeatId());
        }
        Optional<Integer> largestSeatIdOpt = seatIds.stream().max((x, y) -> x - y);
        if (largestSeatIdOpt.isPresent()) {
            Integer largestSeatId = largestSeatIdOpt.get();
            System.out.println("Largest SeatID: " + largestSeatId);
        }
        seatIds.sort((x, y) -> x - y);
        logger.info(seatIds.toString());

        // Find out missing seat
        for (int i = 1; i < seatIds.size() - 1; i++) {
            if (seatIds.get(i) + 1 != seatIds.get(i + 1)) {
                logger.info("Found a skipping number: " + (seatIds.get(i) + 1));
                logger.info("The number prior: " + seatIds.get(i) + "\tThe number after: " + seatIds.get(i+1));
            }
        }
    }
}
