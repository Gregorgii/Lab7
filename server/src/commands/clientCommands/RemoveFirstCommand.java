package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class RemoveFirstCommand extends AbstractClientCommand{
    private final CommandProcessor commandProcessor;

    public RemoveFirstCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("remove_first")
                .withQuantityOfArgs(0)
                .withDescription("print first element and remove it")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request){ return commandProcessor.removeFirst(request);}
    }

