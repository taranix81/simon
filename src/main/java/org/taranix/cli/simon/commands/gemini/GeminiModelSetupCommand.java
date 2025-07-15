package org.taranix.cli.simon.commands.gemini;

import org.taranix.cafe.beans.annotations.CafePrimary;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.variables.GeminiModelVariable;

@CafeCommand(command = "gcm", longCommand = "gemini-custom-model",
        description = "Set Gemini AI model",
        noOfArgs = 1
)
class GeminiModelSetupCommand {

    @CafePrimary
    @CafeCommandRun
    GeminiModelVariable execute(CafeCommandArguments cafeCommandArguments) {
        return GeminiModelVariable.asCustom(cafeCommandArguments.getCliValues()[0]);

    }
}
