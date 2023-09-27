package util.workingWithCommand;

import commands.AvailableCommands;
import util.Request;
import util.Response;

public class CommandManager {
    private static boolean statusOfCommandListening = true;
    private final AvailableCommands availableCommands;

    public CommandManager(CommandProcessor commandProcessor) {
        availableCommands = new AvailableCommands(commandProcessor);
    }

    public static void changeStatus() {
        statusOfCommandListening = !statusOfCommandListening;
    }

    public static boolean isStatusOfCommandListening() {
        return statusOfCommandListening;
    }

    public static void setStatusOfCommandListening(boolean statusOfCommandListening) {
        CommandManager.statusOfCommandListening = statusOfCommandListening;
    }


    public Response executeClientCommand(Request request)  {
        return AvailableCommands.CLIENT_AVAILABLE_COMMANDS.get(request.getCommandName()).executeCommand(request);
    }

    public String executeServerCommand(String commandName) {
        if (AvailableCommands.SERVER_AVAILABLE_COMMANDS.containsKey(commandName)) {
            return AvailableCommands.SERVER_AVAILABLE_COMMANDS.get(commandName).executeCommand();
        } else {
            return ("Такой команды не существует, для того, чтобы увидеть список команд введите HELP");
        }
    }

    public boolean getStatusOfCommandListening() {
        return statusOfCommandListening;
    }

    public AvailableCommands getAvailableCommands() {
        return availableCommands;
    }
}
