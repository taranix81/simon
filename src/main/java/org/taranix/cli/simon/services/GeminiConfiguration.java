package org.taranix.cli.simon.services;

import com.google.genai.Client;
import org.taranix.cafe.beans.annotations.CafeFactory;
import org.taranix.cafe.beans.annotations.CafeProperty;
import org.taranix.cafe.beans.annotations.CafeProvider;
import org.taranix.cli.simon.variables.GeminiModelVariable;

@CafeFactory
class GeminiConfiguration {

    @CafeProperty(name = "gemini.apikey")
    private String apiKey;

    @CafeProperty(name = "gemini.default.model")
    private String defaultModel;


    @CafeProvider
    Client geminiClient() {
        return Client.builder().apiKey(apiKey).build();
    }

    @CafeProvider
    GeminiModelVariable geminiDefaultModelAI() {
        return GeminiModelVariable.asDefault(defaultModel);
    }


}
