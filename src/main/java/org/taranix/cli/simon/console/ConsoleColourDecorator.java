package org.taranix.cli.simon.console;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsoleColourDecorator {

    public static void setColor(ConsoleTextColour color) {
        System.out.print(color.getAnsiColour());
    }

    public static void resetConsoleColour() {
        setColor(ConsoleTextColour.RESET);
    }

    public static void setUserPromptColor() {
        setColor(ConsoleTextColour.YELLOW);
    }

    public static void setGeneratedContentColor() {
        setColor(ConsoleTextColour.GREEN);
    }
}
