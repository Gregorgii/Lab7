package commands.clientCommands;


import managers.CollectionManager;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class ClearCommand extends AbstractClientCommand{
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("clear")
                .withQuantityOfArgs(0)
                .withDescription("clear collection")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response(new ResponseBuilder()
                .withMessageToResponse(collectionManager.clear()));
    }


}
