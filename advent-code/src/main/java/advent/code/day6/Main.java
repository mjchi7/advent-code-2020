package advent.code.day6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        solutionOne();
        solutionTwo();
    }

    public static void solutionOne() throws Exception {
        String inp = Files.readString(Path.of("./src/main/java/advent/code/day6/resource/input.txt"));
        List<String> usersInp = Arrays.asList(inp.split("\\n\\n"));
        usersInp = usersInp.stream().map(s -> s.replace("\n", "").strip()).collect(Collectors
                .toList());
        logger.info(usersInp.toString());
        logger.info("Length of input: " + usersInp.size());
        List<String> uniqueInputs = usersInp.stream().map(Main::getUniqueSeq).collect(Collectors.toList());
        List<Integer> counts = uniqueInputs.stream().map(String::length).collect(Collectors.toList());
        logger.info(counts.toString());
        Integer sum = counts.stream().reduce(0, Integer::sum);
        logger.info("Sum : " + sum);
    }

    public static void solutionTwo() throws Exception {
        String inp = Files.readString(Path.of("./src/main/java/advent/code/day6/resource/input.txt"));
        List<String> usersInp = Arrays.asList(inp.split("\\n\\n"));
        List<String> intersectionGroup = usersInp.stream().map(Main::findCommon).collect(Collectors.toList());
        Integer sum = intersectionGroup.stream().mapToInt(String::length).reduce(0, Integer::sum);
        logger.info("Solution 2, Sum: " + sum);
    }

    public static String findCommon(String seq) {
        // Assume they are delimetered by "\n".
        List<String> inpGroups = Arrays.asList(seq.split("\\n"));
        List<HashSet<Character>> charGroups = new ArrayList<>();
        // CHALLENGES: Try to make the below code into streams of streams
        for (String inp : inpGroups) {
            charGroups.add(inp.chars().mapToObj(intStream -> (char) intStream).collect(Collectors.toCollection(HashSet::new)));
        }
        HashSet<Character> uniqueChar = charGroups.get(0);
        for (HashSet<Character> charGroup : charGroups) {
            uniqueChar.retainAll(charGroup);
        }
        return uniqueChar.stream().map(Object::toString).collect(Collectors
                .joining(""));
        // List<HashSet<Character>> setGroups = inpGroups.stream().map(String::chars).map(intStream -> intStream.mapToObj(intChar -> (char) intChar))
    }


    public static String getUniqueSeq(String seq) {
        StringBuilder unqSeq = new StringBuilder();
        for (int i = 0; i < seq.length(); i++) {
            if (unqSeq.indexOf(Character.toString(seq.charAt(i))) == -1){
                unqSeq.append(seq.charAt(i));
            }
        }
        return unqSeq.toString();
    }
}
