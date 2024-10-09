package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

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

	/**
	 * Launch the application.
	 */
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
	

	/**
	 * Create the frame.
	 */
	public ProductosList(Connection conexion, int tipoUsuario) {
		
		OperacionesProductos op = new OperacionesProductos(conexion);
		productos = op.listarProductos();
		productosActuales = productos;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 575, 41);
		contentPane.add(panel);
		panel.setLayout(null);
		
		buscadorText = new JTextField();
		
		buscadorText.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (buscadorText.getText().isEmpty()) {
					buscadorText.setText("Buscar");
					buscadorText.setForeground(Color.GRAY); // Volver a gris si está vacío
                }
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				buscadorText.setForeground(Color.BLACK); // Texto normal cuando el usuario escribe
				buscadorText.setText("");
                
                
			}
			
		});
		
		
		
		buscadorText.setForeground(SystemColor.scrollbar);
		buscadorText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buscadorText.setText("buscar");
		buscadorText.setBounds(197, 21, 161, 20);
		panel.add(buscadorText);
		buscadorText.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(SystemColor.scrollbar);
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
		
		
		
		ProductosTableModel modelo = new ProductosTableModel(productosActuales);
		table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(12, 12, 551, 242);
		panel_2.add(scrollPane);
		
		if (tipoUsuario == 0) {
			btnDelete.setVisible(true);
			btnAdd.setVisible(true);
			btnUpdate.setVisible(true);
		}else {
			btnDelete.setVisible(false);
			btnAdd.setVisible(false);
			btnUpdate.setVisible(false);
		}
		
		scrollPane.setViewportView(table);
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LogIn logIn = new LogIn(conexion);
				logIn.setLocationRelativeTo(null);
				logIn.setVisible(true);
			}
		});
		
		buscadorText.addInputMethodListener(new InputMethodListener() {
			public void inputMethodTextChanged(InputMethodEvent event) {
				String busqueda = buscadorText.getText(); 
				System.out.println(busqueda);
				
				if (busqueda.equals("")) {
					productosActuales = productos;
					
				}else {
					productosActuales.clear();
					for (Producto producto : productos) {
						if (producto.getNombre().startsWith(busqueda)) {
							productosActuales.add(producto);
						}
					}
				}
				((ProductosTableModel) table.getModel()).setProductos(productosActuales);
			}
			public void caretPositionChanged(InputMethodEvent event) {
				System.out.println("cambio de posicion");
			}
			
		});
		
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
                        productosActuales = productos;
                        ProductosTableModel modelo = new ProductosTableModel(productos);
                        table.setModel(modelo);
					}
                	
                    
                }else {
                	JOptionPane.showMessageDialog(null, "Seleccione un producto para realizar esta accion", "Error", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = table.getSelectedRow();
				
				String nombre = table.getValueAt(filaSeleccionada, 0).toString();
				double precio = Double.parseDouble(table.getValueAt(filaSeleccionada, 1).toString());
				String descripcion = table.getValueAt(filaSeleccionada, 2).toString();
				int stock = Integer.parseInt(table.getValueAt(filaSeleccionada, 3).toString());
				
				Producto producto = new Producto(nombre, precio, descripcion, stock);
				
				dispose();
				ProductosUpdate productoUpdate = new ProductosUpdate(conexion, producto);
				productoUpdate.setLocationRelativeTo(null);
				productoUpdate.setVisible(true);
			}
		});
		
		//----------------------------------
		
		
		
		
		
		
	}
}
