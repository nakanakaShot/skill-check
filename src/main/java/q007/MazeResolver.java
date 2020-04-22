package q007;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import q007.model.Coordinate;
import q007.model.MazeResult;

class MazeResolver {

    private static final int INITIAL_COST = 0;
    private static final int INCREMENTAL_COST = 1;

    static MazeResult resolve(MazeData mazeData, MazeMapper mazeMapper) {
        Optional<Coordinate> minCostCoordinate;
        List<Coordinate> needToExploreList;
        int minCost;

        init(mazeData, mazeMapper);

        while (true) {
            minCostCoordinate = mazeMapper.findMinimumCostCoordinate();
            if (!minCostCoordinate.isPresent()) {
                break;
            }

            mazeMapper.fix(minCostCoordinate.get());

            minCost = mazeMapper.getCost(minCostCoordinate.get());
            int nextCost = minCost + INCREMENTAL_COST;

            needToExploreList = correctNeedToExplore(mazeData, mazeMapper, minCostCoordinate.get());

            needToExploreList.stream()
                .filter(target -> mazeMapper.isUnexplored(target)
                    || nextCost < mazeMapper.getCost(target))
                .forEach(coordinate -> mazeMapper.mapping(coordinate, nextCost));

        }

        Coordinate end = mazeData.findEndCoordinate();
        int steps = mazeMapper.getCost(end);

        return new MazeResult(steps);
    }

    private static void init(MazeData mazeData, MazeMapper mazeMapper) {
        Coordinate start = mazeData.findStartCoordinate();
        mazeMapper.mapping(start, INITIAL_COST);
    }

    private static List<Coordinate> correctNeedToExplore(MazeData mazeData, MazeMapper mazeMapper,
        Coordinate from) {
        Coordinate up = from.up(INCREMENTAL_COST);
        Coordinate down = from.down(INCREMENTAL_COST);
        Coordinate left = from.left(INCREMENTAL_COST);
        Coordinate right = from.right(INCREMENTAL_COST);

        return Stream.of(up, down, left, right)
            .filter(coordinate -> mazeData.canMove(coordinate) && mazeMapper.isUnfixed(coordinate))
            .collect(Collectors.toList());
    }

}
