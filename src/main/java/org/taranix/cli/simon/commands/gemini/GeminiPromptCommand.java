package org.taranix.cli.simon.commands.gemini;

import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.api.GeminiApi;
import org.taranix.cli.simon.console.ConsoleColourDecorator;
import org.taranix.cli.simon.model.AIResponse;
import org.taranix.cli.simon.model.UserPrompt;
import org.taranix.cli.simon.variables.CommandVariable;
import org.taranix.cli.simon.variables.GeminiModelVariable;
import org.taranix.cli.simon.variables.LocalFileVariable;

import java.util.List;

@CafeCommand(command = "gp",
        longCommand = "gemini-prompt",
        description = "Send prompt to gemini",
        noOfArgs = 1)
@Slf4j
class GeminiPromptCommand {
    @CafeInject
    private GeminiApi geminiApi;

    @CafeCommandRun
    void execute(CafeCommandArguments arguments,
                 GeminiModelVariable geminiModelVariable,
                 List<LocalFileVariable> localFileVariables) {

        UserPrompt userPrompt = UserPrompt.builder()
                .text(arguments.getCliValues()[0])
                .files(localFileVariables.stream()
                        .filter(localFileVariable -> !localFileVariable.isDefault())
                        .map(CommandVariable::get)
                        .toList()
                )
                .build();
        ConsoleColourDecorator.setGeneratedContentColor();
        System.out.println("[Gemini Response]");
        AIResponse response = geminiApi.generateContent(userPrompt, geminiModelVariable.get());
        System.out.println(response.getText());
        ConsoleColourDecorator.resetConsoleColour();
        saveAttachments(response);

        geminiApi.close();
    }

    private void saveAttachments(AIResponse response) {
        //TODO : not implemented yet
    }

}
