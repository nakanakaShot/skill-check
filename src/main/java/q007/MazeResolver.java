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

    /**
     * <pre>
     * ダイクストラ法を用いて迷路を探索
     * 参考： https://nw.tsuda.ac.jp/lec/dijkstra/
     * </pre>
     *
     * @param mazeData 迷路データ
     * @return 探索結果
     */
    static MazeResult resolve(MazeData mazeData) {
        // ダイクストラ法　手順1：　迷路データから探索可能なマスを取得し、迷路マッパを作成 -1で埋める
        MazeMapper mazeMapper = MazeMapper.from(mazeData);

        Optional<Coordinate> minCostCoordinate;
        List<Coordinate> needToExploreList;
        int minCost;

        // ダイクストラ法　手順2：　開始地点をコスト0でマッピング
        init(mazeData, mazeMapper);

        // ダイクストラ法　手順3：　未確定のマスがなくなるまで繰り返す
        while (true) {

            // ダイクストラ法　手順3-a：　未確定のマスの中で最もコストが小さい頂点を取得する
            minCostCoordinate = mazeMapper.findMinimumCostCoordinate();
            if (!minCostCoordinate.isPresent()) {
                break;
            }
            // ダイクストラ法　手順3-b：　未確定のマスの中で最もコストが小さい頂点を確定させる
            mazeMapper.fix(minCostCoordinate.get());

            // ダイクストラ法　手順3-c：　隣接するマスに移動するコストを加算
            minCost = mazeMapper.getCost(minCostCoordinate.get());
            int nextCost = minCost + INCREMENTAL_COST;

            // ダイクストラ法　手順4：　上下左右に隣接するマスから以下の条件を満たすマスを取得する
            // ・そのマスに移動可能
            // ・未確定
            needToExploreList = correctNeedToExplore(mazeData, mazeMapper, minCostCoordinate.get());

            // ダイクストラ法　手順5：　以下の条件のいずれかを満たすマスのコストを更新する
            // ・完全に未探索(-1)
            // ・最小コスト + 移動コスト の方が 元々のコストより小さい
            needToExploreList.stream()
                .filter(target -> mazeMapper.isUnexplored(target)
                    || nextCost < mazeMapper.getCost(target))
                .forEach(coordinate -> mazeMapper.mapping(coordinate, nextCost));
        }

        Coordinate end = mazeData.findEndCoordinate();
        int steps = mazeMapper.getCost(end);

        mazeMapper.print();

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
