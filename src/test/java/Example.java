import com.google.genai.types.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Example {

    void example1() throws IOException {
        byte[] imageBytes = Files.readAllBytes(Paths.get("cat.jpg"));

        Content multiModalPrompt = Content.builder().parts(List.of(
                                Part.builder().text("Co to za zwierz� na zdj�ciu? Jaki kolor ma jego sier��?")
                                        .build(),
                                Part.builder()
                                        .inlineData(Blob.builder()
                                                .mimeType("image/jpeg")
                                                .data(imageBytes)
                                                .build())
                                        .build()
                        )
                )
                .role("user")
                .build();
    }

//    Tutaj dodajemy dwie cz�ci: jedn� tekstow� i jedn� z danymi obrazu inline (w formacie Base64).
//            #### Przyk�ad 3: Content jako odpowied� (output)
//    Gdy otrzymasz odpowied� z `generateContent` (obiekt `GenerateContentResponse`), b�dzie ona zawiera� list� `Candidate` (kandydat�w), a ka�dy `Candidate` b�dzie mia� sw�j obiekt `Content` z wygenerowan� tre�ci�.

    void run2() throws IOException {
        // ... po wywo�aniu client.generateContent(...)
        GenerateContentResponse response = null;

        if (response != null && !response.candidates().isEmpty()) {
            Candidate firstCandidate = response.candidates().get().get(0);
            Content generatedContent = firstCandidate.content().get();

            // Mo�esz teraz iterowa� po cz�ciach (Parts) tego contentu
            for (Part part : generatedContent.parts().get()) {
                if (part.text().isPresent()) {
                    System.out.println("Tekst z odpowiedzi: " + part.text());
                }
                // Mo�esz te� sprawdza� inne typy Part, np. FunctionCall, InlineData
            }
            // Rola w odpowiedzi modelu to zazwyczaj "model"
            System.out.println("Rola odpowiedzi: " + generatedContent.role().get());
        }
    }
}
