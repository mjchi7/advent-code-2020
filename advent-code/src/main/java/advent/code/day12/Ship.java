package advent.code.day12;

import org.javatuples.Pair;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.transforms.same.Round;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.INDArrayIndex;
import org.nd4j.linalg.indexing.NDArrayIndex;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.nd4j.linalg.util.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ship {

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

    private final INDArray moveNorthMat = Nd4j.create(new double[]{0, 1}, new int[]{2, 1});

    private final INDArray moveSouthMat = Nd4j.create(new double[]{0, -1}, new int[]{2, 1});

    private final INDArray moveWestMat = Nd4j.create(new double[]{-1, 0}, new int[]{2, 1});

    private final INDArray moveEastMat = Nd4j.create(new double[]{1, 0}, new int[]{2, 1});

    private int x;

    private int y;

    private static final Logger logger = LoggerFactory.getLogger(Ship.class);

    public void turnRight(double deg) {
        direction = ( direction + deg ) % 360;
    }

    public void turnLeft(double deg) {
        direction = ( direction - deg ) % 360;
    }

    /**
     * Function to rotate the translationMatrix such that it aligns with where the
     * ship is currently facing
     * @param translationMatrix the translation matrix
     * @return the aligned translation matrix
     */
    private INDArray alignTranslationMatrix(INDArray translationMatrix) {
        double rad = Math.toRadians(this.direction);
        INDArray rotationMatrix = Nd4j.create(new double[]{
                Math.cos(rad), Math.sin(rad),
                -1 * Math.sin(rad), Math.cos(rad)
        }, new int[]{2, 2});
        rotationMatrix = Transforms.round(rotationMatrix);
        logger.info("rotationMatrix: " + rotationMatrix);
        logger.info("translationMatrix: " + translationMatrix);
        return Nd4j.matmul(rotationMatrix, translationMatrix);
    }

    public void move(Command cmd) {
        int unit = cmd.getUnit();
        char direction = cmd.getDirection();
        switch (direction) {
            case 'F':
                moveForward(unit);
                break;
            case 'R':
                turnRight(unit);
                break;
            case 'L':
                turnLeft(unit);
                break;
            case 'N':
                move(moveNorthMat, unit);
                break;
            case 'S':
                move(moveSouthMat, unit);
                break;
            case 'E':
                move(moveEastMat, unit);
                break;
            case 'W':
                move(moveWestMat, unit);
                break;
            default:
                throw new RuntimeException("Invalid command: '" + direction + "'");
        }
    }

    public void moveForward(int unit) {
        logger.info("Currently facing: " + direction);
        INDArray alignedTranslationMatrix = alignTranslationMatrix(moveNorthMat);
        this.move(alignedTranslationMatrix, unit);
    }

    public void move(INDArray translationMatrix, int unit) {
        // Note: Operations on array (like .muli()) are modifying array in place.
        //      Therefore we'll need to create a copy before applying.
        INDArray _tmp = translationMatrix.dup();
        logger.info("Translation matrix: " + _tmp);
        logger.info("Movement unit: " + unit);
        position = position.add(_tmp.muli(unit));
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
