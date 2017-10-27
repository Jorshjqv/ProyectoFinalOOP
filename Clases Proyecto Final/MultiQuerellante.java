package clProyectoFinalOOP;
import accesoDatosProyectoFinalOOP.*;
import java.sql.*;

public class MultiQuerellante {

	/**
	 * Java Doc
	 * El metodo de Querellante crear hace uso de las variables enviadas por el gestor para poder 
	 * almacenarlas en la base de datos y asi ingresar un nuevo querellante al sistema.
	 * BD=Base de Datos.
	 *
	 * @pnombre variable de tipo String necesaria para la creacion de las consultas en la BD.
	 * @pApellido variable de tipo String necesaria para la creacion de las consultas en la BD.
	 * @pTelefono variable de tipo String necesaria para la creacion de las consultas en la BD.
	 * @pCedula variable de tipo String necesaria para la creacion de las consultas en la BD.
	 * @pDireccion variable de tipo String necesaria para la creacion de las consultas en la BD.
	 * @id_persona variable de tipo int necesaria para la consulta en la BD.
	 * @id variable de tipo int necesaria para la consulta en la BD. Inicializada en cero para poder utilizarla.
	 * @tmpPersona objeto del tipo Querellante inicializado en null que me va a crear un nuevo querellante en el sistema.
	 * @sql variable tipo String necesario para la consulta de la BD.
	 * @rs variable de  tipo Resulset necesaria para la coneccion de la BD,
	 * @return devuelve el objeto querellante que se ha ingresado al sistema 
	 * @version 2.1
	 */
	public static Querellante crear(String pNombre, String pApellido, String pTelefono, String pCedula, String pDireccion) 
			throws java.sql.SQLException,Exception{
		java.sql.ResultSet rs;
		Querellante querellante=null;
		String sql, sql2, id_persona;
		int id = 0;
		
		id_persona="SELECT id_persona FROM persona p WHERE p.cedula=('" +pCedula+"');";
		
		
		sql2 = "INSERT INTO Persona "+
				"(nombre, apellido, telefono, cedula) "+ "VALUES ('"+pNombre+"', '"+pApellido+"', '"+pTelefono+"', '"+pCedula+"');";
		try {
			Conector.getConector().ejecutarSQL(sql2);
		}
			catch (Exception e) {
					throw new Exception ("Esta persona ya se encuentra en el sistema.");
		}		
		
		rs = Conector.getConector().ejecutarSQL(id_persona, true);
		if(rs.next()){
			id = rs.getInt("id_persona");
		}
		sql="INSERT INTO querellante "
		  + "(direccion, persona_id_persona_1)"+ "VALUES ('"+pDireccion+"', "+id+");";

		try {
			Conector.getConector().ejecutarSQL(sql);
			querellante = new Querellante (pNombre, pApellido, pTelefono, pCedula, pDireccion);
		}
		catch (Exception e) {
			throw new Exception ("Este registro ya existe en el sistema");
		}
		return querellante;
	}
	
	
	/**
	 * Java Doc
	 * El metodo de Querellante buscar realiza una busqueda por medio del numero de cedula en el sistema,
	 * 
	 *
	 
	 * @pCedula variable de tipo String necesaria para la creacion de las consultas en la BD.
	 * @sql variable tipo String necesario para la consulta de la BD.
	 * @rs variable de  tipo Resulset necesaria para la coneccion de la BD.
	 * @return devuelve el objeto querellante relacionado al numero de cedula dado. 
	 * @version 1.5
	 */
	public static Querellante buscar(String cedula) throws java.sql.SQLException,Exception{
		Querellante querellante=null;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT p.nombre, p.apellido, p.cedula, p.telefono, q.direccion "+
		"FROM persona p "+ 
		"JOIN querellante q ON(p.id_persona = q.persona_id_persona_1) "+
		"WHERE p.cedula='"+ cedula +"';";
		
		rs = Conector.getConector().ejecutarSQL(sql,true);
		if (rs.next()) {
			querellante = new Querellante (rs.getString("nombre"), 
					                       rs.getString("apellido"), 
					                       rs.getString("telefono"), 
					                       rs.getString("cedula"), 
					                       rs.getString("direccion"));
		} else {
			throw new Exception ("El cliente no est� registrado.");
		}
		rs.close();
		return querellante;
	}
	
	/**
	 * Java Doc
	 * El metodo de Querellante autenticar realiza una validacion de datos por medio del numero de cedula en el sistema,
	 * 
	 *
	 
	 * @pCedula variable de tipo String necesaria para la creacion de las consultas en la BD.
	 * @q es una variable del tipo querellante necesaria para formar la validacion inicial.
	 * @sql variable tipo String necesario para la consulta de la BD de la cedula del querellante a eliminar.
	 * @sql2 realiza la accion de borrar de la base de Datos.
	 * @rs variable de  tipo Resulset necesaria para la coneccion de la BD.
	 * @return devuelve el objeto auth que confirma la validacion en el sistema del numero de cedula dado. 
	 * @version 1.1
	 */
	
	public static boolean autenticar(String pCedula)throws SQLException, Exception{
		Querellante q = null;
		boolean auth = false;
		String sql, cedula;
		ResultSet rs;
		cedula = "";
		
		
		sql = "SELECT cedula "
			+ "FROM querellante "
			+ "WHERE cedula ='"+pCedula+"';";
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			cedula = rs.getString("cedula");
			
		}
		if(pCedula.equals(cedula)){
			auth = true;

		}else{
			throw new Exception("El usuario no existe");
		}
		return auth;
	}
	
	/**
	 * Java Doc
	 * El metodo de Querellante borrar elimina a un usuario del sistema.
	 * 
	 *
	 
	 * @pQuerellante variable de tipo Querellante que trae al objeto que necesita ser eliminado.
	 * @sql variable tipo String necesario para la consulta de la BD.
	 * @rs variable de  tipo Resulset necesaria para la coneccion de la BD.
	 * @id_persona recibe el id en la base de datos relacionado al querellante que se esta intentando eliminar
	 * @return devuelve el objeto querellante relacionado al numero de cedula dado. 
	 * @version 1.9
	 */
	public static void borrar(Querellante pQuerellante) throws java.sql.SQLException,Exception{
		String sql, sql2, id_persona;
		int id = 0;
		ResultSet rs;
		
		id_persona="SELECT id_persona FROM persona WHERE cedula=('"+pQuerellante.getCedula()+"');";
				       
		rs = Conector.getConector().ejecutarSQL(id_persona, true);
		if(rs.next()){
			id = rs.getInt("persona_id_persona_1");
		}
		
		sql2 = "DELETE FROM persona "
			 + "WHERE id_persona ='"+id+"';";
		
		sql = "DELETE FROM querellante "+
		"WHERE persona_id_persona_1='"+id+"';";//no se si se pueda hacer asi
		try {
			Conector.getConector().ejecutarSQL(sql);
			Conector.getConector().ejecutarSQL(sql2);
			System.out.println("Se ha borrado el registro solicitado");
		}
		catch (Exception e) {
			throw new Exception ("No se pudo eliminar al cliente del sistema.");
		}
	}
}
