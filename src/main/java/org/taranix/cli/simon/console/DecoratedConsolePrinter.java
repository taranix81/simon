package org.taranix.cli.simon.console;

import org.taranix.cafe.beans.annotations.CafeService;

@CafeService
public class DecoratedConsolePrinter {

    public void printAiResponse(String text) {
        setColor(CafeConsoleTextColour.GREEN);
        System.out.println(text);
        reset();
    }

    public void printPrompt(String prompt) {
        setColor(CafeConsoleTextColour.YELLOW);
        System.out.print(prompt);
    }

    public void printHelp(String help) {
        setColor(CafeConsoleTextColour.PURPLE);
        System.out.println(help);
        reset();
    }

    public void setColor(CafeConsoleTextColour color) {
        System.out.print(color.getAnsiColour());
    }

    public void reset() {
        setColor(CafeConsoleTextColour.RESET);
    }
}
