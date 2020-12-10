package advent.code.day5;


public class BoardingPassScanner {

    private static final int TOTAL_ROWS = 128;

    private static final int TOTAL_COL = 8;

    private String seq;

    private String rowSeq;

    private String colSeq;

    private int seatId;

    private int row;

    private int column;

    public void load(String seq) {
        this.seq = seq;
        this.rowSeq = seq.substring(0, 7);
        this.colSeq = seq.substring(7);
        this.process();
    }

    public int getSeatId() {
        return this.seatId;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    private void process() {
        this.row = binarySpacePartition(TOTAL_ROWS, this.rowSeq, 'B', 'F');
        this.column = binarySpacePartition(TOTAL_COL, this.colSeq, 'R', 'L');
        this.seatId = (row * 8) + column;
    }

    private int binarySpacePartition(int total, String seq, char upperChar, char lowerChar) {
        int lower = 0;
        int higher = total - 1;
        for (int i = 0; i < seq.length() - 1; i++) {
            int magnitude = total / ( (int) Math.pow(2, i + 1) );
            if (seq.charAt(i) == lowerChar) {
                higher = higher - magnitude;
            } else if (seq.charAt(i) == upperChar) {
                lower = lower + magnitude;
            }
        }
        if (seq.charAt(seq.length() - 1) == lowerChar) {
            return lower;
        } else {
            return higher;
        }
    }

}
