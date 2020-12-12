import advent.code.day6.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testUniqueFunc() {
        String unq = Main.getUniqueSeq("AAABBBCCCDDD");
        assertEquals("ABCD", unq);
    }
}
