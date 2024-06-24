package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import model.Cliente;
import model.ModeloTabela;
import dao.DAO;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Cliente> clientes;
	private principal principal;
	private JTextField barraPesquisaUser;
	private TableRowSorter<ModeloTabela> rowSorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setLocationRelativeTo(frame);
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
	public principal() {
		this.principal = this;
		clientes = new ArrayList<>();
		DAO dao = new DAO();
		try {
			clientes = dao.listarClientes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1278, 767);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrarPrincipal = new JButton("Cadastrar");
		btnCadastrarPrincipal.setBackground(new Color(255, 255, 255));
		btnCadastrarPrincipal.setForeground(Color.BLACK);
		btnCadastrarPrincipal.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCadastrarPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastro Cadastro = new cadastro(null, principal);
				Cadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				Cadastro.setVisible(true);
				
			}
		});
		btnCadastrarPrincipal.setBounds(43, 27, 136, 36);
		contentPane.add(btnCadastrarPrincipal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 138, 1186, 437);
		contentPane.add(scrollPane);
		ModeloTabela modeloTabela = new ModeloTabela(clientes);
		
		barraPesquisaUser = new JTextField();
		barraPesquisaUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}
		});
		barraPesquisaUser.setBounds(185, 27, 640, 36);
		contentPane.add(barraPesquisaUser);
		barraPesquisaUser.setColumns(10);
		
		
		table = new JTable();
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					try {
						Cliente clienteSelecionado = dao.consultarCliente(modeloTabela.getValueAt(table.getSelectedRow(),0).toString());
						cadastro Cadastro = new cadastro(clienteSelecionado, principal);
						Cadastro.setLocationRelativeTo(Cadastro);
						Cadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						Cadastro.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		rowSorter = new TableRowSorter<>(modeloTabela);
		table.setRowSorter(rowSorter);
		scrollPane.setViewportView(table);	
		
		
	}
	
	private void filtrar() {
		String busca = barraPesquisaUser.getText().trim();
		
		if (busca.length()==0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ busca));
		}
		
	}
}