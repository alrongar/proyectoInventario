package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;

import dao.OperacionesProductos;
import database.Connectionbd;
import models.Producto;

public class ProductosList extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField buscadorText;
    private JTable table;
    private List<Producto> productos;
    private List<Producto> productosActuales;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Connectionbd conexionbd = new Connectionbd();
                    Connection conexion = conexionbd.connect();
                    ProductosList frame = new ProductosList(conexion, 0);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    
    
    public ProductosList(Connection conexion, int tipoUsuario) {
        OperacionesProductos op = new OperacionesProductos(conexion);
        productos = op.listarProductos();
        productosActuales = new ArrayList<>(productos); // Lista para los productos filtrados

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 589, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 220, 240));
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(0, 0, 575, 41);
        contentPane.add(panel);
        panel.setLayout(null);

        buscadorText = new JTextField();
        
        buscadorText.setForeground(Color.GRAY);
        buscadorText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        buscadorText.setBounds(196, 12, 161, 20);
        panel.add(buscadorText);
        buscadorText.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("buscar: ");
        lblNewLabel.setBounds(123, 14, 55, 16);
        panel.add(lblNewLabel);
        
        

        // Método para búsqueda dinámica
        buscadorText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filterProducts();
            }

            public void removeUpdate(DocumentEvent e) {
                filterProducts();
            }

            public void changedUpdate(DocumentEvent e) {
                filterProducts();
            }

            private void filterProducts() {
                String busqueda = buscadorText.getText().toLowerCase();
                productosActuales.clear();
                if (busqueda.isEmpty()) {
                    productosActuales.addAll(productos);
                } else {
                    for (Producto producto : productos) {
                        if (producto.getNombre().toLowerCase().startsWith(busqueda)) {
                            productosActuales.add(producto); 
                        }
                    }
                }
                ((ProductosTableModel) table.getModel()).setProductos(productosActuales); 
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.setBackground(new Color(200, 220, 240));
        panel_1.setBounds(-14, 303, 603, 51);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JButton BackBtn = new JButton("Back");
        BackBtn.setBackground(new Color(255, 224, 223));
        BackBtn.setBounds(456, 10, 109, 31);
        panel_1.add(BackBtn);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(264, 10, 109, 31);
        panel_1.add(btnDelete);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(143, 10, 109, 31);
        panel_1.add(btnUpdate);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductosAdd productoAdd = new ProductosAdd(conexion);
                productoAdd.setLocationRelativeTo(null);
                productoAdd.setVisible(true);
            }
        });
        btnAdd.setBounds(22, 10, 109, 31);
        panel_1.add(btnAdd);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 39, 575, 266);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        // Crear el modelo de la tabla
        ProductosTableModel modelo = new ProductosTableModel(productosActuales);
        table = new JTable(modelo);
        table.setRowHeight(50);
        // Personalización de la tabla
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(255, 255, 255));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(100, 150, 255));
        table.setSelectionForeground(Color.WHITE);

        // Personalizar el encabezado de la tabla
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(150, 200, 250));
        headerRenderer.setForeground(Color.BLACK);
        headerRenderer.setFont(new Font("Tahoma", Font.BOLD, 12));

        // Aplicar el renderer al encabezado de la tabla
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // Configurar JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 12, 551, 242);
        panel_2.add(scrollPane);

        // Control de visibilidad de botones según el tipo de usuario
        if (tipoUsuario == 0) {
            btnDelete.setVisible(true);
            btnAdd.setVisible(true);
            btnUpdate.setVisible(true);
        } else {
            btnDelete.setVisible(false);
            btnAdd.setVisible(false);
            btnUpdate.setVisible(false);
        }

        scrollPane.setViewportView(table);
        
        // Acción del botón "Back"
        BackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LogIn logIn = new LogIn(conexion);
                logIn.setLocationRelativeTo(null);
                logIn.setVisible(true);
            }
        });

        // Acción del botón "Delete"
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int respuesta = JOptionPane.showConfirmDialog(
                            ProductosList.this,
                            "¿Estás seguro de que quieres eliminar este elemento?",
                            "Confirmación de Eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (respuesta == JOptionPane.YES_OPTION) {
                        String nombre = table.getValueAt(filaSeleccionada, 0).toString();
                        op.eliminarProducto(nombre);
                        productos = op.listarProductos();
                        productosActuales.clear(); // Limpiar la lista actual
                        productosActuales.addAll(productos); // Actualizar con los nuevos productos
                        ((ProductosTableModel) table.getModel()).setProductos(productosActuales); // Actualizar modelo de la tabla
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para realizar esta acción", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón "Update"
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String nombre = table.getValueAt(filaSeleccionada, 0).toString();
                    double precio = Double.parseDouble(table.getValueAt(filaSeleccionada, 1).toString());
                    String descripcion = table.getValueAt(filaSeleccionada, 2).toString();
                    int stock = Integer.parseInt(table.getValueAt(filaSeleccionada, 3).toString());
                    ImageIcon icon = (ImageIcon) table.getValueAt(filaSeleccionada, 4);
                    String imagen = op.imageToBase64String(icon);
                    Producto producto = new Producto(nombre, precio, descripcion, stock, imagen);
                    dispose();
                    ProductosUpdate productosUpdate = new ProductosUpdate(conexion, producto);
                    productosUpdate.setLocationRelativeTo(null);
                    productosUpdate.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
