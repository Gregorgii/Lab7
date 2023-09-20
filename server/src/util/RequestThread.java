package util;

import managers.UsersManager;
import things.RequestType;
import util.workingWithClient.ServerSocketWorker;
import util.workingWithCommand.CommandManager;

import java.io.IOException;
import java.util.concurrent.*;

public class RequestThread extends Thread {
    private final ServerSocketWorker serverSocketWorker;
    private final CommandManager commandManager;
    private final UsersManager userManager;
    private final ExecutorService fixedService = Executors.newFixedThreadPool(4);
    private final ForkJoinPool forkJoinPool = new ForkJoinPool(4);

    public RequestThread(ServerSocketWorker serverSocketWorker, CommandManager commandManager, UsersManager userManager) {
        this.serverSocketWorker = serverSocketWorker;
        this.commandManager = commandManager;
        this.userManager =  userManager;
    }

    @Override
    public void run() {
        while (commandManager.getStatusOfCommandListening()) {
            try {
                ForkJoinTask<RequestWithAddress> listenTask = forkJoinPool.submit(serverSocketWorker::receiveRequest);
                RequestWithAddress acceptedRequest = listenTask.get();
                CompletableFuture.supplyAsync(acceptedRequest::getRequest)
                        .thenApplyAsync(request -> {
                            if (request.getRequestTypeArgument().equals(RequestType.COMMAND)) {
                                return commandManager.executeClientCommand(request);
                            } else if (request.getRequestTypeArgument().equals(RequestType.LOGIN)) {
                                return userManager.loginUser(request);
                            } else if (request.getRequestTypeArgument().equals(RequestType.REGISTER)) {
                                return userManager.registerNewUser(request);
                            } else {
                                return null;
                            }
                        }, fixedService)
                        .thenAcceptAsync(responseToSend -> {
                            try {
                                serverSocketWorker.sendResponse(responseToSend, acceptedRequest.getSocketAddress());
                            } catch (IOException e) {
                                System.out.println("Error while sending answer to client");
                            }
                        }, fixedService);
            } catch (ExecutionException e) {
                System.out.println("Error while working with request from client");
            } catch (InterruptedException e) {
                System.out.println("Thread was stopped");
            }
        }
        serverSocketWorker.stopServer();
    }
}
