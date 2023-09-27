package commands;

import generators.ConsoleStudyGroupGenerator;
import generators.ScriptStudyGroupGenerator;
import things.StudyGroup;
import util.Validator;
import util.workingWithCommand.CommandListener;
import util.workingWithCommand.CommandManager;
import util.workingWithCommand.CommandToSend;
import util.workingWithServer.ClientSocketWorker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public final class ExecuteScriptCommand {
    private static final HashSet<String> HASH_SET = new HashSet<>();

    private ExecuteScriptCommand() {
    }

    public static void executeCommand(String[] commandArgs, ClientSocketWorker clientSocketWorker, List<String> userInfo) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, 1);
            String fileName = commandArgs[0];
            File file = checkScript(fileName);
            HASH_SET.add(fileName);
            Scanner scanner = new Scanner(file);
            StudyGroup.getStudyGroupGenerator().changeCondition(new ScriptStudyGroupGenerator(scanner));
            CommandListener commandListener = CommandManager.getCommandListener();
            do {
                CommandToSend command = commandListener.readCommandFromScript(scanner);
                CommandManager.performCommand(command, clientSocketWorker, userInfo);
            } while (scanner.hasNextLine());
            HASH_SET.remove(commandArgs[0]);
            scanner.close();
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            Scanner scanner = new Scanner(System.in);
            StudyGroup.getStudyGroupGenerator().changeCondition(new ConsoleStudyGroupGenerator(scanner));
            scanner.close();
        }
    }

    private static File checkScript(String fileName) throws IllegalArgumentException, FileNotFoundException {
        if (HASH_SET.contains(fileName)) {
            throw new IllegalArgumentException("Возможно зацикливание");
        }
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден");
        } else if (!file.canRead()) {
            throw new FileNotFoundException("Нет доступа на чтение");
        } else {
            return file;
        }
    }
}