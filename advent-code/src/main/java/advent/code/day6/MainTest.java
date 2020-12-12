package advent.code.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testUniqueFunc(String seq) {
        String unq = Main.getUniqueSeq("AAABBBCCCDDD");
        assertEquals("ABCD", unq);
    }

}
