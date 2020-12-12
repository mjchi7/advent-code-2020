package advent.code.day7;

import java.util.*;
import java.util.stream.Collectors;

public class RuleParser {

    private final String KEY_VAL_SEP = "contain";

    private final String END_NODE_CLUE = "no other bags";

    private final String VAL_SEP = ",";

    public Map<String, List<String>> getChildToParentsMap() {
        return childToParentsMap;
    }

    public void setChildToParentsMap(Map<String, List<String>> childToParentsMap) {
        this.childToParentsMap = childToParentsMap;
    }

    private Map<String, List<String>> childToParentsMap = new HashMap<>();

    public RuleParser() {
    }

    public void load(String rules) {
        List<String> rulesLines = Arrays.asList(rules.split("\\n"));
        for (String rule : rulesLines) {
            List<String> parentAndChild = Arrays
                    .asList(rule.split(KEY_VAL_SEP));
            String parent = parentAndChild.get(0).replaceAll("bag[s]+", "").strip();
            String dicedChild = parentAndChild.get(1);
            List<String> children = new ArrayList<>();
            // If parent has child, create the map
            if (!dicedChild.contains(END_NODE_CLUE)) {
                children = Arrays.stream(dicedChild.split(","))
                        .map(c -> c.replaceAll("[0-9]*", "").replaceAll("bag[s]?", "")
                                .replace(".", "").strip())
                        .collect(Collectors.toList());
            }
            loadMap(this.childToParentsMap, parent, children);
        }
    }

    // Load the info info our map
    // This will gives us a map, that map each bag to their direct parent
    private void loadMap(Map<String, List<String>> map, String parentBag,
                         List<String> childBag) {
        childBag.stream().forEach(cb -> {
            if (map.containsKey(cb)) {
                map.get(cb).add(parentBag);
            } else {
                map.put(cb, new ArrayList<>(Collections
                        .singletonList(parentBag)));
            }
        });
    }

    public List<String> getParents(String childName) {
        List<String> _parents = this.childToParentsMap.get(childName);
        List<String> parents;
        if (_parents == null || _parents.isEmpty()) {
            return Collections.emptyList();
        } else {
            parents = new ArrayList<>(_parents);
        }
        for (String parent : _parents) {
            parents.addAll(getParents(parent));
        }
        return parents;
    }
}
