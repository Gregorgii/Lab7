package commands.clientCommands;
import managers.*;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class RemoveGreaterCommand extends AbstractClientCommand{

    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("remove_greater")
                .withQuantityOfArgs(0)
                .withDescription("remove all elements greater than input")
                .withDescriptionOfArgs("study group to compare")
                .withGeneratesStudyGroup(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request){
        try {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager
                            .removeGreater(request.getStudyGroupArgument())));
        } catch (IllegalArgumentException e){
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage()));
        }
    }
}
