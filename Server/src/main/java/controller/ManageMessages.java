package controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;

import java.util.List;

@Log
public class ManageMessages extends Thread {
    public List<ClientSocket> socket;

    public ManageMessages(List<ClientSocket> socket) {
        this.socket = socket;
    }

    public void run() {
        log.info("El hilo ManageMessages ha sido lanzado");
    }

    public void sendToAll(DataPackageChat dataPackageChat) {
        socket.forEach(s -> {
            s.forwardMessage(dataPackageChat);
        });
    }

    public void sendToAll(String linea) {
        log.info("Enviando mensajes a todos los clientes");
        socket.forEach(s -> {
            s.forwardMessage(linea);
        });
    }

}
