package br.com.sistema_os.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TelaSobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
	public TelaSobre() {
		setTitle("Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSistemaParaControle = new JLabel("Sistema para controle de Ordem de Serviços");
		lblSistemaParaControle.setBounds(12, 12, 352, 28);
		contentPane.add(lblSistemaParaControle);
		
		JLabel lblDesenvolvidoPorMarwam = new JLabel("Desenvolvido por: Marwam Malta");
		lblDesenvolvidoPorMarwam.setBounds(12, 52, 317, 28);
		contentPane.add(lblDesenvolvidoPorMarwam);
		
		JLabel lblSobALicena = new JLabel("Sob a licença GPL");
		lblSobALicena.setBounds(238, 220, 144, 28);
		contentPane.add(lblSobALicena);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaSobre.class.getResource("/br/com/sistema_os/icones/gpl.png")));
		lblNewLabel.setBounds(12, 117, 244, 249);
		contentPane.add(lblNewLabel);
	}
}
