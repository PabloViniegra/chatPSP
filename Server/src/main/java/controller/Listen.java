package controller;

import lombok.extern.java.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

@Log
public class Listen extends Thread {
    private List<ClientSocket> sockets;
    private ServerSocket server;

    public Listen(List<ClientSocket> sockets, ServerSocket server) {
        this.sockets = sockets;
        this.server = server;
    }

    public void run() {
        ManageMessages manageMessages = new ManageMessages(sockets);
        manageMessages.start();
        log.info("Antes del while true del Listen del server");
        while (true) {
            Socket client;
            try {
                client = server.accept();
                log.info("Se ha conectado un cliente: [Su Host]: " + client.getInetAddress());
                ClientSocket clientSocket = new ClientSocket(client, manageMessages);
                sockets.add(clientSocket);
                clientSocket.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
