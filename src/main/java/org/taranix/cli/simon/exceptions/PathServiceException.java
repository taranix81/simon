package org.taranix.cli.simon.exceptions;

import java.io.IOException;

public class PathServiceException extends RuntimeException {
    public PathServiceException(IOException e) {
        super(e);
    }
}
