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
    private ObjectInputStream objectInputStream;
    public ListenFromServer(Socket client, JTextArea textArea) {
        this.client = client;
        this.txtarea = textArea;
        this.logger = Logger.getLogger(getClass().getName());
    }

    public void run() {
        DataPackageChat dataPackageChat;
        try {
            objectInputStream = new ObjectInputStream(client.getInputStream());
                while ((dataPackageChat = (DataPackageChat) objectInputStream.readObject()) != null) {
                    log.info("ID del usuario y su mensaje" + dataPackageChat.getUuid() + " " + dataPackageChat.getMessage());
                    txtarea.append("\n" + dataPackageChat.getNick() + ": " + dataPackageChat.getMessage() + "\n" + dataPackageChat.getTime().toString().substring(0,8));
                }
        } catch (IOException | ClassNotFoundException e) {
            log.warning(e.getMessage());
        }
    }
}
