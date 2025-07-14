package org.taranix.cli.simon.gemini.commands;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.types.Blob;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cli.simon.console.CafeConsoleTextColour;
import org.taranix.cli.simon.console.ConsoleInterpreter;
import org.taranix.cli.simon.gemini.GeminiResponse;
import org.taranix.cli.simon.services.MimeTypeService;
import org.taranix.cli.simon.variables.ModelAiVariable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@CafeCommand(command = "gc",
        longCommand = "gemini-chat",
        description = "Open Gemini chat")
class GeminiChatCommand {

    @CafeInject
    private Client client;

    @CafeInject
    private MimeTypeService mimeTypeService;

    @CafeCommandRun
    void execute(ModelAiVariable modelAiVariable) {
        Chat chat = client.chats.create(modelAiVariable.get(), GenerateContentConfig.builder()
                .responseModalities(List.of("TEXT"))
                .build());
        ConsoleInterpreter consoleInterpreter = new ConsoleInterpreter();

        while (true) {
            String input = consoleInterpreter.read();
            if (input == null) {
                break;
            }

            if (!input.isEmpty()) {
                queryAI(chat, input);
            }

        }
    }


    private void queryAI(Chat chat, String inputLine) {
        System.out.print(CafeConsoleTextColour.GREEN.getAnsiColour() + "[AI] : ");
        GenerateContentResponse response = chat.sendMessage(inputLine);
        GeminiResponse geminiResponse = new GeminiResponse(response);

        System.out.println(CafeConsoleTextColour.GREEN.getAnsiColour() + geminiResponse.integratedText());
        log.info("User: {}", inputLine);
        log.info("AI: {}", geminiResponse.integratedText());

        geminiResponse.blobs().forEach(this::save);
    }

    private void save(Blob blob) {
        String mimeType = blob.mimeType().orElse(null);
        byte[] data = blob.data().orElse(null);

        if (data == null) {
            log.warn("No data to write");
        } else {
            String fileExtension = mimeTypeService.getFileExtensionFromMimeType(mimeType);
            String fileName = "gemini_output_" + UUID.randomUUID() + "." + fileExtension;
            Path filePath = Paths.get(fileName);

            try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                outputStream.write(data);
                System.out.println("Saved " + filePath);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }


}

