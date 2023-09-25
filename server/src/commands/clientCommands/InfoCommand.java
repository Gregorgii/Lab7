package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class InfoCommand extends AbstractClientCommand{

    private final CommandProcessor commandProcessor;

    public InfoCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("Info")
                .withQuantityOfArgs(0)
                .withDescription("print standard information about collection to stream")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    public Response executeCommand(Request request){
        return commandProcessor.info(request);
    }
}
