package br.com.sistema_os.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.sistema_os.dal.ModuloConexao;

public class TelaUsuario extends JInternalFrame {

	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JPasswordField txtSenha;
	private JTextField txtLogin;
	private JComboBox cboPerfil;

	Connection conexao = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaUsuario() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Cadastro de Usuários");
		setBounds(100, 100, 710, 575);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(46, 69, 36, 24);
		getContentPane().add(lblId);

		JLabel lblNome = new JLabel("* Nome:");
		lblNome.setBounds(12, 116, 70, 24);
		getContentPane().add(lblNome);

		JLabel lblNome_1 = new JLabel("* Senha:");
		lblNome_1.setBounds(285, 202, 70, 24);
		getContentPane().add(lblNome_1);

		JLabel lblNome_1_1 = new JLabel("* Login:");
		lblNome_1_1.setBounds(12, 202, 70, 24);
		getContentPane().add(lblNome_1_1);

		JLabel lblNome_1_2 = new JLabel("* Perfil:");
		lblNome_1_2.setBounds(512, 202, 63, 24);
		getContentPane().add(lblNome_1_2);

		txtId = new JTextField();
		txtId.setBounds(86, 70, 53, 24);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(86, 117, 602, 24);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(86, 161, 200, 24);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(358, 203, 136, 24);
		getContentPane().add(txtSenha);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		cboPerfil.setBounds(574, 202, 114, 24);
		getContentPane().add(cboPerfil);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 160, 70, 24);
		getContentPane().add(lblTelefone);

		txtLogin = new JTextField();
		txtLogin.setBounds(86, 203, 181, 24);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JButton btnNovo = new JButton("");		
		btnNovo.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/sistema_os/icones/btnNovo.png")));
		btnNovo.setBounds(221, 346, 80, 80);
		btnNovo.setToolTipText("Adicionar");
		btnNovo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnNovo);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/sistema_os/icones/btnEditar.png")));
		btnEditar.setBounds(376, 346, 80, 80);
		btnEditar.setToolTipText("Editar");
		btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/sistema_os/icones/btnDelete.png")));
		btnExcluir.setBounds(529, 346, 80, 80);
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnExcluir);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/sistema_os/icones/btnPesquisar.png")));
		btnPesquisar.setBounds(67, 346, 80, 80);
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnPesquisar);


		//##############Actions
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				consultar();				
			}
		});

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});


		conexao = ModuloConexao.conector();

	}


	/*
	 * SALVAR
	 * */
	private void salvar() {

		
		txtLogin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		txtNome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		txtSenha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		String sql = "INSERT INTO usuarios (nome, telefone, login, senha, perfil) value (?,?,?,?,?)";

		try {

			Boolean valid = true;
			String msgValid = "";

			if(txtNome.getText().isEmpty()) {
				valid = false;
				msgValid += "Favor preencher o campo Nome\n";
				txtNome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
			}
			
			if(txtLogin.getText().isEmpty()) {
				valid = false;
				msgValid += "Favor preencher o campo Login\n";
				txtLogin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
			}

			String captura = new String(txtSenha.getPassword());
			if(captura.isEmpty()) {
				valid = false;
				msgValid += "Favor preencher o campo Senha\n";
				txtSenha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
			}

			if(valid) {

				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, txtNome.getText());
				stmt.setString(2, txtTelefone.getText());
				stmt.setString(3, txtLogin.getText());
				stmt.setString(4, captura);
				stmt.setString(5, cboPerfil.getSelectedItem().toString());


				int adicionado = stmt.executeUpdate();

				if(adicionado > 0) {

					txtId.setText("");
					txtNome.setText("");
					txtLogin.setText("");
					txtTelefone.setText("");
					txtSenha.setText("");
					
					txtLogin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
					txtNome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
					txtSenha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

					JOptionPane.showMessageDialog(null, "Usuário "+ txtNome.getText() +" cadastrado com sucesso.");
				}

			} else {
				JOptionPane.showMessageDialog(null, msgValid);
			}



		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Algo deu errado ao salvar usuário. Tente novamente ou contate o administrador do sistema");
		}
	}


	/*
	 * CONSULTAR
	 * */
	private void consultar() {

		String sql = "SELECT * FROM usuarios WHERE id = ?";

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, txtId.getText());

			rs = stmt.executeQuery();

			if (rs.next()) {

				txtNome.setText(rs.getString("nome"));
				txtLogin.setText(rs.getString("login"));
				txtTelefone.setText(rs.getString("telefone"));
				txtSenha.setText(rs.getString("senha"));
				cboPerfil.setSelectedItem(rs.getString("perfil"));

			} else {

				JOptionPane.showMessageDialog(null, "Usuário não cadastrado");

				txtNome.setText("");
				txtLogin.setText("");
				txtTelefone.setText("");
				txtSenha.setText("");

			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Algo deu errado a consulta. Tente novamente ou chame a equipe técnica.");
		}

	}

}
