package org.taranix.cli.simon.console.interpreter.commands;

import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cli.simon.console.ConsoleColourDecorator;
import org.taranix.cli.simon.model.UserPrompt;

import java.io.Console;

@CafeService
class QuitCommand extends AbstractCommand implements ConsoleCommand {
    @Override
    public UserPrompt get(Console console) {
        ConsoleColourDecorator.resetConsoleColour();
        return null;
    }

    @Override
    public String command() {
        return "#bye";
    }

    @Override
    public String description() {
        return "Exit immediately";
    }

}
