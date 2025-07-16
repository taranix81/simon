package org.taranix.cli.simon.factories;

import com.google.common.collect.ImmutableList;
import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.types.*;
import org.taranix.cafe.beans.annotations.CafeFactory;
import org.taranix.cafe.beans.annotations.CafeProperty;
import org.taranix.cafe.beans.annotations.CafeProvider;
import org.taranix.cli.simon.variables.GeminiModelVariable;

import java.util.List;

@CafeFactory
class GeminiConfiguration {

    @CafeProperty(name = "gemini.apikey")
    private String apiKey;

    @CafeProperty(name = "gemini.default.model")
    private String defaultModel;

    @CafeProperty(name = "gemini.default.temperature")
    private Float defaultTemperature;

    @CafeProperty(name = "ai.output.token")
    private Integer maxTokenOutput;

    @CafeProvider
    Chat geminiChat(Client client) {
        return client.chats.create(defaultModel);
    }

    @CafeProvider
    Client geminiClient() {
        return Client.builder().apiKey(apiKey).build();
    }

    @CafeProvider
    GeminiModelVariable geminiDefaultModelAI() {
        return GeminiModelVariable.asDefault(defaultModel);
    }

    @CafeProvider
    GenerateContentConfig generateContentConfig() {
        return GenerateContentConfig.builder()
                .temperature(defaultTemperature)
                .candidateCount(1)
                .maxOutputTokens(maxTokenOutput)
                //     .topP(0.8f)
                //      .topK(40f)
                .safetySettings(safetySettings())
                .responseModalities(List.of("TEXT"))
                .tools(List.of(googleSearchTool()))
                .build();

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

}
