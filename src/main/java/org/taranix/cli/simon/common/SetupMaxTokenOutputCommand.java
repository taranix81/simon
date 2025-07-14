package org.taranix.cli.simon.common;


import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.beans.annotations.CafePrimary;
import org.taranix.cafe.beans.converters.CafeConverter;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.variables.TokenOutputVariable;

@Slf4j
@CafeCommand(command = "aimto",
        longCommand = "ai-max-token-output",
        noOfArgs = 1,
        description = "Setup AI Temperature. Controls the randomness or creativity of the generated output. " +
                "Value 0.0 - 1.0 (Lower means focused, deterministic, higher more creativity")
class SetupMaxTokenOutputCommand {

    @CafeInject
    private CafeConverter<String, Integer> converter;

    @CafePrimary
    @CafeCommandRun
    TokenOutputVariable execute(CafeCommandArguments arguments) {
        String arg = arguments.getCliValues()[0];
        Integer value = converter.convert(arg);
        return TokenOutputVariable.from(value);
    }
}
