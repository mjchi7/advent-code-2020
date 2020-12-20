package advent.code.day7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        String rules = Files.readString(Path
                .of("./src/main/java/advent/code/day7/resource/input.partial.txt"));
        RuleParser ruleParser = new RuleParser();
        ruleParser.load(rules);
        logger.info(ruleParser.getChildToParentsMap().toString());
        List<String> parents = ruleParser.getParents("shiny gold");
        HashSet<String> dedupedParents = new HashSet<>(parents);
        logger.info("Parents of shiny gold: " + parents.toString());
        logger.info("Number of parents of shiny gold: " + parents.size());
        logger.info("Number of dedupedParents of shiny gold: " + dedupedParents.size());

        logger.info(ruleParser.getParentToChildMap().toString());
        List<ChildBag> children = ruleParser.getChildren("shiny gold");
        HashSet<ChildBag> dedupedChildren = new HashSet<>(children);
        logger.info("Number of children of shiny gold: " + sumChild(ruleParser.getParentToChildMap(), "shiny gold"));
    }

    public static int sumChild(Map<String, List<ChildBag>> map, String parentName) {
        List<ChildBag> children = map.get(parentName);
        if (children == null || children.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (ChildBag childBag : children) {
            sum += childBag.getCapacity() + (childBag.getCapacity() * sumChild(map, childBag.getName()));
        }
        return sum;
    }
}
