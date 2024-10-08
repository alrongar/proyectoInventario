package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dao.OperacionesProductos;
import database.Connectionbd;
import models.Producto;
import javax.swing.JScrollPane;

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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 39, 575, 266);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		
		ProductosTableModel modelo = new ProductosTableModel(productosActuales);
		table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(12, 12, 551, 242);
		panel_2.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LogIn logIn = new LogIn(conexion);
				logIn.setLocationRelativeTo(null);
				logIn.setVisible(true);
			}
		});
		
		buscadorText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String busqueda = buscadorText.getText(); 
				
				productosActuales.clear();
				for (Producto producto : productos) {
					if (producto.getNombre().startsWith(busqueda)) {
						productosActuales.add(producto);
					}
				}
				
				modelo.setProductos(productosActuales);
			}
		});
	}
}
