package org.taranix.cli.simon.configuration;

import com.google.genai.Client;
import org.taranix.cafe.beans.annotations.CafeFactory;
import org.taranix.cafe.beans.annotations.CafeProperty;
import org.taranix.cafe.beans.annotations.CafeProvider;
import org.taranix.cli.simon.variables.ModelAiVariable;
import org.taranix.cli.simon.variables.PathVariable;

@CafeFactory
class AIClientConfiguration {

    @CafeProperty(name = "apikey")
    private String apiKey;

    @CafeProvider
    Client client() {
        return Client.builder().apiKey(apiKey).build();
    }

    @CafeProvider
    ModelAiVariable defaultModelAI() {
        return ModelAiVariable.asDefault("gemini-2.5-flash");
    }


    @CafeProvider
    PathVariable defaultPath() {
        return PathVariable.adDefault();
    }
}
