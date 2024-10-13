package models;

public class Producto {

	private String nombre;
	private double precio;
	private String descripcion;
	private int stock;
	private String imagen;
	

	public Producto(String nombre, double precio, String descripcion, int stock, String imagen) {
		
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.stock = stock;
		this.imagen = imagen;
	}


	public String getNombre() {
		return nombre;
	}


	public double getPrecio() {
		return precio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public int getStock() {
		return stock;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

	
	
	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	@Override
	public String toString() {
		return "nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion + ", stock=" + stock
				;
	}
	
	
	
}
