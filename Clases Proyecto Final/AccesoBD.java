package accesoDatosProyectoFinalOOP;
import java.sql.*;
/*
 * Clase accesoBD
 * 
 * @autor Laura Monge Izaguirre
 * Version 2.0
 * */
public class AccesoBD {
    private Connection conn=null;
    private Statement stmt;
    
    public AccesoBD(String pDriver, String pConn, String user, String pwd)throws SQLException, Exception{
    	Class.forName(pDriver);
    	conn = DriverManager.getConnection(pConn, user, pwd);
    	stmt = conn.createStatement();
    }
    
    public void ejecutarSQL(String pSentencia)throws SQLException, Exception{
    	stmt.execute(pSentencia);
    }
    
    public ResultSet ejecutarSQL(String pSentencia, boolean pRetorno)throws SQLException, Exception{
    	ResultSet rs;
    	rs = stmt.executeQuery(pSentencia);
    	return rs;
    }
    
    
    public void iniciarTransaccion()throws SQLException{
    	conn.setAutoCommit(false);
    }
    
    public void terminarTransaccion()throws SQLException{
    	conn.setAutoCommit(true);
    }
    
    public void aceptarTransaccion()throws SQLException{
    	conn.commit();
    }
    
    public void deshacerTransaccion()throws SQLException{
    	conn.rollback();
    }
    
    protected void finalize(){
    	try{
    		conn.close();
    	}catch(Exception e){
    		
    	}
    }
}
