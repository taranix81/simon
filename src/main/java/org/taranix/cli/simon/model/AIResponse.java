package org.taranix.cli.simon.model;

import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
public class AIResponse {


    private List<String> textParts = new ArrayList<>();

    private List<AIResource> resources = new ArrayList<>();

    private String text;

    public static AIResponse from(GenerateContentResponse response) {
        AIResponse result = new AIResponse();
        List<Part> parts = response.candidates()
                .orElse(List.of())
                .stream()
                .map(Candidate::content)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Content::parts)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Collection::stream)
                .toList();

        parts.stream()
                .map(Part::text)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(result.textParts::add);

        result.text = response.text();

        parts.stream()
                .map(Part::inlineData)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(blob ->
                        result.resources.add(
                                AIResource.builder()
                                        .data(blob.data().orElse(null))
                                        .mimeType(blob.mimeType().orElse(null))
                                        .displayName(blob.displayName().orElse(null))
                                        .build()));
        return result;
    }

    public String integratedText() {
        return String.join("\n", getTextParts());
    }


}
