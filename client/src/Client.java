import generators.ConsoleStudyGroupGenerator;
import things.StudyGroup;
import util.workingWithCommand.CommandManager;
import util.workingWithServer.ClientSocketWorker;
import util.workingWithServer.GeneratorClientSocketWorker;
import util.workingWithServer.GeneratorUser;

import java.util.List;
import java.util.Scanner;

public class Client {
    public void startClient() {
        try (Scanner scanner = new Scanner(System.in)) {
            GeneratorClientSocketWorker generatorClientSocketWorker = new GeneratorClientSocketWorker(scanner);
            ClientSocketWorker clientSocketWorker = generatorClientSocketWorker.getClientSocketWorker();
            GeneratorUser generatorUser = new GeneratorUser(scanner, clientSocketWorker);
            List<String> user = generatorUser.getUser();
            StudyGroup.getStudyGroupGenerator().changeCondition(new ConsoleStudyGroupGenerator(scanner));
            CommandManager.runConsoleCycle(scanner, clientSocketWorker, user);
        }
    }
}
