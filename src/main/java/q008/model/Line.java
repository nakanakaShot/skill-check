package q008.model;

import java.util.regex.Pattern;

public class Line {

    private static final char EMBEDDED_STRING_SIGNATURE = '\"';
    private static final char EMBEDDED_CHARACTER_SIGNATURE = '\'';

    private static final Pattern EMBEDDED_STRING_PATTERN = Pattern
        .compile(".*" + EMBEDDED_STRING_SIGNATURE + ".*" + EMBEDDED_STRING_SIGNATURE + ".*");
    private static final Pattern EMBEDDED_CHARACTER_PATTERN = Pattern
        .compile(".*" + EMBEDDED_CHARACTER_SIGNATURE + "." + EMBEDDED_CHARACTER_SIGNATURE + ".*");

    private final long row;
    private final String line;

    public Line(long row, String line) {
        this.row = row;
        this.line = line;
    }

    boolean hasEmbeddedVariable() {
        return EMBEDDED_CHARACTER_PATTERN.matcher(line).matches()
            || EMBEDDED_STRING_PATTERN.matcher(line).matches();
    }

    public Long getRow() {
        return row;
    }

    public String getLine() {
        return line;
    }
}
