package Controller;

import impml.DataPackageChat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<DataPackageChat> dataPackages = new ArrayList<>();
    public static List<ClientSocket> sockets = new ArrayList<>();
    public static final int PORT = 9090;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            while (true) {
                Socket client = server.accept();
                ClientSocket clientSocket = new ClientSocket(client);
                clientSocket.enterChat();
                sockets.add(clientSocket);
                sockets.forEach(Thread::start);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
