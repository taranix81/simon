package org.taranix.cli.simon.gemini.commands;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.auxiliary.PathService;
import org.taranix.cli.simon.gemini.GeminiResponse;
import org.taranix.cli.simon.variables.LocalFileVariable;
import org.taranix.cli.simon.variables.ModelAiVariable;
import org.taranix.cli.simon.variables.TemperatureVariable;
import org.taranix.cli.simon.variables.TokenOutputVariable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@CafeCommand(command = "gp",
        longCommand = "gemini-prompt",
        description = "Send prompt to gemini ",
        noOfArgs = 1)
@Slf4j
class GeminiPromptCommand {
    @CafeInject
    private Client client;

    @CafeInject
    private PathService pathService;

    @CafeCommandRun
    void execute(CafeCommandArguments arguments,
                 ModelAiVariable modelAiVariable,
                 List<LocalFileVariable> localFileVariables,
                 TemperatureVariable temperatureVariable,
                 TokenOutputVariable tokenOutputVariable
    ) {

        String prompt = arguments.getCliValues()[0];
        List<Content> contents = createContent(prompt, localFileVariables);
        GenerateContentConfig config = generationConfig(temperatureVariable, tokenOutputVariable);
        GenerateContentResponse r = client.models.generateContent(modelAiVariable.get(), contents, config);
        GeminiResponse response = new GeminiResponse(r);
        System.out.println(response.integratedText());
        client.close();
    }

    private List<Content> createContent(String prompt, List<LocalFileVariable> localFileVariables) {
        List<Part> parts = new ArrayList<>();

        parts.add(Part.fromText(prompt));
        localFileVariables.forEach(localFileVariable -> {
            if (!localFileVariable.isDefault()) {
                Path p = localFileVariable.get();
                parts.add(Part.fromBytes(pathService.read(p), pathService.mimeType(p)));
            }
        });


        return List.of(Content.fromParts(parts.toArray(new Part[]{})));
    }

    private GenerateContentConfig generationConfig(TemperatureVariable temperatureVariable, TokenOutputVariable tokenOutputVariable) {
        return GenerateContentConfig.builder()
                .temperature(temperatureVariable.get())
                .candidateCount(1)
                .maxOutputTokens(tokenOutputVariable.get())
//                .topP(0.8f)                // Próbkowanie Top-P
//                .topK(40f)                  // Próbkowanie Top-K
                .build();

    }


}
