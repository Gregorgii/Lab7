package commands;

import commands.clientCommands.*;
import commands.serverCommands.AbstractServerCommand;
import commands.serverCommands.ExitCommand;
import commands.serverCommands.SaveCommand;
import commands.serverCommands.ServerHelpCommand;
import managers.CollectionManager;

import java.util.HashMap;

public class AvailableCommands {
    public static final HashMap<String, AbstractClientCommand> CLIENT_AVAILABLE_COMMANDS = new HashMap<>();
    public static final HashMap<String, AbstractServerCommand> SERVER_AVAILABLE_COMMANDS = new HashMap<>();

    public AvailableCommands(CollectionManager collectionManager) {
        AbstractClientCommand clientHelpCommand = new ClientHelpCommand(CLIENT_AVAILABLE_COMMANDS);
        AbstractClientCommand infoCommand =   new InfoCommand(collectionManager);
        AbstractClientCommand showCommand =  new ShowCommand(collectionManager);
        AbstractClientCommand addCommand = new AddCommand(collectionManager);
        AbstractClientCommand updateCommand = new UpdateCommand(collectionManager);
        AbstractClientCommand removeByIdCommand = new RemoveByIdCommand(collectionManager);
        AbstractClientCommand clearCommand = new ClearCommand(collectionManager);
        AbstractClientCommand countGreaterThanTransferredStudentsCommand = new CountGreaterThanTransferredStudentsCommand(collectionManager);
        AbstractClientCommand printFieldDescendingSemesterEnumCommand = new PrintFieldDecsendingSemesterEnumCommand(collectionManager);
        AbstractClientCommand removeFirstCommand = new RemoveFirstCommand(collectionManager);
        AbstractClientCommand addIfMaxCommand = new AddIfMaxCommand(collectionManager);
        AbstractClientCommand removeGreaterCommand = new RemoveGreaterCommand(collectionManager);
        AbstractClientCommand removeAnyByShouldBeExpelled = new RemoveAnyByShouldBeExpelledCommand(collectionManager);


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
        AbstractServerCommand saveCommand = new SaveCommand(collectionManager);
        AbstractServerCommand exitCommand = new ExitCommand(collectionManager);

        SERVER_AVAILABLE_COMMANDS.put(serverHelpCommand.getName(), serverHelpCommand);
        SERVER_AVAILABLE_COMMANDS.put(saveCommand.getName(), saveCommand);
        SERVER_AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
    }
}

