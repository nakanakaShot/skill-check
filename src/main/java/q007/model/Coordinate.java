package q007.model;

import java.util.Objects;

public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 1次元配列インデックス -> 2次元配列座標
     *
     * @param index 1次元配列インデックス
     * @param width 幅
     * @return 2次元配列インデックス
     */
    public static Coordinate extractIndex(int index, int width) {
        return new Coordinate(
            index % width,
            index / width
        );
    }

    /**
     * 2次元配列座標 -> 1次元配列インデックス
     *
     * @param width 幅
     * @return 1次元配列インデックス
     */
    public Integer flattenIndex(int width) {
        return (y * width + x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate target = (Coordinate) obj;

        return x == target.x && y == target.y;
    }
}
