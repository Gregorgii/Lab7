package commands.clientCommands;

import managers.CollectionManager;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class RemoveByIdCommand extends AbstractClientCommand{
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("remove_by_id")
                .withQuantityOfArgs(1)
                .withDescription("remove element by his id")
                .withDescriptionOfArgs("need a Integer greater than 0")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request){
        try {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager
                            .removeById(request.getIntegerArgument())));
        } catch (IllegalArgumentException e){
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage()));
        }
    }
}
