package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Listen extends Thread{
    private List<ClientSocket> sockets;
    private ServerSocket server;

    public Listen(List<ClientSocket> sockets, ServerSocket server) {
        this.sockets = sockets;
        this.server = server;
    }

    public void run() {
        ManageMessages manageMessages = new ManageMessages(sockets);
        manageMessages.start();
        System.out.println("Antes del while true del Listen");
        while (true) {

            Socket client;
            try {
                client = server.accept();
                System.out.println("Se ha conectado un cliente");
                ClientSocket clientSocket = new ClientSocket(client,manageMessages);
                sockets.add(clientSocket);
                clientSocket.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
