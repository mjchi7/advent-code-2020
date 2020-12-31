package advent.code.day12;

public class Command {

    private char direction;

    private int unit;

    public Command() {}

    public Command(char direction, int unit) {
        this.direction = direction;
        this.unit = unit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Command{" +
                "direction=" + direction +
                ", unit=" + unit +
                '}';
    }
}
