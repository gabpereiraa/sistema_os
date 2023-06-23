package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Scanner;

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
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import util.Validador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

@SuppressWarnings({ "unused", "serial" })
public class Fornecedores extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRazao;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtCidade;
	private JTextField txtFone1;
	private JTextField txtFone2;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnLimpar;
	private JButton btnBuscar;
	private JLabel lblNewLabel_4;
	private JTextField txtEmail;
	private JTextField txtBairro;
	private JTextField txtNumeroEndereco;
	private JTextField txtCep;
	private JLabel lblNewLabel_8;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUf;
	private JTextField txtComplemento;
	@SuppressWarnings("rawtypes")
	private JList listaUsuarios;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_11;
	private JTextField txtIDforn;
	private JTextField txtSite;

	public static void main(String[] args) {
		try {
			Fornecedores dialog = new Fornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Fornecedores() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/fornecedor-icon.png")));
		setTitle("Assistencia de Smartphones - FORNECEDORES");
		setBounds(100, 100, 643, 446);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 629, 409);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(103, 90, 246, 67);
		contentPanel.add(scrollPane);
		listaUsuarios = new JList();
		scrollPane.setViewportView(listaUsuarios);
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClienteLista();
			}
		});
		listaUsuarios.setBorder(null);
		JLabel lblNewLabel_1 = new JLabel("ENDEREÇO");
		lblNewLabel_1.setBounds(10, 167, 74, 14);
		contentPanel.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("FONE 1");
		lblNewLabel_2.setBounds(10, 260, 61, 14);
		contentPanel.add(lblNewLabel_2);
		JLabel lblNewLabel_2_1 = new JLabel("CNPJ");
		lblNewLabel_2_1.setBounds(10, 111, 48, 14);
		contentPanel.add(lblNewLabel_2_1);
		JLabel lblNewLabel_2_2 = new JLabel("CIDADE");
		lblNewLabel_2_2.setBounds(10, 210, 48, 14);
		contentPanel.add(lblNewLabel_2_2);
		JLabel lblNewLabel_2_3 = new JLabel("FONE 2");
		lblNewLabel_2_3.setBounds(288, 260, 61, 14);
		contentPanel.add(lblNewLabel_2_3);
		txtRazao = new JTextField();
		txtRazao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}
		});
		txtRazao.setBounds(102, 68, 247, 20);
		contentPanel.add(txtRazao);
		txtRazao.setColumns(10);
		txtRazao.setDocument(new Validador(60));
		txtCnpj = new JTextField();
		txtCnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789/.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCnpj.setBounds(102, 102, 132, 20);
		contentPanel.add(txtCnpj);
		txtCnpj.setColumns(10);
		txtCnpj.setDocument(new Validador(18));
		txtEndereco = new JTextField();
		txtEndereco.setBounds(102, 157, 325, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);
		txtEndereco.setDocument(new Validador(100));
		txtCidade = new JTextField();
		txtCidade.setBounds(102, 204, 195, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);
		txtCidade.setDocument(new Validador(50));
		txtFone1 = new JTextField();
		txtFone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtFone1.setBounds(102, 254, 150, 20);
		contentPanel.add(txtFone1);
		txtFone1.setColumns(10);
		txtFone1.setDocument(new Validador(11));
		txtFone2 = new JTextField();
		txtFone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtFone2.setColumns(10);
		txtFone2.setBounds(385, 254, 150, 20);
		txtFone2.setDocument(new Validador(11));
		contentPanel.add(txtFone2);
		btnBuscar = new JButton("Buscar Fornecedor");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarFornecedor();
			}
		});
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setToolTipText("Buscar Cliente");
		btnBuscar.setBounds(242, 102, 129, 20);
		contentPanel.add(btnBuscar);
		getRootPane().setDefaultButton(btnBuscar);
		txtEmail = new JTextField();
		txtEmail.setBounds(102, 282, 250, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setDocument(new Validador(100));
		lblNewLabel_4 = new JLabel("EMAIL");
		lblNewLabel_4.setBounds(10, 288, 46, 14);
		contentPanel.add(lblNewLabel_4);
		JLabel lblNewLabel = new JLabel("RAZÃO");
		lblNewLabel.setBounds(10, 76, 45, 13);
		contentPanel.add(lblNewLabel);
		btnLimpar = new JButton("");
		btnLimpar.setBounds(274, 351, 48, 48);
		contentPanel.add(btnLimpar);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/eraser.png")));
		btnDelete = new JButton("");
		btnDelete.setBounds(216, 351, 48, 48);
		contentPanel.add(btnDelete);
		btnDelete.setContentAreaFilled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFornecedor();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Excluir Fornecedor");
		btnDelete.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/trash.png")));
		btnUpdate = new JButton("");
		btnUpdate.setBounds(158, 351, 48, 48);
		contentPanel.add(btnUpdate);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("Atualizar Fornecedor");
		btnUpdate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update-motos.png")));
		btnCreate = new JButton("");
		btnCreate.setBounds(103, 351, 48, 48);
		contentPanel.add(btnCreate);
		btnCreate.setContentAreaFilled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFornecedor();
			}
		});
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setToolTipText("Adicionar Fornecedor");
		btnCreate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/add-people.png")));
		txtBairro = new JTextField();
		txtBairro.setBounds(102, 181, 195, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);
		txtBairro.setDocument(new Validador(50));
		JLabel lblNewLabel_6 = new JLabel("BAIRRO");
		lblNewLabel_6.setBounds(10, 187, 46, 14);
		contentPanel.add(lblNewLabel_6);
		txtNumeroEndereco = new JTextField();
		txtNumeroEndereco.setBounds(474, 157, 61, 20);
		contentPanel.add(txtNumeroEndereco);
		txtNumeroEndereco.setColumns(10);
		txtNumeroEndereco.setDocument(new Validador(5));
		JLabel lblNewLabel_7 = new JLabel("N°");
		lblNewLabel_7.setBounds(448, 160, 27, 14);
		contentPanel.add(lblNewLabel_7);
		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCep.setBounds(102, 132, 132, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);
		txtCep.setDocument(new Validador(9));
		lblNewLabel_8 = new JLabel("CEP");
		lblNewLabel_8.setBounds(10, 135, 46, 14);
		contentPanel.add(lblNewLabel_8);
		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(334, 203, 61, 22);
		contentPanel.add(cboUf);
		JLabel lblNewLabel_9 = new JLabel("UF");
		lblNewLabel_9.setBounds(307, 207, 27, 14);
		contentPanel.add(lblNewLabel_9);
		JButton btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscarCep.setBounds(242, 132, 129, 20);
		contentPanel.add(btnBuscarCep);
		txtComplemento = new JTextField();
		txtComplemento.setToolTipText("Complemento");
		txtComplemento.setBounds(102, 230, 269, 20);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);
		txtComplemento.setDocument(new Validador(50));
		JLabel lblNewLabel_10 = new JLabel("COMPLEMENTO");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(10, 236, 85, 14);
		contentPanel.add(lblNewLabel_10);
		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/search-moto.png")));
		lblNewLabel_11.setBounds(359, 47, 48, 48);
		contentPanel.add(lblNewLabel_11);
		txtIDforn = new JTextField();
		txtIDforn.setEditable(false);
		txtIDforn
				.setBorder(new TitledBorder(null, "ID Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtIDforn.setBounds(103, 10, 131, 48);
		contentPanel.add(txtIDforn);
		txtIDforn.setColumns(10);
		JLabel lblNewLabel_3 = new JLabel("SITE");
		lblNewLabel_3.setBounds(10, 312, 45, 13);
		contentPanel.add(lblNewLabel_3);
		txtSite = new JTextField();
		txtSite.setBounds(103, 309, 324, 19);
		contentPanel.add(txtSite);
		txtSite.setColumns(10);
		txtSite.setDocument(new Validador(100));
	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String cep = txtCep.getText();
		String resultado = null;
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("0")) {
						JOptionPane.showMessageDialog(null, "CEP inválido");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}

	private void adicionarFornecedor() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome do Fornecedor");
			txtRazao.requestFocus();
		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do Fornecedor");
			txtCnpj.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do Endereço do Fornecedor");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha Endereço do Fornecedor");
			txtEndereco.requestFocus();
		} else if (txtNumeroEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero do Endereço do Fornecedor");
			txtNumeroEndereco.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro do Endereço do Fornecedor");
			txtBairro.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o UF Endereço do Fornecedor");
			cboUf.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade do Fornecedor");
			txtCidade.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o 1° Telefone do Fornecedor");
			txtFone1.requestFocus();
		} else if (txtFone2.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o 2° Telefone do Fornecedor");
			txtFone2.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email do Fornecedor");
			txtEmail.requestFocus();
		} else {
			String create = "insert into fornecedores(razao,cnpj,endereco,numeroendereco,bairro,cidade,cep,uf,complemento,telefone1,telefone2,email,site) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtCnpj.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumeroEndereco.getText());
				pst.setString(5, txtBairro.getText());
				pst.setString(6, txtCidade.getText());
				pst.setString(7, txtCep.getText());
				pst.setString(8, cboUf.getSelectedItem().toString());
				pst.setString(9, txtComplemento.getText());
				pst.setString(10, txtFone1.getText());
				pst.setString(11, txtFone2.getText());
				pst.setString(12, txtEmail.getText());
				pst.setString(13, txtSite.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Fornecedor Adicionado com Sucesso");
				limparCampos();
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Fornecedor não adicionado. \nEste CNPJ está sendo Utilizado");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void buscarFornecedor() {
		if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CNPJ");
			txtCnpj.requestFocus();
		} else {
			String read = "select * from fornecedores where cnpj = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtCnpj.getText());
				rs = pst.executeQuery();
				if (rs.next()) {
					txtIDforn.setText(rs.getString(1));
					txtRazao.setText(rs.getString(2));
					txtEndereco.setText(rs.getString(4));
					txtNumeroEndereco.setText(rs.getString(5));
					txtBairro.setText(rs.getString(6));
					txtCidade.setText(rs.getString(7));
					txtCep.setText(rs.getString(8));
					cboUf.setSelectedItem(rs.getString(9));
					txtComplemento.setText(rs.getString(10));
					txtFone1.setText(rs.getString(11));
					txtFone2.setText(rs.getString(12));
					txtEmail.setText(rs.getString(13));
					txtSite.setText(rs.getString(14));
					btnCreate.setEnabled(false);
					btnDelete.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnBuscar.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor Inexistente");
					btnCreate.setEnabled(true);
					btnBuscar.setEnabled(false);
					scrollPane.setVisible(false);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaUsuarios.setModel(modelo);
		String readLista = "select * from fornecedores where razao like '" + txtRazao.getText() + "%'"
				+ "order by razao";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery();
			while (rs.next()) {
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtRazao.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarClienteLista() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Razão");
			txtRazao.requestFocus();
		} else {
			int linha = listaUsuarios.getSelectedIndex();
			if (linha >= 0) {
				String read2 = "select * from fornecedores where razao like '" + txtRazao.getText() + "%'"
						+ "order by razao limit " + (linha) + " , 1";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(read2);
					rs = pst.executeQuery();
					if (rs.next()) {
						scrollPane.setVisible(false);
						txtIDforn.setText(rs.getString(1));
						txtRazao.setText(rs.getString(2));
						txtCnpj.setText(rs.getString(3));
						txtEndereco.setText(rs.getString(4));
						txtNumeroEndereco.setText(rs.getString(5));
						txtBairro.setText(rs.getString(6));
						txtCidade.setText(rs.getString(7));
						txtCep.setText(rs.getString(8));
						cboUf.setSelectedItem(rs.getString(9));
						txtComplemento.setText(rs.getString(10));
						txtFone1.setText(rs.getString(11));
						txtFone2.setText(rs.getString(12));
						txtEmail.setText(rs.getString(13));
						txtSite.setText(rs.getString(14));
						btnCreate.setEnabled(false);
						btnDelete.setEnabled(true);
						btnUpdate.setEnabled(true);
						btnBuscar.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Fornecedor Inexistente");
						scrollPane.setVisible(false);
						btnCreate.setEnabled(true);
						btnBuscar.setEnabled(false);
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

	private void editarCliente() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Razão do Fornecedor");
			txtRazao.requestFocus();
		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do Fornecedor");
			txtCnpj.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do Endereço do Fornecedor");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha Endereço do Fornecedor");
			txtEndereco.requestFocus();
		} else if (txtNumeroEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero do Endereço do Fornecedor");
			txtNumeroEndereco.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro do Endereço do Fornecedor");
			txtBairro.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o UF Endereço do Fornecedor");
			cboUf.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade do Endereço");
			txtCidade.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o 1° Telefone do Fornecedor");
			txtFone1.requestFocus();
		} else if (txtFone2.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o 2° Telefone do Fornecedor");
			txtFone2.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email do Fornecedor");
			txtEmail.requestFocus();
		} else {
			String update = "update fornecedores set razao=?,cnpj=?,endereco=?,numeroendereco=?,bairro=?,cidade=?,cep=?,uf=?,complemento=?,telefone1=?,telefone2=?,email=?,site=? where idforn=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtCnpj.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumeroEndereco.getText());
				pst.setString(5, txtBairro.getText());
				pst.setString(6, txtCidade.getText());
				pst.setString(7, txtCep.getText());
				pst.setString(8, cboUf.getSelectedItem().toString());
				pst.setString(9, txtComplemento.getText());
				pst.setString(10, txtFone1.getText());
				pst.setString(11, txtFone2.getText());
				pst.setString(12, txtEmail.getText());
				pst.setString(13, txtSite.getText());
				pst.setString(14, txtIDforn.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do Fornecedor alterado com Sucesso");
				con.close();
				limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Fornecedor não atualizado. \nEste CNPJ está sendo Utilizado.");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void excluirFornecedor() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse Fornecedor?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedores where idforn=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtIDforn.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Fornecedor excluído");
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null,
						"Fornecedor não Deletado. \nEste Fornecedor possui uma Itens em Estoque");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void limparCampos() {
		txtIDforn.setText(null);
		txtRazao.setText(null);
		txtCnpj.setText(null);
		txtEndereco.setText(null);
		txtNumeroEndereco.setText(null);
		txtBairro.setText(null);
		txtCep.setText(null);
		cboUf.setSelectedItem(null);
		txtCidade.setText(null);
		txtFone1.setText(null);
		txtFone2.setText(null);
		txtEmail.setText(null);
		txtComplemento.setText(null);
		txtSite.setText(null);
		btnBuscar.setEnabled(true);
		scrollPane.setVisible(false);
		btnCreate.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
}
