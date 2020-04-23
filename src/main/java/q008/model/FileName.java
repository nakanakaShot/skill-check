package q008.model;

import java.nio.file.Path;

public class FileName {

    private final Path fileName;

    private FileName(Path fileName) {
        this.fileName = fileName;
    }

    public static FileName from(Path path) {
        return new FileName(path.getFileName());
    }

    public Path getFileName() {
        return fileName;
    }
}
