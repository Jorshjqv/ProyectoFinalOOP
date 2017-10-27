/**
 * Java Doc
 * Este clase de Vista sirve para generar la forma en como se van a mostrar los casos que tienen los querellantes en el sistem
 * to generate JavaDoc documentation
 *
 * @author Carlos Cubillo
 * @version 1.1, 27 de Abril del 2017
 */

package uiProyectoFinalOOP;
import clProyectoFinalOOP.*;

import java.awt.Font;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class VistaCasosQuerellante extends JFrame {
	/**
	 * Comienza con la generacion de variables que van a  servir para el JFrame
	 *
	 */

	private JPanel contentPane;
	private JLabel lblCasos;
	private JLabel lblQuerellante;
	private JTextPane textNombreQuerellante;
	private JLabel lblNumeroDeCaso;
	private JLabel lblEstado;
	private JList listNumCaso;
	private JList listEstado;
	private Gestor gst;

	
	public VistaCasosQuerellante() {
		
		
		/**
		 * Se comienzan a definir los bordes de los campos a mostrar en pantalla
		 *  
		 * @version 1.1, 
		 *
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCasos = new JLabel("Casos");
		lblCasos.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblCasos.setBounds(252, 39, 46, 14);
		contentPane.add(lblCasos);
		
		lblQuerellante = new JLabel("Querellante: ");
		lblQuerellante.setBounds(25, 75, 94, 31);
		contentPane.add(lblQuerellante);
		
		textNombreQuerellante = new JTextPane();
		textNombreQuerellante.setBounds(129, 75, 116, 20);
		contentPane.add(textNombreQuerellante);
		
		lblNumeroDeCaso = new JLabel("Numero de Caso:");
		lblNumeroDeCaso.setBounds(58, 141, 126, 26);
		contentPane.add(lblNumeroDeCaso);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(272, 141, 76, 26);
		contentPane.add(lblEstado);
		
		listNumCaso = new JList();
		listNumCaso.setBounds(58, 193, 126, 132);
		contentPane.add(listNumCaso);
		
		listEstado = new JList();
		listEstado.setBounds(252, 193, 126, 132);
		contentPane.add(listEstado);
		gst = new Gestor();
		
	}
	
	public void setVisible(){
		contentPane.setVisible(true);
	}
	
	public void cargarCasosQuerellante(String pCedula){
		/**
		 * Este metodo recibe el String de cedula para poder crear un Vector del Objeto Casos y crear un TreeMap para los datos de cada uno
		 *  de los usuarios del sistema.
		 *  Contiene un try catch exception en caso de que el usuario digite datos invalidos
		 *  @datosCaso es un Treemap para acceder a los casos
		 *  @datosJuez Treemap para buscar al juez asociado
		 *  @datosQuerellante  devuelve la informacion de busqueda para el querellante
		 *  @parametro pCedula es el valor de referencia para realizar la busqueda
		 *  
		 * @version 1.3, 
		 *
		 */
		Vector<TreeMap<String, Object>> casos;
		TreeMap<String, String>datosQuerellante;
		DefaultListModel dlmNC, dlmEstado;
		dlmNC = new DefaultListModel();
		dlmEstado = new DefaultListModel();
		
			try{
				datosQuerellante = gst.querellanteBuscar(pCedula);
				textNombreQuerellante.setText(datosQuerellante.get("Nombre") + " " + datosQuerellante.get("Apellido"));
				casos = gst.casoBuscarPorCedulaJ(pCedula);
				
				for(TreeMap<String, Object> t : casos){
					dlmNC.addElement(t.get("Numero Caso"));
					listNumCaso.setModel(dlmNC);
					dlmEstado.addElement(t.get("Estado"));
					listEstado.setModel(dlmEstado);
				}
			}catch(Exception e){
			    e.printStackTrace();	
			}
	}
}

