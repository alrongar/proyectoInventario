package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logInBtn = new JButton("Log In");
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LogIn logIn = new LogIn();
				logIn.setLocationRelativeTo(null);
				logIn.setVisible(true);
			}
		});
		logInBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logInBtn.setBounds(97, 217, 117, 56);
		contentPane.add(logInBtn);
		
		JButton registerBtn = new JButton("Sign In");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignIn signIn = new SignIn();
				signIn.setLocationRelativeTo(null);
				signIn.setVisible(true);
			}
		});
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerBtn.setBounds(369, 217, 117, 56);
		contentPane.add(registerBtn);
	}
}
