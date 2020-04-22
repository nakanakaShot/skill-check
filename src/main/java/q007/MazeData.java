package q007;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import q007.model.Coordinate;
import q007.model.MazeInputType;

class MazeData extends Maze {

    private final Map<Coordinate, MazeInputType> mapData;

    MazeData(int width, int height, Map<Coordinate, MazeInputType> mapData) {
        super(width, height);
        this.mapData = mapData;

        validate();
    }

    Coordinate findStartCoordinate() {
        return mapData.entrySet().stream()
            .filter(entry -> entry.getValue().isStart())
            .map(Entry::getKey)
            .findFirst().orElseThrow(RuntimeException::new);
    }

    Coordinate findEndCoordinate() {
        return mapData.entrySet().stream()
            .filter(entry -> entry.getValue().isEnd())
            .map(Entry::getKey)
            .findFirst().orElseThrow(RuntimeException::new);
    }

    Set<Coordinate> correctAllCoordinates() {
        return mapData.entrySet().stream()
            .sorted(Comparator.comparing(t -> t.getKey().flattenIndex(getWidth())))
            .map(Entry::getKey)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    void print() {
        AtomicInteger cnt = new AtomicInteger();
        getFlattenData().forEach(mazeInputType -> {
            System.out.print(mazeInputType);
            if (cnt.getAndIncrement() != 0 && cnt.get() % getWidth() == 0) {
                System.out.println();
            }
        });
        System.out.println();
    }

    private void validate() {
        assert (getWidth() * getHeight() == mapData.size());
    }

    private List<MazeInputType> getFlattenData() {
        return mapData.entrySet().stream()
            .sorted(Comparator.comparing(t -> t.getKey().flattenIndex(getWidth())))
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }
}
