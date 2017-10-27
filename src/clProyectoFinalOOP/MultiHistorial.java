package clProyectoFinalOOP;

import java.sql.*;
import accesoDatosProyectoFinalOOP.*;
import java.time.LocalDateTime;
import java.util.*;
/*
 * Clase MultiHistorial
 * 
 * Clase que contiene metodos para crear objetos a partir de consultas a la base de datos
 * para modificar, agregar o borrar historiales de cada caso
 * */
public class MultiHistorial {
    /*
     * El metodo crear toma un objeto con la fecha de creacion del objeto y dos cadenas
     * con la informacion del caso relacionado, guarda esta informacion como un nuevo historial en
     * la base de datos, y crea y retorna un objeto historial para ser consultado por el programa
     * @fecha objeto que representa la fecha de creacion del historial
     * @estado sentencia que representa el nuevo estado del caso
     * @numCaso sentencia que representa el numero de caso con el estado a cambiar
     * */
	public static Historial crear(LocalDateTime fecha, String estado, String numCaso)throws SQLException, Exception{
		Historial h = null;
		String sql, sql2;
		ResultSet rs;
		int id=0;
		sql2 = "SELECT id_caso "
			+ "FROM caso "
			+ "WHERE num_caso ='"+numCaso+"';";
		
		rs = Conector.getConector().ejecutarSQL(sql2, true);
		if(rs.next()){
			id = rs.getInt("id_caso");
		}
		sql = "INSERT INTO historial "+
		      "(fecha, estado, caso_id_caso) "+
			  "VALUES('"+fecha+"', '"+estado+"', "+id+");";
		//Conector.getConector().ejecutarSQL("SET FOREIGN_KEY_CHECKS=0");
		Conector.getConector().ejecutarSQL(sql);
		//Conector.getConector().ejecutarSQL("SET FOREIGN_KEY_CHECKS=1");
		h = new Historial(estado, numCaso, fecha);
		
		return h;
	}
	/*
	 * El metodo buscar toma como parametros un objeto que guarda la fecha de creacion, mas 
	 * las cadenas con la informacion del caso, y retorna un historial que ya este guardado en 
	 * la base de datos
	 * @fecha objeto que representa la fecha de creacion del historial
     * @estado sentencia que representa el nuevo estado del caso
     * @numCaso sentencia que representa el numero de caso con el estado a cambiar
     * */
	public static Historial buscar(LocalDateTime fecha, String numCaso, String estado)throws SQLException, Exception{
		Historial h = null;
		ResultSet rs;
		String sql;
		
		sql = "SELECT fecha, estado, caso_id_caso "
			+ "FROM historial "
			+ "WHERE fecha='"+fecha+"' "
		    + "AND estado='"+estado+"' "
		    + "AND caso_id_caso="
		    + "(SELECT id_caso FROM caso WHERE num_caso='"+numCaso+"')";
		
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			h = new Historial(rs.getString("estado"), numCaso, rs.getTimestamp("fecha").toLocalDateTime());
		}else{
			throw new Exception("El historial no existe");
		}
		return h;
	}
	/*
	 * El metodo buscarHistoriales toma como parametro una cadena con la informacion del caso
	 * del cual extraer todo su historial, estos historiales son guardados en una coleccion que es
	 * luego retornada
	 * @numCaso sentencia que representa el numero de caso a buscar
	 * */
	public static Vector<Historial> buscarHistoriales(String numCaso)throws SQLException, Exception{
		Historial h = null;
		Vector<Historial> historiales;
		ResultSet rs;
		String sql;
		
		historiales = new Vector<Historial>();
		sql = "SELECT fecha, estado "+
		      "FROM historial "+
			  "WHERE caso_id_caso ="
			+ "(SELECT id_caso FROM caso WHERE num_caso ='"+numCaso+"');";
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			do{
			    h = new Historial(rs.getString("estado"), numCaso, rs.getTimestamp("fecha").toLocalDateTime());
			    historiales.add(h);
			}while(rs.next());
		}else{
			throw new Exception("El numero de caso no existe.\n");
		}
		rs.close();
		return historiales;
	}
	/*
	 * El metodo borrar toma un objeto historial como parametro, busca los datos de este
	 * objeto y los borra de la base de datos, sin retorno
	 * @historial objeto que representa el historial con los datos a ser eliminados de la BD
	 * */
	public static void borrar(Historial historial)throws SQLException, Exception{
		
		String sql;
		sql = "DELETE FROM historial "+
		      "WHERE num_caso='"+historial.getNumCaso()+"' "
		    + "AND fecha='"+historial.getFecha()+"' "
		    + "AND estado ='"+historial.getEstado()+"';";
		Conector.getConector().ejecutarSQL(sql);
	}
	
}
