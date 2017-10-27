package uiProyectoFinalOOP;

import java.awt.EventQueue;
import java.util.*;
import accesoDatosProyectoFinalOOP.Conector;

import javax.swing.*;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import clProyectoFinalOOP.*;
/*
 * Clase FrameLogin
 * 
 * @autor Josue Quiros Valverde
 * Version 1.0
 * 
 * Esta clase contiene los elementos para visualizar la aplicacion y para obtener
 * los credenciales de ingreso de jueces y querellantes para poder accesar a la informacion
 * del programa
 * */

public class FrameLogin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JTextPane txtpnUsuario;
	private JButton btnRegistrarUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conector.obtenerDatosLogin();
					
					FrameLogin window = new FrameLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 294);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnLogin = new JButton("Login");		
		btnLogin.setBounds(171, 170, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		textField = new JTextField();
		passwordField = new JPasswordField();
		
		
		txtpnUsuario = new JTextPane();
		JTextPane txtpnCedula = new JTextPane();
		JTextPane txtpnContrasea = new JTextPane();
		JTextPane txtpnNull = new JTextPane();
		
		ButtonGroup gRBotones = new ButtonGroup();
		
		JRadioButton rdbtnJuez = new JRadioButton("juez");
		rdbtnJuez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				textField.setBounds(171, 107, 89, 20);
				
				frame.getContentPane().add(passwordField);
                passwordField.setBounds(171, 138, 89, 20);
                passwordField.setEchoChar('*');
                
				txtpnUsuario.setBackground(SystemColor.menu);
				txtpnUsuario.setText("Usuario");
				txtpnUsuario.setBounds(95, 107, 118, 23);
				frame.getContentPane().add(txtpnUsuario);
				

				txtpnContrasea.setBackground(SystemColor.menu);
				txtpnContrasea.setText("Contraseña");
				txtpnContrasea.setBounds(83, 138, 118, 23);
				frame.getContentPane().add(txtpnContrasea);
				
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdbtnJuez.isSelected()){
							boolean auth = false;
							String usuario = textField.getText();
							String password = new String(passwordField.getPassword());
							VistaCasosJuez casos = null;
							try{
								auth = Gestor.juezAutenticar(usuario, password);
							}catch(Exception err){
								err.printStackTrace();
							}
							if(auth){
								JOptionPane.showMessageDialog(frame, "Ud ha accesado el sistema con exito!");
								casos = new VistaCasosJuez();
								casos.cargarCasosJuez(usuario);
								casos.setVisible(true);
							}else{
								JOptionPane.showMessageDialog(frame, "Los datos no se encuentran en el sitema.");
							}
						}
					}
				});
				
			}
		});
		rdbtnJuez.setBounds(69, 57, 109, 23);
		/*javadoc***
		 * 
		 * 
		 * Desde aca  se crea la opcin para poder acceder a la informacion de los casos de cada querellante y mostrarlos en pantalla.
		 * @rdbtnQuerellante  crea un boton para asi poder darle click a la opcion de querellante y le añade un actionListener para darle funcionalidad.
		 * Aca se hace un llamado al Gestor y la clase de VistaCasosQuerellante para poder acceder a la informacion almacenada en el sistema para cada uno.
		 * @auth boolean variable que funciona para la validacion de datos utilizando otros metodos en el sistema.
		 * @cedula es la variable que se utiliza para la autenticacion del usuario en cuestion.
		 * 
		 * 
		 * ****/
		JRadioButton rdbtnQuerellante = new JRadioButton("querellante");
		rdbtnQuerellante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				textField.setBounds(171, 107, 89, 20);
				
				frame.getContentPane().add(txtpnNull);
				txtpnNull.setBackground(SystemColor.menu);
				txtpnNull.setBounds(171, 138, 89, 20);
				
				
				txtpnCedula.setBackground(SystemColor.menu);
				txtpnCedula.setText("Cedula");
				txtpnCedula.setBounds(95, 107, 50, 20);
				frame.getContentPane().add(txtpnCedula);

				txtpnContrasea.setBackground(SystemColor.menu);
				txtpnContrasea.setText("");
				txtpnContrasea.setBounds(83, 138, 62, 20);
				frame.getContentPane().add(txtpnContrasea);
				
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdbtnQuerellante.isSelected()){
							boolean auth = false;
							TreeMap<String, String> querInfo = null;
							String cedula = textField.getText();
							Gestor gs = new Gestor();
							VistaCasosQuerellante casos = null;
							try{
								 querInfo = Gestor.querellanteBuscar(cedula);
							}catch(Exception err){
								err.printStackTrace();
							}
							if(querInfo.get("Cedula").equals(cedula)){
								JOptionPane.showMessageDialog(frame, "El usuario se encuentra registrado");
								casos = new VistaCasosQuerellante();
								casos.cargarCasosQuerellante(cedula);
								casos.setVisible(true);
							}else{
								JOptionPane.showMessageDialog(frame, "El usuario no se encuentra en el sistema");
							}
						}
					}
				});
			}
		});
		rdbtnQuerellante.setBounds(271, 57, 109, 23);
		gRBotones.add(rdbtnJuez);
		gRBotones.add(rdbtnQuerellante);
		gRBotones.add(btnLogin);
		gRBotones.add(btnRegistrarUsuario);
		frame.getContentPane().add(rdbtnJuez);
		frame.getContentPane().add(rdbtnQuerellante);
		
		JTextPane txtpnAccesoASistema = new JTextPane();
		txtpnAccesoASistema.setBackground(SystemColor.menu);
		txtpnAccesoASistema.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		txtpnAccesoASistema.setText("Acceso a Sistema de Casos");
		txtpnAccesoASistema.setBounds(128, 11, 180, 39);
		frame.getContentPane().add(txtpnAccesoASistema);
		
		btnRegistrarUsuario = new JButton("Registrar Usuario");
		btnRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaIngresarUsuario viu;
				viu = new VistaIngresarUsuario();
				viu.setVisible(true);
				viu.getContentPane().setVisible(true);
			}
		});
		btnRegistrarUsuario.setBounds(158, 204, 115, 29);
		frame.getContentPane().add(btnRegistrarUsuario);
	}
}
