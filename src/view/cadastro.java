package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dao.DAO;
import model.Cliente;


public class cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoCadastroNOME;
	private JTextField campoCadastroCPF;
	private JTextField campoCadastroTELEFONE;
	private JTextField campoCadastroEMAIL;
	private JTextField campoCadastroENDERECO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cadastro frame = new cadastro(null, null);
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
	public cadastro(Cliente clienteSelecionado, principal principal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoCadastroNOME = new JTextField();
		campoCadastroNOME.setBackground(new Color(255, 255, 255));
		campoCadastroNOME.setBounds(10, 74, 726, 35);
		contentPane.add(campoCadastroNOME);
		campoCadastroNOME.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("NOME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(10, 61, 45, 13);
		contentPane.add(lblNewLabel);
		
		campoCadastroCPF = new JTextField();
		campoCadastroCPF.setBounds(10, 140, 363, 35);
		contentPane.add(campoCadastroCPF);
		campoCadastroCPF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(10, 127, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		campoCadastroTELEFONE = new JTextField();
		campoCadastroTELEFONE.setBounds(383, 140, 353, 35);
		contentPane.add(campoCadastroTELEFONE);
		campoCadastroTELEFONE.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("TELEFONE");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(383, 127, 94, 13);
		contentPane.add(lblNewLabel_2);
		
		campoCadastroEMAIL = new JTextField();
		campoCadastroEMAIL.setBounds(10, 205, 726, 35);
		contentPane.add(campoCadastroEMAIL);
		campoCadastroEMAIL.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("E-MAIL");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(10, 193, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		campoCadastroENDERECO = new JTextField();
		campoCadastroENDERECO.setBounds(10, 276, 726, 51);
		contentPane.add(campoCadastroENDERECO);
		campoCadastroENDERECO.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ENDEREÇO");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(10, 262, 94, 13);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton(clienteSelecionado==null?"CADASTRAR":"ALTERAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String id, String nome, String email, String cpf, String telefone, String endereco
				Cliente cliente = (new Cliente(null, campoCadastroEMAIL.getText(), campoCadastroCPF.getText(),
						 campoCadastroNOME.getText(), campoCadastroTELEFONE.getText(), campoCadastroENDERECO.getText()));
						
				if (clienteSelecionado == null) {
					if(!"".equalsIgnoreCase(campoCadastroNOME.getText()) && !"".equalsIgnoreCase(campoCadastroCPF.getText())) {
						dao.cadastarCliente(cliente);
						abrirTelaPrincipal(principal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
					}
					
				} else {
					if(!"".equalsIgnoreCase(campoCadastroNOME.getText()) && !"".equalsIgnoreCase(campoCadastroCPF.getText())) {
						dao.alterarCliente(clienteSelecionado.getId(), cliente);
						abrirTelaPrincipal(principal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
					}
					
				}
			}
		});
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setBounds(383, 353, 238, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXCLUIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(principal);
			}
		});
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(124, 353, 246, 29);
		btnNewButton_1.setVisible(false);
		contentPane.add(btnNewButton_1);
		
		if (clienteSelecionado !=null) {
			preencherCampos(clienteSelecionado);
			btnNewButton_1.setVisible(true);
		}
		
	}
	
	private void preencherCampos(Cliente clienteSelecionado) {
		campoCadastroNOME.setText(clienteSelecionado.getNome());
		campoCadastroCPF.setText(clienteSelecionado.getCpf());
		campoCadastroEMAIL.setText(clienteSelecionado.getEmail());
		campoCadastroTELEFONE.setText(clienteSelecionado.getTelefone());
		campoCadastroENDERECO.setText(clienteSelecionado.getEndereco());
	}
	private void abrirTelaPrincipal(principal principal) {
		principal.dispose();
		dispose();
		principal = new principal();
		principal.setLocationRelativeTo(principal);
		principal.setVisible(true);
		
		
	}
}

