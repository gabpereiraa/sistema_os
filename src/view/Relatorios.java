package view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.xdevapi.PreparableStatement;

import model.DAO;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings({ "serial", "unused" })
public class Relatorios extends JDialog {
	private JButton btnRelClientes;
	DAO dao = new DAO();
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	private JButton btnRelOsPronto;
	private JButton btnAgCli;
	private JButton btnAgTec;
	private JButton btnAgPe;
	private JButton btnRelEstoqueMin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Relatorios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/report.png")));
		setTitle("Assistencia de Smartphones - Relatorios");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		btnRelClientes = new JButton("Clientes");
		btnRelClientes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnRelClientes.setBounds(10, 39, 197, 23);
		getContentPane().add(btnRelClientes);
		btnRelOsPronto = new JButton("OS PRONTA");
		btnRelOsPronto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRelOsPronto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioOsPronto();
			}
		});
		btnRelOsPronto.setBounds(10, 11, 197, 23);
		getContentPane().add(btnRelOsPronto);
		btnAgCli = new JButton("Aguardando Aprovação do Cliente");
		btnAgCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioOsAgAprCliente();
			}
		});
		btnAgCli.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAgCli.setBounds(217, 11, 207, 23);
		getContentPane().add(btnAgCli);
		btnAgTec = new JButton("Aguardando Técnico");
		btnAgTec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioOsAgTecnico();
			}
		});
		btnAgTec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAgTec.setBounds(217, 39, 207, 23);
		getContentPane().add(btnAgTec);
		btnAgPe = new JButton("Aguardando Peças");
		btnAgPe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioOsAgPeca();
			}
		});
		btnAgPe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAgPe.setBounds(217, 68, 207, 23);
		getContentPane().add(btnAgPe);
		btnRelEstoqueMin = new JButton("Estoque Minimo");
		btnRelEstoqueMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioEstoqMin();
			}
		});
		btnRelEstoqueMin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRelEstoqueMin.setBounds(10, 68, 197, 23);
		getContentPane().add(btnRelEstoqueMin);
	}

	private void relatorioClientes() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" "));
			String readClientes = "select nome,cpf,telefone1,email from clientes order by nome";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readClientes);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col2 = new PdfPCell(new Paragraph("CPF"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Telefone"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Email"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
			;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioOsPronto() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("OsProntas.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("OS Concluida:"));
			document.add(new Paragraph(" "));
			String readOSpronta = "select servicos.os,date_format(servicos.dataOS, '%d/%m/%Y') as data_entrada, servicos.usuario, servicos.MarcaOS, servicos.ModeloOS, servicos.defeito, clientes.nome as cliente, clientes.telefone1 as telefone, tecnicos.nome as Técnico, servicos.diagnostico, date_format(dataOSsaida,'%d/%m/%Y') as data_saida, valor from servicos inner join clientes on servicos.idcli = clientes.idcli inner join tecnicos on servicos.idtec = tecnicos.idtec where statusOS = 'PRONTO'";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOSpronta);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(12);
				PdfPCell col1 = new PdfPCell(new Paragraph("N° OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Data Entrada"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Usuario"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Marca Cell"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Modelo Cell"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Defeito"));
				PdfPCell col7 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col8 = new PdfPCell(new Paragraph("Telefone"));
				PdfPCell col9 = new PdfPCell(new Paragraph("Tecnico"));
				PdfPCell col10 = new PdfPCell(new Paragraph("Diagnostico"));
				PdfPCell col11 = new PdfPCell(new Paragraph("Data Saida"));
				PdfPCell col12 = new PdfPCell(new Paragraph("Valor"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);
				tabela.addCell(col7);
				tabela.addCell(col8);
				tabela.addCell(col9);
				tabela.addCell(col10);
				tabela.addCell(col11);
				tabela.addCell(col12);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
					tabela.addCell(rs.getString(7));
					tabela.addCell(rs.getString(8));
					tabela.addCell(rs.getString(9));
					tabela.addCell(rs.getString(10));
					tabela.addCell(rs.getString(11));
					tabela.addCell(rs.getString(12));
				}
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("OsProntas.pdf"));
			;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	private void relatorioOsAgAprCliente() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("OsAguardandoAprovacao.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("OS Aguardando Aprovação do Cliente:"));
			document.add(new Paragraph(" "));
			String readOsAgAprCliente = "select servicos.os, clientes.nome as cliente, clientes.telefone1 as telefone, servicos.MarcaOS, servicos.ModeloOS, servicos.defeito, servicos.diagnostico, servicos.valor from servicos inner join clientes on servicos.idcli = clientes.idcli where statusOS = 'AGUARDANDO APROVAÇÃO DO CLIENTE' ;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOsAgAprCliente);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(8);
				PdfPCell col1 = new PdfPCell(new Paragraph("N° OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Telefone"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Marca Cell"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Modelo Cell"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Defeito"));
				PdfPCell col7 = new PdfPCell(new Paragraph("Diagnostico"));
				PdfPCell col8 = new PdfPCell(new Paragraph("Valor"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);
				tabela.addCell(col7);
				tabela.addCell(col8);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
					tabela.addCell(rs.getString(7));
					tabela.addCell(rs.getString(8));
				}
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("OsAguardandoAprovacao.pdf"));
			;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	private void relatorioOsAgTecnico() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("OsAguardandoTecnico.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("OS Aguardando Técnico:"));
			document.add(new Paragraph(" "));

			String readOsAgTec = "select servicos.os, servicos.MarcaOS, servicos.ModeloOS, servicos.defeito from servicos where statusOS = 'AGUARDANDO TÉCNICO' ;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOsAgTec);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("N° OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Marca Cell"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Modelo Cell"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Defeito"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("OsAguardandoTecnico.pdf"));
			;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	private void relatorioOsAgPeca() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("OsAguardandoMaterial.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("OS Aguardando Peças:"));
			document.add(new Paragraph(" "));
			String readOsAgAprCliente = "select servicos.os, servicos.MarcaOS, servicos.ModeloOS, servicos.defeito, servicos.diagnostico, tecnicos.nome as Técnico  from servicos inner join tecnicos on servicos.idtec = tecnicos.idtec where statusOS = 'AGUARDANDO PEÇAS' ;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOsAgAprCliente);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(6);
				PdfPCell col1 = new PdfPCell(new Paragraph("N° OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Marca Cell"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Modelo Cell"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Defeito"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Diagnostico"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Técnico"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
				}
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("OsAguardandoMaterial.pdf"));
			;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	private void relatorioEstoqMin() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("EstoqueMinimo.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Estoque Minimo:"));
			document.add(new Paragraph(" "));
			String readEstoqMin = "select estoques.idprodut, estoques.produto, estoques.estoque, estoques.estoquemin, fornecedores.razao as Razao, fornecedores.idforn as idforn, fornecedores.telefone1 as telefone1, fornecedores.email as email from estoques inner join fornecedores on estoques.idforn = fornecedores.idforn where estoque < estoquemin;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readEstoqMin);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(8);
				PdfPCell col1 = new PdfPCell(new Paragraph("ID Produto"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Quant. Estoque"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Quant. Estoque Minimo"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Razão"));
				PdfPCell col6 = new PdfPCell(new Paragraph("ID Fornecedor"));
				PdfPCell col7 = new PdfPCell(new Paragraph("Telefone"));
				PdfPCell col8 = new PdfPCell(new Paragraph("Email"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);
				tabela.addCell(col7);
				tabela.addCell(col8);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
					tabela.addCell(rs.getString(7));
					tabela.addCell(rs.getString(8));
				}
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("EstoqueMinimo.pdf"));
			;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}