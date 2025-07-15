package org.taranix.cli.simon.console;

import org.apache.commons.lang3.StringUtils;
import org.taranix.cli.simon.exceptions.ConsoleInterpreterException;

import java.io.Console;

public class ConsoleInterpreter {

    public static final String PROMPT_LINE = ">> ";
    public static final String MULTI_LINE_BLOCK_MODE = "#multi";
    public static final String NEW_LINE = "\n";
    public static final String EXIT = "#exit";
    public static final String QUIT = "#quit";
    public static final String BYE = "#bye";
    private static final String HELP = "#help";
    private final DecoratedConsolePrinter decoratedConsolePrinter;

    public ConsoleInterpreter(DecoratedConsolePrinter decoratedConsolePrinter) {
        this.decoratedConsolePrinter = decoratedConsolePrinter;
    }


    private String readBlock(Console console) {
        StringBuilder block = new StringBuilder();

        while (true) {
            String inputLine = readLine(console, StringUtils.EMPTY);
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
        decoratedConsolePrinter.printPrompt(prompt);
        return console.readLine();

    }

    public String read() throws ConsoleInterpreterException {
        Console console = getConsole();
        String inputLine = readLine(console, PROMPT_LINE);

        if (isQuit(inputLine)) {
            decoratedConsolePrinter.reset();
            return null;
        }

        if (isMultiLineBlock(inputLine)) {
            return readBlock(console);
        }

        if (isHelp(inputLine)) {
            printHelp();
            return read();
        }

        return inputLine;
    }

    private void printHelp() {
        String helpContent = NEW_LINE +
                HELP + ": Print Help" + NEW_LINE +
                MULTI_LINE_BLOCK_MODE + ": Enter into multi line mode" + NEW_LINE +
                EXIT + ": Exit from multi line mode" + NEW_LINE +
                BYE + ": Quit chat" + NEW_LINE +
                QUIT + ": Quit chat" + NEW_LINE;

        decoratedConsolePrinter.printHelp(helpContent);
    }

    private boolean isHelp(String inputLine) {
        String lowerCaseInput = inputLine.toLowerCase().trim();
        return lowerCaseInput.equals(HELP);
    }

    private Console getConsole() {
        Console console = System.console();
        if (console == null) {
            throw new ConsoleInterpreterException("Console access denied. Try running from the command line.");
        }
        return console;
    }

    private boolean isMultiLineBlock(String inputLine) {
        return inputLine.toLowerCase().trim().equals(MULTI_LINE_BLOCK_MODE);
    }

    private boolean isExit(String inputLine) {
        String lowerCaseInput = inputLine.toLowerCase().trim();
        return lowerCaseInput.equals(EXIT);
    }

    private boolean isQuit(String inputLine) {
        String lowerCaseInput = inputLine.toLowerCase().trim();
        return lowerCaseInput.equals(QUIT) || lowerCaseInput.equals(BYE);
    }
}
