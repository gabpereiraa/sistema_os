package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import model.DAO;
import util.Validador;

@SuppressWarnings({ "serial", "unused" })
public class Servico extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JTextField txtOS;
	private JTextField txtIDcli;
	private JTextField txtValor;
	private JTextField txtDefeito;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtImei;
	private JTextField txtDiagnostico;
	@SuppressWarnings("rawtypes")
	private JComboBox cboStatus;
	private JButton btnBuscarOS;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnLimpar;
	private JTextField txtNome;
	@SuppressWarnings("rawtypes")
	private JList listaUsuarios;
	private JScrollPane scrollPane;
	private JDateChooser txtDataSaida;
	private JDateChooser txtDataEntrada;
	private JTextField txtIDtec;
	private JTextField txtNome1;
	private JScrollPane scrollPane1;
	@SuppressWarnings("rawtypes")
	private JList listaUsuarios1;
	private JTextField txtUser;
	public String usuario;
	private JButton btnOS;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servico dialog = new Servico();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Servico() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtUser.setText(usuario);
			}
		});
		setTitle("Assistencia de Smartphones - ORDEM DE SERVIÇO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servico.class.getResource("/img/conserto-celular.png")));
		setBounds(100, 100, 845, 615);
		getContentPane().setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00C9CNICO", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(553, 11, 195, 135);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		scrollPane1 = new JScrollPane();
		scrollPane1.setVisible(false);
		scrollPane1.setBounds(10, 64, 175, 60);
		panel_2.add(scrollPane1);
		listaUsuarios1 = new JList();
		listaUsuarios1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuariosLista();
			}
		});
		scrollPane1.setViewportView(listaUsuarios1);
		txtIDtec = new JTextField();
		txtIDtec.setEditable(false);
		txtIDtec.setColumns(10);
		txtIDtec.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"ID T\u00C9CNICO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtIDtec.setBounds(10, 78, 175, 40);
		panel_2.add(txtIDtec);
		txtNome1 = new JTextField();
		txtNome1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuarios();
			}
		});
		txtNome1.setColumns(10);
		txtNome1.setBorder(new TitledBorder(null, "NOME", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtNome1.setBounds(10, 24, 175, 43);
		panel_2.add(txtNome1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CLIENTE",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(348, 11, 195, 135);
		getContentPane().add(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(10, 64, 175, 60);
		panel.add(scrollPane);
		listaUsuarios = new JList();
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClientesLista();
			}
		});
		scrollPane.setViewportView(listaUsuarios);
		txtIDcli = new JTextField();
		txtIDcli.setEditable(false);
		txtIDcli.setBorder(new TitledBorder(null, "ID CLIENTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtIDcli.setBounds(10, 78, 175, 40);
		panel.add(txtIDcli);
		txtIDcli.setColumns(10);
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}
		});
		txtNome.setBorder(new TitledBorder(null, "NOME", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtNome.setBounds(10, 24, 175, 43);
		panel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(30));
		txtOS = new JTextField();
		txtOS.setEnabled(false);
		txtOS.setBorder(new TitledBorder(null, "OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtOS.setBounds(131, 11, 111, 48);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("DEFEITO");
		lblNewLabel_2.setBounds(10, 298, 64, 14);
		getContentPane().add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("DIAGNÓSTICO");
		lblNewLabel_3.setBounds(10, 406, 93, 14);
		getContentPane().add(lblNewLabel_3);
		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(new String[] { "", "AGUARDANDO DIAGNOSTICO",
				"AGUARDANDO APROVAÇÃO DO CLIENTE", "ORDEM NÃO APROVADA PELO CLIENTE", "AGUARDANDO TÉCNICO",
				"AGUARDANDO PEÇAS", "NA BANCADA", "PRONTO" }));
		cboStatus.setBounds(131, 336, 335, 22);
		getContentPane().add(cboStatus);
		JLabel lblNewLabel_5 = new JLabel("VALOR");
		lblNewLabel_5.setBounds(559, 459, 46, 14);
		getContentPane().add(lblNewLabel_5);
		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtValor.setText("0");
		txtValor.setBounds(637, 457, 111, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		txtDefeito = new JTextField();
		txtDefeito.setBounds(131, 287, 617, 38);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);
		txtDefeito.setDocument(new Validador(200));
		txtDiagnostico = new JTextField();
		txtDiagnostico.setBounds(131, 382, 617, 64);
		getContentPane().add(txtDiagnostico);
		txtDiagnostico.setColumns(10);
		txtDiagnostico.setDocument(new Validador(200));
		JLabel lblNewLabel_11 = new JLabel("DATA ENTRADA");
		lblNewLabel_11.setBounds(10, 99, 91, 14);
		getContentPane().add(lblNewLabel_11);
		btnCreate = new JButton("");
		btnCreate.setToolTipText("Adicionar OS");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarOS();
			}
		});
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setIcon(new ImageIcon(Servico.class.getResource("/img/addfile.png")));
		btnCreate.setBounds(86, 511, 48, 48);
		getContentPane().add(btnCreate);
		btnUpdate = new JButton("");
		btnUpdate.setToolTipText("Atualizar OS");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOS();
			}
		});
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setIcon(new ImageIcon(Servico.class.getResource("/img/update-motos.png")));
		btnUpdate.setBounds(144, 511, 48, 48);
		getContentPane().add(btnUpdate);
		btnLimpar = new JButton("");
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.setIcon(new ImageIcon(Servico.class.getResource("/img/eraser.png")));
		btnLimpar.setBounds(422, 511, 48, 48);
		getContentPane().add(btnLimpar);
		btnDelete = new JButton("");
		btnDelete.setToolTipText("Deletar OS");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOS();
			}
		});
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setIcon(new ImageIcon(Servico.class.getResource("/img/trash.png")));
		btnDelete.setBounds(270, 511, 48, 48);
		getContentPane().add(btnDelete);
		JLabel lblNewLabel_12 = new JLabel("DATA SAIDA");
		lblNewLabel_12.setBounds(10, 460, 79, 13);
		getContentPane().add(lblNewLabel_12);
		btnBuscarOS = new JButton("");
		btnBuscarOS.setToolTipText("Buscar OS");
		btnBuscarOS.setIcon(new ImageIcon(Servico.class.getResource("/img/searchOS.png")));
		btnBuscarOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarOS();
			}
		});
		btnBuscarOS.setBounds(252, 11, 64, 64);
		getContentPane().add(btnBuscarOS);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "APARELHO", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 172, 738, 104);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		JLabel lblNewLabel_6 = new JLabel("MARCA");
		lblNewLabel_6.setBounds(45, 25, 46, 14);
		panel_1.add(lblNewLabel_6);
		txtMarca = new JTextField();
		txtMarca.setBounds(123, 23, 248, 20);
		panel_1.add(txtMarca);
		txtMarca.setColumns(10);
		txtMarca.setDocument(new Validador(15));
		JLabel lblNewLabel_7 = new JLabel("MODELO");
		lblNewLabel_7.setBounds(396, 25, 74, 14);
		panel_1.add(lblNewLabel_7);
		txtModelo = new JTextField();
		txtModelo.setBounds(480, 23, 248, 20);
		panel_1.add(txtModelo);
		txtModelo.setColumns(10);
		txtModelo.setDocument(new Validador(20));
		txtImei = new JTextField();
		txtImei.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtImei.setBounds(123, 53, 248, 20);
		panel_1.add(txtImei);
		txtImei.setColumns(10);
		txtImei.setDocument(new Validador(15));
		JLabel lblNewLabel_8 = new JLabel("IMEI");
		lblNewLabel_8.setBounds(45, 55, 30, 14);
		panel_1.add(lblNewLabel_8);
		JLabel lblNewLabel = new JLabel("GARANTIA DE 3 MESSES\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblNewLabel.setBounds(280, 459, 139, 14);
		getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("STATUS");
		lblNewLabel_1.setBounds(10, 341, 64, 13);
		getContentPane().add(lblNewLabel_1);
		txtDataEntrada = new JDateChooser();
		txtDataEntrada.setEnabled(false);
		txtDataEntrada.setBounds(131, 99, 138, 20);
		getContentPane().add(txtDataEntrada);
		txtDataSaida = new JDateChooser();
		txtDataSaida.setBounds(131, 453, 138, 20);
		getContentPane().add(txtDataSaida);
		txtUser = new JTextField();
		txtUser.setEditable(false);
		txtUser.setBounds(131, 130, 185, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		JLabel lblNewLabel_4 = new JLabel("USUÁRIO");
		lblNewLabel_4.setBounds(10, 132, 64, 14);
		getContentPane().add(lblNewLabel_4);
		btnOS = new JButton("");
		btnOS.setEnabled(false);
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
			}
		});
		btnOS.setToolTipText("Impressão da OS");
		btnOS.setBorderPainted(false);
		btnOS.setContentAreaFilled(false);
		btnOS.setIcon(new ImageIcon(Servico.class.getResource("/img/impressora.png")));
		btnOS.setBounds(212, 511, 48, 48);
		getContentPane().add(btnOS);

	}

	private void buscarOS() {
		String numOS = JOptionPane.showInputDialog("Número da OS");
		String read = "select * from servicos where os = ? ";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
				txtIDcli.setText(rs.getString(2));
				txtDefeito.setText(rs.getString(3));
				txtDiagnostico.setText(rs.getString(4));
				cboStatus.setSelectedItem(rs.getString(5));
				txtValor.setText(rs.getString(6));
				txtMarca.setText(rs.getString(9));
				txtModelo.setText(rs.getString(10));
				txtImei.setText(rs.getString(11));
				txtIDtec.setText(rs.getString(12));
				txtUser.setText(rs.getString(13));
				String setarData = rs.getString(7);
				Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
				txtDataEntrada.setDate(dataFormatada);
				String setarDataSaida = rs.getString(8);
				if (setarDataSaida == null) {
					txtDataSaida.setDate(null);
				} else {
					Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataSaida);
					txtDataSaida.setDate(dataFormatada2);
				}
				btnCreate.setEnabled(false);
				btnDelete.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnBuscarOS.setEnabled(false);
				btnOS.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Ordem de Serviço Inexistente");
				btnCreate.setEnabled(true);
				btnBuscarOS.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaUsuarios.setModel(modelo);
		String readLista = "select * from clientes where nome like '" + txtNome.getText() + "%'" + " order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery(readLista);
			while (rs.next()) {
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarClientesLista() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Usuario");
			txtNome.requestFocus();
		} else {
			int linha = listaUsuarios.getSelectedIndex();
			if (linha >= 0) {
				String read2 = "select * from clientes where nome like '" + txtNome.getText() + "%'"
						+ "order by nome limit " + (linha) + " , 1";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(read2);
					rs = pst.executeQuery();
					if (rs.next()) {
						scrollPane.setVisible(false);
						txtIDcli.setText(rs.getString(1));
						txtNome.setText(rs.getString(2));
					} else {
						JOptionPane.showMessageDialog(null, "Cliente Inexistente");
						btnCreate.setEnabled(true);
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				scrollPane.setVisible(false);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void listarUsuarios() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaUsuarios1.setModel(modelo);
		String readLista = "select * from tecnicos where nome like '" + txtNome1.getText() + "%'" + " order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery(readLista);
			while (rs.next()) {
				scrollPane1.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome1.getText().isEmpty()) {
					scrollPane1.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarUsuariosLista() {
		if (txtNome1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Usuario");
			txtNome1.requestFocus();
		} else {
			int linha = listaUsuarios1.getSelectedIndex();
			if (linha >= 0) {
				String read2 = "select * from tecnicos where nome like '" + txtNome1.getText() + "%'"
						+ "order by nome limit " + (linha) + " , 1";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(read2);
					rs = pst.executeQuery();
					if (rs.next()) {
						scrollPane1.setVisible(false);
						txtIDtec.setText(rs.getString(1));
						txtNome1.setText(rs.getString(2));
					} else {
						JOptionPane.showMessageDialog(null, "Usuario Inexistente");
						btnCreate.setEnabled(true);
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				scrollPane1.setVisible(false);
			}
		}
	}

	private void adicionarOS() {
		if (txtIDcli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o ID do CLiente");
			txtIDcli.requestFocus();
		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Marca do Aparelho");
			txtMarca.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Modelo do Aparelho");
			txtModelo.requestFocus();
		} else if (txtImei.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o IMEI do Aparelho");
			txtImei.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Defeito do Aparelho");
			txtDefeito.requestFocus();
		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o Status do Aparelho");
			cboStatus.requestFocus();
		} else {
			String create = "insert into servicos(idcli, defeito, diagnostico, statusOS, valor, dataOSsaida, marcaOS, modeloOS, imei, idtec, usuario) values (?,?,?,?,?,?,?,?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtIDcli.getText());
				pst.setString(2, txtDefeito.getText());
				pst.setString(3, txtDiagnostico.getText());
				pst.setString(4, cboStatus.getSelectedItem().toString());
				pst.setString(5, txtValor.getText());
				pst.setString(7, txtMarca.getText());
				pst.setString(8, txtModelo.getText());
				pst.setString(9, txtImei.getText());
				pst.setString(10, txtIDtec.getText());
				pst.setString(11, txtUser.getText());
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				if (txtDataSaida.getDate() == null) {
					pst.setString(6, null);
				} else {
					String dataFormatada = formatador.format(txtDataSaida.getDate());
					pst.setString(6, dataFormatada);
				}
				if (!cboStatus.getSelectedItem().equals("AGUARDANDO DIAGNOSTICO") && txtIDtec.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Tecnico");
					txtIDtec.requestFocus();
				} else {
					pst.setString(10, txtIDtec.getText());
					if (txtIDtec.getText().equals("")) {
						pst.setString(10, null);
					} else {
						pst.setString(10, txtIDtec.getText());
					}
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Ordem de Serviço Cadastrada com Sucesso");
					limparCampos();
					recuperarOS();
					btnOS.setEnabled(true);
					con.close();
				}
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void editarOS() {
		if (txtIDcli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o ID do CLiente");
			txtIDcli.requestFocus();
		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Marca do Aparelho");
			txtMarca.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Modelo do Aparelho");
			txtModelo.requestFocus();
		} else if (txtImei.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o IMEI do Aparelho");
			txtImei.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Defeito do Aparelho");
			txtDefeito.requestFocus();
		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o Status do Aparelho");
			cboStatus.requestFocus();
		} else {
			String update = "update servicos set idcli=?, defeito=?, diagnostico=?, statusOS=?, valor=?, dataOSsaida=?, marcaOS=?, modeloOS=?, imei=?, idtec=?, usuario=? where os = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtIDcli.getText());
				pst.setString(2, txtDefeito.getText());
				pst.setString(3, txtDiagnostico.getText());
				pst.setString(4, cboStatus.getSelectedItem().toString());
				pst.setString(5, txtValor.getText());
				pst.setString(7, txtMarca.getText());
				pst.setString(8, txtModelo.getText());
				pst.setString(9, txtImei.getText());
				pst.setString(11, txtUser.getText());
				pst.setString(12, txtOS.getText());
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				if (txtDataSaida.getDate() == null) {
					pst.setString(6, null);
				} else {
					String dataFormatada = formatador.format(txtDataSaida.getDate());
					pst.setString(6, dataFormatada);
				}
				if (!cboStatus.getSelectedItem().equals("AGUARDANDO DIAGNOSTICO") && txtIDtec.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Tecnico");
					txtIDtec.requestFocus();
				} else {
					pst.setString(10, txtIDtec.getText());
					if (txtIDtec.getText().equals("")) {
						pst.setString(10, null);
					} else {
						pst.setString(10, txtIDtec.getText());
					}
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Dados da Ordem de Serviço alterado com Sucesso");
					con.close();
					limparCampos();
					btnOS.setEnabled(true);
				}
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null,
						"Ordem de Serviço não atualizada. \nEste Numero de OS está sendo Utilizado.");
				txtOS.setText(null);
				txtOS.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void excluirOS() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse usuário?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from servicos where os=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtOS.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Ordem de Serviço excluído");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void imprimirOS() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
			document.open();
			String readOS = "select servicos.os,date_format(servicos.dataOS, '%d/%m/%Y') as data_entrada, servicos.usuario, servicos.MarcaOS, servicos.ModeloOS, servicos.imei, servicos.defeito, clientes.nome as cliente, clientes.cpf as cpf, clientes.rg as rg, clientes.telefone1 as telefone, clientes.telefone2 as telefone2, clientes.email as email, clientes.endereco as endereco, clientes.numeroendereco as Numero, clientes.bairro as Bairro, clientes.cidade as cidade, clientes.uf as uf, clientes.cep as cep, clientes.complemento as complemento, tecnicos.nome as Técnico, servicos.diagnostico, date_format(dataOSsaida,'%d/%m/%Y') as data_saida, valor from servicos inner join clientes on servicos.idcli = clientes.idcli inner join tecnicos on servicos.idtec = tecnicos.idtec where os = ?;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOS);
				pst.setString(1, txtOS.getText());
				rs = pst.executeQuery();
				if (rs.next()) {
					Paragraph os = new Paragraph("OS: " + rs.getString(1));
					os.setAlignment(Element.ALIGN_CENTER);
					document.add(os);
					Image image = Image.getInstance(Servico.class.getResource("/img/logo-smartphone.png"));
					image.scaleToFit(192, 148);
					image.setAbsolutePosition(440, 690);
					document.add(image);
					Paragraph usuario = new Paragraph("Usuario: " + rs.getString(3));
					document.add(usuario);
					Paragraph DataEntrada = new Paragraph("Data de Entrada: " + rs.getString(2));
					document.add(DataEntrada);
					document.add(new Paragraph("_______________________________________________________________"));
					document.add(new Paragraph(" "));
					Paragraph TitleCli = new Paragraph("DADOS DO CLIENTE");
					TitleCli.setAlignment(Element.ALIGN_CENTER);
					document.add(TitleCli);
					document.add(new Paragraph(" "));
					Paragraph DadosCli = new Paragraph("CLIENTE:  " + rs.getString(8) + "     CPF: " + rs.getString(9)
							+ "     RG: " + rs.getString(10));
					document.add(DadosCli);
					Paragraph DadosCli2 = new Paragraph(
							"FONE 1: " + rs.getString(11) + "  FONE 2: " + rs.getString(12));
					document.add(DadosCli2);
					Paragraph DadosCli3 = new Paragraph("EMAIL: " + rs.getString(13));
					document.add(DadosCli3);
					document.add(new Paragraph(" "));
					Paragraph EndCli = new Paragraph("ENDEREÇO:  " + rs.getString(14) + "   N°: " + rs.getString(15));
					document.add(EndCli);
					Paragraph EndCli2 = new Paragraph("BAIRRO: " + rs.getString(16) + "     CIDADE: " + rs.getString(17)
							+ "   UF: " + rs.getString(18));
					document.add(EndCli2);
					document.add(new Paragraph(
							"_____________________________________________________________________________"));
					document.add(new Paragraph(" "));
					Paragraph TitleSmart = new Paragraph("DADOS DO SMARTPHONE");
					TitleSmart.setAlignment(Element.ALIGN_CENTER);
					document.add(TitleSmart);
					document.add(new Paragraph(" "));
					Paragraph DadosSmart = new Paragraph("MARCA:  " + rs.getString(4) + "     MODELO: "
							+ rs.getString(5) + "     IMEI: " + rs.getString(6));
					document.add(DadosSmart);
					Paragraph DefeitoSmart = new Paragraph("DEFEITO: " + rs.getString(7));
					document.add(DefeitoSmart);
					Image image2 = Image.getInstance(Servico.class.getResource("/img/smart-impressao.png"));
					image2.scaleToFit(300, 150);
					image2.setAbsolutePosition(15, 300);
					document.add(image2);
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(
							"_____________________________________________________________________________"));
					document.add(new Paragraph(" "));
					Paragraph TitleDiag = new Paragraph("DIAGNOSTICO");
					TitleDiag.setAlignment(Element.ALIGN_CENTER);
					document.add(TitleDiag);
					document.add(new Paragraph(" "));
					Paragraph TecSmart = new Paragraph("TÉCNICO :  " + rs.getString(21));
					document.add(TecSmart);
					document.add(new Paragraph(" "));
					Paragraph DiagSmart = new Paragraph("DIAGNOSTICO :  " + rs.getString(22));
					document.add(DiagSmart);
					document.add(new Paragraph(
							"_____________________________________________________________________________"));
					document.add(new Paragraph(" "));
					if (rs.getString(23) == null) {
						Paragraph DataSaida = new Paragraph("DATA DE SAIDA :  " + " ");
						document.add(DataSaida);
					} else {
						Paragraph DataSaida = new Paragraph("DATA DE SAIDA :  " + rs.getString(23));
						document.add(DataSaida);
					}
					if (rs.getString(24) == "0.00") {
						Paragraph ValorSmart = new Paragraph("VALOR :  " + " ");
						document.add(ValorSmart);
					} else {
						Paragraph ValorSmart = new Paragraph("VALOR :  R$ " + rs.getString(24));
						document.add(ValorSmart);
					}
					document.add(new Paragraph(" "));
					document.add(new Paragraph("ASSINATURA DO CLIENTE : __________________________________________"));
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("os.pdf"));
			;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void recuperarOS() {
		String readOS = "select max(os) from servicos;";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
			} else {
				JOptionPane.showMessageDialog(null, "OS inexistente");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limparCampos() {
		txtOS.setText(null);
		txtIDcli.setText(null);
		txtIDtec.setText(null);
		txtDefeito.setText(null);
		txtDiagnostico.setText(null);
		cboStatus.setSelectedItem("");
		txtValor.setText("0");
		txtDataEntrada.setDate(null);
		txtDataSaida.setDate(null);
		txtMarca.setText(null);
		txtModelo.setText(null);
		txtImei.setText(null);
		txtNome.setText(null);
		txtNome1.setText(null);
		btnBuscarOS.setEnabled(true);
		btnCreate.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		scrollPane.setVisible(false);
		btnOS.setEnabled(false);
	}
}
