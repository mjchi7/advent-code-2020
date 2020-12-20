package advent.code.day7;

public class ChildBag {

    private Integer capacity;

    private String name;

    public ChildBag(Integer capacity, String name) {
        this.capacity = capacity;
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
