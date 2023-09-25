package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class RemoveAnyByShouldBeExpelledCommand extends AbstractClientCommand{
    private final CommandProcessor commandProcessor;
    public RemoveAnyByShouldBeExpelledCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("remove_any_by_should_be_expelled")
                .withQuantityOfArgs(1)
                .withDescription("remove any elements if field should be expelled equals inputted")
                .withDescriptionOfArgs("Integer should be expelled to compare")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request) {
        return commandProcessor.removeAnyByShouldBeExpelled(request);
    }
}
