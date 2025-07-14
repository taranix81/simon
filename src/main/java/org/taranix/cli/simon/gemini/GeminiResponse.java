package org.taranix.cli.simon.gemini;


import com.google.genai.types.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public record GeminiResponse(GenerateContentResponse response) {

    public List<Blob> blobs() {
        return parts().stream()
                .map(Part::inlineData)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public String text() {
        return response.text();
    }

    public String integratedText() {
        return String.join("\n", texts());
    }

    public List<String> texts() {
        return parts().stream()
                .map(Part::text)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<Part> parts() {
        return response.candidates()
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

    }

}
