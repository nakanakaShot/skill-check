package q007;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import q007.model.Coordinate;

class MazeMapper extends Maze {

    private static final int UNEXPLORED_NUMBER = -1;
    private final Map<Coordinate, Integer> mapMapper;

    private MazeMapper(int width, int height, Map<Coordinate, Integer> mapMapper) {
        super(width, height);
        this.mapMapper = mapMapper;

        validate();
    }

    static MazeMapper from(MazeData data) {
        Map<Coordinate, Integer> map = new HashMap<>();

        data.correctAllCoordinates()
            .forEach(coordinate ->
                map.put(coordinate, UNEXPLORED_NUMBER));

        return new MazeMapper(
            data.getWidth(),
            data.getHeight(),
            map
        );
    }

    void print() {
        AtomicInteger cnt = new AtomicInteger();
        getFlattenData().forEach(cost -> {

            if (cost == UNEXPLORED_NUMBER) {
                System.out.print(" - ");
            } else {
                System.out.print(String.format(" %02d", cost));
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

    private List<Integer> getFlattenData() {
        return mapMapper.entrySet().stream()
            .sorted(Comparator.comparing(t -> t.getKey().flattenIndex(getWidth())))
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }
}
