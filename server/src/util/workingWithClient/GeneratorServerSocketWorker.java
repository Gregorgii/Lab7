package util.workingWithClient;


import util.SocketInitializer;

import java.net.SocketException;
import java.util.Scanner;

public class GeneratorServerSocketWorker {

    private ServerSocketWorker serverSocketWorker;

    public GeneratorServerSocketWorker(Scanner scanner) {
        setPort(scanner);
    }

    private void setPort(Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            try {
                Integer port = SocketInitializer.askForPort(scanner);
                serverSocketWorker = new ServerSocketWorker();
                if (port != null) {
                    serverSocketWorker.setPort(port);
                }
                isRunning = false;
            } catch (SocketException e) {
                System.out.println("Ошибка при установке порта");
            }
        }

    }

    public ServerSocketWorker getServerSocketWorker() {
        return serverSocketWorker;
    }
}
