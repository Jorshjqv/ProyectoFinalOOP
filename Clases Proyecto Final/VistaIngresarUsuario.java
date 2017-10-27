package uiProyectoFinalOOP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clProyectoFinalOOP.Gestor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * Clase VistaIngresarUsuario
 * 
 * @autor Josue Quiros Valverde
 * Version 1.0
 * 
 * Esta clase contiene los elementos para la vista de agregar usuarios
 * y funciones para agregar esta informacion al sistema
 * */
public class VistaIngresarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textCedula;
	private JTextField textSala;
	private JTextField textUsuario;
	private JTextField textContraseña;
	private JTextField textRContraseña;
	private JButton btnAgregarUsuario;
	private JLabel lblSala;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JLabel lblRepetirContrasea;
	private ButtonGroup grupoB;
	private JLabel lblDireccion;
	private JEditorPane editorDireccion;
	private JLabel lblIngresarUsuario;
	private JRadioButton rdbtnJuez;
	private JRadioButton rdbtnQuerellante;
	private JLabel lblTipoDeUsuario;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblTelefono;
	private JLabel lblCedula;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaIngresarUsuario frame = new VistaIngresarUsuario();
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
	public VistaIngresarUsuario() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIngresarUsuario = new JLabel("Ingresar Usuario");
		lblIngresarUsuario.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblIngresarUsuario.setBounds(195, 22, 138, 27);
		contentPane.add(lblIngresarUsuario);

		rdbtnJuez = new JRadioButton("Juez");
		rdbtnJuez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		rdbtnJuez.setBounds(43, 82, 109, 23);
		contentPane.add(rdbtnJuez);

		rdbtnQuerellante = new JRadioButton("Querellante");
		rdbtnQuerellante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		rdbtnQuerellante.setBounds(43, 108, 109, 23);
		contentPane.add(rdbtnQuerellante);

		grupoB = new ButtonGroup();
		grupoB.add(rdbtnQuerellante);
		grupoB.add(rdbtnJuez);
		
		lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setBounds(24, 52, 103, 23);
		contentPane.add(lblTipoDeUsuario);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(64, 157, 63, 14);
		contentPane.add(lblNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(64, 192, 63, 14);
		contentPane.add(lblApellido);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(64, 227, 63, 14);
		contentPane.add(lblTelefono);

		lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(64, 261, 63, 14);
		contentPane.add(lblCedula);

		lblSala = new JLabel("Sala:");
		lblUsuario = new JLabel("Usuario:");
		lblContrasea = new JLabel("Contraseña:");
		lblRepetirContrasea = new JLabel("Repetir Contraseña:");

		textNombre = new JTextField();
		textNombre.setBounds(143, 154, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.setBounds(143, 189, 86, 20);
		contentPane.add(textApellido);
		textApellido.setColumns(10);

		textTelefono = new JTextField();
		textTelefono.setBounds(143, 224, 86, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);

		textCedula = new JTextField();
		textCedula.setBounds(143, 258, 86, 20);
		contentPane.add(textCedula);
		textCedula.setColumns(10);

		textSala = new JTextField();
		textUsuario = new JTextField();
		textContraseña = new JTextField();		
		textRContraseña = new JTextField();		

		btnAgregarUsuario = new JButton("Agregar Usuario");
		btnAgregarUsuario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nombre, apellido, telefono, cedula, usuario, contraseña, direccion, sala;
				contraseña = "";
				if(rdbtnJuez.isSelected()){
					nombre = textNombre.getText();
					apellido = textApellido.getText();
					telefono = textTelefono.getText();
					cedula = textCedula.getText();
					usuario = textUsuario.getText();
					if(textContraseña.getText().equals(textRContraseña.getText())){
						contraseña = textContraseña.getText();
					}else{
						JOptionPane.showMessageDialog(contentPane, "Las contraseñas no coinciden.");
					}
					sala = textSala.getText();
					try{
						Gestor.juezCrear(nombre, apellido, cedula, telefono, sala, usuario, contraseña);
						JOptionPane.showMessageDialog(contentPane, "El juez se ha agregado satisfactoriamente al sistema.");
					}catch(Exception ee){
						ee.printStackTrace();
					}
				}else if(rdbtnQuerellante.isSelected()){
					nombre = textNombre.getText();
					apellido = textApellido.getText();
					telefono = textTelefono.getText();
					cedula = textCedula.getText();
					direccion = editorDireccion.getText();
					try{
						Gestor.querellanteCrear(nombre, apellido, telefono, cedula, direccion);
						JOptionPane.showMessageDialog(contentPane, "El querellante se ha agregado satisfactoriamente al sistema.");
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
		btnAgregarUsuario.setBounds(299, 60, 138, 56);
		contentPane.add(btnAgregarUsuario);

		lblDireccion = new JLabel("Direccion:");
		lblSala.setBounds(81, 296, 46, 14);
		contentPane.add(lblSala);

		lblUsuario.setBounds(81, 328, 46, 14);
		contentPane.add(lblUsuario);

		lblContrasea.setBounds(61, 363, 66, 14);
		contentPane.add(lblContrasea);

		lblRepetirContrasea.setBounds(24, 394, 103, 14);
		contentPane.add(lblRepetirContrasea);

		textSala.setBounds(143, 293, 86, 20);
		contentPane.add(textSala);
		textSala.setColumns(10);

		textUsuario.setBounds(143, 325, 86, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		textContraseña.setBounds(143, 360, 86, 20);
		contentPane.add(textContraseña);
		textContraseña.setColumns(10);

		textRContraseña.setBounds(143, 391, 86, 20);
		contentPane.add(textRContraseña);
		textRContraseña.setColumns(10);
		editorDireccion = new JEditorPane();
		lblDireccion.setBounds(277, 189, 65, 14);
		contentPane.add(lblDireccion);

		editorDireccion.setBounds(335, 166, 138, 62);
		contentPane.add(editorDireccion);

	}
}
