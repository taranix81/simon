package org.taranix.cli.simon.services;

import com.theokanning.openai.service.OpenAiService;
import org.taranix.cafe.beans.annotations.CafeFactory;
import org.taranix.cafe.beans.annotations.CafeProperty;
import org.taranix.cafe.beans.annotations.CafeProvider;

@CafeFactory
class OpenAiConfiguration {

    @CafeProperty(name = "openai.apikey")
    private String apiKey;

    @CafeProvider
    OpenAiService openAiService() {
        return new OpenAiService(apiKey);
    }

    ;

}
