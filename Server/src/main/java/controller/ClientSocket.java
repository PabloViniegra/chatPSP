package controller;

import impml.DataPackageChat;

import java.io.*;
import java.net.Socket;

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
            System.out.println("El cliente ha empezado");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("se ha unido al chat");
            DataPackageChat dataPackageChat;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while ((dataPackageChat = (DataPackageChat) objectInputStream.readObject()) != null) {
                System.out.println("Se ha recibido un mensaje");
                manageMessages.sendToAll(dataPackageChat);
            }
        } catch (IOException | ClassNotFoundException e) {
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
