package util;

import util.workingWithCommand.CommandManager;
import util.workingWithCommand.ServerCommandListener;

import java.util.Scanner;

public class ConsoleThread extends Thread {
    private final ServerCommandListener commandListener;
    private final CommandManager commandManager;
    private final Scanner scanner;

    public ConsoleThread(ServerCommandListener commandListener, CommandManager commandManager, Scanner scanner) {
        this.commandListener = commandListener;
        this.commandManager = commandManager;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (commandManager.getStatusOfCommandListening()) {
            String command = commandListener.readCommand(scanner);
            System.out.println(commandManager.executeServerCommand(command));
        }
    }
}