package org.taranix.cli.simon.variables;

import java.nio.file.Path;

public class LocalFileVariable extends CommandVariable<Path> {

    private LocalFileVariable(Path value, boolean isDefault) {
        super(value, isDefault);
    }

    public static LocalFileVariable from(Path path) {
        return new LocalFileVariable(path, false);
    }

    public static LocalFileVariable from(String path) {
        return new LocalFileVariable(Path.of(path), false);
    }

    public static LocalFileVariable adDefault() {
        return new LocalFileVariable(null, true);
    }
}
