package commands.clientCommands;

import managers.*;
import util.Request;
import util.Response;
import util.ResponseBuilder;

public class InfoCommand extends AbstractClientCommand{

    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("Info")
                .withQuantityOfArgs(0)
                .withDescription("print standard information about collection to stream")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    public Response executeCommand(Request request){
        return new Response(new ResponseBuilder()
                .withMessageToResponse(collectionManager.info()));
    }
}
