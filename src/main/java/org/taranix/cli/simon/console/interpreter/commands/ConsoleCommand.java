package org.taranix.cli.simon.console.interpreter.commands;

import org.taranix.cli.simon.model.UserPrompt;

import java.io.Console;

public interface ConsoleCommand {

    UserPrompt get(Console console);

    String command();

    boolean isActive(String value);

    String description();
}
