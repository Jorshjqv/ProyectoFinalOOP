package clProyectoFinalOOP;
import java.time.LocalDateTime;
/*
 * Clase Historial
 * 
 * Esta clase representa un cambio en el historial del caso, cada caso puede tener varios
 * objetos historial relacionados
 * */
public class Historial {
	//Atributos
	private LocalDateTime fecha;
	private String estado;
	private String numCaso;

	//Constructores
	public Historial(String estado, String numCaso, LocalDateTime fecha){
		this.fecha = fecha;
		this.estado = estado;
		this.numCaso = numCaso;
	}

	public Historial(){

	}

	//Getters/Setters
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumCaso() {
		return numCaso;
	}

	public void setNumCaso(String numCaso) {
		this.numCaso = numCaso;
	}

	
	//Metodos

	@Override
	public String toString() {
		return "Historial [fecha=" + fecha + ", estado=" + estado + ", numCaso=" + numCaso + "]";
	}
}
