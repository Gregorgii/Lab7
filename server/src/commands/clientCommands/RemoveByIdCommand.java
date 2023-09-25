package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class RemoveByIdCommand extends AbstractClientCommand{
    private final CommandProcessor commandProcessor;

    public RemoveByIdCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("remove_by_id")
                .withQuantityOfArgs(1)
                .withDescription("remove element by his id")
                .withDescriptionOfArgs("need a Integer greater than 0")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request){
        return commandProcessor.removeById(request);
    }
}
