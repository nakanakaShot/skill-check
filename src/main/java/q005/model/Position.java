package q005.model;

import java.util.Arrays;

public enum Position {
    部長, 課長, 一般;

    public static Position from(String str) {
        return Arrays.stream(Position.values())
            .filter(position -> position.name().equals(str)).findFirst()
            .orElseThrow(RuntimeException::new);
    }
}
