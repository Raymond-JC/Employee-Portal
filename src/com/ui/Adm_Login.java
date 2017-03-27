package com.ui;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.admin.control_panel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

public class Adm_Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frmAdminLogin;
	//a separate class for processing database connection and authentication
	Database2 db;

	 JLabel l_name,l_pass;
	 JTextField t_name;
	 JPasswordField t_pass;     //A special JTextField but hides input text
	 JButton button;
	 Container c;
	 
	//a inner class to handling ActionEvents
	handler handle;
	private JButton btnBack;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adm_Login window = new Adm_Login();
					window.c.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Adm_Login() {
		super("Login Form");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Adm_Login.class.getResource("/res/ico_admin.jpg")));
		setTitle("Admin Login");

	        c = getContentPane();
	 
	        //extra classes
	        db = new Database2();
	            handle  = new handler();
	        GridBagLayout gridBagLayout = new GridBagLayout();
	        gridBagLayout.columnWidths = new int[]{88, 115, 87, 100, 0, 0, 0};
	        gridBagLayout.rowHeights = new int[]{33, 45, 25, 33, 0, 0, 0};
	        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        getContentPane().setLayout(gridBagLayout);
	               
	               JLabel Ico_admin  =  new JLabel("");
	               Ico_admin.setIcon(new ImageIcon(Adm_Login.class.getResource("/res/ico_admin.jpg")));
	               GridBagConstraints gbc_Ico_admin = new GridBagConstraints();
	               gbc_Ico_admin.fill = GridBagConstraints.BOTH;
	               gbc_Ico_admin.insets = new Insets(0, 0, 5, 5);
	               gbc_Ico_admin.gridheight = 3;
	               gbc_Ico_admin.gridx = 1;
	               gbc_Ico_admin.gridy = 1;
	               getContentPane().add(Ico_admin, gbc_Ico_admin);
	        
	                       //swing components
	               l_name  =  new JLabel("Admin Username");
	               
	                      //add to container
	                      GridBagConstraints gbc_l_name = new GridBagConstraints();
	                      gbc_l_name.anchor = GridBagConstraints.SOUTH;
	                      gbc_l_name.fill = GridBagConstraints.HORIZONTAL;
	                      gbc_l_name.insets = new Insets(0, 0, 5, 5);
	                      gbc_l_name.gridx = 2;
	                      gbc_l_name.gridy = 1;
	                      c.add(l_name, gbc_l_name);
	        t_name = new JTextField(60);
	        GridBagConstraints gbc_t_name = new GridBagConstraints();
	        gbc_t_name.anchor = GridBagConstraints.SOUTH;
	        gbc_t_name.fill = GridBagConstraints.HORIZONTAL;
	        gbc_t_name.insets = new Insets(0, 0, 5, 5);
	        gbc_t_name.gridx = 3;
	        gbc_t_name.gridy = 1;
	        c.add(t_name, gbc_t_name);
	        button = new JButton("Login");
	        
	 
	        //adding ActionListener to the button
	        button.addActionListener(handle);
	        l_pass  =  new JLabel("Password");
	        GridBagConstraints gbc_l_pass = new GridBagConstraints();
	        gbc_l_pass.fill = GridBagConstraints.BOTH;
	        gbc_l_pass.insets = new Insets(0, 0, 5, 5);
	        gbc_l_pass.gridx = 2;
	        gbc_l_pass.gridy = 2;
	        c.add(l_pass, gbc_l_pass);
	        t_pass = new JPasswordField(60);
	        GridBagConstraints gbc_t_pass = new GridBagConstraints();
	        gbc_t_pass.anchor = GridBagConstraints.NORTH;
	        gbc_t_pass.fill = GridBagConstraints.HORIZONTAL;
	        gbc_t_pass.insets = new Insets(0, 0, 5, 5);
	        gbc_t_pass.gridx = 3;
	        gbc_t_pass.gridy = 2;
	        c.add(t_pass, gbc_t_pass);
	        GridBagConstraints gbc_button = new GridBagConstraints();
	        gbc_button.insets = new Insets(0, 0, 5, 5);
	        gbc_button.anchor = GridBagConstraints.NORTH;
	        gbc_button.gridwidth = 2;
	        gbc_button.gridx = 2;
	        gbc_button.gridy = 3;
	        c.add(button, gbc_button);
	        
	        //initialize();
	        getRootPane().setDefaultButton(button);
	        
	        btnBack = new JButton("Go back");
	        btnBack.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try
	        		{
	        			Login window  =  new Login();
						window.c.setVisible(true);
	        		}
	        		catch (Exception ex)
	        		{
	        			System.out.println(ex);
	        		}
	        		setVisible(false);
	        	}
	        });
	        GridBagConstraints gbc_btnBack = new GridBagConstraints();
	        gbc_btnBack.insets = new Insets(0, 0, 0, 5);
	        gbc_btnBack.gridx = 4;
	        gbc_btnBack.gridy = 5;
	        getContentPane().add(btnBack, gbc_btnBack);
	        

	        //visual
	        setVisible(true);
	        setBounds(100, 100, 500, 280);
	        
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class handler implements ActionListener
    {
        //must implement method
        //This is triggered whenever the user clicks the login button
        public void actionPerformed(ActionEvent ae)
        {
            //checks if the button clicked
            if(ae.getSource() == button)
            {
                char[] temp_pwd = t_pass.getPassword();
                String pwd = null;
                pwd = String.copyValueOf(temp_pwd);
                System.out.println("Username,Pwd:"+t_name.getText()+","+pwd);
 
                //The entered UserName and password are sent via "checkLogin()" which return boolean
                if(db.checkLogin(t_name.getText(), pwd))
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "You have logged in successfully","Success",
                                        JOptionPane.INFORMATION_MESSAGE);

                    control_panel window1;
					try {
						window1 = new control_panel();
	                    window1.frmControlPanel.setVisible(true);
	                    setVisible(false);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


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


	/**
	 * Initialize the contents of the frame.
	 */


}
