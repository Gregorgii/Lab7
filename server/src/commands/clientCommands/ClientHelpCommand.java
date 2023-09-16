package commands.clientCommands;

import util.Request;
import util.Response;
import util.ResponseBuilder;

import java.util.HashMap;

public class ClientHelpCommand extends AbstractClientCommand {
    private final HashMap<String, AbstractClientCommand> availableCommands;

    public ClientHelpCommand(HashMap<String, AbstractClientCommand> availableCommands) {
        super(new AbstractCommandBuilder()
                .withName("help")
                .withQuantityOfArgs(0)
                .withDescription("print info about available commands")
                .withGeneratesStudyGroup(false));
        this.availableCommands = availableCommands;
    }

    @Override
    public Response executeCommand(Request request) {
        StringBuilder stringBuilder = new StringBuilder("Available commands:\n");
        for (AbstractClientCommand command : availableCommands.values()) {
            stringBuilder.append(command.toString()).append("\n");
        }
        return new Response(new ResponseBuilder()
                .withMessageToResponse(String.valueOf(stringBuilder)));
    }
}
