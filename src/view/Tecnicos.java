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

@SuppressWarnings("unused")
public class Tecnicos extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtID;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	@SuppressWarnings("rawtypes")
	private JList listaUsuarios;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_2;
	private JTextField txtFone;

	public static void main(String[] args) {
		try {
			Tecnicos dialog = new Tecnicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Tecnicos() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		setModal(true);
		setTitle("Assistencia de Smartphones - Tecnicos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tecnicos.class.getResource("/img/smartphone-icon2.png")));
		setBounds(100, 100, 505, 323);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(81, 72, 292, 75);
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
		lblNewLabel.setBounds(10, 57, 48, 14);
		contentPanel.add(lblNewLabel);
		txtNome = new JTextField();
		txtNome.setBorder(null);
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuarios();
			}
		});
		txtNome.setBounds(81, 55, 292, 17);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(30));
		btnCreate = new JButton("");
		btnCreate.setToolTipText("Adicionar");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setBorderPainted(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarTecnico();
			}
		});
		btnCreate.setContentAreaFilled(false);
		btnCreate.setIcon(new ImageIcon(Tecnicos.class.getResource("/img/add-people.png")));
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
		btnLimpar.setIcon(new ImageIcon(Tecnicos.class.getResource("/img/eraser.png")));
		btnLimpar.setBounds(218, 202, 48, 48);
		contentPanel.add(btnLimpar);
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 27, 46, 14);
		contentPanel.add(lblNewLabel_1);
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(81, 24, 59, 20);
		contentPanel.add(txtID);
		txtID.setColumns(10);
		btnUpdate = new JButton("");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarTecnico();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setIcon(new ImageIcon(Tecnicos.class.getResource("/img/update-motos.png")));
		btnUpdate.setBounds(102, 202, 48, 48);
		contentPanel.add(btnUpdate);
		btnDelete = new JButton("");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("excluir usuario");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirTecnico();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setIcon(new ImageIcon(Tecnicos.class.getResource("/img/trash.png")));
		btnDelete.setBounds(160, 202, 48, 48);
		contentPanel.add(btnDelete);
		lblNewLabel_2 = new JLabel("FONE");
		lblNewLabel_2.setBounds(10, 82, 46, 14);
		contentPanel.add(lblNewLabel_2);
		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
		txtFone.setBounds(81, 83, 150, 17);
		contentPanel.add(txtFone);
		txtFone.setColumns(10);
		txtFone.setDocument(new Validador(15));
	}

	private void adicionarTecnico() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Técnico");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Telefone do Técnico");
			txtFone.requestFocus();
		} else {
			String create = "insert into tecnicos(nome,fone) values (?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Técnico Adicionado com Sucesso");
				limparCampos();
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Técnico não adicionado. \nEsses dados estão sendo Utilizados");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void listarUsuarios() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaUsuarios.setModel(modelo);
		String readLista = "select * from tecnicos where nome like '" + txtNome.getText() + "%'" + " order by nome";
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
			JOptionPane.showMessageDialog(null, "Digite o Nome do Tecnico");
			txtNome.requestFocus();
		} else {
			int linha = listaUsuarios.getSelectedIndex();
			if (linha >= 0) {
				String read2 = "select * from tecnicos where nome like '" + txtNome.getText() + "%'"
						+ "order by nome limit " + (linha) + " , 1";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(read2);
					rs = pst.executeQuery();
					if (rs.next()) {
						scrollPane.setVisible(false);
						txtID.setText(rs.getString(1));
						txtNome.setText(rs.getString(2));
						txtFone.setText(rs.getString(3));
						btnCreate.setEnabled(false);
						btnDelete.setEnabled(true);
						btnUpdate.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Técnico Inexistente");
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

	private void editarTecnico() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
		} else {
			String update = "update tecnicos set nome=?, fone=? where idtec = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do Usuário alterado com Sucesso");
				con.close();
				limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Técnico não atualizado. \nEsses Dados estão sendo Utilizados.");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void excluirTecnico() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse Técnico?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tecnicos where idtec=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Técnico excluído");
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Técnico não Deletado. \nEste Técnico possui uma OS");
				txtID.setText(null);
				txtNome.setText(null);
				txtFone.setText(null);
				txtFone.requestFocus();
				txtFone.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		scrollPane.setVisible(false);
		btnCreate.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
}
