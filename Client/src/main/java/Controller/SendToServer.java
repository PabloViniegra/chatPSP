package Controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Log
public class SendToServer extends Thread {
    private Socket client;
    private ObjectOutputStream objectOutputStream;

    public SendToServer(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessage(JTextField txtSend, JTextField txtNick) {
        if (txtSend.getText().length() != 0) {
            DataPackageChat dataPackageChat = new DataPackageChat(txtNick.getText(), txtSend.getText());
            try {
                objectOutputStream.writeObject(dataPackageChat);
            } catch (IOException e1) {
                log.warning(e1.getMessage());
            }
        }
    }

    public void sendUsers(JTextField txtNick) {
        DataPackageChat dataPackageChat = new DataPackageChat("userConnected",txtNick.getText());
        try {
            objectOutputStream.writeObject(dataPackageChat);
        } catch (IOException e1) {
            log.warning(e1.getMessage());
        }
    }
}
