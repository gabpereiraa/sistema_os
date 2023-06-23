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

@SuppressWarnings({ "unused", "serial" })
public class Clientes extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtCidade;
	private JTextField txtFone1;
	private JTextField txtFone2;
	private JTextField txtID;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnLimpar;
	private JButton btnBuscar;
	private JLabel lblNewLabel_4;
	private JTextField txtEmail;
	private JTextField txtRg;
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

	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Clientes() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/smartphone-icon2.png")));
		setTitle("Assistencia de Smartphones - Clientes");
		setBounds(100, 100, 643, 446);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 617, 396);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(103, 54, 246, 67);
		contentPanel.add(scrollPane);
		listaUsuarios = new JList();
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClienteLista();
			}
		});
		scrollPane.setViewportView(listaUsuarios);
		listaUsuarios.setBorder(null);
		JLabel lblNewLabel_1 = new JLabel("ENDEREÇO");
		lblNewLabel_1.setBounds(10, 132, 74, 14);
		contentPanel.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("FONE 1");
		lblNewLabel_2.setBounds(10, 225, 61, 14);
		contentPanel.add(lblNewLabel_2);
		JLabel lblNewLabel_2_1 = new JLabel("CPF");
		lblNewLabel_2_1.setBounds(10, 63, 27, 14);
		contentPanel.add(lblNewLabel_2_1);
		JLabel lblNewLabel_2_2 = new JLabel("CIDADE");
		lblNewLabel_2_2.setBounds(10, 175, 48, 14);
		contentPanel.add(lblNewLabel_2_2);
		JLabel lblNewLabel_2_3 = new JLabel("FONE 2");
		lblNewLabel_2_3.setBounds(288, 225, 61, 14);
		contentPanel.add(lblNewLabel_2_3);
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}
		});
		txtNome.setBounds(102, 32, 247, 20);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(30));
		txtCpf = new JTextField();
		txtCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCpf.setBounds(102, 54, 132, 20);
		contentPanel.add(txtCpf);
		txtCpf.setColumns(10);
		txtCpf.setDocument(new Validador(11));
		txtEndereco = new JTextField();
		txtEndereco.setBounds(102, 122, 325, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);
		txtEndereco.setDocument(new Validador(200));
		txtCidade = new JTextField();
		txtCidade.setBounds(102, 169, 195, 20);
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
		txtFone1.setBounds(102, 219, 150, 20);
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
		txtFone2.setBounds(385, 219, 150, 20);
		txtFone2.setDocument(new Validador(11));
		contentPanel.add(txtFone2);
		btnBuscar = new JButton("Buscar Cliente");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCliente();
			}
		});
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setToolTipText("Buscar Cliente");
		btnBuscar.setBounds(244, 54, 103, 20);
		contentPanel.add(btnBuscar);
		getRootPane().setDefaultButton(btnBuscar);
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(102, 8, 42, 20);
		contentPanel.add(txtID);
		txtID.setColumns(10);
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(10, 14, 27, 14);
		contentPanel.add(lblNewLabel_3);
		txtEmail = new JTextField();
		txtEmail.setBounds(102, 247, 250, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setDocument(new Validador(100));
		lblNewLabel_4 = new JLabel("EMAIL");
		lblNewLabel_4.setBounds(10, 253, 46, 14);
		contentPanel.add(lblNewLabel_4);
		JLabel lblNewLabel = new JLabel("NOME");
		lblNewLabel.setBounds(10, 40, 45, 13);
		contentPanel.add(lblNewLabel);
		txtRg = new JTextField();
		txtRg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtRg.setBounds(102, 75, 132, 19);
		contentPanel.add(txtRg);
		txtRg.setColumns(10);
		txtRg.setDocument(new Validador(9));
		JLabel lblNewLabel_5 = new JLabel("RG");
		lblNewLabel_5.setBounds(10, 84, 45, 13);
		contentPanel.add(lblNewLabel_5);
		btnLimpar = new JButton("");
		btnLimpar.setBounds(218, 314, 48, 48);
		contentPanel.add(btnLimpar);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.setIcon(new ImageIcon(Clientes.class.getResource("/img/eraser.png")));
		btnDelete = new JButton("");
		btnDelete.setBounds(160, 314, 48, 48);
		contentPanel.add(btnDelete);
		btnDelete.setContentAreaFilled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Excluir Cliente");
		btnDelete.setIcon(new ImageIcon(Clientes.class.getResource("/img/trash.png")));
		btnUpdate = new JButton("");
		btnUpdate.setBounds(102, 314, 48, 48);
		contentPanel.add(btnUpdate);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("Atualizar Cliente");
		btnUpdate.setIcon(new ImageIcon(Clientes.class.getResource("/img/update-motos.png")));
		btnCreate = new JButton("");
		btnCreate.setBounds(47, 314, 48, 48);
		contentPanel.add(btnCreate);
		btnCreate.setContentAreaFilled(false);
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setToolTipText("Adicionar Cliente");
		btnCreate.setIcon(new ImageIcon(Clientes.class.getResource("/img/add-people.png")));
		txtBairro = new JTextField();
		txtBairro.setBounds(102, 146, 195, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);
		txtBairro.setDocument(new Validador(50));
		JLabel lblNewLabel_6 = new JLabel("BAIRRO");
		lblNewLabel_6.setBounds(10, 152, 46, 14);
		contentPanel.add(lblNewLabel_6);
		txtNumeroEndereco = new JTextField();
		txtNumeroEndereco.setBounds(474, 122, 61, 20);
		contentPanel.add(txtNumeroEndereco);
		txtNumeroEndereco.setColumns(10);
		txtNumeroEndereco.setDocument(new Validador(5));
		JLabel lblNewLabel_7 = new JLabel("N°");
		lblNewLabel_7.setBounds(448, 125, 27, 14);
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
		txtCep.setBounds(102, 97, 132, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);
		txtCep.setDocument(new Validador(8));
		lblNewLabel_8 = new JLabel("CEP");
		lblNewLabel_8.setBounds(10, 100, 46, 14);
		contentPanel.add(lblNewLabel_8);
		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(334, 168, 61, 22);
		contentPanel.add(cboUf);
		JLabel lblNewLabel_9 = new JLabel("UF");
		lblNewLabel_9.setBounds(307, 172, 27, 14);
		contentPanel.add(lblNewLabel_9);
		JButton btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscarCep.setBounds(244, 97, 103, 20);
		contentPanel.add(btnBuscarCep);
		txtComplemento = new JTextField();
		txtComplemento.setToolTipText("Complemento");
		txtComplemento.setBounds(102, 195, 269, 20);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);
		JLabel lblNewLabel_10 = new JLabel("COMPLEMENTO");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(10, 201, 85, 14);
		contentPanel.add(lblNewLabel_10);
		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(Clientes.class.getResource("/img/search-moto.png")));
		lblNewLabel_11.setBounds(365, 20, 48, 48);
		contentPanel.add(lblNewLabel_11);

	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String cep = txtCep.getText();
		String resultado = null;
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader(); // importar do doc org.dom4j
			Document documento = xml.read(url); // importar do doc org.dom4j
			Element root = documento.getRootElement();// importar do doc org.dom4j
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();)// importar do java.util
			{
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
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void adicionarCliente() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome do Cliente");
			txtNome.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CPF do Cliente");
			txtCpf.requestFocus();
		} else if (txtRg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o RG do Cliente");
			txtRg.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do Endereço do Cliente");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha Endereço do Cliente");
			txtEndereco.requestFocus();
		} else if (txtNumeroEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero do Endereço do Cliente");
			txtNumeroEndereco.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro do Endereço do Cliente");
			txtBairro.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o UF Endereço do Cliente");
			cboUf.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade do Endereço");
			txtCidade.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Telefone do Cliente");
			txtFone1.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email do Cliente");
			txtEmail.requestFocus();
		} else {
			String create = "insert into clientes(nome, cpf, rg, endereco, numeroendereco, bairro, cidade, cep, uf, complemento, telefone1, telefone2, email) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtRg.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumeroEndereco.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, txtCep.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtComplemento.getText());
				pst.setString(11, txtFone1.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente Adicionado com Sucesso");
				limparCampos();
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Cliente não adicionado. \nEste CPF ou RG está sendo Utilizado");
				txtCpf.setText(null);
				txtRg.setText(null);
				txtCpf.requestFocus();
				txtRg.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void buscarCliente() {
		if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CPF");
			txtCpf.requestFocus();
		} else {
			String read = "select * from clientes where cpf = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtCpf.getText());
				rs = pst.executeQuery();
				if (rs.next()) {
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtRg.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtNumeroEndereco.setText(rs.getString(6));
					txtBairro.setText(rs.getString(7));
					txtCidade.setText(rs.getString(8));
					txtCep.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));
					txtComplemento.setText(rs.getString(11));
					txtFone1.setText(rs.getString(12));
					txtFone2.setText(rs.getString(13));
					txtEmail.setText(rs.getString(14));
					btnCreate.setEnabled(false);
					btnDelete.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnBuscar.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");
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
		String readLista = "select * from clientes where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery();
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

	private void buscarClienteLista() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome");
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
						txtID.setText(rs.getString(1));
						txtNome.setText(rs.getString(2));
						txtCpf.setText(rs.getString(3));
						txtRg.setText(rs.getString(4));
						txtEndereco.setText(rs.getString(5));
						txtNumeroEndereco.setText(rs.getString(6));
						txtBairro.setText(rs.getString(7));
						txtCidade.setText(rs.getString(8));
						txtCep.setText(rs.getString(9));
						cboUf.setSelectedItem(rs.getString(10));
						txtComplemento.setText(rs.getString(11));
						txtFone1.setText(rs.getString(12));
						txtFone2.setText(rs.getString(13));
						txtEmail.setText(rs.getString(14));
						btnCreate.setEnabled(false);
						btnDelete.setEnabled(true);
						btnUpdate.setEnabled(true);
						btnBuscar.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Cliente Inexistente");
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
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome do Cliente");
			txtNome.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CPF do Cliente");
			txtCpf.requestFocus();
		} else if (txtRg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o RG do Cliente");
			txtRg.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do Endereço do Cliente");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha Endereço do Cliente");
			txtEndereco.requestFocus();
		} else if (txtNumeroEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero do Endereço do Cliente");
			txtNumeroEndereco.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Bairro do Endereço do Cliente");
			txtBairro.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o UF Endereço do Cliente");
			cboUf.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade do Endereço");
			txtCidade.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Telefone do Cliente");
			txtFone1.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Email do Cliente");
			txtEmail.requestFocus();
		} else {
			String update = "update clientes set nome=?, cpf=?, rg=?, endereco=?, numeroendereco=?, bairro=?, cidade=?, cep=?, uf=?, complemento=?, telefone1=?, telefone2=?, email=? where idcli = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtRg.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumeroEndereco.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, txtCep.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtComplemento.getText());
				pst.setString(11, txtFone1.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());
				pst.setString(14, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do Cliente alterado com Sucesso");
				con.close();
				limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Cliente não atualizado. \nEste CPF ou RG está sendo Utilizado.");
				txtCpf.setText(null);
				txtCpf.requestFocus();
				txtRg.setText(null);
				txtRg.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void excluirCliente() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse Cliente?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Cliente excluído");
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Cliente não Deletado. \nEste Cliente possui uma OS");
				txtCpf.setText(null);
				txtRg.setText(null);
				txtCpf.requestFocus();
				txtRg.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtCpf.setText(null);
		txtRg.setText(null);
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
		btnBuscar.setEnabled(true);
		scrollPane.setVisible(false);
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
}
