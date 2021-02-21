package controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Log
public class Listen extends Thread {
    private List<ClientSocket> sockets = new ArrayList<>();
    private ServerSocket server;

    public Listen(ServerSocket server) {
        this.server = server;
    }

    public void run() {

        log.info("Antes del while true del Listen del server");
        while (true) {
            Socket client;
            try {
                client = server.accept();
                log.info("Se ha conectado un cliente: [Su Host]: " + client.getInetAddress());
                ClientSocket clientSocket = new ClientSocket(client, this);
                sockets.add(clientSocket);
                clientSocket.start();
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
        }
    }

    public void sendToAll(DataPackageChat dataPackageChat) {
        sockets.forEach(s -> {
            log.info("Mensaje para enviar a todos: " + dataPackageChat.getMessage());
            s.forwardMessage(dataPackageChat);
        });
    }
}
