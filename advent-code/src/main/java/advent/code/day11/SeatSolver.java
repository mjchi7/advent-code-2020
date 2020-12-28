package advent.code.day11;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SeatSolver {

    private static final String EMPTY = "L";

    private static final String OCCUPIED = "#";

    private static final String FLOOR = ".";

    private static final String INVALID_COORD = "X";

    private static final Logger logger = LoggerFactory
            .getLogger(SeatSolver.class);

    public String[][] solve(String[][] map) {
        String[][] solvedMap = deepClone2D(map);
        String[][] memMap = deepClone2D(map);
        do {
            List<Pair<String[], String[]>> zipped = zip2D(solvedMap, memMap);
            System.out.println("Still in transient.");
            System.out.println("SolvedMap: \tMemMap:");
            zipped.forEach(pair -> System.out
                    .println(String.join("", pair.getValue0()) + "\t" + String
                            .join("", pair.getValue1())));
            memMap = deepClone2D(solvedMap);
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    solvedMap[y][x] = getNextState(memMap, new Coordinate(x, y));
                }
            }
            if (Arrays.deepEquals(memMap, solvedMap)) {
                System.out.println("Steady state achieved - solvedMap: ");
                Arrays.stream(solvedMap).forEach(stringArray -> System.out
                        .println(String.join("", stringArray)));
                break;
            }
        } while (!Arrays.deepEquals(memMap, solvedMap));
        return solvedMap;
    }

    public String[][] solve2(String[][] map) {
        String[][] solvedMap = deepClone2D(map);
        String[][] memMap = deepClone2D(map);
        do {
            List<Pair<String[], String[]>> zipped = zip2D(solvedMap, memMap);
            System.out.println("Still in transient.");
            System.out.println("SolvedMap: \tMemMap:");
            zipped.forEach(pair -> System.out
                    .println(String.join("", pair.getValue0()) + "\t" + String
                            .join("", pair.getValue1())));
            memMap = deepClone2D(solvedMap);
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    solvedMap[y][x] = getNextStateFirst(memMap,
                            new Coordinate(x, y));
                }
            }
            if (Arrays.deepEquals(memMap, solvedMap)) {
                System.out.println("Steady state achieved - solvedMap: ");
                Arrays.stream(solvedMap).forEach(stringArray -> System.out
                        .println(String.join("", stringArray)));
                break;
            }
        } while (!Arrays.deepEquals(memMap, solvedMap));
        return solvedMap;
    }

    private String getNextStateFirst(String[][] map, Coordinate currentPos) {
        Pair<Integer, Integer> xBound = new Pair<>(0, map[0].length);
        Pair<Integer, Integer> yBound = new Pair<>(0, map.length);

        // Get Symbols from all the direction
        List<Coordinate> coords = Arrays.asList(
                new Coordinate(-1, 0), // left
                new Coordinate(-1, 1),  // lef top
                new Coordinate(0, 1),  // top
                new Coordinate(1, 1),  // right top
                new Coordinate(1, 0),  // right
                new Coordinate(1, -1), // right bottom
                new Coordinate(0, -1),  // bottom
                new Coordinate(-1, -1)); // left bottom
        List<List<String>> symbolsInDirection = coords.stream()
                .map(coord -> getSymbolInDirection(map, currentPos, coord, -1L))
                .collect(Collectors.toList());
        // For each directions, find out the first seat. If there isn't any
        // seat in
        //    a particular direction, just return floor.
        // logger.info("SymbolsInDirection: " + symbolsInDirection.toString());
        List<String> seats = symbolsInDirection.stream().map(s -> s.stream()
                .filter(inner -> inner.equals(EMPTY) || inner.equals(OCCUPIED))
                .findFirst().orElseGet(() -> FLOOR))
                .collect(Collectors.toList());
        // logger.info("Seats: " + seats.toString());
        Map<String, Long> seatStateCounts = seats.stream().collect(Collectors
                .groupingBy(Function.identity(), Collectors.counting()));
        // logger.info("Seats Count: " + seatStateCounts.toString());
        // Apply same rules here about the seat
        String currentSeat = map[currentPos.getY()][currentPos.getX()];
        String res = "";
        if (currentSeat.equals(EMPTY) && !seats.contains(OCCUPIED)) {
            res = OCCUPIED;
        } else if (currentSeat.equals(OCCUPIED) && seatStateCounts
                .getOrDefault(OCCUPIED, 0L) >= 5) {
            res = EMPTY;
        } else {
            res = currentSeat;
        }
        return res;
    }

    public long countOccupied(String[][] map) {
        return Arrays.stream(map).flatMap(Arrays::stream)
                .filter(s -> s.equals(OCCUPIED)).count();
    }

    private List<Pair<String[], String[]>> zip2D(String[][] arr1,
                                                 String[][] arr2) {
        int shortestY = Math.min(arr1.length, arr2.length);
        List<Pair<String[], String[]>> zipped = new ArrayList<>();
        for (int y = 0; y < shortestY; y++) {
            zipped.add(new Pair<String[], String[]>(arr1[y], arr2[y]));
        }
        return zipped;
    }

    private String[][] deepClone2D(String[][] ori) {
        String[][] cloned = new String[ori.length][ori[0].length];
        for (int y = 0; y < ori.length; y++) {
            System.arraycopy(ori[y], 0, cloned[y], 0, ori[0].length);
        }
        return cloned;
    }

    /**
     * Function that return a list of symbol on a particular direction based on
     * translation matrix
     */
    private List<String> getSymbolInDirection(String[][] map,
                                              Coordinate currentPos,
                                              Coordinate translationMatrix,
                                              Long steps) {
        Pair<Integer, Integer> xBound = new Pair<>(0, map[0].length);
        Pair<Integer, Integer> yBound = new Pair<>(0, map.length);
        List<String> symbols = new ArrayList<>();
        Coordinate ptr = new Coordinate(currentPos.getX(), currentPos.getY());
        // Termination Condition: Pointer is out of bound
        long currentStep = 0;
        while (ptrWithinBound(ptr, xBound, yBound)) {
            if (currentStep > steps && steps != -1) {
                break;
            }
            currentStep += 1;
            if (!ptr.equals(currentPos)) {
                symbols.add(map[ptr.getY()][ptr.getX()]);
            }
            // Move the pointer
            ptr.move(translationMatrix);
        }
        return symbols;
    }

    private boolean ptrWithinBound(Coordinate ptr,
                                   Pair<Integer, Integer> xBound,
                                   Pair<Integer, Integer> yBound) {
        boolean xWithinBound = ptr.getX() < xBound.getValue1() && ptr
                .getX() >= xBound
                .getValue0();
        //                 xPtr less than ceiling bound OR xPtr more than
        //                 floor bound
        boolean yWithinBound = ptr.getY() < yBound.getValue1() && ptr
                .getY() >= yBound
                .getValue0();
        return xWithinBound && yWithinBound;
    }

    private String getNextState(String[][] map, Coordinate currentPos) {
        Pair<Integer, Integer> xBound = new Pair<>(0, map[0].length);
        Pair<Integer, Integer> yBound = new Pair<>(0, map.length);

        // Get Symbols from all the direction
        List<Coordinate> coords = Arrays.asList(
                new Coordinate(-1, 0), // left
                new Coordinate(-1, 1),  // lef top
                new Coordinate(0, 1),  // top
                new Coordinate(1, 1),  // right top
                new Coordinate(1, 0),  // right
                new Coordinate(1, -1), // right bottom
                new Coordinate(0, -1),  // bottom
                new Coordinate(-1, -1)); // left bottom
        List<List<String>> symbolsInDirection = coords.stream()
                .map(coord -> getSymbolInDirection(map, currentPos, coord, 1L))
                .collect(Collectors.toList());
        List<String> seats = symbolsInDirection.stream()
                .map(s -> s.stream().findFirst().orElse(FLOOR))
                .collect(Collectors.toList());
        // logger.info("Seats: " + seats.toString());
        Map<String, Long> seatStateCounts = seats.stream().collect(Collectors
                .groupingBy(Function.identity(), Collectors.counting()));
        // logger.info("Seats Count: " + seatStateCounts.toString());
        // Apply same rules here about the seat
        String currentSeat = map[currentPos.getY()][currentPos.getX()];
        String res = "";
        if (currentSeat.equals(EMPTY) && !seats.contains(OCCUPIED)) {
            res = OCCUPIED;
        } else if (currentSeat.equals(OCCUPIED) && seatStateCounts
                .getOrDefault(OCCUPIED, 0L) >= 4) {
            res = EMPTY;
        } else {
            res = currentSeat;
        }
        return res;
    }
}
