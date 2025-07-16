package org.taranix.cli.simon.console;

public enum ConsoleTextColour {
    NONE(""),
    BLACK("\u001b[30m"),
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    PURPLE("\u001b[35m"),
    CYAN("\u001b[36m"),
    WHITE("\u001b[37m"),
    RESET("\u001B[0m");

    private final String ansiColour;

    private ConsoleTextColour(String ansiColour) {
        this.ansiColour = ansiColour;
    }

    public String getAnsiColour() {
        return this.ansiColour;
    }
}