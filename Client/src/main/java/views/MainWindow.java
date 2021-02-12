package views;

import Controller.ConnectToServer;
import Controller.ListenFromServer;
import Controller.SendToServer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.net.Socket;

public class MainWindow extends JFrame {
    private JPanel contentPane;
    private JTextField txtNick;
    private JTextField txtSend;
    private Socket client;
    private JTextArea textArea;
    private JButton btnJoin, btnSend;

    /**
     * Launch the application.
     */
    public void loadWindow(MainWindow frame) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainWindow() {
        client = new Socket();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 619, 426);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 593, 365);
        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 71, 521, 211);
        panel.add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setEditable(false);

        JLabel lblNewLabel = new JLabel("Nick");
        lblNewLabel.setBounds(28, 24, 58, 14);
        panel.add(lblNewLabel);

        txtNick = new JTextField();
        txtNick.setBounds(96, 21, 261, 20);
        panel.add(txtNick);
        txtNick.setColumns(10);
        btnJoin = new JButton("Join");
        btnJoin.addActionListener(e -> {
            ConnectToServer connectToServer = new ConnectToServer(client, textArea, txtNick.getText());
            connectToServer.start();
            ListenFromServer listenFromServer = new ListenFromServer(client, textArea);
            listenFromServer.start();
        });
        btnJoin.setBounds(470, 20, 79, 23);
        panel.add(btnJoin);

        txtSend = new JTextField();
        txtSend.setBounds(28, 310, 329, 20);
        panel.add(txtSend);
        txtSend.setColumns(10);

        btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            SendToServer sendToServer = new SendToServer(client, txtSend, txtNick);
            sendToServer.start();
        });
        btnSend.setBounds(470, 309, 79, 23);
        panel.add(btnSend);
    }
}
