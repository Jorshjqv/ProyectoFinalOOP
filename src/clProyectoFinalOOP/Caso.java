package clProyectoFinalOOP;
import java.time.LocalDateTime;
/*
 * Clase Caso
 * Esta clase guarda los datos del caso a ser consultado 
 * por jueces o querellantes
 * 
 */
public class Caso {
	//Atributos
	private String numCaso;
	private String descripcion;
	private String estado;
	private LocalDateTime fecha;
	private Querellante querellante;
	private Juez juez;



	//Constructores
	public Caso(String numCaso, String estado, LocalDateTime fecha, String descripcion, Juez j, Querellante q){
		this.numCaso = numCaso;
		this.estado = estado;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.juez = j;
		this.querellante = q;
	}

	public Caso(){

	}

	//Getters/Setters
	public String getNumCaso() {
		return numCaso;
	}

	public void setNumCaso(String numCaso) {
		this.numCaso = numCaso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Querellante getQuerellante() {
		return querellante;
	}

	public void setQuerellante(Querellante idQuerellante) {
		this.querellante = idQuerellante;
	}

	public Juez getJuez() {
		return juez;
	}

	public void setJuez(Juez juez) {
		this.juez = juez;
	}

	//Metodos
	@Override
	public String toString() {
		return "Caso [numCaso=" + numCaso + ", estado=" + estado + ", descripcion="+ descripcion + ", fecha=" + fecha +"]";
	}

	
	/*
	 * El procedimiento modificarEstado() solo se usa al inicializar el caso
	 * y al anotarlo como recibido
	 * */
	public void modificarEstado()throws Exception{
		try{
			if(this.estado == null){
				this.estado = "recibido";
			}else if(this.estado.equals("recibido")){
				this.estado = "consulta"; 
			}
		}catch(NullPointerException ne){
			ne.printStackTrace();
		}
	}
		/*
		 * El procedimiento modificarEstado(String estado) modifica el estado
		 * luego de cambiar su valor a "Consulta, "
		 * */
		public void modificarEstado(String estado)throws Exception{
			Historial h;
			if(this.estado.equals("consulta")){
				this.estado = estado;
			}else if(this.estado.equals("aceptado")){
				this.estado = "redactado";
			}else if(this.estado.equals("redactado")){
				this.estado = estado;
			}else if(this.estado.equals("revision")){
				this.estado = "redactado";
			}
		}

		/*
		 * El metodo agregarHistorial crea una nueva instancia de historial
		 * para ser usada cada vez que se cambia el estado del caso
		 * */

	}
