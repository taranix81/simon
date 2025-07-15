package org.taranix.cli.simon.services;

import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cli.simon.exceptions.PathServiceException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@CafeService
public class PathService {

    public byte[] read(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new PathServiceException(e);
        }

    }
}
