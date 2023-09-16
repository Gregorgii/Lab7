package commands.clientCommands;

import util.Request;
import util.Response;
import util.ResponseBuilder;
import managers.*;

public class UpdateCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("update")
                .withQuantityOfArgs(1)
                .withDescription("update element in collection by inputted id")
                .withDescriptionOfArgs("need a id - number greater than 0")
                .withGeneratesStudyGroup(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        try {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager
                            .update(request.getIntegerArgument(), request.getStudyGroupArgument())));
        } catch (IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }
}
