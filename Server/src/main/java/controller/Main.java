package controller;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int PORT = 9090;
    public List<ClientSocket> socketsClientsThread = new ArrayList<>();

    public static void main(String[] args) {
        new Main().main();
    }

    public void main() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            Listen listen = new Listen(socketsClientsThread,server);
            listen.start();
            System.out.println("Estoy en la main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
