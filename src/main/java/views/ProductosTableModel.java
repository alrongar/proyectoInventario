package views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import models.Producto;

public class ProductosTableModel extends AbstractTableModel {

	
	private List<Producto> productos;
    private static final String[] columnas = {"Nombre", "precio", "descripcion", "stock"};
    
    
    
	
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
		 Producto producto = productos.get(rowIndex);
	        switch (columnIndex) {
	            case 0: return producto.getNombre(); 
	            case 1: return producto.getPrecio();
	            case 2: return producto.getDescripcion();
	            case 3: return producto.getStock();
	            default: return null;
	        }
	}
	
	public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return columnIndex == getColumnCount() ? ImageIcon.class : Object.class;
	}

	@Override
	public String getColumnName(int column) {
		
		return columnas[column];
	}
	

	
}
