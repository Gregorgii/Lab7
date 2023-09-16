package util.workingWithServer;

import util.Request;
import util.workingWithCommand.CommandToSend;

import java.io.IOException;
import java.time.LocalTime;

public final class SendRequest {
    private SendRequest() {
    }

    public static boolean sendRequest(CommandToSend command, ClientSocketWorker clientSocketWorker) {
        RequestCreator requestCreator = new RequestCreator();
        try {
            Request request = requestCreator.createRequestOfCommand(command);
            request.setCurrentTime(LocalTime.now());
            request.setClientInfo(clientSocketWorker.getAddress() + " " + clientSocketWorker.getPort());
            clientSocketWorker.sendRequest(request);
            return true;
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}