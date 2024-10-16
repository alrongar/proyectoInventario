package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.OperacionesUsuarios;
import database.Connectionbd;
import models.Usuario;

public class LogIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField userText;
    private JTextField passwordText;
    private JButton volverBtn;
    
    private List<Usuario> usuarios;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Connectionbd conexionbd = new Connectionbd();
                    Connection conexion = conexionbd.connect();
                    LogIn frame = new LogIn(conexion);
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
    public LogIn(Connection conexion) {
        
        OperacionesUsuarios op = new OperacionesUsuarios(conexion);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 589, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(0, 143, 255)); // Cambiado a un azul vibrante

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel.setForeground(new Color(0, 128, 192)); // Texto blanco
        lblNewLabel.setBounds(134, 98, 94, 23);
        contentPane.add(lblNewLabel);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPassword.setForeground(new Color(0, 128, 192)); // Texto blanco
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
        
        JButton logInBtn = new JButton("Log In");
        logInBtn.setForeground(new Color(255, 255, 255));
        logInBtn.setBackground(new Color(144, 238, 144));
        logInBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = userText.getText();
                String password = passwordText.getText();
                
                int tipoUsuario = op.verificarUsuario(name, password);
                if (tipoUsuario < 2) {
                    dispose();
                    ProductosList productosList = new ProductosList(conexion, tipoUsuario);
                    productosList.setLocationRelativeTo(null);
                    productosList.setVisible(true);
                } else if (tipoUsuario == 3) {
                    JOptionPane.showMessageDialog(null, "El usuario no ha sido encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        logInBtn.setFont(new Font("Dialog", Font.BOLD, 16));
        logInBtn.setBounds(134, 203, 115, 41);
        contentPane.add(logInBtn);
        
        volverBtn = new JButton("Back");
        volverBtn.setForeground(new Color(255, 255, 255));
        volverBtn.setBackground(new Color(247, 123, 126)); // Color del botón "Back"
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
        
        JLabel fondoLabel = new JLabel("");
        fondoLabel.setBounds(0, 0, 575, 344);
        ImageIcon fondo = new ImageIcon(Inicio.class.getResource("/image/fondoInicio.jpg"));
        Image imagenEscalada = fondo.getImage().getScaledInstance(fondoLabel.getWidth(), fondoLabel.getHeight(), Image.SCALE_SMOOTH);
        fondo.setImage(imagenEscalada);
        fondoLabel.setIcon(fondo);
        
        contentPane.add(fondoLabel);
    }
}
