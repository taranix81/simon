package org.taranix.cli.simon.commands.gemini;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.console.DecoratedConsolePrinter;
import org.taranix.cli.simon.variables.GeminiModelVariable;
import org.taranix.cli.simon.variables.LocalFileVariable;
import org.taranix.cli.simon.variables.TemperatureVariable;
import org.taranix.cli.simon.variables.TokenOutputVariable;

import java.util.List;

@CafeCommand(command = "gp",
        longCommand = "gemini-prompt",
        description = "Send prompt to gemini",
        noOfArgs = 1)
@Slf4j
class GeminiPromptCommand extends GeminiBase {
    @CafeInject
    private Client client;

    @CafeInject
    private DecoratedConsolePrinter decoratedConsolePrinter;

    @CafeCommandRun
    void execute(CafeCommandArguments arguments,
                 GeminiModelVariable geminiModelVariable,
                 List<LocalFileVariable> localFileVariables,
                 TemperatureVariable temperatureVariable,
                 TokenOutputVariable tokenOutputVariable
    ) {

        String prompt = arguments.getCliValues()[0];
        Content content = createContent(prompt, localFileVariables);
        GenerateContentConfig config = generationConfig(temperatureVariable, tokenOutputVariable);
        GenerateContentResponse r = client.models.generateContent(geminiModelVariable.get(), content, config);
        GeminiResponse response = new GeminiResponse(r);
        decoratedConsolePrinter.printAiResponse(response.integratedText());
        client.close();
    }


}
