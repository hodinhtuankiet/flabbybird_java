package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.AncestorEvent;
import java.awt.SystemColor;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField_surname;
	private JTextField textField_fname;
	public JTextField textField_user;
	private JTextField textField_mail;
	public JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Connection con;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 556);
		Border b = BorderFactory.createLineBorder(Color.WHITE, 2);
//		setUndecorated(true);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("bun.jpg");
		setIconImage(icon.getImage());
		setTitle("Dinh Tuan Kiet");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(160, 82, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(b);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("User: ");
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(111, 165, 59, 70);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(SystemColor.window);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(89, 215, 112, 79);
		contentPane.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setForeground(SystemColor.window);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirmPassword.setBounds(42, 281, 159, 70);
		contentPane.add(lblConfirmPassword);

		JLabel lblGmail = new JLabel("Gmail:");
		lblGmail.setForeground(SystemColor.textHighlightText);
		lblGmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGmail.setBounds(88, 353, 66, 49);
		contentPane.add(lblGmail);

		JLabel lblName = new JLabel("Surname: ");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(76, 111, 78, 56);
		contentPane.add(lblName);

		JLabel lblFirtname = new JLabel("FirstName: ");
		lblFirtname.setForeground(SystemColor.window);
		lblFirtname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirtname.setBounds(343, 104, 136, 70);
		contentPane.add(lblFirtname);

		textField_surname = new JTextField();
		textField_surname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_surname.setBounds(191, 123, 142, 33);
		contentPane.add(textField_surname);
		textField_surname.setColumns(10);

		textField_fname = new JTextField();
		textField_fname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_fname.setBounds(448, 123, 136, 33);
		contentPane.add(textField_fname);
		textField_fname.setColumns(10);

		textField_user = new JTextField();
		textField_user.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_user.setBounds(190, 184, 142, 33);
		contentPane.add(textField_user);
		textField_user.setColumns(10);

		textField_mail = new JTextField();
		textField_mail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_mail.setColumns(10);
		textField_mail.setBounds(191, 361, 273, 33);
		contentPane.add(textField_mail);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(190, 238, 142, 33);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField_1.setBounds(188, 300, 247, 33);
		contentPane.add(passwordField_1);

		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dk = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đăng kí không ","Confirm",JOptionPane.YES_NO_OPTION);
				if(dk!=JOptionPane.YES_NO_OPTION) {
					return ;
				}
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					
					String url = "jdbc:sqlserver://localhost:1433;database=FlabbyBird;encrypt=true;trustServerCertificate=true;";
					String user = "sa";
					String password = "123456";
					con = DriverManager.getConnection(url, user, password);
					System.out.println("Get a connection");
					String sql = "insert into Game values(?,?,?,?,?,?)";
					PreparedStatement st = con.prepareStatement(sql);
					String password1 = new String(passwordField.getPassword());
					String password2 = new String(passwordField_1.getPassword());
					st.setString(1, textField_surname.getText());
					st.setString(2, textField_fname.getText());
					st.setString(3, textField_user.getText());
					st.setString(4, password1);
					st.setString(5, password2);
					st.setString(6, textField_mail.getText());
					
					int n = st.executeUpdate();
					if(textField_fname.getText().equals("") && textField_surname.getText().equals("") && password1.equals("") && password2.equals("") && textField_mail.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane, "Không để dữ liệu trống");
					}
					else if(n!=0) {
						JOptionPane.showMessageDialog(rootPane, "Đăng kí thành công ! ");
						new Login().setVisible(true);
						
						dispose();
					}else {
						JOptionPane.showMessageDialog(rootPane, "Đăng kí thất bại ! ");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(162, 465, 124, 43);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(">>Already have an account , Click here !");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(345, 404, 296, 25);
		contentPane.add(lblNewLabel_1);
		
		Border border = BorderFactory.createLineBorder(Color.white, 2);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(34, 100, 640, 340);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setBorder(border);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogIn.setBounds(428, 465, 124, 43);
		contentPane.add(btnLogIn);
		
		JLabel lblNewLabel_3 = new JLabel("Sign in");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_3.setBounds(228, 28, 251, 66);
		contentPane.add(lblNewLabel_3);
	}
}
