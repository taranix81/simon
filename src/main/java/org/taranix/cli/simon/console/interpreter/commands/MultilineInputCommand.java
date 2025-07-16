package org.taranix.cli.simon.console.interpreter.commands;

import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cli.simon.console.ConsoleColourDecorator;
import org.taranix.cli.simon.console.ConsoleTextColour;
import org.taranix.cli.simon.model.UserPrompt;

import java.io.Console;

@CafeService
class MultilineInputCommand extends AbstractCommand implements ConsoleCommand {

    @Override
    public UserPrompt get(Console console) {
        ConsoleColourDecorator.setColor(ConsoleTextColour.CYAN);
        System.out.println("[Enter #exit to quit multiline mode]");
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = getUserInput(console).trim();
            if ("#exit".equalsIgnoreCase(line)) {
                break;
            }
            sb.append(line);
        }
        ConsoleColourDecorator.resetConsoleColour();
        return UserPrompt.builder().text(sb.toString()).build();
    }

    @Override
    public String command() {
        return "#multi";
    }

    @Override
    public String description() {
        return "Allow enter multiline within one input";
    }


}
