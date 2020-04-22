package q007;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import q007.model.Coordinate;
import q007.model.MazeMappingStatus;

class MazeMapper extends Maze {

    private final Map<Coordinate, MazeMappingStatus> mapMapper;

    private MazeMapper(int width, int height, Map<Coordinate, MazeMappingStatus> mapMapper) {
        super(width, height);
        this.mapMapper = mapMapper;

        validate();
    }

    static MazeMapper from(MazeData data) {
        Map<Coordinate, MazeMappingStatus> map = new HashMap<>();

        data.correctAllCoordinates()
            .forEach(coordinate ->
                map.put(coordinate, MazeMappingStatus.init()));

        return new MazeMapper(
            data.getWidth(),
            data.getHeight(),
            map
        );
    }

    Optional<Coordinate> findMinimumCostCoordinate() {
        return mapMapper.entrySet().stream()
            .filter(entry -> {
                MazeMappingStatus status = entry.getValue();
                return status.isExplored() && status.isUnfixed();
            }).sorted(Comparator.comparing(t -> t.getValue().getCost()))
            .map(Entry::getKey)
            .findFirst();
    }

    int getCost(Coordinate coordinate) {
        return get(coordinate).getCost();
    }

    boolean isUnexplored(Coordinate coordinate) {
        return get(coordinate).isUnexplored();
    }

    boolean isUnfixed(Coordinate coordinate) {
        return get(coordinate).isUnfixed();
    }

    void mapping(Coordinate coordinate, int cost) {
        get(coordinate).mapping(cost);
    }

    void fix(Coordinate coordinate) {
        get(coordinate).fixThis();
    }

    void print() {
        AtomicInteger cnt = new AtomicInteger();
        getFlattenData().forEach(status -> {

            if (status.isUnexplored()) {
                System.out.print(" - ");
            } else {
                System.out.print(String.format(" %02d", status.getCost()));
            }

            if (cnt.getAndIncrement() != 0 && cnt.get() % getWidth() == 0) {
                System.out.println();
            }
        });

        System.out.println();
    }

    private List<MazeMappingStatus> getFlattenData() {
        return mapMapper.entrySet().stream()
            .sorted(Comparator.comparing(t -> t.getKey().flattenIndex(getWidth())))
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }

    private MazeMappingStatus get(Coordinate coordinate) {
        return mapMapper.entrySet().stream()
            .filter(entry ->
                entry.getKey().equals(coordinate))
            .map(Entry::getValue)
            .findFirst().orElseThrow(RuntimeException::new);
    }

    private void validate() {
        assert (getWidth() * getHeight() == mapMapper.size());
    }
}
