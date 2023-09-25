package commands;

import commands.clientCommands.*;
import commands.serverCommands.AbstractServerCommand;
import commands.serverCommands.ExitCommand;
import commands.serverCommands.ServerHelpCommand;
import util.workingWithCommand.CommandProcessor;

import java.util.HashMap;

public class AvailableCommands {
    public static final HashMap<String, AbstractClientCommand> CLIENT_AVAILABLE_COMMANDS = new HashMap<>();
    public static final HashMap<String, AbstractServerCommand> SERVER_AVAILABLE_COMMANDS = new HashMap<>();

    public AvailableCommands(CommandProcessor commandProcessor) {
        AbstractClientCommand clientHelpCommand = new ClientHelpCommand(CLIENT_AVAILABLE_COMMANDS);
        AbstractClientCommand infoCommand =   new InfoCommand(commandProcessor);
        AbstractClientCommand showCommand =  new ShowCommand(commandProcessor);
        AbstractClientCommand addCommand = new AddCommand(commandProcessor);
        AbstractClientCommand updateCommand = new UpdateCommand(commandProcessor);
        AbstractClientCommand removeByIdCommand = new RemoveByIdCommand(commandProcessor);
        AbstractClientCommand clearCommand = new ClearCommand(commandProcessor);
        AbstractClientCommand countGreaterThanTransferredStudentsCommand = new CountGreaterThanTransferredStudentsCommand(commandProcessor);
        AbstractClientCommand printFieldDescendingSemesterEnumCommand = new PrintFieldDescendingSemesterEnumCommand(commandProcessor);
        AbstractClientCommand removeFirstCommand = new RemoveFirstCommand(commandProcessor);
        AbstractClientCommand addIfMaxCommand = new AddIfMaxCommand(commandProcessor);
        AbstractClientCommand removeGreaterCommand = new RemoveGreaterCommand(commandProcessor);
        AbstractClientCommand removeAnyByShouldBeExpelled = new RemoveAnyByShouldBeExpelledCommand(commandProcessor);


        CLIENT_AVAILABLE_COMMANDS.put(clientHelpCommand.getName(), clientHelpCommand);
        CLIENT_AVAILABLE_COMMANDS.put(infoCommand.getName(), infoCommand);
        CLIENT_AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        CLIENT_AVAILABLE_COMMANDS.put(addCommand.getName(), addCommand);
        CLIENT_AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        CLIENT_AVAILABLE_COMMANDS.put(removeByIdCommand.getName(), removeByIdCommand);
        CLIENT_AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
        CLIENT_AVAILABLE_COMMANDS.put(countGreaterThanTransferredStudentsCommand.getName(), countGreaterThanTransferredStudentsCommand);
        CLIENT_AVAILABLE_COMMANDS.put(printFieldDescendingSemesterEnumCommand.getName(), printFieldDescendingSemesterEnumCommand);
        CLIENT_AVAILABLE_COMMANDS.put(removeFirstCommand.getName(), removeFirstCommand);
        CLIENT_AVAILABLE_COMMANDS.put(addIfMaxCommand.getName(), addIfMaxCommand);
        CLIENT_AVAILABLE_COMMANDS.put(removeGreaterCommand.getName(), removeGreaterCommand);
        CLIENT_AVAILABLE_COMMANDS.put(removeAnyByShouldBeExpelled.getName(), removeAnyByShouldBeExpelled);




        AbstractServerCommand serverHelpCommand = new ServerHelpCommand(SERVER_AVAILABLE_COMMANDS);
        AbstractServerCommand exitCommand = new ExitCommand();

        SERVER_AVAILABLE_COMMANDS.put(serverHelpCommand.getName(), serverHelpCommand);
        SERVER_AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
    }
}

