package q007.model;

import java.util.Arrays;

public enum MazeInputType {
    WALL('X', false),
    AISLE(' ', true),
    START('S', true),
    END('E', true);

    private final char indicator;
    private final boolean canMove;


    private MazeInputType(char indicator, boolean canMove) {
        this.indicator = indicator;
        this.canMove = canMove;
    }

    public static MazeInputType from(int code) {
        return Arrays.stream(MazeInputType.values())
            .filter(mazeInputType -> mazeInputType.indicator == code)
            .findFirst().orElseThrow(RuntimeException::new);
    }

    public boolean canMove() {
        return canMove;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }
}
