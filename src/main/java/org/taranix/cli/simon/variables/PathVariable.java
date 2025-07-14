package org.taranix.cli.simon.variables;

import java.nio.file.Path;

public class PathVariable extends CommandVariable<Path> {

    private PathVariable(Path value, boolean isDefault) {
        super(value, isDefault);
    }

    public static PathVariable from(Path path) {
        return new PathVariable(path, false);
    }

    public static PathVariable from(String path) {
        return new PathVariable(Path.of(path), false);
    }

    public static PathVariable adDefault() {
        return new PathVariable(null, true);
    }
}
