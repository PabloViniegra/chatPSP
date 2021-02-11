package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import impml.DataPackageChat;

import javax.print.attribute.standard.JobMediaSheetsCompleted;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.awt.event.ActionEvent;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNick.getText().length() != 0) {
						client = new Socket("localhost", 9090);
						BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
						String line = br.readLine();
						textArea.append(line);
						btnJoin.setEnabled(false);

					} else {
						JOptionPane.showMessageDialog(null, "No has introducido un nick valido", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnJoin.setBounds(470, 20, 79, 23);
		panel.add(btnJoin);

		txtSend = new JTextField();
		txtSend.setBounds(28, 310, 329, 20);
		panel.add(txtSend);
		txtSend.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSend.getText().length() != 0) {
					DataPackageChat dataPackageChat = new DataPackageChat(txtNick.getText(), txtSend.getText());
					try {
						ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
						oos.writeObject(dataPackageChat);
						oos.close();
						ObjectInputStream ois= new ObjectInputStream(client.getInputStream());
						DataPackageChat myDataPackageChat= (DataPackageChat) ois.readObject();
						String line=myDataPackageChat.getNick()+": "+myDataPackageChat.getMessage()+" hora: "+dataPackageChat.getTime();
						textArea.append(line);
						ois.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException classNotFoundException) {
						classNotFoundException.printStackTrace();
					}
				}
			}
		});
		btnSend.setBounds(470, 309, 79, 23);
		panel.add(btnSend);
	}
}
