package commands.clientCommands;
import managers.*;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class CountGreaterThanTransferredStudentsCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public CountGreaterThanTransferredStudentsCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("count_greater_than_transferred_students")
                .withQuantityOfArgs(1)
                .withDescription("count greater than written count of transferred students")
                .withDescriptionOfArgs("count for compare, Integer")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request){
        try{
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager
                            .countGreaterThanTransferredStudents(request.getIntegerArgument())));
        } catch (IllegalArgumentException e){
            return new Response((new ResponseBuilder()
                    .withMessageToResponse(e.getMessage())));
        }
    }
}
