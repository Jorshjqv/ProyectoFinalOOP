package uiProyectoFinalOOP;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import accesoDatosProyectoFinalOOP.*;
import clProyectoFinalOOP.*;


public class UI {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out  = System.out;
    public static void main(String[] args)throws IOException{
    	//logicaUI();
    }
    
    public static void logicaUI()throws IOException{
    	Juez j;
    	ResultSet rs;
    	String sentencia;
    	Querellante q;
    	
    	
    	try
    	{
    	    Conector.obtenerDatosLogin();
    	    out.print(Conector.getUsuario() + " " + Conector.getCont());
    	    
    	    out.println("Conexion exitosa!");
    	    
    	    
    	}
    	catch(Exception se)
    	{
    		out.println("Conexion no exitosa");
    	}
    	
    	try{
    	    
    	    Gestor.juezBorrar("89782154");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    }
}
