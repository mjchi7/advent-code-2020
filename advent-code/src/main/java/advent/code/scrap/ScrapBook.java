package advent.code.scrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ScrapBook {

    static Logger logger = LoggerFactory.getLogger(ScrapBook.class);

    public static void main(String[] args) {
        String str1 = "ABCDE";
        String str2 = "DEFGH";
        HashSet<Character> setA = str1.chars()
                .mapToObj(charInt -> (char) charInt)
                .collect(Collectors.toCollection(HashSet::new));
        HashSet<Character> setB = str2.chars()
                .mapToObj(charInt -> (char) charInt)
                .collect(Collectors.toCollection(HashSet::new));
        setA.retainAll(setB);
        logger.info("Intersection of str1: " + str1 + "\tand str2: " + str2 + "\t=" + setA);

    }
}
