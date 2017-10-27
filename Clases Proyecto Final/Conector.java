package accesoDatosProyectoFinalOOP;
import java.util.Scanner;
import java.io.*;
/*
 * Clase Conector
 * La clase Conector se encarga de tomar los datos de un archivo de texto guardado en el archivo configuracion.txt
 * Modificacion de Conector version 1.0 /Laura Monge Izaguirre
 */
public class Conector {
    private static AccesoBD conectorBD = null;
    private static String usuario;
    private static String contraseña;
    private static File configuracion;
    private static Scanner output;
    //private static PrintWriter input;
    
    public static AccesoBD getConector()throws java.sql.SQLException, Exception{
    	if(conectorBD == null){
    		conectorBD = new AccesoBD("com.mysql.jdbc.Driver", "jdbc:"
    				                + "mysql://127.0.0.1:3306/proyecto_final_BD?autoReconnect=true&useSSL=false&holdResultsOpenOverStatementClose=true", usuario, contraseña);
    	}
    	return conectorBD;
    }
    public static String getUsuario(){
    	return usuario;
    }
    
    public static String getCont(){
    	return contraseña;
    }
    /*
     * Metodo que busca el archivo en la carpeta especificada
     * y alimenta la informacion de usuario y contraseña de la clase
     * */
    public static void obtenerDatosLogin(){
    	configuracion = new File("C:\\Users\\Jorsh\\workspace\\AccesoBDV2\\configuracion.txt");
    	try{
    		output = new Scanner(configuracion);
    		while(output.hasNext()){
    			usuario = output.nextLine();
    			contraseña = output.nextLine();
    		}
    	}catch(FileNotFoundException fe){
    		fe.printStackTrace();
    	}
    }
    /*
     * Metodo que crea un nuevo archivo de configuracion
     * */
    public static void inicializarConfiguracion(){
    	configuracion = new File("configuracion.txt");
    }
}
