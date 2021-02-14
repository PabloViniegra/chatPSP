package Controller;

import lombok.extern.java.Log;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                log.info("Me he conectado al servidor");
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String aux;
                aux = br.readLine();
                log.info("Estoy leyendo " + aux);
                textArea.append(line + " " + aux);
                log.info("Deberia haber enlazado el mensaje al JTextArea: " + aux);
            } else {
                JOptionPane.showMessageDialog(null, "No has introducido un nick valido", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
