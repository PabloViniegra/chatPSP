package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtNick;
	private JTextField textField;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 424, 250);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 71, 349, 110);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel = new JLabel("Nick");
		lblNewLabel.setBounds(28, 24, 58, 14);
		panel.add(lblNewLabel);
		
		txtNick = new JTextField();
		txtNick.setBounds(96, 21, 178, 20);
		panel.add(txtNick);
		txtNick.setColumns(10);
		
		JButton btnNewButton = new JButton("Join");
		btnNewButton.setBounds(298, 20, 79, 23);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(28, 205, 246, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.setBounds(298, 205, 79, 23);
		panel.add(btnNewButton_1);
	}
}
