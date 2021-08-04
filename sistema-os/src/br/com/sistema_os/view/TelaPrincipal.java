package br.com.sistema_os.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.input.DataFormat;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	public static JMenuItem menuItemUsuario;
	public static JMenuItem menuItemServico;
	public static JLabel lblUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		
		setResizable(false);
		setTitle("Sistema de Controle para Ordem de Serviço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 654);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuCadastros = new JMenu("Cadastros");
		menuBar.add(menuCadastros);
		
		JMenuItem menuItemCliente = new JMenuItem("Cliente");
		menuItemCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		menuCadastros.add(menuItemCliente);
		
		JMenuItem menuItemOs = new JMenuItem("OS");
		menuCadastros.add(menuItemOs);
		
		menuItemUsuario = new JMenuItem("Usuário");		
		menuItemUsuario.setEnabled(false);
		menuCadastros.add(menuItemUsuario);
		
		
		JMenu menuRelatorio = new JMenu("Relatório");
		menuBar.add(menuRelatorio);
		
		menuItemServico = new JMenuItem("Serviços");
		menuItemServico.setEnabled(false);
		menuRelatorio.add(menuItemServico);
		
		JMenu menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);
		
		JMenuItem menuItemSobre = new JMenuItem("Sobre");
		menuItemSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		menuAjuda.add(menuItemSobre);
		
		JMenu menuOpcao = new JMenu("Opções");
		menuBar.add(menuOpcao);
		
		JMenuItem menuItemSair = new JMenuItem("Sair");		
		menuItemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		menuOpcao.add(menuItemSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(12, 12, 710, 577);
		contentPane.add(desktopPane);
		
		JLabel lblD = new JLabel("d");
		lblD.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/sistema_os/icones/logo.png")));
		lblD.setBounds(729, 354, 247, 235);
		contentPane.add(lblD);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUsuario.setBounds(740, 40, 236, 31);
		contentPane.add(lblUsuario);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Dialog", Font.BOLD, 24));
		lblData.setBounds(740, 83, 236, 50);
		contentPane.add(lblData);
		
		
		/*
		 * Actions
		 * ##################################
		 * */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
				lblData.setText(formatador.format(data));
				
			}
		});
		
		menuItemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
				sobre.setLocationRelativeTo(null);
				
			}
		});
		
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
				
				if(sair == 0) {
					System.exit(0);
				}
			}
		});
		
		menuItemUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario usuario = new TelaUsuario();
				usuario.setMaximizable(true);
				usuario.setVisible(true);
				desktopPane.add(usuario);
				Dimension d = desktopPane.getSize();
				usuario.setLocation((d.width - desktopPane.getSize().width) / 2, (d.height - desktopPane.getSize().height) / 2);
				
			}
		});
		
	}
}
