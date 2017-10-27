package clProyectoFinalOOP;

import java.util.ArrayList;

public class Juez extends Persona {
	private String sala;
	private String usuario;
	private String contrasena;
	//public static ArrayList<Caso> casos = new ArrayList<Caso>();


	public Juez() {
		super();
	}
	

	public Juez(String nombre, String apellido, String telefono, String cedula, String sala, String usuario,
			String contrasena) {
		super(nombre, apellido, telefono, cedula);
		this.sala = sala;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}


	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/*
	 * public void agregarCaso(Caso casos, String estado){
	 * 
	 *}
	 *public void modificarCaso(Caso caso){
	 *}
	 *
	 *Metodo para registrar Jueces en la Capa L�gica
	 *	private static ArrayList<Juez> listaJueces = new ArrayList<Juez>();
		private static int pos=0;
	public static void registrarJuez(String pNombre, String pApellido, String pTelefono, String pCedula, String pSala, String pUsuario,
			String pContrasena ){
		int n=pos;
		Juez nuevoJuez = new Juez();
		nuevoJuez.setNombre(pNombre);
		nuevoJuez.setApellido(pApellido);
		nuevoJuez.setTelefono(pTelefono);
		nuevoJuez.setCedula(pCedula);
		nuevoJuez.setSala(pSala);
		nuevoJuez.setUsuario(pUsuario);
		nuevoJuez.setContrasena(pContrasena);		
		
		listaJueces.add(n,nuevoJuez);
		pos++;
		
	}
	 *public void verificarUsuario(String pUsuario, String pContrasena){
	 *this.usuario=pUsuario;
	 *this.contrasena=pContrasena;
	 *}
	 * */
	

}
