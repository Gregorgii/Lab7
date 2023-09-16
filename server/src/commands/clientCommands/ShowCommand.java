package commands.clientCommands;

import util.Request;
import util.Response;
import util.ResponseBuilder;
import managers.*;

public class ShowCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("show")
                .withQuantityOfArgs(0)
                .withDescription("show all elements of collection in the standards output stream")
                .withGeneratesStudyGroup(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response(new ResponseBuilder()
                .withCollectionToResponse(collectionManager.getGroupCollection()));
    }


}
