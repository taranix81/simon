package org.taranix.cli.simon.gemini;

import com.google.genai.Client;
import org.taranix.cafe.beans.annotations.CafeFactory;
import org.taranix.cafe.beans.annotations.CafeProperty;
import org.taranix.cafe.beans.annotations.CafeProvider;
import org.taranix.cli.simon.variables.ModelAiVariable;

@CafeFactory
class GeminiConfiguration {

    @CafeProperty(name = "apikey")
    private String apiKey;

    @CafeProvider
    Client geminiClient() {
        return Client.builder().apiKey(apiKey).build();
    }

    @CafeProvider
    ModelAiVariable geminiDefaultModelAI() {
        return ModelAiVariable.asDefault("gemini-2.5-flash");
    }


}
