package commands.clientCommands;

import managers.*;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class PrintFieldDecsendingSemesterEnumCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public PrintFieldDecsendingSemesterEnumCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("print_field_descending_semester")
                .withQuantityOfArgs(0)
                .withDescription("print all fields with info 'bout semester in descending")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        try {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager
                            .printFieldDescendingSemesterEnum()));
        } catch(IllegalArgumentException e){
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage()));
        }
    }
}
