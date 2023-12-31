package commands.clientCommands;


import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class ClearCommand extends AbstractClientCommand{
    private final CommandProcessor commandProcessor;

    public ClearCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("clear")
                .withQuantityOfArgs(0)
                .withDescription("clear collection")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request) {
        return commandProcessor.clear(request);
    }


}
