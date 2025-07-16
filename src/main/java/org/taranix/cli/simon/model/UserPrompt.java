package org.taranix.cli.simon.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Setter
@ToString
public class UserPrompt {
    private String text;
    @Builder.Default
    private List<Path> files = new ArrayList<>();
    @Builder.Default
    private List<URI> resources = new ArrayList<>();

    public boolean isEmpty() {
        return StringUtils.isBlank(getText())
                && getFiles().isEmpty()
                && getResources().isEmpty();
    }
}
