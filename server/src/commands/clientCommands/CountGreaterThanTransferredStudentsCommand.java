package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class    CountGreaterThanTransferredStudentsCommand extends AbstractClientCommand {
    private final CommandProcessor commandProcessor;

    public CountGreaterThanTransferredStudentsCommand(CommandProcessor commandProcessor){
        super(new AbstractCommandBuilder()
                .withName("count_greater_than_transferred_students")
                .withQuantityOfArgs(1)
                .withDescription("count greater than written count of transferred students")
                .withDescriptionOfArgs("count for compare, Integer")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request){
      return commandProcessor.countGreaterThanTransferredStudents(request);
    }
}
