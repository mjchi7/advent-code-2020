package advent.code.day8;

import java.util.List;

public class Instruction {

    private String instruction;

    private Integer val;

    private Instruction() {
        // prevent construction through "new"
    }

    // Method to interpret a raw instruction and return instruction object.
    public static Instruction interpret(String rawInst) {
        Instruction inst = new Instruction();
        String[] dicedInst = rawInst.split(" ");
        inst.setInstruction(dicedInst[0]);
        inst.setVal(Integer.valueOf(dicedInst[1]));
        return inst;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
