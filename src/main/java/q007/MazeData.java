package q007;

import java.util.Map;
import java.util.Map.Entry;
import q007.model.Coordinate;
import q007.model.MazeInputType;

class MazeData {

    private final Map<Coordinate, MazeInputType> mapData;

    MazeData(Map<Coordinate, MazeInputType> mapData) {
        this.mapData = mapData;
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
}
