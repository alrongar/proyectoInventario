package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Connectionbd;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

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
        
        JLabel fondoLabel = new JLabel("");
        fondoLabel.setBounds(0, 0, 575, 344);
        ImageIcon fondo = new ImageIcon(Inicio.class.getResource("/image/fondoInicio.jpg"));
        Image imagenEscalada = fondo.getImage().getScaledInstance(fondoLabel.getWidth(), fondoLabel.getHeight(), Image.SCALE_SMOOTH);
        fondo.setImage(imagenEscalada);
        
        JLabel imagen = new JLabel("");
        imagen.setBounds(208, 41, 175, 155);
        ImageIcon icono = new ImageIcon(Inicio.class.getResource("/image/imagenInicio.jpg"));
        Image iconoEscalado = icono.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
        icono.setImage(iconoEscalado);
        contentPane.add(imagen);
        fondoLabel.setIcon(fondo);
        imagen.setIcon(icono);
        
        JLabel lblNewLabel = new JLabel("Inventario");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(0, 128, 192));
        lblNewLabel.setBounds(208, 0, 175, 39);
        contentPane.add(lblNewLabel);
        contentPane.add(fondoLabel);
    }
}
