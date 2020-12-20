package advent.code.day8;

import java.util.ArrayList;
import java.util.List;

public class OpInterpreter {

    private List<String> sequences;

    private List<Integer> executedLines = new ArrayList<>();

    private Integer globalVal = 0;

    private Integer currentPointer = 0;

    private boolean executedSuccessfully = false;

    private static final String INST_NO_OP = "nop";

    private static final String INST_ACC = "acc";

    private static final String INST_JMP = "jmp";

    public OpInterpreter(List<String> sequences) {
        this.sequences = sequences;
    }

    public void reset() {
        this.currentPointer = 0;
        this.globalVal = 0;
        this.executedLines = new ArrayList<>();
        this.executedSuccessfully = true;
    }

    public Integer getGlobalVal() {
        return globalVal;
    }

    public void setGlobalVal(Integer globalVal) {
        this.globalVal = globalVal;
    }

    public void run() {
        while (this.currentPointer < this.sequences.size()) {
            try {
                this.exec(this.sequences.get(this.currentPointer));
            } catch (ExecBeforeException ex ) {
                executedSuccessfully = false;
                break;
            }
        }
    }

    public void runUntilSucceed() {
        for (int i = 0; i < this.sequences.size(); i++ ) {
            String seq = this.sequences.get(i);
            String modSeq = new String(seq);
            if (seq.contains(INST_NO_OP)) {
                modSeq = seq.replace(INST_NO_OP, INST_JMP);
            } else if (seq.contains(INST_JMP)) {
                modSeq = seq.replace(INST_JMP, INST_NO_OP);
            }
            this.sequences.set(i, modSeq);
            reset();
            run();
            if (executedSuccessfully) {
                break;
            } else {
                this.sequences.set(i, seq);
            }
        }
    }

    public void exec(String instruction) throws ExecBeforeException {
        Instruction inst = Instruction.interpret(instruction);
        String ops = inst.getInstruction();
        this.isLineExecBefore();
        this.executedLines.add(this.currentPointer);
        switch (ops) {
            case INST_NO_OP:
                advancePointer(1);
                break;
            case INST_ACC:
                this.globalVal += inst.getVal();
                advancePointer(1);
                break;
            case INST_JMP:
                advancePointer(inst.getVal());
                break;
        }
    }

    private void isLineExecBefore() throws ExecBeforeException {
        if (this.executedLines.contains(this.currentPointer)) {
            throw new ExecBeforeException();
        }
    }

    private void advancePointer(Integer amount) {
        this.currentPointer += amount;
    }
}
