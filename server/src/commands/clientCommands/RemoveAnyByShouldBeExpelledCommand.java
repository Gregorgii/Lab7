package commands.clientCommands;
import managers.*;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class RemoveAnyByShouldBeExpelledCommand extends AbstractClientCommand{
    private final CollectionManager collectionManager;

    public RemoveAnyByShouldBeExpelledCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("remove_any_by_should_be_expelled")
                .withQuantityOfArgs(1)
                .withDescription("remove any elements if field should be expelled equals inputted")
                .withDescriptionOfArgs("Integer should be expelled to compare")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        try {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager
                            .removeAnyByShouldBeExpelled(request.getIntegerArgument())));
        } catch (IllegalArgumentException e){
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage()));
        }
    }
}
