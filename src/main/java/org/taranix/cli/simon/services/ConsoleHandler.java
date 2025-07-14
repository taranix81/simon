package org.taranix.cli.simon.services;

import org.taranix.cli.simon.exceptions.ConsoleInterpreterException;
import org.taranix.cli.simon.utils.CafeConsoleTextColour;

import java.io.Console;

public class ConsoleHandler {


    public static final String PROMPT_LINE = ">> ";
    public static final String PROMPT_BLOCK_LINE = "[Block] >> ";
    public static final String MULTI_LINE_BLOCK_MODE = "#multi";
    public static final String NEW_LINE = "\n";


    private String blockRead(Console console) {
        StringBuilder block = new StringBuilder();

        while (true) {
            String inputLine = readLine(console, PROMPT_BLOCK_LINE);
            if (isExit(inputLine)) {
                break;
            }
            if (block.length() > 0) {
                block.append(NEW_LINE);
            }
            block.append(inputLine);
        }

        return block.toString();
    }

    private String readLine(Console console, String prompt) {
        System.out.print(CafeConsoleTextColour.YELLOW.getAnsiColour() + prompt);
        return console.readLine();

    }

    public String read() throws ConsoleInterpreterException {
        Console console = getConsole();
        String inputLine = readLine(console, PROMPT_LINE);

        if (isQuit(inputLine)) {
            return null;
        }

        if (isMultiLineBlock(inputLine)) {
            return blockRead(console);
        }

        return inputLine;
    }

    private Console getConsole() {
        Console console = System.console();
        if (console == null) {
            throw new ConsoleInterpreterException("Brak dostępu do konsoli. Spróbuj uruchomić z linii poleceń.");
        }
        return console;
    }

    private boolean isMultiLineBlock(String inputLine) {
        return inputLine.toLowerCase().trim().equals(MULTI_LINE_BLOCK_MODE);
    }

    private boolean isExit(String inputLine) {
        String lowerCaseInput = inputLine.toLowerCase().trim();
        return lowerCaseInput.equals("#exit");
    }

    private boolean isQuit(String inputLine) {
        String lowerCaseInput = inputLine.toLowerCase().trim();
        return lowerCaseInput.equals("#quit") || lowerCaseInput.equals("bye");
    }
}
