package commands.clientCommands;

import util.Request;
import managers.CollectionManager;
import util.Response;
import util.ResponseBuilder;


public class AddCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;


    public AddCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("add")
                .withQuantityOfArgs(0)
                .withDescription("Add new elem to collection")
                .withGeneratesStudyGroup(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request){
        return new Response(new ResponseBuilder()
                .withMessageToResponse(collectionManager
                        .add(request.getStudyGroupArgument())));
    }

}