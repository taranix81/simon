package org.taranix.cli.simon.gemini;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GeminiResponseHandler {
//    public GeminiResponse process(GenerateContentResponse response) {
//        if (response == null) {
//            log.warn("Empty response");
//            return GeminiResponse.builder().text("Otrzymano pustą odpowiedź.").build();
//        }
//
//
//        if (response.candidates().isEmpty()) {
//            log.debug("No candidates");
//            return GeminiResponse.builder()
//                    .text(response.promptFeedback()
//                            .flatMap(GenerateContentResponsePromptFeedback::blockReasonMessage)
//                            .orElse("Brak feedbacku"))
//                    .build();
//
//        }
//
//        return response.candidates().map(this::processCandidates)
//                .orElse(GeminiResponse.builder().build());
//
//    }
//
//    private void processPart(GeminiResponse result, Part part) {
//        part.text().ifPresent(text -> result.getTexts().add(text));
//
//        part.inlineData().ifPresent(blob -> {
//            String mimeType = blob.mimeType().orElse(null);
//            ByteBuffer data = blob.data().map(ByteBuffer::wrap).orElse(null);
//            String fileExtension = getFileExtensionFromMimeType(mimeType);
//            String fileName = "gemini_output_" + UUID.randomUUID().toString() + "." + fileExtension;
//        });
//
//    }
//
//    private GeminiResponse processCandidates(List<Candidate> candidateList) {
//        GeminiResponse result = GeminiResponse.builder().build();
//
//        for (Candidate candidate : candidateList) {
//            List<Part> parts = candidate.content()
//                    .flatMap(Content::parts)
//                    .orElse(List.of());
//
//
//            parts.forEach(part -> processPart(result, part));
//
//            candidate.finishReason().ifPresent(finishReason -> System.out.println("Powód zakończenia generowania: " + finishReason));
//
//        }
//        return result;
//    }

}
