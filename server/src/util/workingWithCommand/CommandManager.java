package util.workingWithCommand;

import commands.AvailableCommands;
import util.Request;
import util.Response;
import util.ResponseBuilder;

import java.sql.SQLException;

public class CommandManager {
    private static boolean statusOfCommandListening = true;
    private final AvailableCommands availableCommands;


    public CommandManager(CommandProcessor commandProcessor) {
        availableCommands = new AvailableCommands(commandProcessor);
    }

    public Response executeClientCommand(Request request) throws NullPointerException {
        try {
        return AvailableCommands.CLIENT_AVAILABLE_COMMANDS.get(request.getCommandName()).executeCommand(request);
        } catch (NullPointerException e){
            return (new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()))) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String executeServerCommand(String commandName) {
        if (AvailableCommands.SERVER_AVAILABLE_COMMANDS.containsKey(commandName)) {
            return AvailableCommands.SERVER_AVAILABLE_COMMANDS.get(commandName).executeCommand();
        } else {
            return ("Command isn't exist, print HELP to see available commands");
        }
    }

    public static void changeStatus() {
        statusOfCommandListening = !statusOfCommandListening;
    }

    public boolean getStatusOfCommandListening() {
        return statusOfCommandListening;
    }

    public static void setStatusOfCommandListening(boolean statusOfCommandListening) {
        CommandManager.statusOfCommandListening = statusOfCommandListening;
    }


    public static boolean isStatusOfCommandListening() {
        return statusOfCommandListening;
    }

    public AvailableCommands getAvailableCommands() {
        return availableCommands;
    }
}

