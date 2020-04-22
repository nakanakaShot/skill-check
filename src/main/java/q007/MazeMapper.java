package q007;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

    private void validate() {
        assert (getWidth() * getHeight() == mapMapper.size());
    }

    private List<MazeMappingStatus> getFlattenData() {
        return mapMapper.entrySet().stream()
            .sorted(Comparator.comparing(t -> t.getKey().flattenIndex(getWidth())))
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }
}
