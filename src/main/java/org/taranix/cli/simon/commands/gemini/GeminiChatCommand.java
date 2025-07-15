package org.taranix.cli.simon.commands.gemini;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.google.genai.types.Blob;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cli.simon.console.ConsoleInterpreter;
import org.taranix.cli.simon.console.DecoratedConsolePrinter;
import org.taranix.cli.simon.services.MimeTypeService;
import org.taranix.cli.simon.variables.GeminiModelVariable;
import org.taranix.cli.simon.variables.TemperatureVariable;
import org.taranix.cli.simon.variables.TokenOutputVariable;

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
class GeminiChatCommand extends GeminiBase {

    @CafeInject
    private Client client;

    @CafeInject
    private DecoratedConsolePrinter decoratedConsolePrinter;

    @CafeInject
    private MimeTypeService mimeTypeService;

    @CafeCommandRun
    void execute(GeminiModelVariable geminiModelVariable,
                 TemperatureVariable temperatureVariable,
                 TokenOutputVariable tokenOutputVariable) {
        Chat chat = client.chats.create(geminiModelVariable.get(), GenerateContentConfig.builder()
                .responseModalities(List.of("TEXT"))
                .build());
        ConsoleInterpreter consoleInterpreter = new ConsoleInterpreter(decoratedConsolePrinter);

        while (true) {
            String input = consoleInterpreter.read();
            if (input == null) {
                break;
            }

            if (!input.isEmpty()) {
                queryAI(chat, input, temperatureVariable, tokenOutputVariable);
            }

        }
    }


    private void queryAI(Chat chat, String inputLine, TemperatureVariable temperatureVariable,
                         TokenOutputVariable tokenOutputVariable) {
        decoratedConsolePrinter.printAiResponse("[AI] : ");
        Content content = createContent(inputLine, List.of());
        GenerateContentConfig config = generationConfig(temperatureVariable, tokenOutputVariable);
        GenerateContentResponse response = chat.sendMessage(content, config);
        GeminiResponse geminiResponse = new GeminiResponse(response);

        decoratedConsolePrinter.printAiResponse(geminiResponse.integratedText());
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
            String fileExtension = mimeTypeService.extensionByMimeType(mimeType);
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

