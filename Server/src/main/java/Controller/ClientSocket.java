package Controller;

import impml.DataPackageChat;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

public class ClientSocket extends Thread {
    private Socket socket;
    private DataPackageChat dataPackageChat;

    public ClientSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        PrintWriter printWriter;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            DataPackageChat dataPackageChat = (DataPackageChat) objectInputStream.readObject();
            Main.dataPackages.add(dataPackageChat);
            objectOutputStream.writeObject(dataPackageChat);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void enterChat() {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(this.socket.getOutputStream());
            printWriter.println("Te has unido al chat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
