package org.taranix.cli.simon.commands;

import org.taranix.cafe.beans.annotations.CafePrimary;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.variables.ModelAiVariable;

@CafeCommand(command = "cm", longCommand = "custom-model",
        description = "Set AI model",
        noOfArgs = 1
)
class ConfigModelAICommand {

    @CafePrimary
    @CafeCommandRun
    ModelAiVariable execute(CafeCommandArguments cafeCommandArguments) {
        return ModelAiVariable.asCustom(cafeCommandArguments.getCliValues()[0]);

    }
}
