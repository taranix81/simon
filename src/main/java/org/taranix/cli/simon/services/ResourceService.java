package org.taranix.cli.simon.services;

import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@CafeService
@Slf4j
public class ResourceService {

    public byte[] fromClassPath(String resource) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource)) {
            if (is != null) {
                return is.readAllBytes();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[]{};
    }

    public byte[] fromPath(Path path) {
        try {
            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[]{};
    }
}
