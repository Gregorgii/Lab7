package util;

import util.workingWithClient.ServerSocketWorker;
import util.workingWithCommand.CommandManager;

import java.io.IOException;

public class RequestThread extends Thread {
    private final ServerSocketWorker serverSocketWorker;
    private final CommandManager commandManager;

    public RequestThread(ServerSocketWorker serverSocketWorker, CommandManager commandManager) {
        this.serverSocketWorker = serverSocketWorker;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        while (commandManager.getStatusOfCommandListening()) {
            try {
                Request acceptedRequest = serverSocketWorker.receiveRequest();
                Response responseToSend = commandManager.executeClientCommand(acceptedRequest);
                serverSocketWorker.sendResponse(responseToSend);
            } catch (IOException e) {
                System.out.println("Error at work with client's request");
            } catch (ClassNotFoundException e) {
                System.out.println("Incorrect request from client");
            }
        }
        serverSocketWorker.stopServer();
    }
}