package org.taranix.cli.simon.commands;


import org.taranix.cafe.beans.annotations.CafePrimary;
import org.taranix.cafe.shell.annotations.CafeCommand;
import org.taranix.cafe.shell.annotations.CafeCommandRun;
import org.taranix.cafe.shell.commands.CafeCommandArguments;
import org.taranix.cli.simon.variables.PathVariable;

@CafeCommand(command = "a", description = "File to be send to AI", longCommand = "attachment", noOfArgs = 1)
class FileAttachmentCommand {

    @CafePrimary
    @CafeCommandRun
    PathVariable execute(CafeCommandArguments arguments) {
        String path = arguments.getCliValues()[0];
        return PathVariable.from(path);
    }
}
