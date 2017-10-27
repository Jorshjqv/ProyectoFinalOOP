package clProyectoFinalOOP;

public class Querellante extends Persona{
	private String direccion;

	public Querellante() {
		super();
	}

	public Querellante(String nombre, String apellido, String telefono, String cedula, String direccion) {
		super(nombre, apellido, telefono, cedula);
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	

}
