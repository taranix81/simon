package org.taranix.cli.simon.commands;

import com.google.genai.Client;
import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.variables.ModelAiVariable;
import org.taranix.cli.simon.variables.PathVariable;

//@CafeCommand(command = "p",
//        longCommand = "prompt",
//        description = "Make a prompt with optional attachments",
//        noOfArgs = 1)
@Slf4j
/*
This command is always triggered at the end.
 */

public class MultiModalPromptAICommand {
    @CafeInject
    private Client client;

    @CafeCommandRun
    void execute(CafeCommandArguments arguments, ModelAiVariable modelAiVariable, PathVariable pathVariable) {

//        if (arguments.getCliValues().length != 0) {
//            String prompt = arguments.getCliValues()[0];
//            if (pathVariable.isDefault()) {
//                System.out.println(client.prompt(modelAiVariable.get(), prompt).text());
//            } else {
//                System.out.println(client.complexPrompt(modelAiVariable.get(), pathVariable.get(), prompt).text());
//            }
//        }
        client.close();
    }


}
