package org.taranix.cli.simon.commands.gemini;

import com.google.common.collect.ImmutableList;
import com.google.genai.types.*;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cli.simon.services.MimeTypeService;
import org.taranix.cli.simon.services.PathService;
import org.taranix.cli.simon.variables.LocalFileVariable;
import org.taranix.cli.simon.variables.TemperatureVariable;
import org.taranix.cli.simon.variables.TokenOutputVariable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

abstract class GeminiBase {

    @CafeInject
    private PathService pathService;

    @CafeInject
    private MimeTypeService mimeTypeService;

    protected Content createContent(String prompt, List<LocalFileVariable> localFileVariables) {
        List<Part> parts = new ArrayList<>();

        parts.add(Part.fromText(prompt));
        localFileVariables.forEach(localFileVariable -> {
            if (!localFileVariable.isDefault()) {
                Path p = localFileVariable.get();
                parts.add(Part.fromBytes(pathService.read(p), mimeTypeService.mimeType(p)));
            }
        });


        return Content.fromParts(parts.toArray(new Part[]{}));
    }

    private ImmutableList<SafetySetting> safetySettings() {
        return ImmutableList.of(
                SafetySetting.builder()
                        .category(HarmCategory.Known.HARM_CATEGORY_HATE_SPEECH)
                        .threshold(HarmBlockThreshold.Known.BLOCK_ONLY_HIGH)
                        .build(),
                SafetySetting.builder()
                        .category(HarmCategory.Known.HARM_CATEGORY_DANGEROUS_CONTENT)
                        .threshold(HarmBlockThreshold.Known.BLOCK_LOW_AND_ABOVE)
                        .build());
    }

    private Tool googleSearchTool() {
        return Tool.builder()
                .googleSearch(GoogleSearch.builder().build())
                .build();
    }

    protected GenerateContentConfig generationConfig(TemperatureVariable temperatureVariable, TokenOutputVariable tokenOutputVariable) {
        return GenerateContentConfig.builder()
                .temperature(temperatureVariable.get())
                .candidateCount(1)
                .maxOutputTokens(tokenOutputVariable.get())
                //     .topP(0.8f)
                //      .topK(40f)
                .safetySettings(safetySettings())
                .responseModalities(List.of("TEXT"))
                .tools(List.of(googleSearchTool()))
                .build();

    }
}
