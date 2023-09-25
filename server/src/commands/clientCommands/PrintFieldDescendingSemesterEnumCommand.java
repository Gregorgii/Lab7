package commands.clientCommands;

import util.Request;
import util.Response;
import util.workingWithCommand.CommandProcessor;

public class PrintFieldDescendingSemesterEnumCommand extends AbstractClientCommand {
    private final CommandProcessor commandProcessor;

    public PrintFieldDescendingSemesterEnumCommand(CommandProcessor commandProcessor) {
        super(new AbstractCommandBuilder()
                .withName("print_field_descending_semester")
                .withQuantityOfArgs(0)
                .withDescription("print all fields with info 'bout semester in descending")
                .withGeneratesStudyGroup(false));
        this.commandProcessor = commandProcessor;
    }

    @Override
    public Response executeCommand(Request request) {
        return commandProcessor.printFieldDescendingSemester(request);
    }
}
