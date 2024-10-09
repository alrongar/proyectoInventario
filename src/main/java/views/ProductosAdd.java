package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dao.OperacionesProductos;
import database.Connectionbd;

public class ProductosAdd extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nombreText;
    private JTextField precioText;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Connectionbd conexionbd = new Connectionbd();
                    Connection conexion = conexionbd.connect();
                    ProductosAdd frame = new ProductosAdd(conexion);
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
    public ProductosAdd(Connection conexion) {
        
        OperacionesProductos op = new OperacionesProductos(conexion);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 589, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Fondo de color plano
        contentPane.setBackground(new Color(240, 248, 255)); // Color Alice Blue
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(-14, 303, 603, 51);
        panel_1.setLayout(null);
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.setBackground(SystemColor.scrollbar);
        contentPane.add(panel_1);
        
        JButton BackBtn = new JButton("Back");
        BackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductosList productoList = new ProductosList(conexion, 0);
                productoList.setLocationRelativeTo(null);
                productoList.setVisible(true);
            }
        });
        BackBtn.setBackground(new Color(255, 224, 223)); // Color suave
        BackBtn.setForeground(Color.BLACK);
        BackBtn.setBounds(456, 10, 109, 31);
        panel_1.add(BackBtn);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(22, 10, 109, 31);
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setBackground(new Color(144, 238, 144)); // Color suave
        panel_1.add(btnAdd);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBackground(SystemColor.scrollbar);
        panel.setBounds(0, 0, 575, 41);
        contentPane.add(panel);
        
        JLabel lblNewLabel = new JLabel("Create product");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel.setBounds(223, 12, 123, 16);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre:");
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1.setBounds(112, 81, 70, 16);
        lblNewLabel_1.setForeground(new Color(25, 25, 112)); // Color azul oscuro
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Precio:");
        lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1_1.setBounds(112, 109, 70, 16);
        lblNewLabel_1_1.setForeground(new Color(25, 25, 112)); // Color azul oscuro
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Descripcion:");
        lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1_2.setBounds(112, 137, 95, 16);
        lblNewLabel_1_2.setForeground(new Color(25, 25, 112)); // Color azul oscuro
        contentPane.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_3 = new JLabel("Stock:");
        lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1_3.setBounds(335, 137, 70, 16);
        lblNewLabel_1_3.setForeground(new Color(25, 25, 112)); // Color azul oscuro
        contentPane.add(lblNewLabel_1_3);
        
        SpinnerNumberModel modelo = new SpinnerNumberModel(0, -1, 100, 1);
        
        JSpinner stockText = new JSpinner(modelo);
        stockText.setFont(new Font("Dialog", Font.BOLD, 15));
        stockText.setBounds(423, 131, 81, 30);
        contentPane.add(stockText);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(stockText, "#");
        stockText.setEditor(editor);
        
        JTextArea descripcionText = new JTextArea();
        descripcionText.setBounds(122, 165, 280, 122);
        contentPane.add(descripcionText);
        
        nombreText = new JTextField();
        nombreText.setBounds(200, 80, 104, 20);
        contentPane.add(nombreText);
        nombreText.setColumns(10);
        
        precioText = new JTextField();
        precioText.setBounds(200, 108, 104, 20);
        contentPane.add(precioText);
        precioText.setColumns(10);
        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreText.getText();
                    double precio = Double.parseDouble(precioText.getText());
                    String descripcion = descripcionText.getText();
                    int stock = (Integer) stockText.getValue();
                    
                    if (stock >= 0) {
                        int respuesta = JOptionPane.showConfirmDialog(
                            ProductosAdd.this,
                            "¿Estás seguro de que quieres añadir este elemento?",
                            "Confirmación de creación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                        );
                        if (respuesta == JOptionPane.YES_OPTION) {
                            op.crearProducto(nombre, descripcion, precio, stock);
                            dispose();
                            ProductosList productoList = new ProductosList(conexion, 0);
                            productoList.setLocationRelativeTo(null);
                            productoList.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos introducidos en stock no válidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Datos introducidos en precio no válidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

