package clProyectoFinalOOP;

import java.sql.*;
import java.util.Date;
import java.util.Vector;
import accesoDatosProyectoFinalOOP.*;

public class MultiJuez {
	
	public static Juez crear(String pNombre, String pApellido, String pTelefono, String pCedula, String pSala, String pUsuario,String pContrasena) 
			throws java.sql.SQLException,Exception{
		java.sql.ResultSet rs;
		Juez juez = null;
		int id=0;
		String sql, sql2, id_juez, id_persona;
		
		id_persona="SELECT id_persona FROM persona p WHERE p.cedula=('" +pCedula+"');";
		
		
		
		sql2 = "INSERT INTO persona "+
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
		
		sql="INSERT INTO juez "+"(sala, usuario, contraseña, persona_id_persona_2)"+"VALUES ('"+pSala+"','"+pUsuario+"','"+pContrasena+"', "+id+");";
		
		try {
			Conector.getConector().ejecutarSQL(sql);
			juez = new Juez (pNombre, pApellido, pTelefono, pCedula, pSala, pUsuario, pContrasena);
		}
		catch (Exception e) {
			throw new Exception ("Este juez ya existe en el sistema");
		}
		
		
		return juez;	
	}
	

	public static Juez buscar(String pCedula) throws java.sql.SQLException,Exception{
		Juez juez=null;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT p.nombre, p.apellido, p.telefono, p.cedula, j.sala, j.usuario, j.contraseña "+
		"FROM persona p "+
		"JOIN juez j ON(p.id_persona = j.persona_id_persona_2)"+
		"WHERE p.cedula='"+ pCedula +"';";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		if (rs.next()) {
			juez = new Juez (rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("cedula"),rs.getString("sala"), rs.getString("usuario"), rs.getString("contraseña"));
		} else {
			throw new Exception ("El Juez no esta registrado.");
		}
		rs.close();
		return juez;
	}
	
	public static boolean autenticar(String pUsuario, String pContraseña)throws SQLException, Exception{
		Juez j = null;
		boolean auth = false;
		String sql, user, pwd;
		ResultSet rs;
		user = "";
		pwd = "";
		
		sql = "SELECT usuario, contraseña "
			+ "FROM juez "
			+ "WHERE usuario ='"+pUsuario+"';";
		rs = Conector.getConector().ejecutarSQL(sql, true);
		if(rs.next()){
			user = rs.getString("usuario");
			pwd = rs.getString("contraseña");
		}
		if(pUsuario.equals(user)&&pContraseña.equals(pwd)){
			auth = true;
		}else if(!(pContraseña.equals(pwd))){
			throw new Exception("La contraseña no coincide");
		}else{
			throw new Exception("El usuario no existe");
		}
		return auth;
	}
	
	public static Juez buscarUsuario(String pUsuario) throws java.sql.SQLException,Exception{
		Juez juez=null;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT p.nombre, p.apellido, p.telefono, p.cedula, j.sala, j.usuario, j.contraseña "+
		"FROM persona p "+
		"JOIN juez j ON(p.id_persona = j.persona_id_persona_2) "+
		"WHERE j.usuario='"+ pUsuario+"';";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		if (rs.next()) {
			juez = new Juez (rs.getString("nombre"), 
					         rs.getString("apellido"), 
					         rs.getString("telefono"), 
					         rs.getString("cedula"),
					         rs.getString("sala"), 
					         rs.getString("usuario"), 
					         rs.getString("contraseña"));
		} else {
			throw new Exception ("El Juez no esta registrado.");
		}
		rs.close();
		return juez;
	}

	public static void actualizar(Juez pJuez) throws java.sql.SQLException,Exception{
		String sql, sql2, id_persona;
		int id=0;
		ResultSet rs;
		
		id_persona="SELECT id_persona FROM persona p WHERE p.cedula=('" +pJuez.getCedula()+"');";
		rs = Conector.getConector().ejecutarSQL(id_persona, true);
		if(rs.next()){
			id = rs.getInt("id_persona");
		}
		sql2 = "UPDATE persona "
			 + "SET nombre ='"+pJuez.getNombre()+"',"
			 + "apellido ='"+pJuez.getApellido()+"',"
			 + "telefono ='"+pJuez.telefono+"' "
			 + "WHERE cedula ='"+pJuez.getCedula()+"';";
		
		sql = "UPDATE juez "+
		"SET usuario='"+pJuez.getUsuario()+"', "+
		"contrase�a ='"+pJuez.getContrasena()+"' "+		
		"WHERE persona_id_persona_2="+id+";";
		try {
			Conector.getConector().ejecutarSQL(sql2);
			Conector.getConector().ejecutarSQL(sql);
		}
		catch (Exception e) {
			throw new Exception ("El cliente no esta registrado o la contrase�a es incorrecta.");
		}
	}
	
	//Le agregue un segundo delete para la persona
	public static void borrar(Juez pJuez) throws java.sql.SQLException,Exception{
		String sql, sql2, id_persona;
		int id=0;
		ResultSet rs;
		
		id_persona = "SELECT j.persona_id_persona_2 FROM juez j JOIN persona p ON(p.id_persona = j.persona_id_persona_2) WHERE p.cedula = '"+pJuez.getCedula()+"';";
		rs = Conector.getConector().ejecutarSQL(id_persona, true);
		if(rs.next()){
			id = rs.getInt("persona_id_persona_2");
		}
		sql2 = "DELETE FROM persona "
			 + "WHERE cedula = '"+pJuez.getCedula()+"';";
		
		sql = "DELETE FROM juez "+
		"WHERE persona_id_persona_2="+id+";";
		try {
			Conector.getConector().ejecutarSQL(sql);
			Conector.getConector().ejecutarSQL(sql2);
			System.out.println("Se ha borrado el registro solicitado");
		}
		catch (Exception e) {
			throw new Exception ("No se pudo eliminar al juez del sistema.");
		}
	}
	
					
}
