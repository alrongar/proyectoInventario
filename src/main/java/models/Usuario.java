package models;

public class Usuario implements Comparable<Usuario>{

	private String nombre;
	private String contraseña;
	private boolean isAdministrador;
	
	
	public Usuario(String nombre, String contraseña, boolean isAdministrador) {
		
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.isAdministrador = isAdministrador;
	}
	
	public Usuario(String nombre , String contraseña) {
		this(nombre,contraseña,false);
	}

	public String getNombre() {
		return nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public boolean isAdministrador() {
		return isAdministrador;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", contraseña=" + contraseña;
	}

	@Override
	public int compareTo(Usuario o) {
		return this.nombre.compareTo(o.nombre);
	}

	
	
	

	

	
	
	
}
