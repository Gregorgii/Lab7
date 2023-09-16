import generators.CollectionManagerGenerator;
import managers.CollectionManager;
import util.ConsoleThread;
import util.RequestThread;
import util.workingWithClient.GeneratorServerSocketWorker;
import util.workingWithClient.ServerSocketWorker;
import util.workingWithCommand.CommandManager;
import util.workingWithCommand.FileManager;
import util.workingWithCommand.ServerCommandListener;

import java.io.IOException;
import java.util.Scanner;

public class Server {
    private final String fileName;
    private final ServerCommandListener serverCommandListener = new ServerCommandListener();
    public Server(String fileName){ this.fileName = fileName; }

    public void startServer() throws IOException {
        try {
            FileManager fileManager = new FileManager(fileName);
            CollectionManagerGenerator collectionManagerGenerator = new CollectionManagerGenerator(fileManager);
            CollectionManager collectionManager = collectionManagerGenerator.getCollectionManager();
            CommandManager commandManager = new CommandManager(collectionManager);
            Scanner scanner = new Scanner(System.in);
            GeneratorServerSocketWorker generatorServerSocketWorker = new GeneratorServerSocketWorker(scanner);
            ServerSocketWorker serverSocketWorker = generatorServerSocketWorker.getServerSocketWorker();
            RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager);
            ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager, scanner);
            requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
