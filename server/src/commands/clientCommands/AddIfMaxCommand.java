package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class AddIfMaxCommand extends AbstractClientCommand{
    private final CommandProcessor commandProcessor;

    public AddIfMaxCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("add_if_max")
                .withQuantityOfArgs(0)
                .withDescription("add element in collection of count of students greater than groups in collection")
                .withDescriptionOfArgs("study group to compare")
                .withGeneratesStudyGroup(true));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request) {
        return commandProcessor.addIfMax(request);
    }
}
