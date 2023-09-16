import java.io.IOException;

public final class Main {

    private Main() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server("data.csv");
        server.startServer();
    }
}