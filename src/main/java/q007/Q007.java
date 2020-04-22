package q007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import q007.model.Coordinate;
import q007.model.MazeInputType;

/**
 * <pre>
 * q007 最短経路探索
 *
 * 壁を 'X' 通路を ' ' 開始を 'S' ゴールを 'E' で表現された迷路で、最短経路を通った場合に
 * 何歩でゴールまでたどり着くかを出力するプログラムを実装してください。
 * もし、ゴールまで辿り着くルートが無かった場合は -1 を出力してください。
 * なお、1歩は上下左右のいずれかにしか動くことはできません（斜めはNG）。
 *
 * 迷路データは MazeInputStream から取得してください。
 * 迷路の横幅と高さは毎回異なりますが、必ず長方形（あるいは正方形）となっており、外壁は全て'X'で埋まっています。
 * 空行が迷路データの終了です。
 * </pre>
 *
 * <pre>
 * [迷路の例]
 * XXXXXXXXX
 * XSX    EX
 * X XXX X X
 * X   X X X
 * X X XXX X
 * X X     X
 * XXXXXXXXX
 *
 * [答え]
 * 14
 * </pre>
 */
public class Q007 {

    public static void main(String[] args) {
        MazeData mazeData = readMazeData();
        MazeMapper mazeMapper = MazeMapper.from(mazeData);

        mazeData.print();
        mazeMapper.print();
    }

    private static MazeData readMazeData() {
        InputStream is = new MazeInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String resource;
        Map<Coordinate, MazeInputType> maze = new HashMap<>();

        try {
            int width = -1;
            AtomicInteger heightCount = new AtomicInteger();
            while ((resource = br.readLine()) != null) {
                if (width == -1) {
                    width = resource.length();
                }
                AtomicInteger widthCount = new AtomicInteger();
                resource.chars().forEach(
                    character ->
                        maze.put(
                            new Coordinate(
                                widthCount.getAndIncrement(),
                                heightCount.getAndIncrement()
                            )
                            , MazeInputType.from(character))
                );
            }
            return new MazeData(width, heightCount.get(), maze);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
// 完成までの時間: xx時間 xx分