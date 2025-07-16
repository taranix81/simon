package org.taranix.cli.simon.console.interpreter.commands;

import org.apache.commons.lang3.StringUtils;
import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cli.simon.console.ConsoleColourDecorator;
import org.taranix.cli.simon.console.ConsoleTextColour;
import org.taranix.cli.simon.model.UserPrompt;

import java.io.Console;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CafeService
class LocalFileCommand extends AbstractCommand implements ConsoleCommand {
    @Override
    public UserPrompt get(Console console) {
        ConsoleColourDecorator.setColor(ConsoleTextColour.WHITE);
        String line = getUserInput(console).trim();
        if (isFile(line)) {
            Path localFile = Paths.get(line).toAbsolutePath();
            ConsoleColourDecorator.resetConsoleColour();
            return UserPrompt.builder()
                    .files(List.of(localFile))
                    .build();
        }

        System.out.printf("[File %s doesn't exists]%n", line);

        ConsoleColourDecorator.resetConsoleColour();
        return UserPrompt.builder().build();
    }

    @Override
    public String command() {
        return "#file";
    }

    @Override
    public String description() {
        return "Attach file to user input";
    }

    private boolean isFile(String line) {
        if (StringUtils.isBlank(line)) {
            return false;
        }

        Path localFile = Paths.get(line);
        return Files.exists(localFile);
    }

}
