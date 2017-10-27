package clProyectoFinalOOP;
import java.time.LocalDateTime;
import accesoDatosProyectoFinalOOP.Conector;
import java.sql.*;
import java.util.*;
/*
 * Clase MultiCaso
 * 
 * Esta clase contiene metodos para modificar, buscar, agregar o eliminar
 * casos de la base de datos, y crear objetos de tipo Caso para ser consultados
 * por el sistema
 * */
public class MultiCaso {

	/*
	 * El metodo crear toma una serie de cadenas y objetos que contienen la informacion del caso a guardar
	 * @numCaso sentencia que representa el numero de caso a ser creado/guardado
	 * @estado sentencia que representa el estado del nuevo caso
	 * @fecha objeto que contiene la fecha en que se creo el caso
	 * @descripcion sentencia que representa una breve descripcion del caso
	 * @juez objeto que contiene los datos del juez a ser asignado al caso
	 * @querellante objeto que contiene los datos del querellante que pidio el caso
	 * */
	public static Caso crear(String numCaso, String estado, LocalDateTime fecha, String descripcion, Juez juez, Querellante querellante)throws SQLException, Exception{
		Caso c = null;
		String sql, sql2,sql3;
		int idJuez, idQuerellante;
		ResultSet rs;
		idJuez = -1;
		idQuerellante = -1;
		sql2 = "SELECT j.id_juez FROM juez j JOIN persona p ON(p.id_persona = j.persona_id_persona_2) WHERE p.cedula =('"+juez.getCedula()+"');";
		sql3 = "Select q.id_querellante FROM querellante q JOIN persona p ON(p.id_persona = q.persona_id_persona_1) WHERE p.cedula =('"+querellante.getCedula()+"')";
		rs = Conector.getConector().ejecutarSQL(sql2, true);
		if(rs.next()){
			idJuez = rs.getInt("id_juez");
		}
		rs = Conector.getConector().ejecutarSQL(sql3, true);
		if(rs.next()){
			idQuerellante = rs.getInt("id_querellante");
		}

		sql = "INSERT INTO caso "+ 
				"(num_caso, estado_caso, fecha, juez_id_juez, querellante_id_querellante) "+
				"VALUES('"+numCaso+"', '"+estado+"', '"+fecha+"', "+idJuez+", "+idQuerellante+");";
		//Conector.getConnector().ejecutarSQL("SET FOREIGN_KEY_CHECKS=0");
		Conector.getConector().ejecutarSQL(sql);
		//Conector.getConnector().ejecutarSQL("SET FOREIGN_KEY_CHECKS=1");
		c = new Caso(numCaso, estado, fecha, descripcion, juez, querellante);
		
		return c;
	}

	/*
	 * El metodo buscarPorNumero toma como parametro una cadena con la informacion del caso a buscar
	 * en la base de datos, y retorna un objeto con los datos a consultar
	 * @numCaso sentencia que representa el numero de caso a buscar
	 * */
	public static Caso buscarPorNumero(String numCaso)throws SQLException, Exception{
		Caso c = null;
		Juez j = null;
		Querellante q = null;
		String sql;
		ResultSet rs;

		sql = "SELECT c.num_caso, c.fecha, c.estado_caso, c.descripcion_caso, q.cedula, j.cedula "
				+ "FROM caso c "
				+ "JOIN juez ON(juez.id_juez = c.juez_id_juez) "
				+ "JOIN persona j ON(j.id_persona = juez.persona_id_persona_2) "
				+ "JOIN querellante ON(querellante.id_querellante = c.querellante_id_querellante) "
				+ "JOIN persona q ON(q.id_persona = querellante.persona_id_persona_1)"
				+ "WHERE c.num_caso = '"+numCaso+"';";

		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
		    j = MultiJuez.buscar(rs.getString("j.cedula"));
		    q = MultiQuerellante.buscar(rs.getString("q.cedula"));
		   
			c = new Caso(rs.getString("num_caso"), rs.getString("estado_caso"), rs.getTimestamp("fecha").toLocalDateTime(), rs.getString("descripcion_caso"),j,q);
		}else{
			throw new Exception("El caso no existe");
		}

		rs.close();
		return c;
	}

	/*
	 * El metodo buscarPorCedula toma una cadena con los datos de los casos relacionados
	 * con la informacion contenida en ella
	 * @pCedula sentencia que representa los datos del numero de cedula a buscar
	 * */
	public static Vector<Caso> buscarPorCedulaJ(String pCedula)throws SQLException, Exception{
		Juez j;
		Caso c = null;
		String sql, cedulaj, sql2;
		Vector<Caso> casos;
		ResultSet rs, rs2;
		
		
		try{
		    j = MultiJuez.buscar(pCedula);
		}catch(Exception e){
			j = new Juez();
		}
		casos = new Vector<Caso>();
		sql = "SELECT c.num_caso, c.fecha, c.estado_caso, c.descripcion_caso, q.cedula "
				+ "FROM caso c "
				+ "JOIN juez ON(juez.id_juez = c.juez_id_juez) "
				+ "JOIN persona j ON(j.id_persona = juez.persona_id_persona_2) "
				+ "JOIN querellante ON(querellante.id_querellante = c.querellante_id_querellante) "
				+ "JOIN persona q ON(q.id_persona = querellante.persona_id_persona_1) "
				+ "WHERE j.cedula = '"+pCedula+"' OR q.cedula = '"+pCedula+"';";

		rs = Conector.getConector().ejecutarSQL(sql, true);
		while(rs.next()){
			c = new Caso(rs.getString("num_caso"), 
					rs.getString("estado_caso"), 
					rs.getTimestamp("fecha").toLocalDateTime(), 
					rs.getString("descripcion_caso"),
					j, MultiQuerellante.buscar(rs.getString("cedula")));
			
			casos.add(c);
			
		}
		rs.close();
		return casos;
	}

	/*
	 * El metodo actualizar toma como parametro un objeto con los datos del 
	 * caso a ser actualizado en la base de datos, sin retornos
	 * @c objeto con los datos del caso a ser actualizados
	 * */
    public static void actualizar(Caso c)throws SQLException, Exception{
    	String sql;
    	
    	sql = "UPDATE caso "
    		+ "SET estado_caso ='"+c.getEstado()+"', "
    		+ "descripcion_caso ='"+c.getDescripcion()+"' "
    		+ "WHERE num_caso LIKE('"+c.getNumCaso()+"');";
    	Conector.getConector().ejecutarSQL(sql);
    }
 
    /*
     * El metodo borrar toma como parametro un objeto con los datos del caso 
     * a borrar de la base de datos, sin retornos
     * @caso objeto que representa el caso a borrar
     * */
	public static void borrar(Caso caso)throws SQLException, Exception{
		String sql;

		sql = "DELETE FROM proyecto_final_bd.caso "+
				"WHERE num_caso ='"+caso.getNumCaso()+"';";

		Conector.getConector().ejecutarSQL(sql);

	}
}
