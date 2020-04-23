package q008.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lines {

    private final List<Line> lines;

    public Lines(List<Line> lines) {
        this.lines = lines;
    }


    public Lines correctHasEmbeddedVariable() {
        return new Lines(
            lines.stream()
                .filter(Line::hasEmbeddedVariable)
                .sorted(Comparator.comparing(Line::getRow))
                .collect(Collectors.toList())
        );
    }

    public List<Line> getLines() {
        return lines;
    }
}
