package advent.code.day10;

public class JoltAnswer {

    private int differencesOfOne;

    private int differencesOfTwo;

    private int differencesOfThree;

    public void incrementDifferencesOfOne() {
        this.differencesOfOne += 1;
    }

    public void incrementDifferencesOfTwo() {
        this.differencesOfTwo += 1;
    }

    public void incrementDifferencesOfThree() {
        this.differencesOfThree += 1;
    }

    public int getDifferencesOfOne() {
        return differencesOfOne;
    }

    public void setDifferencesOfOne(int differencesOfOne) {
        this.differencesOfOne = differencesOfOne;
    }

    public int getDifferencesOfTwo() {
        return differencesOfTwo;
    }

    public void setDifferencesOfTwo(int differencesOfTwo) {
        this.differencesOfTwo = differencesOfTwo;
    }

    public int getDifferencesOfThree() {
        return differencesOfThree;
    }

    public void setDifferencesOfThree(int differencesOfThree) {
        this.differencesOfThree = differencesOfThree;
    }
}
