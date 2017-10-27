package clProyectoFinalOOP;
import java.util.*;
import java.time.LocalDateTime;
/*
 * Clase Gestor
 * Esta clase se encarga de empaquetar la informacion de los multis
 * para ser consultada por la GUI
 * 
 * */
public class Gestor {
	/*
	 * El metodo casoAgregar toma como parametros dos cadenas de caracteres, un objeto LocalDateTime y
	 * los objetos Juez y Querellante, para crear un caso y agregarlo a la base de datos de casos
	 * @numCaso sentencia que representa el numero de caso a asignar
	 * @estado sentencia que representa el estado a asignar al caso
	 * @fecha objeto con la fecha de creacion del caso
	 * @descripcion sentencia que represena la informacion que describe los pormenores del caso
	 * @pJuez instancia de Juez asignado al caso
	 * @pQuerellante instancia de Querellante que solicito el caso
	 * */
    public static void casoAgregar(String numCaso, String estado, LocalDateTime fecha, String descripcion, Juez pJuez, Querellante pQuerellante)throws Exception{
    	Caso c;
    	c = MultiCaso.crear(numCaso, estado, fecha, descripcion, pJuez, pQuerellante);
    }
    /*
     * El metodo casoBuscar obtiene una cadena de caracteres como parametro y retorna un TreeMap
     * con los datos del caso a revisar.
     * @numCaso sentencia que representa el numero de caso a buscar
     * */
    public static TreeMap<String, Object> casoBuscar(String numCaso)throws Exception{
    	TreeMap<String, Object>	datos = null;
    	Caso c = null;
    	datos = new TreeMap<String, Object>();
    	c = MultiCaso.buscarPorNumero(numCaso);
    	datos.put("Numero Caso", c.getNumCaso());
    	datos.put("Fecha", c.getFecha());
    	datos.put("Estado", c.getEstado());
    	datos.put("Descripcion", c.getDescripcion());
    	datos.put("CedulaQ", c.getQuerellante().getCedula());
    	return datos;
    }
    /*
     * El metodo casoBuscarPorCedula toma una cadena de caracteres como parametro y retorna un 
     * Vector de TreeMaps, con la informacion de distintos casos relacionados a un juez
     * @cedula sentencia que representa la cedula del juez a consultar
     * */
    public static Vector<TreeMap<String, Object>> casoBuscarPorCedulaJ(String cedula)throws Exception{
    	Vector<Caso> casos = null;
    	Vector datosCasos = null;
    	casos = MultiCaso.buscarPorCedulaJ(cedula);
    	datosCasos = new Vector();
    	for(Caso c : casos){
    		TreeMap<String, Object> datosCaso = new TreeMap<String, Object>();
    		datosCaso.put("Numero Caso", c.getNumCaso());
    		datosCaso.put("Fecha", c.getFecha());
    		datosCaso.put("Estado", c.getEstado());
    		datosCaso.put("Descripcion", c.getDescripcion());
    		datosCaso.put("CedulaQ", c.getQuerellante().getCedula());
    		datosCasos.add(datosCaso);
    	}
    	return datosCasos;
    }
    /*
     * El metodo casoActualizar toma una cadena y crea un caso, que es
     * luego actualizado en la base de datos, y no tiene retorno
     * @numCaso sentencia que representa el numero de caso a actualizar*/
    public static void casoActualizar(Caso c)throws Exception{
    	
    	MultiCaso.actualizar(c);
    }
    /*
     * El metodo casoBorrar toma una cadena como parametro y utiliza esa cadena
     * para buscar un caso y borrarlo de la base de datos
     * @numCaso sentencia que representa el numero de caso a  borrar
     * */
    public static void casoBorrar(String numCaso)throws Exception{
    	Caso c;
    	c = MultiCaso.buscarPorNumero(numCaso);
    	MultiCaso.borrar(c);
    }
    /*
     * El metodo historialAgregar toma un objeto que contiene la fecha, y dos cadenas con 
     * informacion para crear un nuevo historial en la base de datos sin retorno
     * @fecha objeto LocalDateTime con la fecha de creacion del historial
     * @estado sentencia que representa el nuevo estado del caso
     * @numCaso sentencia que representa el numero de caso al que pertenece el historial
     * */
    public static void historialAgregar(LocalDateTime fecha, String estado, String numCaso)throws Exception{
    	Historial h;
    	h = MultiHistorial.crear(fecha, estado, numCaso);
    }
    /*
     * El metofo historialBuscar obtiene los datos de un historial en especifico usando como
     * parametros un objeto que guarda la fecha exacta del historial, y dos cadenas con informacion 
     * para encontrar el historial en especifico, y retorna un mapa con los datos del historial
     * @fecha objeto LocalDateTime con la fecha de creacion del historial
     * @estado sentencia que representa el nuevo estado del caso
     * @numCaso sentencia que representa el numero de caso al que pertenece el historial
     * */
    public static TreeMap<String, Object> historialBuscar(LocalDateTime fecha, String numCaso, String estado)throws Exception{
    	Historial h;
    	TreeMap<String, Object> datosHistorial;
    	h = MultiHistorial.buscar(fecha, numCaso, estado);
    	datosHistorial = new TreeMap<String, Object>();
    	datosHistorial.put("Numero Caso", h.getNumCaso());
    	datosHistorial.put("Estado", h.getEstado());
    	datosHistorial.put("Fecha", h.getFecha());
    	return datosHistorial;
    }
    /*
     * El metodo historialesBuscar toma una cadena con la informacion del caso con todos los historiales
     * generados, y guarda los datos en una coleccion de mapas que es luego retornada
     * @numCaso sentencia que representa el numero del caso con los historiales a consultar
     * */
    public static Vector<TreeMap<String, Object>> historialesBuscar(String numCaso)throws Exception{
    	Vector<Historial> historiales;
    	Vector<TreeMap<String, Object>> datosHistoriales;
    	historiales = MultiHistorial.buscarHistoriales(numCaso);
    	datosHistoriales = new Vector<TreeMap<String, Object>>();
    	for(Historial h : historiales){
    		TreeMap<String, Object> datosHistorial = new TreeMap<String, Object>();
    		datosHistorial.put("Fecha", h.getFecha());
    		datosHistorial.put("Numero Caso", h.getNumCaso());
    		datosHistorial.put("Estado", h.getEstado());
    		datosHistoriales.add(datosHistorial);
    	}
    	return datosHistoriales;
    }
    /*
     * El metodo historialBorrar toma un objeto con la fecha exacta del historial a borrar, y dos cadenas
     * con datos para ubicar el historial exacto y borrarlo, sin retorno
     * @fecha objeto LocalDateTime con la fecha de creacion del historial
     * @estado sentencia que representa el nuevo estado del caso
     * @numCaso sentencia que representa el numero de caso al que pertenece el historial
     * */
    public static void historialBorrar(LocalDateTime fecha, String numCaso, String estado)throws Exception{
    	Historial h;
    	h = MultiHistorial.buscar(fecha, numCaso, estado);
    	MultiHistorial.borrar(h);
    }
    /*
     * El metodo juezCrear toma como parametros las cadenas necesarias para crear un objeto juez y agregarlo
     * a la base de datos, sin retornos
     * @pNombre sentencia que representa el nombre del juez a crear
     * @pApellido sentencia que representa el apellido del juez a crear
     * @pCedula sentencia que representa la cedula del juez a crear
     * @pTelefono sentencia que representa el numero telefonico del juez
     * @pSala sentencia que representa la sala asignada al juez
     * @pUsuario sentencia que representa el nombre de usuario del juez para accesar al sistema
     * @pContraseña sentencia que represena la contraseña del juez para accesar al sistema
     * */
    public static void juezCrear(String pNombre, String pApellido, String pCedula, String pTelefono, String pSala, String pUsuario,String pContrasena)throws Exception{
    	Juez j;
    	j = MultiJuez.crear(pNombre, pApellido, pCedula, pTelefono, pSala, pUsuario, pContrasena);
    }
    /*
     * El metodo juezBuscar toma como parametro una cadena con la informacion de un usuario
     * y retorna un mapa con los datos de ese usuario
     * @pUsuario sentencia que representa el nombre de usuario asignado al juez a buscar
     * */
    public static TreeMap<String, String> juezBuscar(String pUsuario)throws Exception{
    	TreeMap<String, String> datosJuez;
    	Juez j;
    	j = MultiJuez.buscarUsuario(pUsuario);
    	datosJuez = new TreeMap<String, String>();
    	datosJuez.put("Nombre", j.getNombre());
    	datosJuez.put("Apellido", j.getApellido());
    	datosJuez.put("Telefono", j.getTelefono());
    	datosJuez.put("Cedula", j.getCedula());
    	datosJuez.put("Sala", j.getSala());
    	datosJuez.put("Usuario", j.getUsuario());
    	return datosJuez;
    	
    }
    /*
     * El metodo juezAutenticar toma dos cadenas, retorna un boolean con la informacion de si el usuario
     * se encuentra registrado en el sistema
     * @pUsuario sentencia que representa el nombre del usuario a autenticar
     * @pContraseña sentencia que representa la contraseña registrada en el sistema
     *  */
    public static boolean juezAutenticar(String pUsuario, String pContraseña)throws Exception{
    	boolean auth;
    	auth = MultiJuez.autenticar(pUsuario, pContraseña);
    	return auth;
    }
    /*
     * El metodo juezModificar tomar una serie de cadenas con los datos del juez a modificar en la 
     * base de datos, sin retorno
     * @pNombre sentencia que representa el nombre del juez a crear
     * @pApellido sentencia que representa el apellido del juez a crear
     * @pCedula sentencia que representa la cedula del juez a crear
     * @pTelefono sentencia que representa el numero telefonico del juez
     * @pSala sentencia que representa la sala asignada al juez
     * @pUsuario sentencia que representa el nombre de usuario del juez para accesar al sistema
     * @pContraseña sentencia que represena la contraseña del juez para accesar al sistema
     * */
    public static void juezModificar(String pNombre, String pApellido, String pCedula, String pTelefono, String pSala, String pUsuario,String pContrasena)throws Exception{
    	Juez j;
    	j = MultiJuez.buscar(pCedula);
    	j.setNombre(pNombre);
    	j.setApellido(pApellido);
    	j.setCedula(pCedula);
    	j.setTelefono(pTelefono);
    	j.setSala(pSala);
    	j.setUsuario(pUsuario);
    	j.setContrasena(pContrasena);
    	MultiJuez.actualizar(j);
    }
    /*
     * El metodo juezBorrar toma una cadena y borra al objeto obtenido de la busqueda
     * sin retornos
     * @pCedula sentencia que representa el numero de cedula del juez a borrar
     * */
    public static void juezBorrar(String pCedula)throws Exception{
    	Juez j;
    	j = MultiJuez.buscar(pCedula);
    	MultiJuez.borrar(j);
    }
    /*
     * El metodo querellanteCrear toma como parametros una serie de cadenas con los datos
     * del querellante a crear, sin retorno
     * @pNombre sentencia que representa el nombre del querellante
     * @pApellido sentencia que representa el apellido del querellante
     * @pTelefono sentencia que representea el telefono del querellante
     * @pCedula sentencia que representa la cedula del querellante
     * @pDireccion sentencia que representa la direccion del querellante
     * */
    public static void querellanteCrear(String pNombre, String pApellido, String pTelefono, String pCedula, String pDireccion)throws Exception{
    	Querellante q;
    	q = MultiQuerellante.crear(pNombre, pApellido, pTelefono, pCedula, pDireccion);
    }
    /*
     *El metodo querellanteBuscar toma una cadena como parametro y retorna un mapa con los datos del objeto
     *@pCedula sentencia que representa la cedula del querellante a buscar
     **/
    public static TreeMap<String, String> querellanteBuscar(String pCedula)throws Exception{
    	TreeMap<String, String> datosQuerellante;
    	Querellante q;
    	q = MultiQuerellante.buscar(pCedula);
    	datosQuerellante = new TreeMap<String, String>();
    	datosQuerellante.put("Nombre", q.getNombre());
    	datosQuerellante.put("Apellido", q.getApellido());
    	datosQuerellante.put("Telefono", q.getTelefono());
    	datosQuerellante.put("Cedula", q.getCedula());
    	datosQuerellante.put("Direccion", q.getDireccion());
    	
    	return datosQuerellante;
    }
    /*
     * El metodo querellanteBorrar toma una cadena como parametro, busca al objeto con esa 
     * informacion y lo borra de la base de datos
     * @pCedula sentencia que representa la cedula del querellante a borrar
     * */
    public static void querellanteBorrar(String pCedula)throws Exception{
    	Querellante q;
    	q = MultiQuerellante.buscar(pCedula);
    	MultiQuerellante.borrar(q);
    }
    
}
