package advent.code.day9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class XMASParser {

    private Integer preamble;

    private Deque<Long> memory;

    private List<Long> sequence;

    private static final Logger logger = LoggerFactory.getLogger(XMASParser.class);

    public XMASParser(Integer preamble) {
        this.preamble = preamble;
        this.memory = new ArrayDeque<>();
    }

    public void loadSequence(List<Long> seq) {
        this.sequence = seq;
    }

    public Long findFirstInvalid() {
        for (Long val : sequence) {
            if (memory.size() >= preamble) {
                if (!validElem(val)) {
                    return val;
                }
                this.memory.removeLast();
            }
            this.memory.push(val);
        }
        throw new NoInvalidValueFoundError();
    }

    private boolean validElem(Long elem) {
        for (Long val : memory) {
            Long complement = Math.abs(val - elem);
            if (complement.equals(val)) {
                continue;
            }
            if (memory.contains(complement)) {
                return true;
            }
        }
        return false;
    }

    public List<Long> findContiguousSet(Long invalidNumber) {
        int seqSize = sequence.size();
        int fromIndex = 0;
        int toIndex = 0;
        for (int i = 0; i < seqSize; i++) {
            long complement = Math.abs(invalidNumber - sequence.get(i));
            // logger.debug("Complement: " + complement);
            int innerPtr = i + 1;
            while (innerPtr < seqSize && complement >= sequence.get(innerPtr) && complement != 0) {
                complement = Math.abs(complement - sequence.get(innerPtr));
                // logger.debug("Current innerPtr: " + innerPtr + " \tCurrent i: " + i + " \tCurrent Complement: " + complement);
                innerPtr = innerPtr + 1;
            }
            if (complement == 0) {
                // Found our contiguous set
                fromIndex = i;
                toIndex = innerPtr;
                break;
            }
        }
        return sequence.subList(fromIndex, toIndex);
    }
}
