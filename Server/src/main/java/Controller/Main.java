package Controller;

import impml.DataPackageChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<DataPackageChat> dataPackages = new ArrayList<>();
    public static List<Socket> sockets = new ArrayList<>();
    public static final int PORT = 9090;

    public static void main(String[] args) {
        PrintWriter printWriter;
        try {
            ServerSocket server = new ServerSocket(PORT);
            while (true) {
                Socket client = server.accept();
                printWriter = new PrintWriter(client.getOutputStream());
                printWriter.println("Te has unido al chat");
                sockets.add(client);
                sockets.forEach(socket -> {
                    new ClientSocket(socket).start();
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
