package Controller;

import impml.DataPackageChat;
import lombok.extern.java.Log;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
@Log
public class SendToServer extends Thread{
    private Socket client;
    private JTextField txtSend;
    private JTextField txtNick;
    private JTextArea txtarea;

    public SendToServer(Socket client, JTextField txtSend, JTextField txtNick, JTextArea txtarea) {
        this.client = client;
        this.txtSend = txtSend;
        this.txtNick = txtNick;
        this.txtarea = txtarea;
    }
    public void run() {
        if (txtSend.getText().length() != 0) {
            DataPackageChat dataPackageChat = new DataPackageChat(txtNick.getText(), txtSend.getText());
            try {
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(dataPackageChat);
                txtarea.append("\n" + dataPackageChat.getNick() + ": " + dataPackageChat.getMessage() + "\n" + dataPackageChat.getTime());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
