package controller;

import impml.DataPackageChat;

import java.util.List;

public class ManageMessages extends Thread{
    public List<ClientSocket> socket;

    public ManageMessages(List<ClientSocket> socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("El manage ha empezado");
    }

     public void sendToAll(DataPackageChat dataPackageChat) {
        socket.forEach(s -> {
            s.forwardMessage(dataPackageChat);
        });
     }

    public void sendToAll(String linea) {
        System.out.println("Enviando mensajes s todos los clientes");
        socket.forEach(s -> {
            s.forwardMessage(linea);
        });
    }

}
