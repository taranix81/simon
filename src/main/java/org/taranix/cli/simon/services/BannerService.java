package org.taranix.cli.simon.services;


import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.beans.annotations.CafeService;

import java.nio.file.Path;
import java.util.Optional;

@CafeService
public class BannerService {

    @CafeInject
    private ResourceService resourceService;

    private String resourceName = "banner.txt";

    public void printBannerInConsole() {
        banner().ifPresent(System.out::println);
    }

    private Optional<String> banner() {
        byte[] fromPath = resourceService.fromPath(Path.of(resourceName).toAbsolutePath());
        if (fromPath.length > 0) {
            return Optional.of(new String(fromPath));
        }

        byte[] fromClassPath = resourceService.fromClassPath(resourceName);
        if (fromClassPath.length > 0) {
            return Optional.of(new String(fromClassPath));
        }

        return Optional.empty();
    }
}
