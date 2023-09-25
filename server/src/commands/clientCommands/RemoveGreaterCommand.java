package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class RemoveGreaterCommand extends AbstractClientCommand{

    private final CommandProcessor commandProcessor;

    public RemoveGreaterCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("remove_greater")
                .withQuantityOfArgs(0)
                .withDescription("remove all elements greater than input")
                .withDescriptionOfArgs("study group to compare")
                .withGeneratesStudyGroup(true));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request){
        return commandProcessor.removeGreater(request);
    }
}
