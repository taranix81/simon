package org.taranix.cli.simon.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AIResource {

    private byte[] data;
    private String mimeType;
    private String displayName;
}
