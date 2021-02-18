package controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;

import java.io.*;
import java.net.Socket;
@Log
public class ClientSocket extends Thread {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ManageMessages manageMessages;

    public ClientSocket(Socket socket, ManageMessages manageMessages) {
        this.socket = socket;
        this.manageMessages = manageMessages;
    }

    @Override
    public void run() {
        try {
            log.info("El cliente ha empezado en el hilo. El Datapackage debería llegar");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("se ha unido al chat");
            DataPackageChat dataPackageChat;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while ((dataPackageChat = (DataPackageChat) objectInputStream.readObject()) != null) {
                log.info("Se ha recibido un mensaje: " + dataPackageChat.getMessage());
                manageMessages.sendToAll(dataPackageChat);
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void forwardMessage(DataPackageChat dataPackageChat) {
        try {
            objectOutputStream.writeObject(dataPackageChat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forwardMessage(String linea) {
        try {
            objectOutputStream.writeUTF(linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
