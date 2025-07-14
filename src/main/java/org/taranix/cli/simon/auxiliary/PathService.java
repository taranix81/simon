package org.taranix.cli.simon.auxiliary;

import org.taranix.cafe.beans.annotations.CafeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@CafeService
public class PathService {

    public String mimeType(Path path) {
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] read(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
