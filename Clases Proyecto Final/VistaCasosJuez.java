package uiProyectoFinalOOP;

import clProyectoFinalOOP.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.util.*;
import java.awt.event.*;
import java.time.LocalDateTime;
/*
 * Clase VistaCasosJuez
 * @autor Josue Quiros Valverde
 * version 1.0
 * Clase que se encarga de la presentacion de los casos del juez
 * */
public class VistaCasosJuez extends JFrame {

	private JPanel contentPane;
	private JLabel lblCasos;
	private JLabel lblJuez;
	private JTextPane textPane;
	private JLabel lblNumeroDeCaso;
	private JLabel lblDescripcion;
	private JLabel lblNombreQuerellante;
	private JLabel lblEstado;
	private JList listNumCaso;
	private JList listDescripcion;
	private JList listQuerellante;
	private JList listEstado;
	private Gestor gst;
	private JButton btnVerHistorial;
	private JButton btnCambiarEstado;
	private JButton btnRefDatos;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCasosJuez frame = new VistaCasosJuez();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VistaCasosJuez() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCasos = new JLabel("Casos");
		lblCasos.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblCasos.setBounds(302, 32, 77, 20);
		contentPane.add(lblCasos);
		
		lblJuez = new JLabel("Juez: ");
		lblJuez.setBounds(58, 86, 46, 20);
		contentPane.add(lblJuez);
		
		textPane = new JTextPane();
		textPane.setBounds(103, 86, 116, 20);
		contentPane.add(textPane);
		
		lblNumeroDeCaso = new JLabel("Numero de Caso:");
		lblNumeroDeCaso.setBounds(101, 144, 118, 23);
		contentPane.add(lblNumeroDeCaso);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(235, 144, 92, 23);
		contentPane.add(lblDescripcion);
		
		lblNombreQuerellante = new JLabel("Nombre Querellante:");
		lblNombreQuerellante.setBounds(355, 144, 128, 23);
		contentPane.add(lblNombreQuerellante);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(509, 144, 77, 23);
		contentPane.add(lblEstado);
		
		listNumCaso = new JList();
		listNumCaso.setBounds(117, 193, 84, 255);
		contentPane.add(listNumCaso);
		
		listDescripcion = new JList();
		listDescripcion.setBounds(228, 193, 116, 255);
		contentPane.add(listDescripcion);
		
		listQuerellante = new JList();
		listQuerellante.setBounds(367, 193, 116, 255);
		contentPane.add(listQuerellante);
		
		listEstado = new JList();
		listEstado.setBounds(509, 193, 77, 255);
		contentPane.add(listEstado);
		
		btnVerHistorial = new JButton("Ver Historial");
		btnVerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerHistorial_MouseClicked(e);
			}
		});
		btnVerHistorial.setBounds(10, 204, 94, 40);
		contentPane.add(btnVerHistorial);
		
		btnCambiarEstado = new JButton("Cambiar Estado");
		btnCambiarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCambiarEstado_MouseClicked(e);
			}
		});
		btnCambiarEstado.setBounds(10, 254, 94, 46);
		contentPane.add(btnCambiarEstado);
		
		btnRefDatos = new JButton("Refrescar Datos");
		
		btnRefDatos.setBounds(441, 11, 145, 44);
		contentPane.add(btnRefDatos);
		gst = new Gestor();
		
	}
	
	public void setVisible(){
		contentPane.setVisible(true);
	}
	/*
	 *El metodo cargarCasosJuez toma como parametro una cadena con la informacion del usuario
	 *con la informacion a cargar, sin retornos
	 *@pUsuario sentencia que representa el nombre de usuario con la informacion a cargar
	 **/
	public void cargarCasosJuez(String pUsuario){
		Vector<TreeMap<String, Object>> casos;
		TreeMap<String, Object> datosCaso;
		TreeMap<String, String> datosJuez;
		TreeMap<String, String>datosQuerellante;
		String cedulaJ = "";
		DefaultListModel dlmNC, dlmDesc, dlmQuerellante, dlmEstado;
		dlmNC = new DefaultListModel();
		dlmDesc = new DefaultListModel();
		dlmQuerellante = new DefaultListModel();
		dlmEstado = new DefaultListModel();
		
		try{
			datosJuez = Gestor.juezBuscar(pUsuario);
			textPane.setText(datosJuez.get("Nombre") + " " + datosJuez.get("Apellido"));
			cedulaJ = (String)datosJuez.get("Cedula");
			casos = gst.casoBuscarPorCedulaJ(cedulaJ);
			
	        listNumCaso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);;
			for(TreeMap<String, Object> t : casos){
				datosQuerellante = gst.querellanteBuscar((String)t.get("CedulaQ"));
				dlmNC.addElement(t.get("Numero Caso"));
				listNumCaso.setModel(dlmNC);
				dlmDesc.addElement(t.get("Descripcion"));
				listDescripcion.setModel(dlmDesc);
				dlmQuerellante.addElement(datosQuerellante.get("Nombre") + " " + datosQuerellante.get("Apellido"));
				listQuerellante.setModel(dlmQuerellante);
				dlmEstado.addElement(t.get("Estado"));
				listEstado.setModel(dlmEstado);
			}
			
			btnRefDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarCasosJuez(pUsuario);
				}
			});
		}catch(Exception e){
		    e.printStackTrace();	
		}
	}
	/*
	 * El metodo bthVerHistorial_MouseClicked toma como parametro un objeto evento y 
	 * y carga la vista de historiales del caso seleccionado
	 * @e objeto que representa la accion a realizar al darle click al boton
	 * */
	public void btnVerHistorial_MouseClicked(ActionEvent e){
		String numCaso="";
		Vector historiales = new Vector();
		TreeMap datosHistorial=new TreeMap();
		VistaHistorialCasos vhc;
		try{
			numCaso = (String)listNumCaso.getSelectedValue();
			historiales = gst.historialesBuscar(numCaso);
			vhc = new VistaHistorialCasos();
			vhc.cargarHistorial(historiales);
			vhc.setVisible(true);
		}catch(RuntimeException re){
			JOptionPane.showMessageDialog(contentPane, "Por favor selecciones una opcion de la fila 'Numero de caso'.");
		}catch(Exception ee){
			ee.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, (String) ee.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	/*
	 * El metodo btnCambiarEstado toma un objeto de tipo evento como parametro, y se encarga
	 * de cambiar el estado del caso, y generar un nuevo historial en la base de datos, sin
	 * retornos
	 * @e objeto que representa el boton siendo clickeado
	 * */
	public void btnCambiarEstado_MouseClicked(ActionEvent e){
		String numCaso="";
	    String estado = "";
	    TreeMap datosCaso = new TreeMap();
	    Caso c = null;
	    try{
	    	numCaso = (String)listNumCaso.getSelectedValue();
	    	datosCaso = gst.casoBuscar(numCaso);
	    	c = new Caso();
	    	estado= (String)datosCaso.get("Estado");
	    	/*JOptionPane.showMessageDialog(contentPane,"Indique el nuevo estado del caso:"
	    			                  + "\n(Si el estado se encuentra 'Recibido', pasara automaticamente a 'Consulta'. \n"
	    			                  + "Si el estado es 'Consulta', puede escoger entre:\n1.'Aceptado'.\n2.'Rechazado'.\n"
	    			                  + "Si el estado es 'Aceptado', pasara automaticamente a 'Redactado'.\n"
	    			                  + "Si el estado es 'Redactado', puede escoger entre:\n1.'Revision'.\n2.'Resuelto.')");*/
	    	if(estado.equals(null) || estado.equals("recibido")){
	    		c.setEstado(estado);
	    		c.modificarEstado();
	    		c.setNumCaso(numCaso);
	    		c.setDescripcion((String)datosCaso.get("descripcion"));
	    		c.setFecha(LocalDateTime.now());
				gst.historialAgregar(c.getFecha(), c.getDescripcion(), c.getNumCaso());
	    		gst.casoActualizar(c);
	    		JOptionPane.showMessageDialog(contentPane,"El estado ha sido cambiado con exito");
	    	}else if(estado.equals("consulta") || estado.equals("redactado")){
	    		int opcion=-1;
	    		if(estado.equals("consulta")){
	    			opcion = Integer.parseInt(JOptionPane.showInputDialog("Indique el estado al que quiere cambiar el caso\n"
	    					                                            + "1.'Aceptado'.\n"
	    					                                            + "2.'Rechazado'."));
	    			if(opcion==1){
	    				c.setEstado(estado);
	    				c.modificarEstado("aceptado");
	    				c.setNumCaso(numCaso);
	    				c.setDescripcion((String)datosCaso.get("descripcion"));
	    				c.setFecha(LocalDateTime.now());
	    				gst.historialAgregar(c.getFecha(), c.getDescripcion(), c.getNumCaso());
	    				gst.casoActualizar(c);
	    				JOptionPane.showMessageDialog(contentPane,"El estado ha sido cambiado con exito");
	    			}else if(opcion==2){
	    				c.setEstado(estado);
	    				c.modificarEstado("rechazado");
	    				c.setNumCaso(numCaso);
	    				c.setDescripcion((String)datosCaso.get("descripcion"));
	    				c.setFecha(LocalDateTime.now());
	    				gst.historialAgregar(c.getFecha(), c.getDescripcion(), c.getNumCaso());
	    				gst.casoActualizar(c);
	    				JOptionPane.showMessageDialog(contentPane,"El estado ha sido cambiado con exito");
	    			}else{
	    				JOptionPane.showMessageDialog(contentPane,"La opcion seleccionada es invalida, intente de nuevo");
	    			}
	    		}else if(estado.equals("redactado")){
	    			opcion = Integer.parseInt(JOptionPane.showInputDialog("Indique el estado al que quiere cambiar el caso\n"
                                                                        + "1.'Aceptado'.\n"
                                                                        + "2.'Rechazado'."));
	    			if(opcion==1){
	    				c.setEstado(estado);
	    				c.modificarEstado("revision");
	    				c.setNumCaso(numCaso);
	    				c.setDescripcion((String)datosCaso.get("descripcion"));
	    				c.setFecha(LocalDateTime.now());
	    				gst.historialAgregar(c.getFecha(), c.getDescripcion(), c.getNumCaso());
	    				gst.casoActualizar(c);
	    				JOptionPane.showMessageDialog(contentPane,"El estado ha sido cambiado con exito");
	    			}else if(opcion==2){
	    				c.setEstado(estado);
	    				c.modificarEstado("resuelto");
	    				c.setNumCaso(numCaso);
	    				c.setDescripcion((String)datosCaso.get("descripcion"));
	    				c.setFecha(LocalDateTime.now());
	    				gst.historialAgregar(c.getFecha(), c.getDescripcion(), c.getNumCaso());
	    				gst.casoActualizar(c);
	    				JOptionPane.showMessageDialog(contentPane,"El estado ha sido cambiado con exito");
	    			}else{
	    				JOptionPane.showMessageDialog(contentPane,"La opcion seleccionada es invalida, intente de nuevo");
	    			}
	    		}
	    		
	    	}else if(estado.equals("revision") || estado.equals("aceptado")){
	    		c.setEstado(estado);
	    	    c.modificarEstado("");
	    	    c.setNumCaso(numCaso);
	    	    c.setDescripcion((String)datosCaso.get("descripcion"));
	    	    c.setFecha(LocalDateTime.now());
				gst.historialAgregar(c.getFecha(), c.getDescripcion(), c.getNumCaso());
	    	    gst.casoActualizar(c);
	    	    JOptionPane.showMessageDialog(contentPane,"El estado ha sido cambiado con exito");
	    	}else{
	    		JOptionPane.showMessageDialog(contentPane, "El caso no puede ser cambiado de estado.");
	    	}
	    }catch(RuntimeException re){
	    	JOptionPane.showMessageDialog(contentPane, "Por favor seleccione una opcion de la fila 'Numero de caso'.");
	    	re.printStackTrace();
	    }catch(Exception ee){
	    	ee.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, (String) ee.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	    }
	}
}
