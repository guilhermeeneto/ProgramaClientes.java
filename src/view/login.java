package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UserLogin;
	private JPasswordField UserPassword;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 10, 416, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		UserLogin = new JTextField();
		UserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		UserLogin.setBounds(150, 87, 130, 19);
		panel.add(UserLogin);
		UserLogin.setColumns(10);
		
		JButton btnEntrarLogin = new JButton("ENTRAR");
		btnEntrarLogin.setBackground(new Color(190, 252, 211));
		btnEntrarLogin.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEntrarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo abaixo validação para login e senha não ficar em branco
				if (UserLogin.getText()!=null &&
						!UserLogin.getText().isEmpty() && 
						UserPassword.getText()!=null &&
						!UserPassword.getText().isEmpty()) {
				} else {
					JOptionPane.showMessageDialog(UserLogin, "Login e/ou senha inválidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEntrarLogin.setBounds(150, 173, 130, 30);
		panel.add(btnEntrarLogin);
		
		UserPassword = new JPasswordField();
		UserPassword.setBounds(150, 134, 130, 19);
		panel.add(UserPassword);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel.setBounds(151, 73, 66, 13);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_1.setBounds(151, 121, 66, 13);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Sistema de Exames - Núcleo Digestivo");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel_2.setBounds(10, 10, 396, 40);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("© 2024 Guilherme Neto");
		lblNewLabel_3.setBounds(10, 230, 151, 13);
		panel.add(lblNewLabel_3);
	}
}
