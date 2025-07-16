package org.taranix.cli.simon.console.interpreter;

import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cli.simon.console.ConsoleColourDecorator;
import org.taranix.cli.simon.console.interpreter.commands.ConsoleCommand;
import org.taranix.cli.simon.model.UserPrompt;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@CafeService
public class ConsoleReader {


    @CafeInject
    private List<ConsoleCommand> modes;

    private String getUserInput(Console console) {
        return console.readLine();
    }

    public UserPrompt get(Console console) {
        ConsoleColourDecorator.setUserPromptColor();
        System.out.print(">> ");
        String line = getUserInput(console).trim();
        ConsoleColourDecorator.resetConsoleColour();
        
        Optional<ConsoleCommand> mode = modes.stream()
                .filter(inputMode -> inputMode.isActive(line))
                .findFirst();

        if (mode.isPresent()) {
            return mode.get().get(console);
        }
        return UserPrompt.builder().text(line).build();
    }


}
