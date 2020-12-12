package advent.code.day7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        String rules = Files.readString(Path
                .of("./src/main/java/advent/code/day7/resource/input.txt"));
        RuleParser ruleParser = new RuleParser();
        ruleParser.load(rules);
        logger.info(ruleParser.getChildToParentsMap().toString());
        List<String> parents = ruleParser.getParents("shiny gold");
        HashSet<String> dedupedParents = new HashSet<>(parents);
        logger.info("Parents of shiny gold: " + parents.toString());
        logger.info("Number of parents of shiny gold: " + parents.size());
        logger.info("Number of dedupedParents of shiny gold: " + dedupedParents.size());
    }
}
