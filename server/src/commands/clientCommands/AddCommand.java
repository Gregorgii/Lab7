package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;


public class AddCommand extends AbstractClientCommand {
    private final CommandProcessor commandProcessor;


    public AddCommand(CommandProcessor commandProcessor) {
        super(new AbstractCommandBuilder()
                .withName("add")
                .withQuantityOfArgs(0)
                .withDescription("Add new elem to collection")
                .withGeneratesStudyGroup(true));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request){
        return commandProcessor.add(request);
    }

}