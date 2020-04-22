package q007;

abstract class Maze {

    private final int width;
    private final int height;

    Maze(int width, int height) {
        this.width = width;
        this.height = height;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }
}
