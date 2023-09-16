package commands.serverCommands;


import managers.CollectionManager;
import util.workingWithCommand.CommandManager;

import java.io.IOException;
import java.util.Scanner;

import static util.SocketInitializer.acceptAnswer;

public class ExitCommand extends AbstractServerCommand {

    private final Scanner scanner = new Scanner(System.in);
    private final CollectionManager collectionManager;

    public ExitCommand(CollectionManager collectionManager){
        super(new AbstractServerCommand.AbstractCommandBuilder()
                .withName("exit")
                .withDescription("exit from program "));
        this.collectionManager = collectionManager;
    }

    @Override
    public String executeCommand(){
        askForSave(scanner);
        CommandManager.changeStatus();
        System.out.println("Server was stopped");
        scanner.close();
        System.exit(0);
        return "Server was stopped";
    }

    private void askForSave(Scanner scanner) {
        String question = "R u want to save? Print y/n";
        System.out.println(question);
        boolean isRunning = true;
        while (isRunning){
            try {
                boolean answer = acceptAnswer(scanner);
                save(answer);
                isRunning = false;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void save(boolean answer) {
        try {
            if (answer) {
                collectionManager.getFileManager().fileWriter(collectionManager);
                System.out.println(("Collection was saved successfully"));
            } else {
                System.out.println("Changes has been lost");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
