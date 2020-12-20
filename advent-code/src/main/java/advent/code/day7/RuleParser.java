package advent.code.day7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RuleParser {

    private static final Logger logger = LoggerFactory.getLogger(RuleParser.class);

    private final String KEY_VAL_SEP = "contain";

    private final String END_NODE_CLUE = "no other bags";

    private final String VAL_SEP = ",";

    private Map<String, List<String>> childToParentsMap = new HashMap<>();

    private Map<String, List<ChildBag>> parentToChildMap = new HashMap<>();

    public Map<String, List<ChildBag>> getParentToChildMap() {
        return parentToChildMap;
    }

    public void setParentToChildMap(Map<String, List<ChildBag>> parentToChildMap) {
        this.parentToChildMap = parentToChildMap;
    }

    public Map<String, List<String>> getChildToParentsMap() {
        return childToParentsMap;
    }

    public void setChildToParentsMap(Map<String, List<String>> childToParentsMap) {
        this.childToParentsMap = childToParentsMap;
    }

    public RuleParser() {
    }

    public void load(String rules) {
        List<String> rulesLines = Arrays.asList(rules.split("\\n"));
        for (String rule : rulesLines) {
            List<String> parentAndChild = Arrays
                    .asList(rule.split(KEY_VAL_SEP));
            String parent = parentAndChild.get(0).replaceAll("bag[s]+", "")
                    .strip();
            String dicedChild = parentAndChild.get(1);
            List<ChildBag> children = new ArrayList<>();
            // If parent has child, create the map
            if (!dicedChild.contains(END_NODE_CLUE)) {
                /**
                children = Arrays.stream(dicedChild.split(","))
                        .map(c -> c.replaceAll("[0-9]*", "")
                                .replaceAll("bag[s]?", "")
                                .replace(".", "").strip())
                        .collect(Collectors.toList());
                 **/
                Pattern numberGroup = Pattern.compile("([0-9]*)");
                children = Arrays.stream(dicedChild.split(","))
                        .map(child -> {
                            String _pChild = child.replaceAll("bag[s]?", "").replace(".", "").strip();
                            Matcher matcher = numberGroup.matcher(_pChild);
                            String cap = null;
                            if (matcher.find()) {
                                cap = matcher.group(0);
                            } else {
                                throw new RuntimeException("No capacity found for the following rule: " + _pChild);
                            }
                            String name = _pChild.replaceAll("[0-9]*", "").strip();
                            return new ChildBag(Integer.valueOf(cap), name);
                        }).collect(Collectors.toList());
            }
            loadChild2ParentMap(this.childToParentsMap, parent, children.stream().map(ChildBag::getName).collect(Collectors.toList()));
            loadParent2ChildMap(this.parentToChildMap, parent, children);
        }
    }

    private void loadParent2ChildMap(Map<String, List<ChildBag>> map,
                                     String parentBag, List<ChildBag> childBags) {
        if (map.containsKey(parentBag)) {
            map.get(parentBag).addAll(childBags);
        } else {
            map.put(parentBag, childBags);
        }
    }

    // Load the info info our map
    // This will gives us a map, that map each bag to their direct parent
    private void loadChild2ParentMap(Map<String, List<String>> map,
                                     String parentBag,
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

    public List<ChildBag> getChildren(String parentName) {
        List<ChildBag> _children = this.parentToChildMap.get(parentName);
        List<ChildBag> children;
        if (_children == null || _children.isEmpty()) {
            return Collections.emptyList();
        } else {
            children = new ArrayList<>(_children);
        }
        for (ChildBag child : _children) {
            children.addAll(getChildren(child.getName()));
        }
        return children;
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
