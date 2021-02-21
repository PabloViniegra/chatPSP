package controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

@Log
public class ClientSocket extends Thread {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Listen listen;

    public ClientSocket(Socket socket, Listen listen) {
        this.socket = socket;
        this.listen = listen;
    }

    @Override
    public void run() {
        try {
            log.info("El cliente ha empezado en el hilo. El Datapackage debería llegar");
            DataPackageChat dataPackageChat;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while ((dataPackageChat = (DataPackageChat) objectInputStream.readObject()) != null) {
                log.info("Se ha recibido un mensaje: " + dataPackageChat.getMessage());
                if (!dataPackageChat.getNick().equals("userConnected")) {
                    listen.sendToAll(dataPackageChat);
                } else {
                    DataPackageChat dataPackageChat1 = new DataPackageChat("","Se ha conectado " + dataPackageChat.getMessage());
                    listen.sendToAll(dataPackageChat1);
                }
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void forwardMessage(DataPackageChat dataPackageChat) {
        try {
            objectOutputStream.writeObject(dataPackageChat);
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }
}
