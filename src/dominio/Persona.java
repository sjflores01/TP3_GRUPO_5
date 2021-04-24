package dominio;

import ejercicio1.DniInvalidoException;

public class Persona {
	private String Dni;
	private String Nombre;
	private String Apellido;

	// Constructors
	public Persona(String dni, String nombre, String apellido) {
		try
		{
			verificarDni(dni);
			this.setDni(dni);
			this.setNombre(nombre);
			this.setApellido(apellido);
		}
		catch(DniInvalidoException ex)
		{
			ex.printStackTrace();
		}
			
	}

	// Getters y Setters
	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	
	
	//Methods
	private void verificarDni(String dni) throws DniInvalidoException {
		for(int i=0;i<dni.length();i++) {
			if(dni.charAt(i) < '0' || dni.charAt(i)>'9') {
				throw new DniInvalidoException();
			}
		}
	}

	@Override
	public String toString() {
		return "Persona [Dni=" + this.getDni() + ", Nombre=" + this.getNombre() + ", Apellido=" + this.getApellido() + "]";
	}

}
