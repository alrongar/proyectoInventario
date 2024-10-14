package views;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import javax.swing.JLabel;
import dao.OperacionesProductos;
import models.Producto;

public class ProductosTableModel extends AbstractTableModel {

	
	private List<Producto> productos;
    private static final String[] columnas = {"Nombre", "precio", "descripcion", "stock", "imagen"};
    
    
    
	
	public ProductosTableModel(List<Producto> productos) {
		super();
		
		this.productos = productos;
		
		if (productos == null) {
			this.productos = new ArrayList<Producto>();
		}
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OperacionesProductos op = new OperacionesProductos(null);
		
		
		 Producto producto = productos.get(rowIndex);
		 ImageIcon imagen = new ImageIcon(op.decodeBase64ToImage(producto.getImagen()));
		 Image imagenEscalada = imagen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		 //imagen.setImage(imagenEscalada);
		 
		 
	        switch (columnIndex) {
	            case 0: return producto.getNombre(); 
	            case 1: return producto.getPrecio();
	            case 2: return producto.getDescripcion();
	            case 3: return producto.getStock();
	            case 4: return imagen!= null ? imagen : "Sin imagen";
	            default: return null;
	        }
	        
	}
	
	public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return columnIndex == 4 ? ImageIcon.class : Object.class;
	}

	@Override
	public String getColumnName(int column) {
		
		return columnas[column];
	}
	
	
	
}
