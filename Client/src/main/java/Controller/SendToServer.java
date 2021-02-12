package Controller;

import impml.DataPackageChat;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendToServer extends Thread{
    private Socket client;
    private JTextField txtSend;
    private JTextField txtNick;

    public SendToServer(Socket client, JTextField txtSend, JTextField txtNick) {
        this.client = client;
        this.txtSend = txtSend;
        this.txtNick = txtNick;
    }
    public void run() {
        if (txtSend.getText().length() != 0) {
            DataPackageChat dataPackageChat = new DataPackageChat(txtNick.getText(), txtSend.getText());
            try {
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(dataPackageChat);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
