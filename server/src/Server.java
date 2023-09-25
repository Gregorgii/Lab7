import DB.Connector;
import DB.DBManager;
import managers.CollectionManager;
import managers.UsersManager;
import util.ConsoleThread;
import util.RequestThread;
import util.workingWithClient.GeneratorServerSocketWorker;
import util.workingWithClient.ServerSocketWorker;
import util.workingWithCommand.CommandManager;
import util.workingWithCommand.CommandProcessor;
import util.workingWithCommand.ServerCommandListener;

import java.sql.SQLException;
import java.util.Scanner;

public class Server {
    private final ServerCommandListener serverCommandListener = new ServerCommandListener();
    private Connector dbConnector;
    private DBManager dbManager;
    private UsersManager usersManager;
    private CommandProcessor commandProcessor;
    private CommandManager commandManager;
    private CollectionManager collectionManager;

    public void startServer() {
        dbConnector = new Connector();
        dbManager = new DBManager(dbConnector);
        usersManager = new UsersManager(dbManager);
        collectionManager = new CollectionManager();
        commandProcessor = new CommandProcessor(dbManager, collectionManager);
        commandManager = new CommandManager(commandProcessor);
        try {
            collectionManager.setGroupCollection(dbManager.loadCollection());
        } catch (SQLException e) {
            System.out.println("Ошибка при инициализации коллекции");
            e.printStackTrace();
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);
        GeneratorServerSocketWorker generatorServerSocketWorker = new GeneratorServerSocketWorker(scanner);
        ServerSocketWorker serverSocketWorker = generatorServerSocketWorker.getServerSocketWorker();
        RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager, usersManager);
        ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager, scanner);
        requestThread.start();
        consoleThread.start();
    }
}
