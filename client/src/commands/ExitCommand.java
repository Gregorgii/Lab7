package commands;

import util.Validator;
import util.workingWithCommand.CommandManager;

public class ExitCommand {
    private ExitCommand(){
    }

    public static void executeCommand(String[] commandArgs){
        try {
            Validator.validateQuantityOfArgs(commandArgs, 0);
            System.out.println("Exit from program now");
            CommandManager.changeStatus();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

}
