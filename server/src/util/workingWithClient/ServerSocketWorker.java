package util.workingWithClient;


import util.DeSerializer;
import util.RequestWithAddress;
import util.Response;
import util.Serializer;

import java.io.IOException;
import java.net.*;

public class ServerSocketWorker {
    private static final int DEFAULT_PORT = 2000;
    private DatagramSocket datagramSocket;
    private int port = DEFAULT_PORT;
    private SocketAddress clientAddress;

    public ServerSocketWorker() throws SocketException {
        try {
            datagramSocket = new DatagramSocket(port, InetAddress.getByName("localhost"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setPort(int port) throws SocketException {
        this.port = port;
        try {
            datagramSocket = new DatagramSocket(port, InetAddress.getByName("localhost"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        datagramSocket.close();
    }

    public RequestWithAddress receiveRequest() throws IOException, ClassNotFoundException {
        int receivedSize = datagramSocket.getReceiveBufferSize();
        byte[] byteArray = new byte[receivedSize];
        DatagramPacket dpToReceive = new DatagramPacket(byteArray, byteArray.length);
        datagramSocket.receive(dpToReceive);
        clientAddress = dpToReceive.getSocketAddress();
        byteArray = dpToReceive.getData();
        return new RequestWithAddress(DeSerializer.deSerializeRequest(byteArray), clientAddress);
    }

    public void sendResponse(Response response, SocketAddress socketAddress) throws IOException {
        byte[] bufferToSend = Serializer.serializeResponse(response);
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, socketAddress);
        datagramSocket.send(datagramPacket);
    }
}
