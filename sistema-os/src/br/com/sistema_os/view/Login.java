package br.com.sistema_os.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.sql.*;
import br.com.sistema_os.dal.ModuloConexao;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {
	//
	private JFrame frmSistemaDeOs;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	Connection conexao = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;


	/*
	 * Método para realizar o login
	 * */
	public void logar() {

		String sql = "SELECT * FROM usuarios WHERE login = ? and senha = ?";

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, txtUsuario.getText());

			String captura = new String(txtSenha.getPassword());
			stmt.setString(2, captura);


			rs = stmt.executeQuery();

			if(rs.next()) {

				String perfil = rs.getString("perfil");
				String nomeUsuario = rs.getString("nome");

				TelaPrincipal principal = new TelaPrincipal();
				
				if(perfil.equals("admin")) {
					TelaPrincipal.menuItemUsuario.setEnabled(true);
					TelaPrincipal.menuItemServico.setEnabled(true);
					TelaPrincipal.lblUsuario.setForeground(Color.red);
				}
				
				TelaPrincipal.lblUsuario.setText(nomeUsuario);
				
				
				principal.setVisible(true);
				principal.setLocationRelativeTo(null);

				this.frmSistemaDeOs.dispose();
				conexao.close();

			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha inválido");
				txtSenha.setText("");
			}


		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Algo deu errado ao acessar base de dados. Favor entrar em contado com a administração");
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmSistemaDeOs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();


	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		conexao = ModuloConexao.conector();		

		frmSistemaDeOs = new JFrame();
		frmSistemaDeOs.setResizable(false);
		frmSistemaDeOs.setTitle("Sistema de OS - Login");
		frmSistemaDeOs.setBounds(100, 100, 319, 228);
		frmSistemaDeOs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeOs.getContentPane().setLayout(null);
		frmSistemaDeOs.setLocationRelativeTo(null);  

		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBounds(12, 44, 70, 15);
		frmSistemaDeOs.getContentPane().add(lblUsurio);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(12, 100, 70, 15);
		frmSistemaDeOs.getContentPane().add(lblSenha);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(79, 36, 221, 32);
		frmSistemaDeOs.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		txtUsuario.setMargin(new Insets(0, 10, 0, 0));

		txtSenha = new JPasswordField();
		txtSenha.setBounds(79, 92, 221, 32);
		frmSistemaDeOs.getContentPane().add(txtSenha);
		txtSenha.setMargin(new Insets(0, 10, 0, 0));

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(183, 152, 117, 32);
		frmSistemaDeOs.getContentPane().add(btnLogin);

		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/br/com/sistema_os/icones/dbDesconectado32.png")));
		lblStatus.setBounds(12, 136, 37, 48);
		frmSistemaDeOs.getContentPane().add(lblStatus);

		//################################################
		//Actions

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});



		/*
		 * Setando icones verifica se conexão com o banco esta ok
		 * */
		if(conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_os/icones/dbConectado32.png")));
		} else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_os/icones/dbDesconectado32.png")));
		}
	}
}
