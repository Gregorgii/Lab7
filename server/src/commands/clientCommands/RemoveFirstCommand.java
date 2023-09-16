package commands.clientCommands;

import util.Request;
import util.Response;
import util.ResponseBuilder;
import managers.*;

public class RemoveFirstCommand extends AbstractClientCommand{
    private final CollectionManager collectionManager;

    public RemoveFirstCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("remove_first")
                .withQuantityOfArgs(0)
                .withDescription("print first element and remove it")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request){
        try{
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager.removeFirst()));
        } catch (IllegalArgumentException e){
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage()));
        }
    }
}
