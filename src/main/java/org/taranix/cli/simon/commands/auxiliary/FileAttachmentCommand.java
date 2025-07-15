package org.taranix.cli.simon.commands.auxiliary;


import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.variables.LocalFileVariable;

@CafeCommand(command = "fa",
        description = "File to be send to AI",
        longCommand = "file-attachment",
        noOfArgs = 1)
class FileAttachmentCommand {

    @CafeCommandRun
    LocalFileVariable execute(CafeCommandArguments arguments) {
        String path = arguments.getCliValues()[0];
        return LocalFileVariable.from(path);
    }
}
