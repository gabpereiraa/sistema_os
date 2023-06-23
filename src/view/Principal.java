package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.JEditorPane;

@SuppressWarnings("unused")
public class Principal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblData;
	private JLabel lblLogo;
	public JButton btnUsuarios;
	public JLabel lblUsuario;
	public JButton btnRelatorios;
	public JPanel panelRodape;
	public JButton btnTecnicos;
	public JButton btnFornecedores;
	public JButton btnEstoque;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/smartphone-icon2.png")));
		setResizable(false);
		setTitle("Assistencia de Smartphones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		JButton btnSobre = new JButton("");
		btnSobre.setBounds(619, 10, 48, 48);
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		btnSobre.setContentAreaFilled(false);
		btnSobre.setBorderPainted(false);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/img/about.png")));
		contentPane.add(btnSobre);
		panelRodape = new JPanel();
		panelRodape.setBounds(0, 401, 677, 40);
		panelRodape.setBackground(SystemColor.windowBorder);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);
		lblData = new JLabel("");
		lblData.setBounds(339, 7, 308, 18);
		panelRodape.add(lblData);
		lblData.setForeground(SystemColor.text);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 16));
		JLabel lblNewLabel = new JLabel("Usuário:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 58, 14);
		panelRodape.add(lblNewLabel);
		lblUsuario = new JLabel("New label");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBounds(66, 11, 120, 14);
		panelRodape.add(lblUsuario);
		lblLogo = new JLabel("");
		lblLogo.setBounds(563, 232, 200, 200);
		lblLogo.setIcon(new ImageIcon(Principal.class.getResource("/img/smartphone-image.png")));
		contentPane.add(lblLogo);
		btnUsuarios = new JButton("");
		btnUsuarios.setBounds(10, 11, 128, 128);
		btnUsuarios.setEnabled(false);
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setBorderPainted(false);
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/usuarios.png")));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		contentPane.add(btnUsuarios);
		JButton btnServico = new JButton("");
		btnServico.setBounds(10, 150, 128, 128);
		btnServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico servicos = new Servico();
				servicos.setVisible(true);
				servicos.usuario = lblUsuario.getText();
			}
		});
		btnServico.setContentAreaFilled(false);
		btnServico.setBorderPainted(false);
		btnServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServico.setToolTipText("Ordem de Serviço");
		btnServico.setIcon(new ImageIcon(Principal.class.getResource("/img/icon-orderservice.png")));
		contentPane.add(btnServico);
		JButton btnClientes = new JButton("");
		btnClientes.setBounds(148, 150, 128, 128);
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBorderPainted(false);
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/icon-clientes.png")));
		btnClientes.setToolTipText("Cliente");
		contentPane.add(btnClientes);
		btnRelatorios = new JButton("");
		btnRelatorios.setBounds(148, 11, 128, 128);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setEnabled(false);
		btnRelatorios.setContentAreaFilled(false);
		btnRelatorios.setBorderPainted(false);
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/report1.png")));
		btnRelatorios.setToolTipText("Relatorio");
		contentPane.add(btnRelatorios);
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(563, 10, 48, 48);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				Principal.this.dispose();
			}
		});
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/img/logout.png")));
		btnNewButton.setToolTipText("Sair");
		contentPane.add(btnNewButton);
		btnTecnicos = new JButton("");
		btnTecnicos.setBounds(286, 11, 128, 128);
		btnTecnicos.setEnabled(false);
		btnTecnicos.setToolTipText("Técnicos");
		btnTecnicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTecnicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tecnicos tecnicos = new Tecnicos();
				tecnicos.setVisible(true);
			}
		});
		btnTecnicos.setBorderPainted(false);
		btnTecnicos.setContentAreaFilled(false);
		btnTecnicos.setIcon(new ImageIcon(Principal.class.getResource("/img/icon-tecnico.png")));
		contentPane.add(btnTecnicos);
		btnFornecedores = new JButton("");
		btnFornecedores.setBounds(424, 11, 128, 128);
		btnFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores fornecedores = new Fornecedores();
				fornecedores.setVisible(true);
			}
		});
		btnFornecedores.setIcon(new ImageIcon(Principal.class.getResource("/img/fornecedor-principal.png")));
		btnFornecedores.setToolTipText("Fornecedores");
		btnFornecedores.setEnabled(false);
		btnFornecedores.setContentAreaFilled(false);
		btnFornecedores.setBorderPainted(false);
		contentPane.add(btnFornecedores);
		btnEstoque = new JButton("");
		btnEstoque.setBounds(286, 150, 128, 128);
		btnEstoque.setEnabled(false);
		btnEstoque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoques estoques = new Estoques();
				estoques.setVisible(true);
			}
		});
		btnEstoque.setIcon(new ImageIcon(Principal.class.getResource("/img/storage-estoque.png")));
		btnEstoque.setToolTipText("Estoque");
		btnEstoque.setContentAreaFilled(false);
		btnEstoque.setBorderPainted(false);
		contentPane.add(btnEstoque);
	}

	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(data));
	}
}
