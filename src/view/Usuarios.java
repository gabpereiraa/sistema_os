package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import util.Validador;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Usuarios extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JTextField txtID;
	private JButton btnBuscar;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	@SuppressWarnings("rawtypes")
	private JComboBox cboPerfil;
	private JCheckBox chkSenha;
	@SuppressWarnings("rawtypes")
	private JList listaUsuarios;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Usuarios() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		setModal(true);
		setTitle("Assistencia de Smartphones - Usuários");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/smartphone-icon2.png")));
		setBounds(100, 100, 505, 323);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(81, 104, 292, 75);
		contentPanel.add(scrollPane);
		listaUsuarios = new JList();
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarContatoLista();
			}
		});
		scrollPane.setViewportView(listaUsuarios);
		JLabel lblNewLabel = new JLabel("NOME");
		lblNewLabel.setBounds(10, 92, 48, 14);
		contentPanel.add(lblNewLabel);
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(10, 59, 48, 14);
		contentPanel.add(lblLogin);
		txtNome = new JTextField();
		txtNome.setBorder(null);
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuarios();
			}
		});
		txtNome.setBounds(81, 90, 292, 17);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(30));
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setBounds(10, 134, 48, 14);
		contentPanel.add(lblSenha);
		txtLogin = new JTextField();
		txtLogin.setBounds(81, 57, 127, 17);
		contentPanel.add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setDocument(new Validador(20));
		btnCreate = new JButton("");
		btnCreate.setToolTipText("Adicionar");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setEnabled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnCreate.setContentAreaFilled(false);
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/add-people.png")));
		btnCreate.setBounds(52, 202, 48, 48);
		contentPanel.add(btnCreate);
		JButton btnLimpar = new JButton("");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBorderPainted(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/eraser.png")));
		btnLimpar.setBounds(218, 202, 48, 48);
		contentPanel.add(btnLimpar);
		txtSenha = new JPasswordField();
		txtSenha.setBounds(81, 132, 231, 17);
		contentPanel.add(txtSenha);
		txtSenha.setDocument(new Validador(250));
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 27, 46, 14);
		contentPanel.add(lblNewLabel_1);
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(81, 24, 59, 20);
		contentPanel.add(txtID);
		txtID.setColumns(10);
		btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search-moto.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarContato();
			}
		});
		btnBuscar.setToolTipText("Pesquisar Contato");
		btnBuscar.setBounds(218, 38, 48, 48);
		contentPanel.add(btnBuscar);
		getRootPane().setDefaultButton(btnBuscar);
		btnUpdate = new JButton("");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkSenha.isSelected()) {
					editarUsuarioSenha();
				} else {
					editarUsuario();
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update-motos.png")));
		btnUpdate.setBounds(102, 202, 48, 48);
		contentPanel.add(btnUpdate);
		btnDelete = new JButton("");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("excluir usuario");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setIcon(new ImageIcon(Usuarios.class.getResource("/img/trash.png")));
		btnDelete.setBounds(160, 202, 48, 48);
		contentPanel.add(btnDelete);
		JLabel lblNewLabel_2 = new JLabel("PERFIL");
		lblNewLabel_2.setBounds(322, 134, 46, 14);
		contentPanel.add(lblNewLabel_2);
		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(372, 130, 66, 22);
		contentPanel.add(cboPerfil);
		chkSenha = new JCheckBox("Alterar a Senha");
		chkSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSenha.setEditable(true);
				txtSenha.setText(null);
				txtSenha.requestFocus();
				txtSenha.setBackground(Color.yellow);
			}
		});
		chkSenha.setVisible(false);
		chkSenha.setBounds(81, 156, 127, 23);
		contentPanel.add(chkSenha);
	}

	private void adicionarUsuario() {
		String capturaSenha = new String(txtSenha.getPassword());
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Usuário");
			txtNome.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login do Usuário");
			txtLogin.requestFocus();
		} else if (capturaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do Usuário");
			txtLogin.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o Perfil do Usuário");
			cboPerfil.requestFocus();
		} else {
			String create = "insert into usuarios(nome,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuário Adicionado com Sucesso");
				limparCampos();
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Usuário não adicionado. \nEste login está sendo Utilizado");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	private void buscarContato() {
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Login do Usuario");
			txtLogin.requestFocus();
		} else {
			String read = "select * from usuarios where login = ? ";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtSenha.setText(rs.getString(4));
					cboPerfil.setSelectedItem(rs.getString(5));
					btnCreate.setEnabled(false);
					btnDelete.setEnabled(true);
					btnUpdate.setEnabled(true);
					chkSenha.setVisible(true);
					txtSenha.setEditable(false);
					btnBuscar.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Usuário Inexistente");
					btnCreate.setEnabled(true);
					btnBuscar.setEnabled(false);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void listarUsuarios() {
		// System.out.println("busca avançada");
		// criar um objeto -> lista (vetor dinamico) para exibir a lista de usuarios do
		// banco na pesquisa avançada
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaUsuarios.setModel(modelo);
		// CRUD Read
		String readLista = "select * from usuarios where nome like '" + txtNome.getText() + "%'" + " order by nome";
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

	private void buscarContatoLista() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do Usuario");
			txtNome.requestFocus();
		} else {
			int linha = listaUsuarios.getSelectedIndex();
			if (linha >= 0) {
				String read2 = "select * from usuarios where nome like '" + txtNome.getText() + "%'"
						+ "order by nome limit " + (linha) + " , 1";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(read2);
					rs = pst.executeQuery();
					if (rs.next()) {
						scrollPane.setVisible(false);
						txtID.setText(rs.getString(1));
						txtNome.setText(rs.getString(2));
						txtLogin.setText(rs.getString(3));
						txtSenha.setText(rs.getString(4));
						cboPerfil.setSelectedItem(rs.getString(5));
						btnCreate.setEnabled(false);
						btnDelete.setEnabled(true);
						btnUpdate.setEnabled(true);
						chkSenha.setVisible(true);
						txtSenha.setEditable(false);
						btnBuscar.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Usuário Inexistente");
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

	private void editarUsuarioSenha() {
		String capturaSenha = new String(txtSenha.getPassword());
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome");
			txtNome.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
		} else if (capturaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Senha");
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o Perfil");
		} else {
			String update = "update usuarios set nome=?, login=?, senha=md5(?), perfil=? where iduser = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do Usuário alterado com Sucesso");
				con.close();
				limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Usuário não atualizado. \nEste login está sendo Utilizado.");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void editarUsuario() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome");
			txtNome.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o Perfil");
		} else {
			String update = "update usuarios set nome=?, login=?, perfil=? where iduser = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do Usuário alterado com Sucesso");
				con.close();
				limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Usuário não atualizado. \nEste login está sendo Utilizado.");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void excluirUsuario() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse usuário?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where iduser=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usuário excluído");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		scrollPane.setVisible(false);
		cboPerfil.setSelectedItem("");
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		chkSenha.setVisible(false);
		btnBuscar.setEnabled(true);
		txtSenha.setEditable(true);
		txtSenha.setBackground(Color.white);
	}
}
