package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class UpdateCommand extends AbstractClientCommand {
    private final CommandProcessor commandProcessor;

    public UpdateCommand(CommandProcessor commandProcessor) {
        super(new AbstractCommandBuilder()
                .withName("update")
                .withQuantityOfArgs(1)
                .withDescription("update element in collection by inputted id")
                .withDescriptionOfArgs("need a id - number greater than 0")
                .withGeneratesStudyGroup(true));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request) {
       return commandProcessor.update(request);
    }
}
