package org.taranix.cli.simon.commands.openai;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.console.DecoratedConsolePrinter;

import java.util.List;

@CafeCommand(command = "oap",
        longCommand = "open-ai-prompt",
        description = "Send prompt to open-ai",
        noOfArgs = 1)
class OpenAiPromptCommand {

    @CafeInject
    OpenAiService service;

    @CafeInject
    private DecoratedConsolePrinter decoratedConsolePrinter;

    @CafeCommandRun
    void execute(CafeCommandArguments arguments) {
        String prompt = arguments.getCliValues()[0];

        ChatMessage userMessage = new ChatMessage("user", prompt);
        ChatCompletionRequest request = ChatCompletionRequest
                .builder()
                .model("gpt-4.1")
                .messages(List.of(userMessage))
                .temperature(0.5d)
                .maxTokens(1000)
                .build();

        try {
            List<ChatCompletionChoice> choices = service.createChatCompletion(request).getChoices();
            decoratedConsolePrinter.printAiResponse(choices.get(0).getMessage().getContent());
        } catch (Exception e) {
            decoratedConsolePrinter.printError(e);
        }
    }
}
