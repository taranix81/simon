package org.taranix.cli.simon.commands.gemini;

import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cli.simon.api.GeminiApi;
import org.taranix.cli.simon.console.ConsoleColourDecorator;
import org.taranix.cli.simon.console.interpreter.ConsoleReader;
import org.taranix.cli.simon.exceptions.ConsoleInterpreterException;
import org.taranix.cli.simon.model.AIResponse;
import org.taranix.cli.simon.model.UserPrompt;
import org.taranix.cli.simon.services.MimeTypeService;

import java.io.Console;

@Slf4j
@CafeCommand(command = "gc",
        longCommand = "gemini-chat",
        description = "Open Gemini chat")
class GeminiChatCommand {

    @CafeInject
    private GeminiApi geminiApi;


    @CafeInject
    private MimeTypeService mimeTypeService;

    @CafeInject
    private ConsoleReader consoleReader;

    private

    @CafeCommandRun
    void execute() {
        Console console = getConsole();

        while (true) {
            UserPrompt input = consoleReader.get(console);
            if (input == null) {
                break;
            }

            if (!input.isEmpty()) {
                ConsoleColourDecorator.setGeneratedContentColor();
                System.out.println("[Gemini Response]");
                AIResponse response = geminiApi.sendMessage(input);
                System.out.println(response.getText());
                saveAttachments(response);
                ConsoleColourDecorator.resetConsoleColour();
            }
        }
        geminiApi.close();
    }

    private void saveAttachments(AIResponse response) {
        //TODO : not implemented yet
    }

    private Console getConsole() {
        Console console = System.console();
        if (console == null) {
            throw new ConsoleInterpreterException("Console access denied. Try running from the command line.");
        }
        return console;
    }
}

