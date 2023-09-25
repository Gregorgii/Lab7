package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class ShowCommand extends AbstractClientCommand {
    private final CommandProcessor commandProcessor;

    public ShowCommand(CommandProcessor commandProcessor) {
        super(new AbstractCommandBuilder()
                .withName("show")
                .withQuantityOfArgs(0)
                .withDescription("show all elements of collection in the standards output stream")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request) {
        return commandProcessor.show(request);
    }


}
