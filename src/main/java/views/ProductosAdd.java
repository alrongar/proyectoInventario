package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.OperacionesProductos;
import database.Connectionbd;

public class ProductosAdd extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nombreText;
    private JTextField precioText;
    private File imagenFile;
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
    
    
    public void mostrarImagenEnVentana(File archivoSeleccionado, JLabel imagenLabel) {
        // Cargar la imagen seleccionada
        ImageIcon imagen = new ImageIcon(archivoSeleccionado.getAbsolutePath());

        // Redimensionar la imagen para que se ajuste al JLabel
        Image imagenEscalada = imagen.getImage().getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_SMOOTH);
        imagen.setImage(imagenEscalada);

        // Mostrar la imagen en el JLabel
        imagenLabel.setIcon(imagen);
    }
    
    public File abrirFileChooserConJOptionPane(JLabel imagenLabel) {
        
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filtro);
        File archivoSeleccionado = null;
        // Mostrar el JFileChooser dentro de un JOptionPane
        int resultado = JOptionPane.showConfirmDialog(null, fileChooser, "Seleccionar Imagen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Si el usuario selecciona un archivo
        if (resultado == JOptionPane.OK_OPTION) {
            archivoSeleccionado = fileChooser.getSelectedFile();
            if (archivoSeleccionado != null) {
                mostrarImagenEnVentana(archivoSeleccionado, imagenLabel);
                
            }
            
        }
        return archivoSeleccionado;
        
    }
    

    /**
     * Create the frame.
     */
    public ProductosAdd(Connection conexion) {
        
        OperacionesProductos op = new OperacionesProductos(conexion);
        imagenFile = null;
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
        panel_1.setBackground(new Color(200, 220, 240));
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
        BackBtn.setBackground(new Color(247, 123, 126)); // Color suave
        BackBtn.setForeground(new Color(255, 255, 255));
        BackBtn.setBounds(456, 10, 109, 31);
        panel_1.add(BackBtn);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(22, 10, 109, 31);
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setBackground(new Color(144, 238, 144)); // Color suave
        panel_1.add(btnAdd);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBackground(new Color(200, 220, 240));
        panel.setBounds(0, 0, 575, 41);
        contentPane.add(panel);
        
        JLabel lblNewLabel = new JLabel("Create product");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel.setBounds(223, 0, 123, 41);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre:");
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1.setBounds(28, 54, 70, 16);
        lblNewLabel_1.setForeground(new Color(0, 128, 192)); // Color azul oscuro
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Precio:");
        lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1_1.setBounds(28, 83, 70, 16);
        lblNewLabel_1_1.setForeground(new Color(0, 128, 192)); // Color azul oscuro
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Descripcion:");
        lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1_2.setBounds(28, 111, 95, 16);
        lblNewLabel_1_2.setForeground(new Color(0, 128, 192)); // Color azul oscuro
        contentPane.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_3 = new JLabel("Stock:");
        lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_1_3.setBounds(331, 77, 70, 16);
        lblNewLabel_1_3.setForeground(new Color(0, 128, 192)); // Color azul oscuro
        contentPane.add(lblNewLabel_1_3);
        
        SpinnerNumberModel modelo = new SpinnerNumberModel(0, -1, 100, 1);
        
        JSpinner stockText = new JSpinner(modelo);
        stockText.setFont(new Font("Dialog", Font.BOLD, 15));
        stockText.setBounds(419, 71, 81, 30);
        contentPane.add(stockText);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(stockText, "#");
        stockText.setEditor(editor);
        
        JTextArea descripcionText = new JTextArea();
        descripcionText.setBounds(28, 139, 280, 122);
        contentPane.add(descripcionText);
        
        nombreText = new JTextField();
        nombreText.setBounds(116, 53, 104, 20);
        contentPane.add(nombreText);
        nombreText.setColumns(10);
        
        precioText = new JTextField();
        precioText.setBounds(116, 82, 104, 20);
        contentPane.add(precioText);
        precioText.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Imagen: ");
        lblNewLabel_2.setForeground(new Color(0, 128, 192));
        lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 15));
        lblNewLabel_2.setBounds(331, 106, 64, 26);
        contentPane.add(lblNewLabel_2);
        
        JButton btnSeleccion = new JButton("seleccionar imagen");
        
        btnSeleccion.setFont(new Font("Dialog", Font.BOLD, 8));
        btnSeleccion.setBounds(400, 107, 112, 30);
        contentPane.add(btnSeleccion);
        
        JLabel imagen = new JLabel("");
        imagen.setBounds(350, 139, 150, 122);
        contentPane.add(imagen);
        
        JLabel fondoLabel = new JLabel("");
        fondoLabel.setBounds(0, 39, 575, 266);
        ImageIcon fondo = new ImageIcon(Inicio.class.getResource("/image/fondoInicio.jpg"));
        Image imagenEscalada = fondo.getImage().getScaledInstance(fondoLabel.getWidth(), fondoLabel.getHeight(), Image.SCALE_SMOOTH);
        fondo.setImage(imagenEscalada);
        fondoLabel.setIcon(fondo);
        contentPane.add(fondoLabel);
        
        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreText.getText();
                    double precio = Double.parseDouble(precioText.getText());
                    String descripcion = descripcionText.getText();
                    int stock = (Integer) stockText.getValue();
                    String imagen = op.encodeImageToBase64(imagenFile);
                    
                    if (stock >= 0) {
                        int respuesta = JOptionPane.showConfirmDialog(
                            ProductosAdd.this,
                            "¿Estás seguro de que quieres añadir este elemento?",
                            "Confirmación de creación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                        );
                        if (respuesta == JOptionPane.YES_OPTION) {
                            op.crearProducto(nombre, descripcion, precio, stock, imagen);
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
        
        btnSeleccion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		imagenFile = abrirFileChooserConJOptionPane(imagen);
        	}
        });
        
    }
}

