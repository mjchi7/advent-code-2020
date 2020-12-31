package advent.code.day12;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ShipWithWaypoint {

    //private INDArray direction = Nd4j.create(new double[]{1, 0}, new int[]{2, 1});

    // The direction is relative to y-axis.
    // Given the initial starting direction is to east, therefore the direction is 90 initially.
    //
    //        y axis (north)
    //          ^
    //          |
    //          |-+
    //          +-+-----> x axis (east)
    //
    // The idea is to rotate the translation matrix before adding them to the position matrix
    // For example, if we are facing north, move forward means y + 1.
    // But if we are facing east, move forward means x + 1.
    // To achieve this, we can make use of the rotation matrix.
    // see https://en.wikipedia.org/wiki/Rotation_matrix
    //
    private double direction = 90;

    // Position matrix, a 2 x 1 matrix.
    //  [[x]]
    //  [[y]]
    //
    private INDArray position = Nd4j.create(new double[]{0, 0}, new int[]{2, 1});

    // The position of the waypoint. Starts at 10 east, 1 north
    // Note: the position is always relative to the ship.
    private INDArray waypointPosition = Nd4j.create(new double[]{10, 1}, new int[]{2, 1});

    private final INDArray moveNorthMat = Nd4j.create(new double[]{0, 1}, new int[]{2, 1});

    private final INDArray moveSouthMat = Nd4j.create(new double[]{0, -1}, new int[]{2, 1});

    private final INDArray moveWestMat = Nd4j.create(new double[]{-1, 0}, new int[]{2, 1});

    private final INDArray moveEastMat = Nd4j.create(new double[]{1, 0}, new int[]{2, 1});

    private final List<Character> waypointTranslationCommand = Arrays.asList('N', 'S', 'E', 'W');

    private final List<Character> waypointRotationCommand = Arrays.asList('R', 'L');

    private int x;

    private int y;

    private static final Logger logger = LoggerFactory.getLogger(ShipWithWaypoint.class);

    // Rotate relative to ship.
    // Since waypoint is always [10, 1] relative to ship
    public void turnRight(double deg) {
        this.waypointPosition = rotateClockwise(this.waypointPosition, deg);
    }

    public void turnLeft(double deg) {
        this.waypointPosition = rotateClockwise(this.waypointPosition, -1 * deg);
    }

    /**
     * Function to rotate the given matrix clockwise by deg amount.
     * ship is currently facing
     * @param matrix the matrix to be rotated clockwise
     * @param deg the amount of degree to rotate the matrix by.
     *            (+ve value for clockwise, -ve value for counterclockwise)
     * @return the rotated matrix
     */
    private INDArray rotateClockwise(INDArray matrix, double deg) {
        double rad = Math.toRadians(deg);
        INDArray rotationMatrix = Nd4j.create(new double[]{
                Math.cos(rad), Math.sin(rad),
                -1 * Math.sin(rad), Math.cos(rad)
        }, new int[]{2, 2});
        // Round it up to remove the annoying left over infinitesimal value
        rotationMatrix = Transforms.round(rotationMatrix);
        return Nd4j.matmul(rotationMatrix, matrix);
    }

    public void moveWaypoint(char cmd, int unit) {
        INDArray translation = null;
        switch(cmd) {
            case 'N':
                translation = moveNorthMat.dup();
                break;
            case 'S':
                translation = moveSouthMat.dup();
                break;
            case 'E':
                translation = moveEastMat.dup();
                break;
            case 'W':
                translation = moveWestMat.dup();
                break;
            default:
                throw new RuntimeException("Invalid direction to move waypoint");
        }
        this.waypointPosition = this.waypointPosition.add(translation.muli(unit));
    }

    public void rotateWaypoint(char cmd, int deg) {
        if (cmd == 'R') {
            turnRight(deg);
        } else if (cmd == 'L') {
            turnLeft(deg);
        } else {
            throw new RuntimeException("Rotate command can only be 'R' or 'L'. Command '" + cmd + "' found instead");
        }
    }

    public void move(Command cmd) {
        int unit = cmd.getUnit();
        char command = cmd.getDirection();
        if (command == 'F') {
            moveShip(unit);
        } else if (waypointTranslationCommand.contains(command)) {
            moveWaypoint(command, unit);
        } else if (waypointRotationCommand.contains(command)) {
            rotateWaypoint(command, unit);
        } else {
            throw new RuntimeException("Illegal command found: '" + command + "'");
        }
    }

    public void moveShip(int unit) {
        INDArray translationMatrix = this.waypointPosition.dup();
        translationMatrix.muli(unit);
        this.position.addi(translationMatrix);
    }

    /**
     * Function that returns the manhattan distance.
     * Note: Manhattan distance = abs(y value) + abs(x value)
     * @return
     */
    public int getManhattanDistance() {
        return Transforms.abs(this.position).sumNumber().intValue();
    }

    public void printPosition() {
        logger.info("Position: " + position.toString());
    }

    public void printDirection() {
        logger.info("Direction: " + direction);
    }
    public void printPositionAndDirection() {
        printPosition();
        printDirection();
    }

}
