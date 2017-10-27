package clProyectoFinalOOP;

import accesoDatosProyectoFinalOOP.*;
import java.sql.*;
import java.io.*;
import java.util.*;


public class MultiPersona{

	public  Persona crear(String pnombre, String papellido, String pcedula, String ptelefono) 
			throws Exception{
		Persona tmpPersona=null;
		String sql;
		
		sql = "INSERT INTO Persona "+
		"(nombre, apellido, telefono, cedula) "+
		"VALUES ('"+pnombre+"', '"+papellido+"', '"+ptelefono+"', '"+pcedula+"');";
		try {
			Conector.getConector().ejecutarSQL(sql);
			tmpPersona = new Persona (pnombre, papellido, ptelefono, pcedula);
		}
		catch (Exception e) {
			throw new Exception ("Esta persona ya se encuentra en el sistema.");
		}
		return tmpPersona;
	}
	
	public  Persona buscar(String pcedula) throws 
			java.sql.SQLException,Exception{
		Persona tmpPersona=null;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT nombre, apellido, cedula, telefono "+
		"FROM persona "+
		"WHERE cedula='"+ pcedula +"';";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		if (rs.next()) {
			tmpPersona = new Persona (rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("cedula"));
		} else {
			throw new Exception ("El cliente no est� registrado.");
		}
		rs.close();
		return tmpPersona;
	}
	
	public  Vector buscarPorNombre(String pnombrePersona) throws 
			java.sql.SQLException,Exception{
		Persona tmpPersona=null;
		Vector clientes=null;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT nombre, identificacion "+
		"FROM persona "+
		"WHERE nombre LIKE '%"+pnombrePersona+"%';";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		clientes = new Vector ();
		if (rs.next()) {
			do {
				tmpPersona = new Persona (rs.getString("nombre"), rs.getString("apellido"), rs.getString("cedula"), rs.getString("telefono"));
				clientes.add(tmpPersona);
			} while (rs.next());
		} else {
			throw new Exception ("No hay clientes con ese nombre.");
		}
		rs.close();
		return clientes;
	}
	
	public  void actualizar(Persona pPersona) throws 
			java.sql.SQLException,Exception{
		String sql;
		sql = "UPDATE persona "+
		"SET nombre='"+pPersona.getNombre()+"', "+ "apellido='"+pPersona.getApellido()+"', "+"cedula='"+pPersona.getCedula()+"', "+"telefono='"+pPersona.getTelefono()+"', "+
		"WHERE cedula='"+pPersona.getCedula()+"';";
		try {
			Conector.getConector().ejecutarSQL(sql);
			System.out.println("Se ha actualizado el registro solicitado");
		}
		catch (Exception e) {
			throw new Exception ("El cliente no est� registrado.");
		}
	}
	
	public  void borrar(Persona pPersona) throws 
			java.sql.SQLException,Exception{
		String sql;
		sql = "DELETE FROM persona "+
		"WHERE cedula='"+pPersona.getCedula()+"';";
		try {
			Conector.getConector().ejecutarSQL(sql);
			System.out.println("Se ha borrado el registro solicitado");
		}
		catch (Exception e) {
			throw new Exception ("El cliente tiene cuentas.");
		}
	}
}