import generators.ConsoleStudyGroupGenerator;
import things.StudyGroup;
import util.workingWithCommand.CommandManager;
import util.workingWithServer.ClientSocketWorker;
import util.workingWithServer.GeneratorClientSocketWorker;

import java.util.Scanner;

public class Client {
    public void startClient() {
        try (Scanner scanner = new Scanner(System.in)) {
            GeneratorClientSocketWorker generatorClientSocketWorker = new GeneratorClientSocketWorker(scanner);
            ClientSocketWorker clientSocketWorker = generatorClientSocketWorker.getClientSocketWorker();
            StudyGroup.getStudyGroupGenerator().changeCondition(new ConsoleStudyGroupGenerator(new Scanner(System.in)));
            CommandManager.runConsoleCycle(scanner, clientSocketWorker);
        }
    }
}
