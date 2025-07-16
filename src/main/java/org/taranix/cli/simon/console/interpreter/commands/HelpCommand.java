package org.taranix.cli.simon.console.interpreter.commands;

import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cli.simon.console.ConsoleColourDecorator;
import org.taranix.cli.simon.console.ConsoleTextColour;
import org.taranix.cli.simon.model.UserPrompt;

import java.io.Console;

@CafeService
class HelpCommand extends AbstractCommand implements ConsoleCommand {


    @Override
    public UserPrompt get(Console console) {
        ConsoleColourDecorator.setColor(ConsoleTextColour.PURPLE);

//        commandList.forEach(consoleCommand ->
//                console.printf("%s - %s \n", consoleCommand.command(), consoleCommand.description())
//        );

        ConsoleColourDecorator.resetConsoleColour();
        return UserPrompt.builder().build();
    }

    @Override
    public String command() {
        return "help";
    }

    @Override
    public String description() {
        return "Print this help";
    }
}
