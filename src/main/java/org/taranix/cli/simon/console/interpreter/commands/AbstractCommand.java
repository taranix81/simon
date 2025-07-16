package org.taranix.cli.simon.console.interpreter.commands;

import java.io.Console;

public abstract class AbstractCommand implements ConsoleCommand {

    protected String getUserInput(Console console) {
        return console.readLine();
    }

    @Override
    public boolean isActive(String value) {
        return command().equalsIgnoreCase(value);
    }
}
