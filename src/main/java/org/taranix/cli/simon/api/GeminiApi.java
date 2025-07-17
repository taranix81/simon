package org.taranix.cli.simon.api;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cli.simon.model.AIResponse;
import org.taranix.cli.simon.model.UserPrompt;
import org.taranix.cli.simon.services.MimeTypeService;
import org.taranix.cli.simon.services.ResourceService;

import java.util.ArrayList;
import java.util.List;

@CafeService
@Slf4j
public class GeminiApi {

    @CafeInject
    private Chat chat;

    @CafeInject
    private Client client;


    @CafeInject
    private GenerateContentConfig config;

    @CafeInject
    private ResourceService resourceService;

    @CafeInject
    private MimeTypeService mimeTypeService;

    public void close() {
        client.close();
    }

    public AIResponse generateContent(UserPrompt userPrompt, String model) {
        Content content = createContent(userPrompt);
        GenerateContentResponse response = client.models.generateContent(model, content, config);
        AIResponse aiResponse = AIResponse.from(response);
        log.info("Prompt: {}", userPrompt);
        log.info("Gemini AI: {}", aiResponse.getText());
        log.info("Gemini AI (integrated text: {}", aiResponse.integratedText());
        return aiResponse;
    }

    public AIResponse sendMessage(UserPrompt userPrompt) {
        Content content = createContent(userPrompt);
        GenerateContentResponse response = chat.sendMessage(content, config);
        AIResponse aiResponse = AIResponse.from(response);
        log.info("Prompt: {}", userPrompt);
        log.info("Gemini AI: {}", aiResponse.getText());
        log.info("Gemini AI (integrated text: {}", aiResponse.integratedText());
        return aiResponse;
    }

    protected Content createContent(UserPrompt userPrompt) {
        List<Part> parts = new ArrayList<>();
        if (StringUtils.isNoneBlank(userPrompt.getText())) {
            parts.add(Part.fromText(userPrompt.getText()));

        }

        userPrompt.getFiles().forEach(path -> parts.add(Part.fromBytes(resourceService.fromPath(path), mimeTypeService.mimeType(path))));
        userPrompt.getResources().forEach(uri -> parts.add(Part.fromUri(uri.toString(), "")));
        return Content.fromParts(parts.toArray(new Part[]{}));
    }


}
