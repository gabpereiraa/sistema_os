package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import model.DAO;
import util.Validador;

import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

@SuppressWarnings({ "unused", "serial" })
public class Estoques extends JDialog {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JTextField txtID;
	private JTextField txtCodBarras;
	private JTextField txtIDforn;
	private JTextField txtRazao;
	private JTextField txtQuantEstoque;
	private JTextField txtQuantEstoqueMin;
	private JTextField txtValor;
	private JTextField txtDescricao;
	private JDateChooser txtDataValidade;
	private JTextField txtProduto;
	private JPanel panel_1;
	@SuppressWarnings("rawtypes")
	private JList listaUsuarios;
	private JButton btnBuscarItem;
	private JButton btnCreate;
	private JButton btnDelete;
	private JButton btnLimpar;
	private JTextField txtLocal;
	private JButton btnUpdate;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	@SuppressWarnings("rawtypes")
	private JList listaUsuarios1;
	private JDateChooser txtDataEntrada;
	private JPanel panel_2;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUnidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoques dialog = new Estoques();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Estoques() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
				scrollPane1.setVisible(false);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Estoques.class.getResource("/img/estoque-icon.png")));
		setTitle("Assistencia de Smartphones - ESTOQUE");
		setBounds(100, 100, 719, 450);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		scrollPane1 = new JScrollPane();
		scrollPane1.setVisible(false);
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "UNIDADE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(556, 125, 122, 42);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		cboUnidade = new JComboBox();
		cboUnidade.setModel(new DefaultComboBoxModel(new String[] { "", "UN", "PC", "CX", "KG", "g", "M", "CM" }));
		cboUnidade.setBounds(10, 15, 102, 20);
		panel_2.add(cboUnidade);
		scrollPane1.setBounds(37, 167, 239, 55);
		getContentPane().add(scrollPane1);
		listaUsuarios1 = new JList();
		listaUsuarios1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarItemLista();
			}
		});
		scrollPane1.setViewportView(listaUsuarios1);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"FORNECEDOR", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(302, 10, 376, 112);
		getContentPane().add(panel);
		panel.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(10, 55, 352, 57);
		panel.add(scrollPane);
		listaUsuarios = new JList();
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuariosLista();
			}
		});
		scrollPane.setViewportView(listaUsuarios);
		txtIDforn = new JTextField();
		txtIDforn.setEditable(false);
		txtIDforn.setBounds(10, 63, 352, 43);
		panel.add(txtIDforn);
		txtIDforn.setColumns(10);
		txtIDforn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"ID FORNECEDOR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtRazao = new JTextField();
		txtRazao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuarios();
			}
		});
		txtRazao.setColumns(10);
		txtRazao.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"RAZ\u00C3O FORNECEDOR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtRazao.setBounds(10, 15, 352, 43);
		panel.add(txtRazao);
		txtRazao.setDocument(new Validador(60));
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"CODIGO PRODUTO", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtID.setBounds(37, 11, 211, 49);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		txtCodBarras = new JTextField();
		txtCodBarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarItemCodBarra();
				}
			}
		});
		txtCodBarras.setBorder(
				new TitledBorder(null, "CODIGO DE BARRAS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtCodBarras.setBounds(302, 125, 176, 42);
		getContentPane().add(txtCodBarras);
		txtCodBarras.setColumns(10);
		txtCodBarras.setDocument(new Validador(13));
		txtQuantEstoque = new JTextField();
		txtQuantEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtQuantEstoque.setColumns(10);
		txtQuantEstoque.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"QUANT. ESTOQUE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtQuantEstoque.setBounds(302, 172, 176, 42);
		getContentPane().add(txtQuantEstoque);
		txtQuantEstoque.setDocument(new Validador(9));
		txtQuantEstoqueMin = new JTextField();
		txtQuantEstoqueMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtQuantEstoqueMin.setColumns(10);
		txtQuantEstoqueMin.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"ESTOQUE MINIMO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtQuantEstoqueMin.setBounds(488, 172, 190, 42);
		getContentPane().add(txtQuantEstoqueMin);
		txtQuantEstoqueMin.setDocument(new Validador(9));
		txtValor = new JTextField();
		txtValor.setToolTipText("");
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtValor.setColumns(10);
		txtValor.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "VALOR",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtValor.setBounds(488, 293, 176, 42);
		getContentPane().add(txtValor);
		txtValor.setDocument(new Validador(10));
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"DESCRI\u00C7\u00C3O", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtDescricao.setBounds(37, 224, 641, 59);
		getContentPane().add(txtDescricao);
		txtDescricao.setDocument(new Validador(100));
		txtProduto = new JTextField();
		txtProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarItem();
			}
		});
		txtProduto.setColumns(10);
		txtProduto.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "PRODUTO",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtProduto.setBounds(37, 125, 239, 42);
		getContentPane().add(txtProduto);
		txtProduto.setDocument(new Validador(50));
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "VALIDADE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(37, 285, 239, 50);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		txtDataValidade = new JDateChooser();
		txtDataValidade.setBounds(10, 16, 219, 24);
		panel_1.add(txtDataValidade);
		btnUpdate = new JButton("");
		btnUpdate.setEnabled(false);
		btnUpdate.setToolTipText("Atualizar Item");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarItem();
			}
		});
		btnUpdate.setIcon(new ImageIcon(Estoques.class.getResource("/img/update-motos.png")));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBounds(98, 345, 48, 48);
		getContentPane().add(btnUpdate);
		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Excluir Item");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirItem();
			}
		});
		btnDelete.setIcon(new ImageIcon(Estoques.class.getResource("/img/trash.png")));
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(156, 345, 48, 48);
		getContentPane().add(btnDelete);
		btnLimpar = new JButton("");
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setIcon(new ImageIcon(Estoques.class.getResource("/img/eraser.png")));
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.setBounds(272, 345, 48, 48);
		getContentPane().add(btnLimpar);
		btnCreate = new JButton("");
		btnCreate.setToolTipText("Adicionar Item");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarItem();
			}
		});
		btnCreate.setIcon(new ImageIcon(Estoques.class.getResource("/img/adicionar-icon.png")));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setBounds(37, 345, 48, 48);
		getContentPane().add(btnCreate);
		btnBuscarItem = new JButton("");
		btnBuscarItem.setToolTipText("Buscar Item");
		btnBuscarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarItem();
			}
		});
		btnBuscarItem.setBorderPainted(false);
		btnBuscarItem.setContentAreaFilled(false);
		btnBuscarItem.setIcon(new ImageIcon(Estoques.class.getResource("/img/search-moto.png")));
		btnBuscarItem.setBounds(250, 10, 48, 48);
		getContentPane().add(btnBuscarItem);
		txtLocal = new JTextField();
		txtLocal.setColumns(10);
		txtLocal.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"LOCAL ARMAZENADO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtLocal.setBounds(37, 172, 239, 42);
		getContentPane().add(txtLocal);
		txtLocal.setDocument(new Validador(50));
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"DATA ENTRADA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(37, 64, 211, 50);
		getContentPane().add(panel_1_1);
		txtDataEntrada = new JDateChooser();
		txtDataEntrada.setEnabled(false);
		txtDataEntrada.setBounds(10, 16, 191, 24);
		panel_1_1.add(txtDataEntrada);
	}

	@SuppressWarnings("unchecked")
	private void listarItem() {
		DefaultListModel<String> modelo1 = new DefaultListModel<>();
		listaUsuarios1.setModel(modelo1);
		String readLista1 = "select * from estoques where produto like '" + txtProduto.getText() + "%'"
				+ " order by produto";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista1);
			rs = pst.executeQuery(readLista1);
			while (rs.next()) {
				scrollPane1.setVisible(true);
				modelo1.addElement(rs.getString(3));
				if (txtProduto.getText().isEmpty()) {
					scrollPane1.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarItemLista() {
		if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Produto");
			txtProduto.requestFocus();
		} else {
			int linha1 = listaUsuarios1.getSelectedIndex();
			if (linha1 >= 0) {
				String read2 = "select * from estoques where produto like '" + txtProduto.getText() + "%'"
						+ "order by produto limit " + (linha1) + " , 1";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(read2);
					rs = pst.executeQuery();
					if (rs.next()) {
						scrollPane1.setVisible(false);
						txtID.setText(rs.getString(1));
						txtIDforn.setText(rs.getString(2));
						txtProduto.setText(rs.getString(3));
						txtCodBarras.setText(rs.getString(4));
						cboUnidade.setSelectedItem(rs.getString(5));
						txtLocal.setText(rs.getString(6));
						txtQuantEstoque.setText(rs.getString(7));
						txtQuantEstoqueMin.setText(rs.getString(8));
						txtDescricao.setText(rs.getString(9));
						txtValor.setText(rs.getString(11));
						String setarData = rs.getString(12);
						Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
						txtDataEntrada.setDate(dataFormatada);
						String setarDataSaida = rs.getString(10);
						if (setarDataSaida == null) {
							txtDataValidade.setDate(null);
						} else {
							Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataSaida);
							txtDataValidade.setDate(dataFormatada2);
						}
						btnBuscarItem.setEnabled(false);
						btnUpdate.setEnabled(true);
						btnDelete.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Técnico Inexistente");
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

	private void buscarItem() {
		String numOS = JOptionPane.showInputDialog("Digite o Codigo do Item");
		String read = "select * from estoques where idprodut = ? ";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtIDforn.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				txtCodBarras.setText(rs.getString(4));
				cboUnidade.setSelectedItem(rs.getString(5));
				txtLocal.setText(rs.getString(6));
				txtQuantEstoque.setText(rs.getString(7));
				txtQuantEstoqueMin.setText(rs.getString(8));
				txtDescricao.setText(rs.getString(9));
				txtValor.setText(rs.getString(11));
				String setarData = rs.getString(12);
				Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
				txtDataEntrada.setDate(dataFormatada);
				String setarDataSaida = rs.getString(10);
				if (setarDataSaida == null) {
					txtDataValidade.setDate(null);
				} else {
					Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataSaida);
					txtDataValidade.setDate(dataFormatada2);
				}
				btnCreate.setEnabled(false);
				btnDelete.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnBuscarItem.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Item Inexistente");
				btnCreate.setEnabled(true);
				btnBuscarItem.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarItemCodBarra() {
		String read = "select * from estoques where codebarra = ? ";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtCodBarras.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtIDforn.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				cboUnidade.setSelectedItem(rs.getString(5));
				txtLocal.setText(rs.getString(6));
				txtQuantEstoque.setText(rs.getString(7));
				txtQuantEstoqueMin.setText(rs.getString(8));
				txtDescricao.setText(rs.getString(9));
				txtValor.setText(rs.getString(11));
				String setarData = rs.getString(12);
				Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
				txtDataEntrada.setDate(dataFormatada);
				String setarDataSaida = rs.getString(10);
				if (setarDataSaida == null) {
					txtDataValidade.setDate(null);
				} else {
					Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataSaida);
					txtDataValidade.setDate(dataFormatada2);
				}
				btnCreate.setEnabled(false);
				btnDelete.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnBuscarItem.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Codigo de Barras não encontrado");
				btnCreate.setEnabled(true);
				btnBuscarItem.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void listarUsuarios() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaUsuarios.setModel(modelo);
		String readLista = "select * from fornecedores where razao like '" + txtRazao.getText() + "%'"
				+ " order by razao";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery(readLista);
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

	private void buscarUsuariosLista() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome Razão");
			txtRazao.requestFocus(); // setar o cursor na caixa do texto
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
					} else {
						JOptionPane.showMessageDialog(null, "Fornecedor Inexistente");
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

	private void adicionarItem() {
		if (txtIDforn.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o ID do Fornecedor");
			txtIDforn.requestFocus();
		} else if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Produto");
			txtProduto.requestFocus();
		} else if (txtCodBarras.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Codigo de Barras");
			txtCodBarras.requestFocus();
		} else if (cboUnidade.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha a Unidade de Medida");
			cboUnidade.requestFocus();
		} else if (txtQuantEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Quantidade do Estoque");
			txtQuantEstoque.requestFocus();
		} else if (txtQuantEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Quantidade Minima do Estoque");
			txtQuantEstoqueMin.requestFocus();
		} else if (txtDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Descrição do Produto");
			txtDescricao.requestFocus();
		} else if (txtDataValidade.getDate() == null) { // observar a executar
			JOptionPane.showMessageDialog(null, "Preencha a Validade do Produto");
			txtDataValidade.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o valor do produto");
			txtValor.requestFocus();
		} else {
			String create = "insert into estoques(idforn,produto,codebarra,unimedida,localarm,estoque,estoquemin,descricao,validade,valor) values (?,?,?,?,?,?,?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtIDforn.getText());
				pst.setString(2, txtProduto.getText());
				pst.setString(3, txtCodBarras.getText());
				pst.setString(4, cboUnidade.getSelectedItem().toString());
				pst.setString(5, txtLocal.getText());
				pst.setString(6, txtQuantEstoque.getText());
				pst.setString(7, txtQuantEstoqueMin.getText());
				pst.setString(8, txtDescricao.getText());
				pst.setString(10, txtValor.getText());
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				if (txtDataValidade.getDate() == null) {
					pst.setString(9, null);
				} else {
					String dataFormatada = formatador.format(txtDataValidade.getDate());
					pst.setString(9, dataFormatada);
				}
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Item Adicionado com Sucesso");
				limparCampos();
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null,
						"Item não adicionado. \nEste Codigo de Barras está sendo Utilizado");
				txtCodBarras.setText(null);
				txtCodBarras.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	private void editarItem() {
		if (txtIDforn.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o ID do Fornecedor");
			txtIDforn.requestFocus();
		} else if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Produto");
			txtProduto.requestFocus();
		} else if (txtCodBarras.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Codigo de Barras");
			txtCodBarras.requestFocus();
		} else if (cboUnidade.getSelectedItem().equals(" ")) {
			JOptionPane.showMessageDialog(null, "Preencha a Unidade de Medida");
			cboUnidade.requestFocus();
		} else if (txtQuantEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Quantidade do Estoque");
			txtQuantEstoque.requestFocus();
		} else if (txtQuantEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Quantidade Minima do Estoque");
			txtQuantEstoqueMin.requestFocus();
		} else if (txtDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Descrição do Produto");
			txtDescricao.requestFocus();
		} else if (txtDataValidade.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Preencha a Validade do Produto");
			txtDataValidade.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o valor do produto");
			txtValor.requestFocus();
		} else {
			String update = "update estoques set idforn=?,produto=?,codebarra=?,unimedida=?,localarm=?,estoque=?,estoquemin=?,descricao=?,validade=?,valor=? where idprodut=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtIDforn.getText());
				pst.setString(2, txtProduto.getText());
				pst.setString(3, txtCodBarras.getText());
				pst.setString(4, cboUnidade.getSelectedItem().toString());
				pst.setString(5, txtLocal.getText());
				pst.setString(6, txtQuantEstoque.getText());
				pst.setString(7, txtQuantEstoqueMin.getText());
				pst.setString(8, txtDescricao.getText());
				pst.setString(10, txtValor.getText());
				pst.setString(11, txtID.getText());
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				if (txtDataValidade.getDate() == null) {
					pst.setString(9, null);
				} else {
					String dataFormatada = formatador.format(txtDataValidade.getDate());
					pst.setString(9, dataFormatada);
				}
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do Item alterado com Sucesso");
				con.close();
				limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null,
						"\"Item não adicionado. \\nEste Codigo de Barras está sendo Utilizado\"");
				txtCodBarras.setText(null);
				txtCodBarras.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void excluirItem() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse Item?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from estoques where idprodut=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Item excluído");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void limparCampos() {
		txtID.setText(null);
		txtRazao.setText(null);
		txtIDforn.setText(null);
		txtProduto.setText(null);
		txtCodBarras.setText(null);
		cboUnidade.setSelectedItem("");
		txtLocal.setText(null);
		txtQuantEstoque.setText(null);
		txtQuantEstoqueMin.setText(null);
		txtDescricao.setText(null);
		txtDataEntrada.setDate(null);
		txtDataValidade.setDate(null);
		txtValor.setText(null);
		btnCreate.setEnabled(true);
		btnBuscarItem.setEnabled(true);
		scrollPane.setVisible(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
}
