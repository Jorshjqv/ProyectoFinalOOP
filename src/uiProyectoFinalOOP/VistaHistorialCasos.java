package uiProyectoFinalOOP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JList;
/*
 * 
 * Clase VistaHistorialCasos
 * 
 * @autor Josue Quiros Valverde
 * 
 * Version 1.0
 * 
 * Esta clase contiene los elementos para presentar los datos del historial del caso
 * consultado en la VistaCasosJuez
 * */
public class VistaHistorialCasos extends JFrame {

	private JPanel contentPane;
	private JLabel lblTituloHistorial;
	private JLabel lblNumCaso;
	private JTextPane textNumCaso;
	private JLabel lblFecha;
	private JLabel lblEstado ;
	private JList listFecha;
	private JList listEstado;
	

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaHistorialCasos frame = new VistaHistorialCasos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public VistaHistorialCasos() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTituloHistorial = new JLabel("Historial del Caso");
		lblTituloHistorial.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblTituloHistorial.setBounds(147, 22, 123, 14);
		contentPane.add(lblTituloHistorial);
		
		lblNumCaso = new JLabel("Numero Caso:");
		lblNumCaso.setBounds(42, 86, 98, 25);
		contentPane.add(lblNumCaso);
		
		textNumCaso = new JTextPane();
		textNumCaso.setBounds(138, 86, 75, 20);
		contentPane.add(textNumCaso);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(58, 137, 46, 14);
		contentPane.add(lblFecha);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(211, 137, 46, 14);
		contentPane.add(lblEstado);
		
		listFecha = new JList();
		listFecha.setBounds(42, 169, 123, 232);
		contentPane.add(listFecha);
		
		listEstado = new JList();
		listEstado.setBounds(197, 169, 90, 232);
		contentPane.add(listEstado);
	}
	/*
	 * El metodo cargarHistorial toma como parametro una coleccion de objetos con 
	 * los datos a ser cargados en la vista
	 * pHistoriales coleccion de objetos que representa el vector con los datos de los historiales
	 * relacionados al caso consultado
	 * */
	public void cargarHistorial(Vector<TreeMap<String, Object>> pHistoriales){
		DefaultListModel dlmFecha, dlmEstado;
		dlmFecha = new DefaultListModel();
		dlmEstado = new DefaultListModel();
		String numCaso = (String)pHistoriales.get(0).get("Numero Caso");
		
		try{
			textNumCaso.setText(numCaso);
			for(TreeMap<String, Object> t : pHistoriales){
				dlmFecha.addElement(t.get("Fecha"));
				listFecha.setModel(dlmFecha);
				dlmEstado.addElement(t.get("Estado"));
				listEstado.setModel(dlmEstado);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
