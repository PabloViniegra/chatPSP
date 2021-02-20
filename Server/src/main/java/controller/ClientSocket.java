package controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
            new Thread(() -> {
                try {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println("se ha unido al chat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            TimeUnit.MILLISECONDS.sleep(500);
            DataPackageChat dataPackageChat;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while ((dataPackageChat = (DataPackageChat) objectInputStream.readObject()) != null) {
                log.info("Se ha recibido un mensaje: " + dataPackageChat.getMessage());
                manageMessages.sendToAll(dataPackageChat);
            }
        }catch (StreamCorruptedException e) {
            try {
                objectOutputStream.reset();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void forwardMessage(DataPackageChat dataPackageChat) {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
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
