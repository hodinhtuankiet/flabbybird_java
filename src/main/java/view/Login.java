package view;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import view.SignUp;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class Login extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;
	private JPasswordField passwordField;
	SignUp sign;
	private Connection con;
	ResultSet rs;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
public Login(JTextField textField) throws HeadlessException {
		super();
		this.textField = textField;
	}

	public Login() {
		init();
//				
	}
	
	
	@SuppressWarnings("unchecked")
	public void init() {
//		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 492);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		setTitle("Dinh Tuan Kiet");
		contentPane.setBackground(new Color(128, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		ImageIcon icon = new ImageIcon("login.jpg");
//		setIconImage(icon.getImage());
		ImageIcon icon = new ImageIcon("bun.jpg");
		setIconImage(icon.getImage());
		Border b = BorderFactory.createLineBorder(Color.BLACK, 1);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(479, 124, 108, 55);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(638, 135, 221, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(479, 184, 95, 55);
		contentPane.add(lblNewLabel_2);

		JCheckBox chckbxNewCheckBox = new JCheckBox(" Remember Password");
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(new Color(128, 0, 64));
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(633, 248, 202, 21);
		contentPane.add(chckbxNewCheckBox);

		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(128, 0, 64));
		btnNewButton.addActionListener(new ActionListener() {

			// get data from sign up
			public void actionPerformed(ActionEvent e) {
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						
						String url = "jdbc:sqlserver://localhost:1433;database=FlabbyBird;encrypt=true;trustServerCertificate=true;";
						String user = "sa";
						String password = "123456";
						con = DriverManager.getConnection(url, user, password);
//						System.out.println("Get a connection");
						String sql = "select * from Game where User =? or Password =?";
						PreparedStatement st = con.prepareCall(sql);
						String password3 = new String(passwordField.getPassword());
						st.setString(1, textField.getText());
						st.setString(2, password3);
						
						rs  = st.executeQuery();
						if(textField.getText().equals("") || password3.equals("") ) {
							JOptionPane.showMessageDialog(rootPane, "Do not leave data blank");
						}
						else if(rs.next()) {
							JOptionPane.showMessageDialog(rootPane, "Hi ! . Welcome to Flabby Bird ");
							Game game = new Game();
							new Window(900, 540, "Flappy Bird", game);
							dispose();
						}else {
							JOptionPane.showMessageDialog(rootPane, "Đăng nhập thất bại ! ");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					// add user 
			}
		});
		Border bo = BorderFactory.createLineBorder(Color.white, 1); 
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(638, 299, 221, 41);
		contentPane.add(btnNewButton);
		btnNewButton.setBorder(bo);

		JButton btnCancle = new JButton("Cancle");
		btnCancle.setForeground(Color.WHITE);
		btnCancle.setBackground(new Color(128, 0, 64));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnCancle.setBorder(bo);
		btnCancle.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancle.setBounds(473, 404, 114, 41);
		contentPane.add(btnCancle);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(638, 195, 221, 35);
		contentPane.add(passwordField);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(128, 0, 64));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBorder(bo);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(840, 404, 108, 41);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("don't have account >> Click here !");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new SignUp().setVisible(true);
				dispose();
			}
		});
		Border b1 = BorderFactory.createLineBorder(Color.RED,2);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(745, 275, 207, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Log in");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_5.setBounds(659, 78, 164, 35);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("WELCOME TO LOGIN FORM");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_3.setBounds(53, 320, 353, 68);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("DINH TUAN KIET");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(137, 385, 156, 24);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\qwerl\\Downloads\\windows_log_off.png"));
		lblNewLabel_6.setBounds(67, 31, 288, 259);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\qwerl\\Downloads\\gradient-brown-red-linear-1920x1080-c2-800000-ffa07a-a-120-f-14.png"));
		lblNewLabel_4.setBounds(0, 0, 445, 455);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\qwerl\\Downloads\\Close-2-icon.png"));
		lblNewLabel_7.setBounds(928, 10, 24, 41);
		contentPane.add(lblNewLabel_7);
	}
	public String getTen() {
		return this.textField.getText();
	}
}
