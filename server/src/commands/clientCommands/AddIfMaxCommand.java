package commands.clientCommands;

import managers.*;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class AddIfMaxCommand extends AbstractClientCommand{
    private final CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("add_if_max")
                .withQuantityOfArgs(0)
                .withDescription("add element in collection of count of students greater than groups in collection")
                .withDescriptionOfArgs("study group to compare")
                .withGeneratesStudyGroup(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request){
        try {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager
                            .addIfMax(request.getStudyGroupArgument())));
        } catch (IllegalArgumentException e){
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage()));
        }
    }
}
