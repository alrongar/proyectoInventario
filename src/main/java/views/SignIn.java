package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.OperacionesUsuarios;
import database.Connectionbd;

public class SignIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField userText;
    private JTextField passwordText;
    private JButton volverBtn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Connectionbd conexionbd = new Connectionbd();
                    Connection conexion = conexionbd.connect();
                    SignIn frame = new SignIn(conexion);
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
    public SignIn(Connection conexion) {
        
        OperacionesUsuarios op = new OperacionesUsuarios(conexion);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 589, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(0, 143, 255)); // Fondo azul vibrante

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel.setForeground(Color.WHITE); // Texto blanco
        lblNewLabel.setBounds(134, 98, 94, 23);
        contentPane.add(lblNewLabel);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPassword.setForeground(Color.WHITE); // Texto blanco
        lblPassword.setBounds(134, 133, 94, 23);
        contentPane.add(lblPassword);
        
        userText = new JTextField();
        userText.setBounds(246, 98, 153, 23);
        contentPane.add(userText);
        userText.setColumns(10);
        
        passwordText = new JTextField();
        passwordText.setColumns(10);
        passwordText.setBounds(246, 136, 153, 23);
        contentPane.add(passwordText);
        
        JButton signInBtn = new JButton("Sign In");
        signInBtn.setFont(new Font("Dialog", Font.BOLD, 16));
        signInBtn.setBackground(new Color(255, 223, 224)); // Color del botón
        signInBtn.setForeground(Color.BLACK); // Texto en negro
        signInBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = userText.getText();
                String password = passwordText.getText();
                
                op.incluirUsuario(name, password, false);
            }
        });
        signInBtn.setBounds(134, 203, 115, 41);
        contentPane.add(signInBtn);
        
        volverBtn = new JButton("Back");
        volverBtn.setBackground(new Color(255, 223, 224)); // Color del botón "Back"
        volverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Inicio inicio = new Inicio(conexion);
                inicio.setLocationRelativeTo(null);
                inicio.setVisible(true);
            }
        });
        volverBtn.setFont(new Font("Dialog", Font.BOLD, 16));
        volverBtn.setBounds(335, 203, 115, 41);
        contentPane.add(volverBtn);
    }
}

