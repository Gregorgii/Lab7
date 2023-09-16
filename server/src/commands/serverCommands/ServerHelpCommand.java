package commands.serverCommands;

import java.util.HashMap;
public class ServerHelpCommand extends AbstractServerCommand {

    private final HashMap<String, AbstractServerCommand> availableCommands;

    public ServerHelpCommand(HashMap<String, AbstractServerCommand> availableCommands) {
        super(new AbstractServerCommand.AbstractCommandBuilder()
                .withName("help")
                .withDescription("print info about available commands"));
        this.availableCommands = availableCommands;
    }

    @Override
    public String executeCommand() {
        StringBuilder stringBuilder = new StringBuilder("Available commands:\n");
        for (AbstractServerCommand command : availableCommands.values()) {
            stringBuilder.append(command.toString()).append("\n");
        }
        return String.valueOf(stringBuilder);
    }
}