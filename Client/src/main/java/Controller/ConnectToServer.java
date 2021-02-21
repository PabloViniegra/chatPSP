package Controller;

import lombok.extern.java.Log;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@Log
public class ConnectToServer extends Thread {
    private String line;
    private JTextArea textArea;
    private Socket client;
    public ConnectToServer(Socket client, JTextArea textArea, String line) {
        this.textArea = textArea;
        this.line = line;
        this.client = client;
    }

    public void run() {
        try {
            log.info("Ha empezado el hilo para conectar al servidor");
            if (line.length() != 0) {
                SocketAddress socketAddress = new InetSocketAddress("localhost",9090);
                client.connect(socketAddress);
            } else {
                JOptionPane.showMessageDialog(null, "No has introducido un nick valido", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e1) {
            log.warning(e1.getLocalizedMessage());
        }
    }
}
