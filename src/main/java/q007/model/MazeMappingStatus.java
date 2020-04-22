package q007.model;

public class MazeMappingStatus {

    private static final int UNEXPLORED_NUMBER = -1;

    private int cost;
    private boolean fixed;

    private MazeMappingStatus(int cost, boolean fixed) {
        this.cost = cost;
        this.fixed = fixed;
    }

    public static MazeMappingStatus init() {
        return new MazeMappingStatus(
            UNEXPLORED_NUMBER, false
        );
    }

    public Integer getCost() {
        return cost;
    }

    public boolean isExplored() {
        return !isUnexplored();
    }

    public boolean isUnexplored() {
        return cost == UNEXPLORED_NUMBER;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean isUnfixed() {
        return !isFixed();
    }

    public void mapping(int newCost) {
        this.cost = newCost;
    }

    public void fixThis() {
        this.fixed = true;
    }
}
