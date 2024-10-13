package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Connectionbd;

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
                    Connectionbd conexionbd = new Connectionbd();
                    Connection conexion = conexionbd.connect();
                    Inicio frame = new Inicio(conexion);
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
    public Inicio(Connection conexion) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 589, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(0, 143, 255)); // Cambiado a un color blanco azulado claro

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton logInBtn = new JButton("Log In");
        logInBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LogIn logIn = new LogIn(conexion);
                logIn.setLocationRelativeTo(null);
                logIn.setVisible(true);
            }
        });
        logInBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        logInBtn.setBounds(97, 217, 117, 56);
        logInBtn.setBackground(new Color(70, 130, 180)); // Cambiado a Steel Blue
        logInBtn.setForeground(Color.WHITE); // Texto blanco
        contentPane.add(logInBtn);
        
        JButton registerBtn = new JButton("Sign In");
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignIn signIn = new SignIn(conexion);
                signIn.setLocationRelativeTo(null);
                signIn.setVisible(true);
            }
        });
        registerBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        registerBtn.setBounds(369, 217, 117, 56);
        registerBtn.setBackground(new Color(34, 139, 34)); // Cambiado a Forest Green
        registerBtn.setForeground(Color.WHITE); // Texto blanco
        contentPane.add(registerBtn);
    }
}
