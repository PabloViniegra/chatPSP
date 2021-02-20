package Controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

@Log
public class ListenFromServer extends Thread{
    private Socket client;
    private JTextArea txtarea;
    private Logger logger;
    public ListenFromServer(Socket client, JTextArea textArea) {
        this.client = client;
        this.txtarea = textArea;
        this.logger = Logger.getLogger(getClass().getName());
    }

    public void run() {
        DataPackageChat dataPackageChat;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(client.getInputStream());
            synchronized (this) {
                while ((dataPackageChat = (DataPackageChat) objectInputStream.readObject()) != null) {
                    log.info("ID del usuario y su mensaje" + dataPackageChat.getUuid() + " " + dataPackageChat.getMessage());
                    txtarea.append("\n" + dataPackageChat.getNick() + ": " + dataPackageChat.getMessage() + "\n" + dataPackageChat.getTime());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
