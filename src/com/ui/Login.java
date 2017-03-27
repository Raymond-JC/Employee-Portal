package com.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



public class Login extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID  =  1L;

	JFrame frmLogin;
	
	//a separate class for processing database connection and authentication
	private Database1 db;
	 JLabel l_name,l_pass;
	public JTextField t_name;
	 JPasswordField t_pass;     //A special JTextField but hides input text
	 JButton submitButton;
	 public Container c;
	 
	//a inner class to handling ActionEvents
	    handler handle;
	    private JButton btnAdminLogin;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window  =  new Login();
					window.frmLogin.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		super("Login form");
		getContentPane().setBackground(SystemColor.activeCaption);
		setBackground(Color.GRAY);
		setForeground(SystemColor.inactiveCaption);
		setFont(new Font("Aparajita", Font.PLAIN, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/res/ico_login_title.jpg")));

	        c = getContentPane();
	 
	        //extra classes
	        db = new Database1();
	            handle  = new handler();
	        GridBagLayout gridBagLayout = new GridBagLayout();
	        gridBagLayout.columnWidths = new int[]{88, 166, 100, 77, 125, 0};
	        gridBagLayout.rowHeights = new int[]{33, 144, 30, 28, 28, 25, 23, 0};
	        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        getContentPane().setLayout(gridBagLayout);
	        
	        JLabel lblNewLabel  =  new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/res/ico_login.png")));
	        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
	        gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
	        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
	        gbc_lblNewLabel.gridwidth = 4;
	        gbc_lblNewLabel.gridx = 1;
	        gbc_lblNewLabel.gridy = 1;
	        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	        
	                       //swing components
	               l_name  =  new JLabel("Username");
	               l_name.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
	               
	                      //add to container
	                      GridBagConstraints gbc_l_name = new GridBagConstraints();
	                      gbc_l_name.anchor = GridBagConstraints.EAST;
	                      gbc_l_name.fill = GridBagConstraints.VERTICAL;
	                      gbc_l_name.insets = new Insets(0, 0, 5, 5);
	                      gbc_l_name.gridx = 1;
	                      gbc_l_name.gridy = 3;
	                      c.add(l_name, gbc_l_name);
	        t_name = new JTextField(60);
	        GridBagConstraints gbc_t_name = new GridBagConstraints();
	        gbc_t_name.anchor = GridBagConstraints.NORTH;
	        gbc_t_name.fill = GridBagConstraints.HORIZONTAL;
	        gbc_t_name.insets = new Insets(0, 0, 5, 5);
	        gbc_t_name.gridx = 2;
	        gbc_t_name.gridy = 3;
	        c.add(t_name, gbc_t_name);
	        l_pass  =  new JLabel("Password");
	        l_pass.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
	        GridBagConstraints gbc_l_pass = new GridBagConstraints();
	        gbc_l_pass.anchor = GridBagConstraints.EAST;
	        gbc_l_pass.fill = GridBagConstraints.VERTICAL;
	        gbc_l_pass.insets = new Insets(0, 0, 5, 5);
	        gbc_l_pass.gridx = 1;
	        gbc_l_pass.gridy = 4;
	        c.add(l_pass, gbc_l_pass);
	        
	        btnAdminLogin = new JButton("Admin Login");
	        btnAdminLogin.setFont(new Font("Tunga", Font.PLAIN, 16));
	        btnAdminLogin.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try
	        		{
	        		Adm_Login window = new Adm_Login();
	        		window.frmAdminLogin.setVisible(true);
	        		}
	        		catch (Exception ex)
	        		{
	        			System.out.println(ex);
	        		}
	        		setVisible(false);
	        	}
	        });
	        t_pass = new JPasswordField(60);
	        GridBagConstraints gbc_t_pass = new GridBagConstraints();
	        gbc_t_pass.anchor = GridBagConstraints.NORTH;
	        gbc_t_pass.fill = GridBagConstraints.HORIZONTAL;
	        gbc_t_pass.insets = new Insets(0, 0, 5, 5);
	        gbc_t_pass.gridx = 2;
	        gbc_t_pass.gridy = 4;
	        c.add(t_pass, gbc_t_pass);
	        submitButton = new JButton("Login");
	        submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
	        
	 
	        //adding ActionListener to the submitButton
	        submitButton.addActionListener(handle);
	        GridBagConstraints gbc_submitButton = new GridBagConstraints();
	        gbc_submitButton.anchor = GridBagConstraints.EAST;
	        gbc_submitButton.fill = GridBagConstraints.VERTICAL;
	        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
	        gbc_submitButton.gridwidth = 2;
	        gbc_submitButton.gridx = 1;
	        gbc_submitButton.gridy = 5;
	        c.add(submitButton, gbc_submitButton);
	        getRootPane().setDefaultButton(submitButton);
	        GridBagConstraints gbc_btnAdminLogin = new GridBagConstraints();
	        gbc_btnAdminLogin.fill = GridBagConstraints.BOTH;
	        gbc_btnAdminLogin.gridx = 4;
	        gbc_btnAdminLogin.gridy = 6;
	        getContentPane().add(btnAdminLogin, gbc_btnAdminLogin);
	        //visual
	        setVisible(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        setBounds(screenSize.width / 4, screenSize.height / 4, 600, 410);
	        // 	int inset = 50;
			//	frmTest.setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        initialize();
	}

	class handler implements ActionListener
    {
        //must implement method
        //This is triggered whenever the user clicks the login button
        public void actionPerformed(ActionEvent ae)
        {
            //checks if the button clicked
            if(ae.getSource() == submitButton)
            {
                char[] temp_pwd = t_pass.getPassword();
                String pwd = null;
                String user = null;
                user = t_name.getText();
                pwd = String.copyValueOf(temp_pwd);
                System.out.println("Username,Pwd:"+t_name.getText()+","+pwd);
 
                //The entered UserName and password are sent via "checkLogin()" which return boolean
                if(db.checkLogin(t_name.getText(), pwd))
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "You have logged in successfully","Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                    MainWindow window1;
					try {
						window1 = new MainWindow(user);
	                    window1.frmEmployeePortal.setVisible(true);
	                    setVisible(false);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                    //can include the following if need be to remove all information stored in memory from this form
                    //frmLogin.dispose();
                }
                else
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "Invalid login username and password. Login failed!","Failed!!",
                                        JOptionPane.ERROR_MESSAGE);
                    t_pass.setText("");
                }
            }//if
        }//method
 
    }//inner class
	

	private void initialize() {
		// since we declare the components in the constructor 
		// we initialize a JFrame and then leave it blank to overcome nullPointerException for the initialize()
		frmLogin  =  new JFrame();
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}//class
