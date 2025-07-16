package org.taranix.cli.simon.commands.setup;


import lombok.extern.slf4j.Slf4j;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.beans.converters.CafeConverter;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.variables.TemperatureVariable;

@Slf4j
//@CafeCommand(command = "ait",
//        longCommand = "ai-temperature",
//        noOfArgs = 1,
//        description = "Setup AI Temperature. Controls the randomness or creativity of the generated output. " +
//                "Value 0.0 - 1.0 (Lower means focused, deterministic, higher more creativity")
class SetupTemperatureCommand {

    @CafeInject
    private CafeConverter<String, Float> converter;

    @CafeCommandRun
    TemperatureVariable execute(CafeCommandArguments arguments) {
        String arg = arguments.getCliValues()[0];
        Float value = converter.convert(arg);
        return TemperatureVariable.from(value);
    }
}
