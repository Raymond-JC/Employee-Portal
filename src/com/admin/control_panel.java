package com.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JMenuBar;

import com.reports.Contact_Details_Report;
import com.reports.Employee_Personal_Details_Report;
import com.reports.Leave_Report;
import com.reports.Notifications_Report;
import com.reports.Payroll_Details_Report;
import com.reports.Permanent_Address_Report;
import com.reports.Work_Details_Report;
import com.ui.Login;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;

import components.DocumentSizeFilter;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class control_panel {

	public JFrame frmControlPanel;
	private JTextField tF_UserName;
	private JPasswordField pF_Pwd;
	private JPasswordField pF_ConfirmPwd;
	public JFrame frmLeaveReport_by_EmpID;
	
	private JTextField tF_UserNameSearch_UpdateForm;
	
	
	Connection con;
	Connection con2;
	@SuppressWarnings("rawtypes")
	ArrayList<Comparable> EmpList;
   	int recordNumber = -1, flag = 0;
   	private JPasswordField pF_Pwd_UpdateForm;
   	private JPasswordField pF_ConfirmPwd_UpdateForm;
   	private JTextField tF_Basic_Pay;
   	private JTextField tF_PF;
   	private JTextField tF_ESI;
   	private JTextField tF_Salary;
   	private JTextField tF_Emp_ID;
   	private JTextField tF_Current_Job;
   	private JTextField tF_Reason;
   	private JTextField tF_EmpID;
   	private JTextField tF_LeaveFrom;
   	private JTextField tF_LeaveTo;
   	private JTextField tF_LeaveType;
    private JTextField tF_LeaveID;
	private JTextField tF_SearchLeaveID;
	private JTextField tF_Dept;
	private JTextField tF_Bank_Name;
	private JTextField tF_Bank_Address;
	private JTextField tF_Bank_Ac_No;
    private JTextField tF_searchEmpID;
   	private JTextArea tA_Comment;
   	private JTextArea tA_Info;
	
   	private DefaultStyledDocument doc;
    private JLabel remainingLabel = new JLabel();
    
    private String userid = "root"; // User name
	private String pwd = "password1234"; // password required to connect to MySql server
	private String url = "jdbc:mysql://localhost:3306/empdet_db?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
  

    ArrayList<?> personsList;
    private JTextField tF_LeaveStatus;
    String sql = "SELECT * FROM emp_leave_table";

	// Create a prepared statement
	Statement s;
	// Create a result set
	ResultSet rs;
	
   	
	// buttons
	private JButton btnNext;
	private JButton btnPrevious;
	private JButton btnReset;
	
	// panels
	private JPanel panel_update_user;
	private JPanel panel_Leave_Portal;
	private JPanel panel_new_user;
	private JPanel panel_coverpage;
	private JPanel panel_Update_Form;
	
	// date picker
	private JXDatePicker datePicker;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					control_panel window = new control_panel();
					window.frmControlPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public control_panel() throws ClassNotFoundException, SQLException {
		initialize();
		personsList = new ArrayList<Object>();
	} 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException, ClassNotFoundException {
		frmControlPanel = new JFrame();
		frmControlPanel.getContentPane().setBackground(SystemColor.inactiveCaption);
		frmControlPanel.setIconImage(Toolkit.getDefaultToolkit().getImage(control_panel.class.getResource("/res/cloud.jpg")));
		frmControlPanel.setTitle("Control Panel");
		frmControlPanel.setBounds(100, 70, 852, 660);	
		frmControlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		personsList = new ArrayList<Object>();
    	
	      String Leave_ID = "";
	      String Emp_ID = "";
	      String Leave_Type = "";
	      String Reason = "";
	      String Leave_From = "";
	      String Leave_To = "";
	      String Leave_Info = "";
	      String Leave_Approved = "";
	      String Leave_Comments = "";
     // -------- Load leave database --------
   	
  	try {
		Class.forName("com.mysql.jdbc.Driver");	

	} 
	catch(java.lang.ClassNotFoundException e) {
		System.err.print("ClassNotFoundException: ");
		System.err.println(e.getMessage());
	}

	try {
		con2 = DriverManager.getConnection(url, userid, pwd);
	} 
	catch(SQLException ex) {
		System.err.println("SQLException: " + ex.getMessage());
	}
	
	try	{
		s = con2.createStatement();
		rs = s.executeQuery(sql);
	   Leave_ID 		= "";
   	   Emp_ID 			= "";
   	   Leave_Type 		= "";
   	   Reason		 	= "";
   	   Leave_From 		= "";
   	   Leave_To 		= "";
   	   Leave_Info 		= "";
   	   Leave_Approved 	= "";
   	   Leave_Comments 	= "";
   	   
   	   
   	   while(rs.next())
   	   {
			Leave_ID 		= rs.getString("Leave_ID");
			Emp_ID 			= rs.getString("Emp_ID");
           Leave_Type 		= rs.getString("Leave_Type");
           Reason			= rs.getString("Reason");
           Leave_From 		= rs.getString("Leave_From");
           Leave_To 		= rs.getString("Leave_To");
           Leave_Info 		= rs.getString("Leave_Info");
           Leave_Approved 	= rs.getString("Leave_Approved");
           Leave_Comments 	= rs.getString("Leave_Comments");

			//Add the emp_table object to array list
			EmpList.add(Leave_ID);
			EmpList.add(Emp_ID);
			EmpList.add(Leave_Type);
			EmpList.add(Reason);
			EmpList.add(Leave_From);
			EmpList.add(Leave_To);
			EmpList.add(Leave_Info);
			EmpList.add(Leave_Approved);
			EmpList.add(Leave_Comments);
		}

	}
		catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		catch(Exception ex1) {
			System.err.println("Exception: " + ex1.getMessage());
		}
	
		// <------ contentPane ------>
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{41, 70, 101, 51, 232, 0, 111, 0, 55, 61, 0};
		gridBagLayout.rowHeights = new int[]{23, 23, 0, 535, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmControlPanel.getContentPane().setLayout(gridBagLayout);
		

		
	
		// <--->
		JButton btn_LogOut = new JButton("Log Out");
		btn_LogOut.setToolTipText("Log out of Admin Control");
		btn_LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window1  =  new Login();
                window1.c.setVisible(true); // c is the content pane from the login form
                frmControlPanel.setVisible(false);
			}
		});
		btn_LogOut.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_btn_LogOut = new GridBagConstraints();
		gbc_btn_LogOut.fill = GridBagConstraints.BOTH;
		gbc_btn_LogOut.insets = new Insets(0, 0, 5, 0);
		gbc_btn_LogOut.gridx = 9;
		gbc_btn_LogOut.gridy = 0;
		frmControlPanel.getContentPane().add(btn_LogOut, gbc_btn_LogOut);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Menu.background"));
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.BOTH;
		gbc_menuBar.anchor = GridBagConstraints.NORTH;
		gbc_menuBar.insets = new Insets(0, 0, 5, 5);
		gbc_menuBar.gridx = 2;
		gbc_menuBar.gridy = 1;
		frmControlPanel.getContentPane().add(menuBar, gbc_menuBar);
		
		JMenu mnNewMenu = new JMenu("Main menu →");
		menuBar.add(mnNewMenu);
		
		JMenu menu_1 = new JMenu("Reports");
		mnNewMenu.add(menu_1);
		
		JMenuItem menuItem = new JMenuItem("Employee Details");
		menuItem.addActionListener(new Report_Emp_PersonalDetails());
		menu_1.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Leave Report");
		menuItem_1.addActionListener(new Report_Emp_LeaveDetails());
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Work details");
		menuItem_2.addActionListener(new Report_Emp_WorkDetails());
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Contact Details");
		menuItem_3.addActionListener(new Report_Emp_ContactDetails());
		menu_1.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("Permanent Address Details");
		menuItem_4.addActionListener(new Report_Permanent_Address_Details());
		menu_1.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Notifications");
		menuItem_5.addActionListener(new Report_Emp_NotificationSettings());
		menu_1.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("Payroll Details");
		menuItem_6.addActionListener(new Report_Emp_Payroll_Details());
		menu_1.add(menuItem_6);
		
		JMenuItem divider1 = new JMenuItem("-------");
		mnNewMenu.add(divider1);
		divider1.setEnabled(false);
		
		JMenuItem Logout = new JMenuItem("Log Out");
		Logout.addActionListener(new LogOut());
		Logout.setToolTipText("Log out of Admin Control");
		mnNewMenu.add(Logout);
		
		JMenuItem divider2 = new JMenuItem("-------");
		mnNewMenu.add(divider2);
		divider2.setEnabled(false);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new exitApp());
		exit.setToolTipText("Exit the Application");
		mnNewMenu.add(exit);
		
		JButton btn_UpdateUser = new JButton("Search / Update User Details");
		btn_UpdateUser.setHorizontalAlignment(SwingConstants.LEADING);
		btn_UpdateUser.setIcon(new ImageIcon(control_panel.class.getResource("/res/ico_small_update_user.jpg")));
		btn_UpdateUser.setToolTipText("Search and update user details\r\n");
		btn_UpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_update_user.setVisible(true);
				panel_new_user.setVisible(false);
				panel_coverpage.setVisible(false);
				panel_Leave_Portal.setVisible(false);
				refresh();
			}
		});
		
		JButton btn_NewUser = new JButton("New User");
		btn_NewUser.setToolTipText("Add new user");
		btn_NewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_new_user.setVisible(true);
				panel_update_user.setVisible(false);
				panel_coverpage.setVisible(false);
				panel_Leave_Portal.setVisible(false);
				refresh();
			}
		});
		GridBagConstraints gbc_btn_NewUser = new GridBagConstraints();
		gbc_btn_NewUser.anchor = GridBagConstraints.NORTH;
		gbc_btn_NewUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_NewUser.insets = new Insets(0, 0, 5, 5);
		gbc_btn_NewUser.gridx = 3;
		gbc_btn_NewUser.gridy = 1;
		frmControlPanel.getContentPane().add(btn_NewUser, gbc_btn_NewUser);
		GridBagConstraints gbc_btn_UpdateUser = new GridBagConstraints();
		gbc_btn_UpdateUser.fill = GridBagConstraints.BOTH;
		gbc_btn_UpdateUser.insets = new Insets(0, 0, 5, 5);
		gbc_btn_UpdateUser.gridx = 4;
		gbc_btn_UpdateUser.gridy = 1;
		frmControlPanel.getContentPane().add(btn_UpdateUser, gbc_btn_UpdateUser);
		
		doc = new DefaultStyledDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(300));
        doc.addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) { updateCount();}
            @Override
            public void insertUpdate(DocumentEvent e) { updateCount();}
            @Override
            public void removeUpdate(DocumentEvent e) { updateCount();}
        });

        updateCount();
		System.out.println(recordNumber);
				
				
				// <--------->
				JButton btn_Leave_Portal = new JButton("Leave Portal");
				btn_Leave_Portal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						panel_Leave_Portal.setVisible(true);
						panel_update_user.setVisible(false);
						panel_new_user.setVisible(false);
						panel_coverpage.setVisible(false);
						refresh();
					}
				});
				GridBagConstraints gbc_btn_Leave_Portal = new GridBagConstraints();
				gbc_btn_Leave_Portal.anchor = GridBagConstraints.NORTH;
				gbc_btn_Leave_Portal.fill = GridBagConstraints.HORIZONTAL;
				gbc_btn_Leave_Portal.insets = new Insets(0, 0, 5, 5);
				gbc_btn_Leave_Portal.gridx = 5;
				gbc_btn_Leave_Portal.gridy = 1;
				frmControlPanel.getContentPane().add(btn_Leave_Portal, gbc_btn_Leave_Portal);
		
		
						// -------- Design Components --------
								
				
				JPanel panel = new JPanel();
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 0, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridwidth = 8;
				gbc_panel.gridx = 1;
				gbc_panel.gridy = 3;
				frmControlPanel.getContentPane().add(panel, gbc_panel);
				panel.setLayout(new CardLayout(0, 0));
				
				panel_coverpage = new JPanel();
				panel.add(panel_coverpage, "empty_panel");
				GridBagLayout gbl_panel_coverpage = new GridBagLayout();
				gbl_panel_coverpage.columnWidths = new int[]{0};
				gbl_panel_coverpage.rowHeights = new int[]{0};
				gbl_panel_coverpage.columnWeights = new double[]{Double.MIN_VALUE};
				gbl_panel_coverpage.rowWeights = new double[]{Double.MIN_VALUE};
				panel_coverpage.setLayout(gbl_panel_coverpage);
				
				panel_new_user = new JPanel();
				panel.add(panel_new_user, "Register_User");
				GridBagLayout gbl_panel_new_user = new GridBagLayout();
				gbl_panel_new_user.columnWidths = new int[]{67, 95, 188, 0, 142, 0, 0};
				gbl_panel_new_user.rowHeights = new int[]{38, 19, 32, 25, 25, 25, 37, 23, 0};
				gbl_panel_new_user.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panel_new_user.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel_new_user.setLayout(gbl_panel_new_user);
				
				JLabel lblNewLabel = new JLabel("New User");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				panel_new_user.add(lblNewLabel, gbc_lblNewLabel);
				
				JLabel lblUsername = new JLabel("Username");
				GridBagConstraints gbc_lblUsername = new GridBagConstraints();
				gbc_lblUsername.fill = GridBagConstraints.BOTH;
				gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsername.gridx = 1;
				gbc_lblUsername.gridy = 3;
				panel_new_user.add(lblUsername, gbc_lblUsername);
				
				tF_UserName = new JTextField(60);
				GridBagConstraints gbc_tF_UserName = new GridBagConstraints();
				gbc_tF_UserName.anchor = GridBagConstraints.NORTH;
				gbc_tF_UserName.fill = GridBagConstraints.HORIZONTAL;
				gbc_tF_UserName.insets = new Insets(0, 0, 5, 5);
				gbc_tF_UserName.gridx = 2;
				gbc_tF_UserName.gridy = 3;
				panel_new_user.add(tF_UserName, gbc_tF_UserName);
				
				JLabel label = new JLabel("Password");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.fill = GridBagConstraints.BOTH;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 1;
				gbc_label.gridy = 4;
				panel_new_user.add(label, gbc_label);
				
				pF_Pwd = new JPasswordField(60);
				GridBagConstraints gbc_pF_Pwd = new GridBagConstraints();
				gbc_pF_Pwd.anchor = GridBagConstraints.NORTH;
				gbc_pF_Pwd.fill = GridBagConstraints.HORIZONTAL;
				gbc_pF_Pwd.insets = new Insets(0, 0, 5, 5);
				gbc_pF_Pwd.gridx = 2;
				gbc_pF_Pwd.gridy = 4;
				panel_new_user.add(pF_Pwd, gbc_pF_Pwd);
				
				JLabel lblConfirmPassword = new JLabel("Confirm Password");
				GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
				gbc_lblConfirmPassword.fill = GridBagConstraints.BOTH;
				gbc_lblConfirmPassword.insets = new Insets(0, 0, 5, 5);
				gbc_lblConfirmPassword.gridx = 1;
				gbc_lblConfirmPassword.gridy = 5;
				panel_new_user.add(lblConfirmPassword, gbc_lblConfirmPassword);
				
				JButton btn_SubmitNewUser = new JButton("Submit");
				btn_SubmitNewUser.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						try  //try block
                {
                //declare variables
							String username = "";
							String password;

							//get values using getText() method
							username = tF_UserName.getText().trim();
							password = pF_Pwd.getText().trim();

                // check condition it field equals to blank throw error message
							if (username.equals("")|| password.equals(""))
							{
								JOptionPane.showMessageDialog(null," UserName or Password field is blank","Error",JOptionPane.ERROR_MESSAGE);
							}
							else if(Arrays.equals(pF_Pwd.getPassword(), pF_ConfirmPwd.getPassword())) //else if insert query is run properly
							{
								String IQuery = "INSERT INTO `empdet_db`.`emp_table`(`Emp_ID`,`Emp_Password`,`Time_Stamp`) VALUES('"+username+"', '"+password+"',current_timestamp)";
								System.out.println(IQuery);//print on console
								System.out.println("Connecting to a selected database...");

								// Open a connection
								Class.forName("com.mysql.jdbc.Driver");
								// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
								// here schema is the database name and we use no data truncation so that we can store any size of data
					            con = DriverManager.getConnection(url,userid,pwd);
								System.out.println("Connected database successfully...");
								((Connection)con).createStatement().execute(IQuery);//select the rows
								// define SMessage variable
								String SMessage = "User account for ["+username+"] created.";
	
                       // create dialog ox which is print message
								JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
								//close connection
								((java.sql.Connection)con).close();
								

							}
							else // if both passwords don't match
							{
								JOptionPane.showMessageDialog(null," Passwords don't match","Error",JOptionPane.ERROR_MESSAGE);
							}
                }
						catch (SQLException se) 
						{
							//handle errors for JDBC
							se.printStackTrace();
						}
						catch (Exception a) //catch block
						{
							a.printStackTrace();
						}
					}
				});
				
				pF_ConfirmPwd = new JPasswordField(60);
				GridBagConstraints gbc_pF_ConfirmPwd = new GridBagConstraints();
				gbc_pF_ConfirmPwd.anchor = GridBagConstraints.NORTH;
				gbc_pF_ConfirmPwd.fill = GridBagConstraints.HORIZONTAL;
				gbc_pF_ConfirmPwd.insets = new Insets(0, 0, 5, 5);
				gbc_pF_ConfirmPwd.gridx = 2;
				gbc_pF_ConfirmPwd.gridy = 5;
				panel_new_user.add(pF_ConfirmPwd, gbc_pF_ConfirmPwd);
				GridBagConstraints gbc_btn_SubmitNewUser = new GridBagConstraints();
				gbc_btn_SubmitNewUser.insets = new Insets(0, 0, 0, 5);
				gbc_btn_SubmitNewUser.anchor = GridBagConstraints.NORTH;
				gbc_btn_SubmitNewUser.gridx = 2;
				gbc_btn_SubmitNewUser.gridy = 7;
				panel_new_user.add(btn_SubmitNewUser, gbc_btn_SubmitNewUser);
				
				panel_update_user = new JPanel();
				panel.add(panel_update_user, "Update_User");
				panel.setVisible(true);
				panel_update_user.setLayout(null);
				GridBagLayout gbl_panel_update_user = new GridBagLayout();
				gbl_panel_update_user.columnWidths = new int[]{0, 78, 78, 100, 418, 0, 0};
				gbl_panel_update_user.rowHeights = new int[]{30, 14, 20, 23, 431, 0};
				gbl_panel_update_user.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_panel_update_user.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel_update_user.setLayout(gbl_panel_update_user);
				
				
				JLabel lblUpdateUser = new JLabel("Update User");
				GridBagConstraints gbc_lblUpdateUser = new GridBagConstraints();
				gbc_lblUpdateUser.fill = GridBagConstraints.BOTH;
				gbc_lblUpdateUser.insets = new Insets(0, 0, 5, 5);
				gbc_lblUpdateUser.gridx = 1;
				gbc_lblUpdateUser.gridy = 1;
				panel_update_user.add(lblUpdateUser, gbc_lblUpdateUser);
				
				JLabel label_2 = new JLabel("Username");
				GridBagConstraints gbc_label_2 = new GridBagConstraints();
				gbc_label_2.fill = GridBagConstraints.BOTH;
				gbc_label_2.insets = new Insets(0, 0, 5, 5);
				gbc_label_2.gridx = 1;
				gbc_label_2.gridy = 2;
				panel_update_user.add(label_2, gbc_label_2);
				JButton btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					@SuppressWarnings("rawtypes")
					public void actionPerformed(ActionEvent e) {
						String username = tF_UserNameSearch_UpdateForm.getText();
						
						if(username.equals("")){
					   		JOptionPane.showMessageDialog(null,"Please enter person name to search.");
					   	}
					   	else
					   	{
						   	
					   		try
					   		{
					   			
						      //searchPerson();
					   		 // -------- Retrieve Table data --------
								String Emp_ID = "", Emp_Password = "";
														
								String Current_Job = "", Department = "", Date_Of_Joining = "";
								
								int Basic_Pay = 0, PF = 0, ESI = 0, Salary = 0;
								String Bank_Name = "", Bank_Address = "", Bank_Account_No = "";
								
								System.out.println("Connecting to a selected database...");
								Class.forName("com.mysql.jdbc.Driver");	

								con = DriverManager.getConnection(url,userid,pwd);
								System.out.println("Connected database successfully...");
								//String sql = "SELECT First_Name FROM emp_table WHERE Emp_ID like '%"+username+"%'";
								String sql = "SELECT * FROM emp_table WHERE Emp_ID = '"+username+"'";
								System.out.println("SQL statement executed successfully...");
								System.out.println(sql);
								// Create a prepared statement
								Statement s = con.createStatement();
								ResultSet rs = s.executeQuery(sql);
								// ((Connection)con).createStatement().execute(sql);//select the rows
								EmpList = new ArrayList<Comparable>();
								while(rs.next())
								{

									Emp_ID 			= rs.getString("Emp_ID");
									Emp_Password	= rs.getString("Emp_Password");			
						    	    Current_Job		= rs.getString("Current_Job");
						    	    Department 		= rs.getString("Department");
						    	    Date_Of_Joining = rs.getString("Date_Of_Joining");
						    	    Bank_Name		= rs.getString("Bank_Name");
						    	    Bank_Address	= rs.getString("Bank_Address");
						    	    Bank_Account_No	= rs.getString("Bank_Account_No");

						    	    Basic_Pay	= rs.getInt("Basic_Pay");
						    	    PF			= rs.getInt("PF");
						    	    ESI			= rs.getInt("ESI");
						    	    Salary		= rs.getInt("Salary");
						    	    

									//Add the emp_table object to array list either individually or using constructor of EmpInfo
						    	    EmpList.add(Emp_ID);
						    	    EmpList.add(Emp_Password);
									 EmpList.add(Current_Job);
									 EmpList.add(Department);
									 EmpList.add(Date_Of_Joining);												 
									 EmpList.add(Basic_Pay);
									 EmpList.add(PF);
									 EmpList.add(ESI);
									 EmpList.add(Salary);
									 EmpList.add(Bank_Name);
									 EmpList.add(Bank_Address);
									 EmpList.add(Bank_Account_No);
									 					 
								}
						   		if(EmpList.size() == 0){
						   			JOptionPane.showMessageDialog(null, "No records found for "+username);
						   			panel_Update_Form.setVisible(false);
						   		}
						   		else
						   		{
								tF_Emp_ID.setText(Emp_ID);
								tF_Current_Job.setText(Current_Job);
								tF_Dept.setText(Department);
								datePicker.getEditor().setText(Date_Of_Joining);
								tF_Basic_Pay.setText(""+Basic_Pay);
								tF_PF.setText(""+PF);
								tF_ESI.setText(""+ESI);
								tF_Salary.setText(""+Salary);
								tF_Bank_Name.setText(Bank_Name);
								tF_Bank_Address.setText(Bank_Address);
								tF_Bank_Ac_No.setText(Bank_Account_No);
								
								panel_Update_Form.setVisible(true);
						   		}

					   		}
					   		catch (Exception ex)
					   		{
					   			System.out.println(ex);
					   		}
					   		EmpList.clear();
					   	}
					}
				});
				
				tF_UserNameSearch_UpdateForm = new JTextField(60);
				GridBagConstraints gbc_tF_UserNameSearch_UpdateForm = new GridBagConstraints();
				gbc_tF_UserNameSearch_UpdateForm.anchor = GridBagConstraints.NORTH;
				gbc_tF_UserNameSearch_UpdateForm.fill = GridBagConstraints.HORIZONTAL;
				gbc_tF_UserNameSearch_UpdateForm.insets = new Insets(0, 0, 5, 5);
				gbc_tF_UserNameSearch_UpdateForm.gridx = 2;
				gbc_tF_UserNameSearch_UpdateForm.gridy = 2;
				panel_update_user.add(tF_UserNameSearch_UpdateForm, gbc_tF_UserNameSearch_UpdateForm);
				String username = ""+tF_UserNameSearch_UpdateForm.getText();
				GridBagConstraints gbc_btnSearch = new GridBagConstraints();
				gbc_btnSearch.anchor = GridBagConstraints.NORTH;
				gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
				gbc_btnSearch.gridx = 1;
				gbc_btnSearch.gridy = 3;
				panel_update_user.add(btnSearch, gbc_btnSearch);
				
				
				panel_Update_Form = new JPanel();
				panel_Update_Form.setBorder(UIManager.getBorder("InternalFrame.border"));
				GridBagConstraints gbc_panel_Update_Form = new GridBagConstraints();
				gbc_panel_Update_Form.insets = new Insets(0, 0, 0, 5);
				gbc_panel_Update_Form.fill = GridBagConstraints.BOTH;
				gbc_panel_Update_Form.gridwidth = 4;
				gbc_panel_Update_Form.gridx = 1;
				gbc_panel_Update_Form.gridy = 4;
				panel_update_user.add(panel_Update_Form, gbc_panel_Update_Form);
				panel_Update_Form.setVisible(false);
				GridBagLayout gbl_panel_Update_Form = new GridBagLayout();
				gbl_panel_Update_Form.columnWidths = new int[]{0, 95, 200, 47, 105, 146, 0};
				gbl_panel_Update_Form.rowHeights = new int[]{1, 25, 35, 0, 25, 29, 25, 25, 25, 0, 0, 0, 35, 0};
				gbl_panel_Update_Form.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panel_Update_Form.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel_Update_Form.setLayout(gbl_panel_Update_Form);
				
				
				
				JLabel label_9 = new JLabel("Username");
				GridBagConstraints gbc_label_9 = new GridBagConstraints();
				gbc_label_9.fill = GridBagConstraints.BOTH;
				gbc_label_9.insets = new Insets(0, 0, 5, 5);
				gbc_label_9.gridx = 1;
				gbc_label_9.gridy = 1;
				panel_Update_Form.add(label_9, gbc_label_9);
				
				tF_Emp_ID = new JTextField(60);
				tF_Emp_ID.setEditable(false);
				tF_Emp_ID.setText(username);
				GridBagConstraints gbc_tF_Emp_ID = new GridBagConstraints();
				gbc_tF_Emp_ID.anchor = GridBagConstraints.NORTH;
				gbc_tF_Emp_ID.fill = GridBagConstraints.HORIZONTAL;
				gbc_tF_Emp_ID.insets = new Insets(0, 0, 5, 5);
				gbc_tF_Emp_ID.gridx = 2;
				gbc_tF_Emp_ID.gridy = 1;
				panel_Update_Form.add(tF_Emp_ID, gbc_tF_Emp_ID);
				
				JLabel label_8 = new JLabel("Change Password?");
				GridBagConstraints gbc_label_8 = new GridBagConstraints();
				gbc_label_8.anchor = GridBagConstraints.NORTH;
				gbc_label_8.fill = GridBagConstraints.HORIZONTAL;
				gbc_label_8.insets = new Insets(0, 0, 5, 5);
				gbc_label_8.gridx = 4;
				gbc_label_8.gridy = 1;
				panel_Update_Form.add(label_8, gbc_label_8);
				
				JLabel lblJobTitle = new JLabel("Job Title");
				GridBagConstraints gbc_lblJobTitle = new GridBagConstraints();
				gbc_lblJobTitle.fill = GridBagConstraints.BOTH;
				gbc_lblJobTitle.insets = new Insets(0, 0, 5, 5);
				gbc_lblJobTitle.gridx = 1;
				gbc_lblJobTitle.gridy = 2;
				panel_Update_Form.add(lblJobTitle, gbc_lblJobTitle);
				
					tF_Current_Job = new JTextField(60);
					GridBagConstraints gbc_tF_Current_Job = new GridBagConstraints();
					gbc_tF_Current_Job.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Current_Job.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Current_Job.gridx = 2;
					gbc_tF_Current_Job.gridy = 2;
					panel_Update_Form.add(tF_Current_Job, gbc_tF_Current_Job);
					
					
					
					
					JLabel label_1 = new JLabel("Password");
					GridBagConstraints gbc_label_1 = new GridBagConstraints();
					gbc_label_1.fill = GridBagConstraints.BOTH;
					gbc_label_1.insets = new Insets(0, 0, 5, 5);
					gbc_label_1.gridx = 4;
					gbc_label_1.gridy = 2;
					panel_Update_Form.add(label_1, gbc_label_1);
					
					pF_Pwd_UpdateForm = new JPasswordField(60);
					GridBagConstraints gbc_pF_Pwd_UpdateForm = new GridBagConstraints();
					gbc_pF_Pwd_UpdateForm.anchor = GridBagConstraints.NORTH;
					gbc_pF_Pwd_UpdateForm.fill = GridBagConstraints.HORIZONTAL;
					gbc_pF_Pwd_UpdateForm.insets = new Insets(0, 0, 5, 0);
					gbc_pF_Pwd_UpdateForm.gridx = 5;
					gbc_pF_Pwd_UpdateForm.gridy = 2;
					panel_Update_Form.add(pF_Pwd_UpdateForm, gbc_pF_Pwd_UpdateForm);
					
					JLabel lblDepartment = new JLabel("Department");
					GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
					gbc_lblDepartment.fill = GridBagConstraints.VERTICAL;
					gbc_lblDepartment.anchor = GridBagConstraints.WEST;
					gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
					gbc_lblDepartment.gridx = 1;
					gbc_lblDepartment.gridy = 3;
					panel_Update_Form.add(lblDepartment, gbc_lblDepartment);
					
					tF_Dept = new JTextField(60);
					GridBagConstraints gbc_tF_Dept = new GridBagConstraints();
					gbc_tF_Dept.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Dept.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Dept.gridx = 2;
					gbc_tF_Dept.gridy = 3;
					panel_Update_Form.add(tF_Dept, gbc_tF_Dept);
					
					JLabel lblDateOfJoining = new JLabel("Date of Joining");
					GridBagConstraints gbc_lblDateOfJoining = new GridBagConstraints();
					gbc_lblDateOfJoining.fill = GridBagConstraints.BOTH;
					gbc_lblDateOfJoining.insets = new Insets(0, 0, 5, 5);
					gbc_lblDateOfJoining.gridx = 1;
					gbc_lblDateOfJoining.gridy = 4;
					panel_Update_Form.add(lblDateOfJoining, gbc_lblDateOfJoining);
					
					
					
					// date picker
					datePicker = new JXDatePicker();
					datePicker.getEditor().setToolTipText("Date of Joining the company\r\n");
					GridBagConstraints gbc_datePicker = new GridBagConstraints();
					gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
					gbc_datePicker.insets = new Insets(0, 0, 5, 5);
					gbc_datePicker.gridx = 2;
					gbc_datePicker.gridy = 4;
					panel_Update_Form.add(datePicker, gbc_datePicker);
					
					JLabel label_3 = new JLabel("Confirm Password");
					GridBagConstraints gbc_label_3 = new GridBagConstraints();
					gbc_label_3.anchor = GridBagConstraints.WEST;
					gbc_label_3.fill = GridBagConstraints.VERTICAL;
					gbc_label_3.insets = new Insets(0, 0, 5, 5);
					gbc_label_3.gridx = 4;
					gbc_label_3.gridy = 4;
					panel_Update_Form.add(label_3, gbc_label_3);
					
					pF_ConfirmPwd_UpdateForm = new JPasswordField(60);
					GridBagConstraints gbc_pF_ConfirmPwd_UpdateForm = new GridBagConstraints();
					gbc_pF_ConfirmPwd_UpdateForm.anchor = GridBagConstraints.NORTH;
					gbc_pF_ConfirmPwd_UpdateForm.fill = GridBagConstraints.HORIZONTAL;
					gbc_pF_ConfirmPwd_UpdateForm.insets = new Insets(0, 0, 5, 0);
					gbc_pF_ConfirmPwd_UpdateForm.gridx = 5;
					gbc_pF_ConfirmPwd_UpdateForm.gridy = 4;
					panel_Update_Form.add(pF_ConfirmPwd_UpdateForm, gbc_pF_ConfirmPwd_UpdateForm);
					
					JLabel lblBasic = new JLabel("Basic pay");
					GridBagConstraints gbc_lblBasic = new GridBagConstraints();
					gbc_lblBasic.fill = GridBagConstraints.BOTH;
					gbc_lblBasic.insets = new Insets(0, 0, 5, 5);
					gbc_lblBasic.gridx = 1;
					gbc_lblBasic.gridy = 5;
					panel_Update_Form.add(lblBasic, gbc_lblBasic);
					
					tF_Basic_Pay = new JTextField(60);
					GridBagConstraints gbc_tF_Basic_Pay = new GridBagConstraints();
					gbc_tF_Basic_Pay.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Basic_Pay.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Basic_Pay.gridx = 2;
					gbc_tF_Basic_Pay.gridy = 5;
					panel_Update_Form.add(tF_Basic_Pay, gbc_tF_Basic_Pay);
					
					JButton button_1 = new JButton("Save Password");
					button_1.addActionListener(new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent e) {
							try  //try block
                {
                //declare variables
								String username = "";
								String password;

								//get values using getText() method
								username = tF_Emp_ID.getText().trim();
								password = pF_Pwd_UpdateForm.getText().trim();

                // check condition it field equals to blank throw error message
								if (username.equals("")|| password.equals(""))
								{
									JOptionPane.showMessageDialog(null," UserName or Password field is blank","Error",JOptionPane.ERROR_MESSAGE);
								}
								else if(Arrays.equals(pF_Pwd_UpdateForm.getPassword(), pF_ConfirmPwd_UpdateForm.getPassword())) //else if insert query is run properly
								{
									String IQuery = "UPDATE `empdet_db`.`emp_table` SET `Emp_Password`='"+password+"' WHERE `Emp_ID`='"+username+"' ";
									//String IQuery = "UPDATE 'emp_table' SET 'Password' = '"+password+"' WHERE 'Emp_ID' = '"+username+"' ";
									// ` and ' is different....
									System.out.println(IQuery);//print on console
									System.out.println("Connecting to a selected database...");

									// Open a connection
									Class.forName("com.mysql.jdbc.Driver");
									// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
									// here schema is the database name and we use no data truncation so that we can store any size of data
						            con = DriverManager.getConnection(url,userid,pwd);
									System.out.println("Connected database successfully...");
									((Connection)con).createStatement().execute(IQuery);//select the rows
									// define SMessage variable
									String SMessage = "Record added for "+username;
	
                       // create dialog ox which is print message
									JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
									//close connection
									((java.sql.Connection)con).close();
								}
								else // if both passwords don't match
								{
									JOptionPane.showMessageDialog(null," Passwords don't match","Error",JOptionPane.ERROR_MESSAGE);
								}
                }
							catch (SQLException se) 
							{
								//handle errors for JDBC
								se.printStackTrace();
							}
							catch (Exception a) //catch block
							{
								a.printStackTrace();
							}
						}
					});
					GridBagConstraints gbc_button_1 = new GridBagConstraints();
					gbc_button_1.anchor = GridBagConstraints.NORTHWEST;
					gbc_button_1.insets = new Insets(0, 0, 5, 5);
					gbc_button_1.gridx = 4;
					gbc_button_1.gridy = 5;
					panel_Update_Form.add(button_1, gbc_button_1);
					
					JLabel lblPf = new JLabel("PF");
					GridBagConstraints gbc_lblPf = new GridBagConstraints();
					gbc_lblPf.fill = GridBagConstraints.BOTH;
					gbc_lblPf.insets = new Insets(0, 0, 5, 5);
					gbc_lblPf.gridx = 1;
					gbc_lblPf.gridy = 6;
					panel_Update_Form.add(lblPf, gbc_lblPf);
					
					tF_PF = new JTextField(60);
					GridBagConstraints gbc_tF_PF = new GridBagConstraints();
					gbc_tF_PF.anchor = GridBagConstraints.NORTH;
					gbc_tF_PF.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_PF.insets = new Insets(0, 0, 5, 5);
					gbc_tF_PF.gridx = 2;
					gbc_tF_PF.gridy = 6;
					panel_Update_Form.add(tF_PF, gbc_tF_PF);
					
					JLabel lblEsi = new JLabel("ESI");
					GridBagConstraints gbc_lblEsi = new GridBagConstraints();
					gbc_lblEsi.fill = GridBagConstraints.BOTH;
					gbc_lblEsi.insets = new Insets(0, 0, 5, 5);
					gbc_lblEsi.gridx = 1;
					gbc_lblEsi.gridy = 7;
					panel_Update_Form.add(lblEsi, gbc_lblEsi);
					
					tF_ESI = new JTextField(60);
					GridBagConstraints gbc_tF_ESI = new GridBagConstraints();
					gbc_tF_ESI.anchor = GridBagConstraints.NORTH;
					gbc_tF_ESI.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_ESI.insets = new Insets(0, 0, 5, 5);
					gbc_tF_ESI.gridx = 2;
					gbc_tF_ESI.gridy = 7;
					panel_Update_Form.add(tF_ESI, gbc_tF_ESI);
					
					JLabel lblSalary = new JLabel("Salary");
					GridBagConstraints gbc_lblSalary = new GridBagConstraints();
					gbc_lblSalary.fill = GridBagConstraints.BOTH;
					gbc_lblSalary.insets = new Insets(0, 0, 5, 5);
					gbc_lblSalary.gridx = 1;
					gbc_lblSalary.gridy = 8;
					panel_Update_Form.add(lblSalary, gbc_lblSalary);
					
					JButton btnDeleteUser = new JButton("Delete User");
					btnDeleteUser.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try
							{
							    int okcancel = JOptionPane.showConfirmDialog((Component) null, "Are your sure ? This action is not reversible .",
								        "Alert/Warning !!", JOptionPane.OK_CANCEL_OPTION);
							    System.out.println("ret : " + okcancel);
							    
							    if(okcancel == 0)
							    {
							    	String username = tF_UserNameSearch_UpdateForm.getText();
							    	try
							    	{
									String IQuery = "DELETE FROM `empdet_db`.`emp_table` WHERE `Emp_ID`='"+username+"' ";

									System.out.println(IQuery);//print on console
									System.out.println("Connecting to a selected database...");

									// Open a connection
									Class.forName("com.mysql.jdbc.Driver");
									// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
									// here schema is the database name and we use no data truncation so that we can store any size of data
						            con = DriverManager.getConnection(url,userid,pwd);
									System.out.println("Connected database successfully...");
									((Connection)con).createStatement().execute(IQuery);//select the rows
									// define SMessage variable
									String SMessage = "Username and records for "+username+" deleted.";
	
                       // create dialog ox which is print message
									JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
									//close connection
									((java.sql.Connection)con).close();
									panel_Update_Form.setVisible(false);
									tF_UserNameSearch_UpdateForm.setText("");
								}
								catch (SQLException se) 
								{
									//handle errors for JDBC
									se.printStackTrace();
								}
								catch (Exception a) //catch block
								{
									a.printStackTrace();
								}
							   }// end of if condition
							}
	
							catch(Exception ex)
							{
								System.out.println(ex);
							}
							 
						}
					});
					
					JButton btn_SavePayrollDetails = new JButton("Save");
					btn_SavePayrollDetails.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String username = tF_UserNameSearch_UpdateForm.getText();
							try
							{
								
								String IQuery = "UPDATE `empdet_db`.`emp_table` SET `Current_Job` = '"+tF_Current_Job.getText()+"' ,"
										+ " `Department` = '"+tF_Dept.getText()+"' , `Date_Of_Joining`  =  '"+datePicker.getEditor().getText()+"'"
										+ " ,  `Basic_Pay`  =  '"+tF_Basic_Pay.getText()+"' , `PF` ='"+tF_PF.getText()+"' , "
										+ "`ESI` ='"+tF_ESI.getText()+"' , `Salary` ='"+tF_Salary.getText()+"', `Bank_Name` ='"+tF_Bank_Name.getText()+"' "
										+ ", `Bank_Address` ='"+tF_Bank_Address.getText()+"' , `Bank_Account_No` ='"+tF_Bank_Ac_No.getText()+"' "
												+ "WHERE `Emp_ID` =  '"+username+"'  ";
								System.out.println(IQuery);//print on console
								System.out.println("Connecting to a selected database...");

								// Open a connection
								Class.forName("com.mysql.jdbc.Driver");
								// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
								// here schema is the database name and we use no data truncation so that we can store any size of data
					            con = DriverManager.getConnection(url,userid,pwd);
								System.out.println("Connected database successfully...");
								((Connection)con).createStatement().execute(IQuery);//select the rows
								// define SMessage variable
								String SMessage = "Employee Details updated for "+username;

                   // create dialog ox which is print message
								JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
								//close connection
								((java.sql.Connection)con).close();

              }
						  catch (SQLException se) 
						  {
							    //handle errors for JDBC
						    	se.printStackTrace();
						  }
						  catch (Exception a) //catch block
						  {
							 a.printStackTrace();
						  }
						}
					});
					
					tF_Salary = new JTextField(60);
					GridBagConstraints gbc_tF_Salary = new GridBagConstraints();
					gbc_tF_Salary.anchor = GridBagConstraints.NORTH;
					gbc_tF_Salary.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Salary.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Salary.gridx = 2;
					gbc_tF_Salary.gridy = 8;
					panel_Update_Form.add(tF_Salary, gbc_tF_Salary);
					
					JLabel lblBankName = new JLabel("Bank Name");
					GridBagConstraints gbc_lblBankName = new GridBagConstraints();
					gbc_lblBankName.fill = GridBagConstraints.VERTICAL;
					gbc_lblBankName.anchor = GridBagConstraints.WEST;
					gbc_lblBankName.insets = new Insets(0, 0, 5, 5);
					gbc_lblBankName.gridx = 1;
					gbc_lblBankName.gridy = 9;
					panel_Update_Form.add(lblBankName, gbc_lblBankName);
					
					tF_Bank_Name = new JTextField(60);
					GridBagConstraints gbc_tF_Bank_Name = new GridBagConstraints();
					gbc_tF_Bank_Name.anchor = GridBagConstraints.NORTH;
					gbc_tF_Bank_Name.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Bank_Name.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Bank_Name.gridx = 2;
					gbc_tF_Bank_Name.gridy = 9;
					panel_Update_Form.add(tF_Bank_Name, gbc_tF_Bank_Name);
					
					JLabel lblBankAccountNo = new JLabel("Bank Address");
					GridBagConstraints gbc_lblBankAccountNo = new GridBagConstraints();
					gbc_lblBankAccountNo.fill = GridBagConstraints.VERTICAL;
					gbc_lblBankAccountNo.anchor = GridBagConstraints.WEST;
					gbc_lblBankAccountNo.insets = new Insets(0, 0, 5, 5);
					gbc_lblBankAccountNo.gridx = 1;
					gbc_lblBankAccountNo.gridy = 10;
					panel_Update_Form.add(lblBankAccountNo, gbc_lblBankAccountNo);
					
					tF_Bank_Address = new JTextField(60);
					GridBagConstraints gbc_tF_Bank_Address = new GridBagConstraints();
					gbc_tF_Bank_Address.anchor = GridBagConstraints.NORTH;
					gbc_tF_Bank_Address.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Bank_Address.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Bank_Address.gridx = 2;
					gbc_tF_Bank_Address.gridy = 10;
					panel_Update_Form.add(tF_Bank_Address, gbc_tF_Bank_Address);
					
					JLabel lblBankAccountNo_1 = new JLabel("Bank Account No.");
					GridBagConstraints gbc_lblBankAccountNo_1 = new GridBagConstraints();
					gbc_lblBankAccountNo_1.fill = GridBagConstraints.VERTICAL;
					gbc_lblBankAccountNo_1.anchor = GridBagConstraints.WEST;
					gbc_lblBankAccountNo_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblBankAccountNo_1.gridx = 1;
					gbc_lblBankAccountNo_1.gridy = 11;
					panel_Update_Form.add(lblBankAccountNo_1, gbc_lblBankAccountNo_1);
					
					tF_Bank_Ac_No = new JTextField(60);
					GridBagConstraints gbc_tF_Bank_Ac_No = new GridBagConstraints();
					gbc_tF_Bank_Ac_No.anchor = GridBagConstraints.NORTH;
					gbc_tF_Bank_Ac_No.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Bank_Ac_No.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Bank_Ac_No.gridx = 2;
					gbc_tF_Bank_Ac_No.gridy = 11;
					panel_Update_Form.add(tF_Bank_Ac_No, gbc_tF_Bank_Ac_No);
					GridBagConstraints gbc_btn_SavePayrollDetails = new GridBagConstraints();
					gbc_btn_SavePayrollDetails.anchor = GridBagConstraints.NORTH;
					gbc_btn_SavePayrollDetails.fill = GridBagConstraints.HORIZONTAL;
					gbc_btn_SavePayrollDetails.insets = new Insets(0, 0, 0, 5);
					gbc_btn_SavePayrollDetails.gridx = 1;
					gbc_btn_SavePayrollDetails.gridy = 12;
					panel_Update_Form.add(btn_SavePayrollDetails, gbc_btn_SavePayrollDetails);
					GridBagConstraints gbc_btnDeleteUser = new GridBagConstraints();
					gbc_btnDeleteUser.anchor = GridBagConstraints.SOUTHWEST;
					gbc_btnDeleteUser.gridx = 5;
					gbc_btnDeleteUser.gridy = 12;
					panel_Update_Form.add(btnDeleteUser, gbc_btnDeleteUser);
					
					panel_Leave_Portal = new JPanel();
					panel.add(panel_Leave_Portal, "Leave_Portal");
					GridBagLayout gbl_panel_Leave_Portal = new GridBagLayout();
					gbl_panel_Leave_Portal.columnWidths = new int[]{47, 130, 138, 90, 97, 0, 0, 29, 0};
					gbl_panel_Leave_Portal.rowHeights = new int[]{0, 23, 23, 452, 0};
					gbl_panel_Leave_Portal.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
					gbl_panel_Leave_Portal.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					panel_Leave_Portal.setLayout(gbl_panel_Leave_Portal);
					
					JLabel lblNewLabel_1 = new JLabel("Leave Applications");
					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
					gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_1.gridx = 1;
					gbc_lblNewLabel_1.gridy = 1;
					panel_Leave_Portal.add(lblNewLabel_1, gbc_lblNewLabel_1);
					
					tF_searchEmpID = new JTextField();
					GridBagConstraints gbc_tF_searchEmpID = new GridBagConstraints();
					gbc_tF_searchEmpID.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_searchEmpID.insets = new Insets(0, 0, 5, 5);
					gbc_tF_searchEmpID.gridx = 4;
					gbc_tF_searchEmpID.gridy = 1;
					panel_Leave_Portal.add(tF_searchEmpID, gbc_tF_searchEmpID);
					tF_searchEmpID.setColumns(10);
					
					JButton btnSearch_EmpID = new JButton("Search by EmpID");
					btnSearch_EmpID.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							flag = 0;
							if (tF_searchEmpID.getText().equals(""))
								JOptionPane.showMessageDialog(null," Search field is blank. Please enter the Employee ID.","Error",JOptionPane.ERROR_MESSAGE);
							else
								try
								{
									searchLeaveRecord();
									if (flag == 3)
										JOptionPane.showMessageDialog(null," No records found for the Employee ID: ["+tF_searchEmpID.getText()+"].","Error",JOptionPane.ERROR_MESSAGE);
									else
									{
									// Open a connection
									Class.forName("com.mysql.jdbc.Driver");
									// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
									// here schema is the database name and we use no data truncation so that we can store any size of data
									con = DriverManager.getConnection(url,userid,pwd);
									System.out.println("Connected database successfully...");
									DBTablePrinter.printTable(con, "emp_leave_table");
									showEmp_LeaveDetails_by_EmpID();
									}
								}
						   		catch(Exception ex){
						   			System.out.println(ex);
						   		}
						}
					});
					GridBagConstraints gbc_btnSearch_EmpID = new GridBagConstraints();
					gbc_btnSearch_EmpID.anchor = GridBagConstraints.NORTH;
					gbc_btnSearch_EmpID.insets = new Insets(0, 0, 5, 5);
					gbc_btnSearch_EmpID.gridx = 5;
					gbc_btnSearch_EmpID.gridy = 1;
					panel_Leave_Portal.add(btnSearch_EmpID, gbc_btnSearch_EmpID);
					
					JButton btnShowAll = new JButton("Show All records");
					btnShowAll.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try
							{
							// Open a connection
							Class.forName("com.mysql.jdbc.Driver");
							// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
							// here schema is the database name and we use no data truncation so that we can store any size of data
	            con = DriverManager.getConnection(url,userid,pwd);
							System.out.println("Connected database successfully...");
							DBTablePrinter.printTable(con, "emp_leave_table");
							Leave_Report window  =  new Leave_Report();
							window.Leave_Report_Frame.setVisible(true);
							}
						   	catch(Exception ex){
								System.out.println(ex);
							}
						}
					});
					GridBagConstraints gbc_btnShowAll = new GridBagConstraints();
					gbc_btnShowAll.anchor = GridBagConstraints.NORTH;
					gbc_btnShowAll.insets = new Insets(0, 0, 5, 5);
					gbc_btnShowAll.gridx = 5;
					gbc_btnShowAll.gridy = 2;
					panel_Leave_Portal.add(btnShowAll, gbc_btnShowAll);
					
					final JPanel panel_Display_Leaves = new JPanel();
					panel_Display_Leaves.setForeground(new Color(0, 0, 128));
					panel_Display_Leaves.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
					GridBagConstraints gbc_panel_Display_Leaves = new GridBagConstraints();
					gbc_panel_Display_Leaves.insets = new Insets(0, 0, 0, 5);
					gbc_panel_Display_Leaves.fill = GridBagConstraints.BOTH;
					gbc_panel_Display_Leaves.gridwidth = 6;
					gbc_panel_Display_Leaves.gridx = 1;
					gbc_panel_Display_Leaves.gridy = 3;
					panel_Leave_Portal.add(panel_Display_Leaves, gbc_panel_Display_Leaves);
					GridBagLayout gbl_panel_Display_Leaves = new GridBagLayout();
					gbl_panel_Display_Leaves.columnWidths = new int[]{143, 130, 95, 89, 89, 76, 160, 0, 0};
					gbl_panel_Display_Leaves.rowHeights = new int[]{0, 34, 20, 22, 23, 68, 14, 23, 82, 25, 0};
					gbl_panel_Display_Leaves.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					gbl_panel_Display_Leaves.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					panel_Display_Leaves.setLayout(gbl_panel_Display_Leaves);
					final JButton btnGoToLeave = new JButton("Go To Leave ID");
					btnGoToLeave.setFont(new Font("Tahoma", Font.PLAIN, 10));
					btnGoToLeave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							 searchRecord();
							btnNext.setEnabled(false);
							btnPrevious.setEnabled(false);
							if (flag == 0)
							btnReset.setEnabled(true);
						}
					});
					GridBagConstraints gbc_btnGoToLeave = new GridBagConstraints();
					gbc_btnGoToLeave.fill = GridBagConstraints.BOTH;
					gbc_btnGoToLeave.insets = new Insets(0, 0, 5, 5);
					gbc_btnGoToLeave.gridx = 1;
					gbc_btnGoToLeave.gridy = 1;
					panel_Display_Leaves.add(btnGoToLeave, gbc_btnGoToLeave);
					
					tF_SearchLeaveID = new JTextField();
					tF_SearchLeaveID.setText("");
					tF_SearchLeaveID.setColumns(10);
					GridBagConstraints gbc_tF_SearchLeaveID = new GridBagConstraints();
					gbc_tF_SearchLeaveID.anchor = GridBagConstraints.NORTH;
					gbc_tF_SearchLeaveID.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_SearchLeaveID.insets = new Insets(0, 0, 5, 5);
					gbc_tF_SearchLeaveID.gridx = 2;
					gbc_tF_SearchLeaveID.gridy = 1;
					panel_Display_Leaves.add(tF_SearchLeaveID, gbc_tF_SearchLeaveID);
					
					
					btnReset = new JButton("Reset");
					btnReset.setEnabled(false);
					btnReset.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btnNext.setEnabled(true);
							btnPrevious.setEnabled(false);
							tF_SearchLeaveID.setText("");
							reset();
							btnReset.setEnabled(false);
							try {
								rs.previous();
							} 
							catch (SQLException e1) 
							{
								
								e1.printStackTrace();
							}
						}
					});		
					GridBagConstraints gbc_btnReset = new GridBagConstraints();
					gbc_btnReset.anchor = GridBagConstraints.NORTH;
					gbc_btnReset.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnReset.insets = new Insets(0, 0, 5, 5);
					gbc_btnReset.gridx = 3;
					gbc_btnReset.gridy = 1;
					panel_Display_Leaves.add(btnReset, gbc_btnReset);
					
					JLabel lblLeaveId = new JLabel("Leave ID");
					GridBagConstraints gbc_lblLeaveId = new GridBagConstraints();
					gbc_lblLeaveId.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblLeaveId.insets = new Insets(0, 0, 5, 5);
					gbc_lblLeaveId.gridx = 1;
					gbc_lblLeaveId.gridy = 2;
					panel_Display_Leaves.add(lblLeaveId, gbc_lblLeaveId);
					
					tF_LeaveID = new JTextField();
					tF_LeaveID.setEditable(false);
					tF_LeaveID.setColumns(10);
					tF_LeaveID.setText(Leave_ID);
					GridBagConstraints gbc_tF_LeaveID = new GridBagConstraints();
					gbc_tF_LeaveID.anchor = GridBagConstraints.NORTH;
					gbc_tF_LeaveID.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_LeaveID.insets = new Insets(0, 0, 5, 5);
					gbc_tF_LeaveID.gridwidth = 2;
					gbc_tF_LeaveID.gridx = 2;
					gbc_tF_LeaveID.gridy = 2;
					panel_Display_Leaves.add(tF_LeaveID, gbc_tF_LeaveID);
					
					tF_EmpID = new JTextField();
					tF_EmpID.setEditable(false);
					tF_EmpID.setColumns(10);
					tF_EmpID.setText(Emp_ID);
					GridBagConstraints gbc_tF_EmpID = new GridBagConstraints();
					gbc_tF_EmpID.anchor = GridBagConstraints.NORTH;
					gbc_tF_EmpID.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_EmpID.insets = new Insets(0, 0, 5, 5);
					gbc_tF_EmpID.gridx = 5;
					gbc_tF_EmpID.gridy = 2;
					panel_Display_Leaves.add(tF_EmpID, gbc_tF_EmpID);
					
					JLabel label_13 = new JLabel("Employee ID");
					GridBagConstraints gbc_label_13 = new GridBagConstraints();
					gbc_label_13.fill = GridBagConstraints.HORIZONTAL;
					gbc_label_13.insets = new Insets(0, 0, 5, 5);
					gbc_label_13.gridx = 4;
					gbc_label_13.gridy = 2;
					panel_Display_Leaves.add(label_13, gbc_label_13);
					
					JLabel label_4 = new JLabel("Leave Type");
					GridBagConstraints gbc_label_4 = new GridBagConstraints();
					gbc_label_4.fill = GridBagConstraints.HORIZONTAL;
					gbc_label_4.insets = new Insets(0, 0, 5, 5);
					gbc_label_4.gridx = 1;
					gbc_label_4.gridy = 3;
					panel_Display_Leaves.add(label_4, gbc_label_4);
					
					tF_LeaveType = new JTextField();
					tF_LeaveType.setEditable(false);
					tF_LeaveType.setColumns(10);
					tF_LeaveType.setText(Leave_Type);
					GridBagConstraints gbc_tF_LeaveType = new GridBagConstraints();
					gbc_tF_LeaveType.anchor = GridBagConstraints.SOUTH;
					gbc_tF_LeaveType.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_LeaveType.insets = new Insets(0, 0, 5, 5);
					gbc_tF_LeaveType.gridwidth = 2;
					gbc_tF_LeaveType.gridx = 2;
					gbc_tF_LeaveType.gridy = 3;
					panel_Display_Leaves.add(tF_LeaveType, gbc_tF_LeaveType);
					
					JLabel label_7 = new JLabel("Leave from");
					GridBagConstraints gbc_label_7 = new GridBagConstraints();
					gbc_label_7.fill = GridBagConstraints.HORIZONTAL;
					gbc_label_7.insets = new Insets(0, 0, 5, 5);
					gbc_label_7.gridx = 4;
					gbc_label_7.gridy = 3;
					panel_Display_Leaves.add(label_7, gbc_label_7);
					
					tF_LeaveFrom = new JTextField();
					tF_LeaveFrom.setEditable(false);
					tF_LeaveFrom.setColumns(10);
					tF_LeaveFrom.setText(Leave_From);
					GridBagConstraints gbc_tF_LeaveFrom = new GridBagConstraints();
					gbc_tF_LeaveFrom.anchor = GridBagConstraints.NORTH;
					gbc_tF_LeaveFrom.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_LeaveFrom.insets = new Insets(0, 0, 5, 5);
					gbc_tF_LeaveFrom.gridx = 5;
					gbc_tF_LeaveFrom.gridy = 3;
					panel_Display_Leaves.add(tF_LeaveFrom, gbc_tF_LeaveFrom);
					
					JLabel label_6 = new JLabel("Reason");
					GridBagConstraints gbc_label_6 = new GridBagConstraints();
					gbc_label_6.fill = GridBagConstraints.HORIZONTAL;
					gbc_label_6.insets = new Insets(0, 0, 5, 5);
					gbc_label_6.gridx = 1;
					gbc_label_6.gridy = 4;
					panel_Display_Leaves.add(label_6, gbc_label_6);
					
					tF_Reason = new JTextField();
					tF_Reason.setEditable(false);
					tF_Reason.setColumns(10);
					tF_Reason.setText(Reason);
					GridBagConstraints gbc_tF_Reason = new GridBagConstraints();
					gbc_tF_Reason.anchor = GridBagConstraints.SOUTH;
					gbc_tF_Reason.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_Reason.insets = new Insets(0, 0, 5, 5);
					gbc_tF_Reason.gridwidth = 2;
					gbc_tF_Reason.gridx = 2;
					gbc_tF_Reason.gridy = 4;
					panel_Display_Leaves.add(tF_Reason, gbc_tF_Reason);
					
					JLabel label_11 = new JLabel("To");
					GridBagConstraints gbc_label_11 = new GridBagConstraints();
					gbc_label_11.fill = GridBagConstraints.HORIZONTAL;
					gbc_label_11.insets = new Insets(0, 0, 5, 5);
					gbc_label_11.gridx = 4;
					gbc_label_11.gridy = 4;
					panel_Display_Leaves.add(label_11, gbc_label_11);
					
					tF_LeaveTo = new JTextField();
					tF_LeaveTo.setEditable(false);
					tF_LeaveTo.setColumns(10);
					tF_LeaveTo.setText(Leave_To);
					GridBagConstraints gbc_tF_LeaveTo = new GridBagConstraints();
					gbc_tF_LeaveTo.anchor = GridBagConstraints.NORTH;
					gbc_tF_LeaveTo.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_LeaveTo.insets = new Insets(0, 0, 5, 5);
					gbc_tF_LeaveTo.gridx = 5;
					gbc_tF_LeaveTo.gridy = 4;
					panel_Display_Leaves.add(tF_LeaveTo, gbc_tF_LeaveTo);
					
					JLabel label_12 = new JLabel("Additional Information");
					GridBagConstraints gbc_label_12 = new GridBagConstraints();
					gbc_label_12.anchor = GridBagConstraints.NORTHWEST;
					gbc_label_12.insets = new Insets(0, 0, 5, 5);
					gbc_label_12.gridx = 1;
					gbc_label_12.gridy = 5;
					panel_Display_Leaves.add(label_12, gbc_label_12);
					
					tA_Info = new JTextArea();
					tA_Info.setEditable(false);
					tA_Info.setWrapStyleWord(true);
					tA_Info.setLineWrap(true);
					tA_Info.setColumns(10);
					tA_Info.setText(Leave_Info);
					GridBagConstraints gbc_tA_Info = new GridBagConstraints();
					gbc_tA_Info.anchor = GridBagConstraints.WEST;
					gbc_tA_Info.fill = GridBagConstraints.VERTICAL;
					gbc_tA_Info.insets = new Insets(0, 0, 5, 5);
					gbc_tA_Info.gridwidth = 5;
					gbc_tA_Info.gridx = 2;
					gbc_tA_Info.gridy = 5;
					panel_Display_Leaves.add(tA_Info, gbc_tA_Info);
					
					JButton btn_Submit = new JButton("Submit / Save");
					btn_Submit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try  //try block
                {
                //declare variables
								String EmpID = "";
								String LeaveID = "";
								String LeaveStatus = "";
								String Leave_Comments = "";

								//get values using getText() method
								EmpID = tF_EmpID.getText().trim();
								LeaveID = tF_LeaveID.getText().trim();
								LeaveStatus = tF_LeaveStatus.getText().trim();
								Leave_Comments = tA_Comment.getText().trim();

                // check condition it field equals to blank throw error message
								if (LeaveStatus.equals(""))
								{
									JOptionPane.showMessageDialog(null," LeaveStatus field is blank","Error",JOptionPane.ERROR_MESSAGE);
								}
								else 
								{
									String IQuery = "UPDATE `empdet_db`.`emp_leave_table` SET `Leave_Approved`='"+LeaveStatus+"',"
											+ "`Leave_Comments`='"+Leave_Comments+"' WHERE `Leave_ID`='"+LeaveID+"' ";
									
									System.out.println(IQuery);//print on console
									System.out.println("Connecting to a selected database...");

									// Open a connection
									Class.forName("com.mysql.jdbc.Driver");
									// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
									// here schema is the database name and we use no data truncation so that we can store any size of data
						            con = DriverManager.getConnection(url,userid,pwd);
									System.out.println("Connected database successfully...");
									((Connection)con).createStatement().execute(IQuery);//select the rows
									// define SMessage variable
									String SMessage = "Record updated for "+LeaveID+" with Employee ID: "+EmpID+".";
									
	
                       // create dialog ox which is print message
									JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
									//close connection
									((java.sql.Connection)con).close();
									frmControlPanel.repaint();
								}
								
                }
							catch (SQLException se) 
							{
								//handle errors for JDBC
								se.printStackTrace();
							}
							catch (Exception a) //catch block
							{
								a.printStackTrace();
							}
						}
					});
					
					JLabel lblGrantLeave = new JLabel("Leave Status");
					lblGrantLeave.setToolTipText("Leave Staus");
					GridBagConstraints gbc_lblGrantLeave = new GridBagConstraints();
					gbc_lblGrantLeave.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblGrantLeave.insets = new Insets(0, 0, 5, 5);
					gbc_lblGrantLeave.gridx = 1;
					gbc_lblGrantLeave.gridy = 7;
					panel_Display_Leaves.add(lblGrantLeave, gbc_lblGrantLeave);
					
					tF_LeaveStatus = new JTextField();
					tF_LeaveStatus.setToolTipText("Leave Approval or denial.");
					tF_LeaveStatus.setText(Leave_Approved);
					GridBagConstraints gbc_tF_LeaveStatus = new GridBagConstraints();
					gbc_tF_LeaveStatus.fill = GridBagConstraints.HORIZONTAL;
					gbc_tF_LeaveStatus.insets = new Insets(0, 0, 5, 5);
					gbc_tF_LeaveStatus.gridwidth = 2;
					gbc_tF_LeaveStatus.gridx = 2;
					gbc_tF_LeaveStatus.gridy = 7;
					panel_Display_Leaves.add(tF_LeaveStatus, gbc_tF_LeaveStatus);
					tF_LeaveStatus.setColumns(10);
					
					btnPrevious = new JButton("Previous");
					btnPrevious.setEnabled(false);
					btnPrevious.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							previousRecord();
							if (flag == 1)
							{
								btnPrevious.setEnabled(false);
								btnNext.setEnabled(true);
							}
								btnNext.setEnabled(true);
						}
					});
					GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
					gbc_btnPrevious.anchor = GridBagConstraints.NORTHEAST;
					gbc_btnPrevious.insets = new Insets(0, 0, 5, 5);
					gbc_btnPrevious.gridx = 4;
					gbc_btnPrevious.gridy = 7;
					panel_Display_Leaves.add(btnPrevious, gbc_btnPrevious);
					
					btnNext = new JButton("Next");
					btnNext.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							nextRecord();
							if (flag == 2)
							{
								btnNext.setEnabled(false);
								btnPrevious.setEnabled(true);
							}
								btnPrevious.setEnabled(true);
						}
					});
					GridBagConstraints gbc_btnNext = new GridBagConstraints();
					gbc_btnNext.anchor = GridBagConstraints.NORTHWEST;
					gbc_btnNext.insets = new Insets(0, 0, 5, 5);
					gbc_btnNext.gridx = 5;
					gbc_btnNext.gridy = 7;
					panel_Display_Leaves.add(btnNext, gbc_btnNext);
					
					JLabel lblComment = new JLabel("Comments");
					GridBagConstraints gbc_lblComment = new GridBagConstraints();
					gbc_lblComment.anchor = GridBagConstraints.NORTH;
					gbc_lblComment.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblComment.insets = new Insets(0, 0, 5, 5);
					gbc_lblComment.gridx = 1;
					gbc_lblComment.gridy = 8;
					panel_Display_Leaves.add(lblComment, gbc_lblComment);
					
					
					tA_Comment = new JTextArea();
					tA_Comment.setWrapStyleWord(true);
					tA_Comment.setLineWrap(true);
					tA_Comment.setText(Leave_Comments);
					GridBagConstraints gbc_tA_Comment = new GridBagConstraints();
					gbc_tA_Comment.fill = GridBagConstraints.BOTH;
					gbc_tA_Comment.insets = new Insets(0, 0, 5, 5);
					gbc_tA_Comment.gridwidth = 4;
					gbc_tA_Comment.gridx = 2;
					gbc_tA_Comment.gridy = 8;
					panel_Display_Leaves.add(tA_Comment, gbc_tA_Comment);
					tA_Comment.setColumns(10);
					tA_Comment.setDocument(doc);
					GridBagConstraints gbc_btn_Submit = new GridBagConstraints();
					gbc_btn_Submit.insets = new Insets(0, 0, 0, 5);
					gbc_btn_Submit.gridwidth = 2;
					gbc_btn_Submit.gridx = 2;
					gbc_btn_Submit.gridy = 9;
					panel_Display_Leaves.add(btn_Submit, gbc_btn_Submit);
					GridBagConstraints gbc_remainingLabel = new GridBagConstraints();
					gbc_remainingLabel.insets = new Insets(0, 0, 0, 5);
					gbc_remainingLabel.fill = GridBagConstraints.BOTH;
					gbc_remainingLabel.gridx = 5;
					gbc_remainingLabel.gridy = 9;
					panel_Display_Leaves.add(remainingLabel, gbc_remainingLabel);
					
				
			
			
	}
	
	// Emp_data Report
	final class Report_Emp_Payroll_Details implements ActionListener
	{
		 public void actionPerformed(ActionEvent e)
	        {
			 try
				{
				 Payroll_Details_Report window = new Payroll_Details_Report();
					window.frmPayrollDetails_Report.setVisible(true);
				}
			   	catch(Exception ex){
					System.out.println(ex);
				}

	        }
	}

	// Emp_PersonalDetails Report
		final class Report_Emp_PersonalDetails implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {	
				 try
					{
					 Employee_Personal_Details_Report window = new Employee_Personal_Details_Report();
					 window.Emp_Personal_Details_Report.setVisible(true);
					}
				   	catch(Exception ex){
						System.out.println(ex);
					}				 
		        }
		}
		
		// Emp_LeaveDetails Report
		final class Report_Emp_LeaveDetails implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {
				 
				 try {
					    Leave_Report window = new Leave_Report();
						window.Leave_Report_Frame.setVisible(true);
					} 
				 catch (Exception e1) {
						
						e1.printStackTrace();
					}
		        }
		}
		
		// Emp_WorkDetails Report
		final class Report_Emp_WorkDetails implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {	
				 try
					{
					 Work_Details_Report window = new Work_Details_Report();
					 window.frmWorkDetails_Report.setVisible(true);
					}
				   	catch(Exception ex){
						System.out.println(ex);
					}		
		        }
		}
		
		// Emp_ContactDetails Report
		final class Report_Emp_ContactDetails implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {
				 try
					{
					 Contact_Details_Report window = new Contact_Details_Report();
					 window.frmContactDetails_Report.setVisible(true);
					}
				   	catch(Exception ex){
						System.out.println(ex);
					}
		        }
		}
		// Permanent Address Details Report
		final class Report_Permanent_Address_Details implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Permanent_Address_Report window = new Permanent_Address_Report();
					window.Permanent_Address_Details_Report.setVisible(true);
				}
				catch(Exception ex){
						System.out.println(ex);
				}
			}
		}
		// NotificationSettings Report
		final class Report_Emp_NotificationSettings implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {
				 try
					{
					 Notifications_Report window = new Notifications_Report();
					 window.frmNotificationDetails_Report.setVisible(true);
					}
				   	catch(Exception ex){
						System.out.println(ex);
					}
		        }
		}
	// log out 
	final class LogOut implements ActionListener
	{
		 public void actionPerformed(ActionEvent e)
	        {
			 	Login window1  =  new Login();
        		window1.c.setVisible(true); // c is the content pane from the login form
        		frmControlPanel.setVisible(false);
	        }
	}
	
    // Exit app
    static class exitApp implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    

    
    
    
    
   
    
    public void showEmp_LeaveDetails_by_EmpID() {
    	 PreparedStatement pst;
    	 JTable table;
    	 
    	 frmLeaveReport_by_EmpID = new JFrame("Employee Leave Details");
    	 frmLeaveReport_by_EmpID.getContentPane().setLayout(new BorderLayout());
    	 String[] columnNames = {"Leave ID","Emp ID","Leave Type", "Reason", "Leave From", "Leave To", "Leave Info", "Leave Status", "Comments"};
    	 
    	 JMenuBar menuBar = new JMenuBar();
    	 frmLeaveReport_by_EmpID.setJMenuBar(menuBar);
    	 
    	 JMenu mnNewMenu = new JMenu("File →");
 		menuBar.add(mnNewMenu);
 		
 		JMenuItem Save = new JMenuItem("Save As...");
 		Save.addActionListener(new Save());
 		Save.setToolTipText("Save As");
 		mnNewMenu.add(Save);
 		
 		JMenuItem divider2 = new JMenuItem("-------");
 		mnNewMenu.add(divider2);
 		divider2.setEnabled(false);
 		
 		JMenuItem exit = new JMenuItem("Exit");
 		exit.addActionListener(new Close());
 		exit.setToolTipText("Close the current window.");
 		mnNewMenu.add(exit);
    	 

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
  	    String Leave_ID = "";
        String Emp_ID = "";
 	    String Leave_Type = "";
 	    String Reason = "";
 	    String Leave_From = "";
	    String Leave_To = "";
	    String Leave_Info = "";
	    String Leave_Approved = "";
	    String Leave_Comments = "";
	    String username1 = tF_searchEmpID.getText().trim();

        try {
            pst = con.prepareStatement("SELECT * FROM emp_leave_table WHERE Emp_ID ='"+username1+"'");
            ResultSet rs = pst.executeQuery();
            while(rs.next())
 			{

         	Leave_ID		= rs.getString("Leave_ID");
         	Emp_ID			= rs.getString("Emp_ID");
         	Leave_Type		= rs.getString("Leave_Type");           	   	  
         	Reason			= rs.getString("Reason");
         	Leave_From		= rs.getString("Leave_From");
         	Leave_To		= rs.getString("Leave_To");
         	Leave_Info		= rs.getString("Leave_Info");
         	Leave_Approved	= rs.getString("Leave_Approved");
         	Leave_Comments	= rs.getString("Leave_Comments");
        	    
                model.addRow(new Object[]{Leave_ID, Emp_ID, Leave_Type, Reason, Leave_From, Leave_To, Leave_Info, Leave_Approved, Leave_Comments});  
                }
            rs.close();
            pst.close();
            
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frmLeaveReport_by_EmpID.getContentPane().add(scroll);
        frmLeaveReport_by_EmpID.setVisible(true);
        frmLeaveReport_by_EmpID.setSize(1000, 500);
        
     
    }// end of showEmp_LeaveDetails_by_EmpID()
    
 // Save()
		final class Save implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {
				 JFileChooser fileChooser = new JFileChooser();
				 fileChooser.setDialogTitle("Specify a file to save"); 
				 fileChooser.setSelectedFile(new File("*.xlsx"));
				 
				 FileNameExtensionFilter xlsxFilter = new FileNameExtensionFilter("xlsx files (*.xlsx)","xslx");
			        // add filters
				 fileChooser.addChoosableFileFilter(xlsxFilter);
				 fileChooser.setFileFilter(xlsxFilter);
			        
				 int userSelection = fileChooser.showSaveDialog(frmLeaveReport_by_EmpID);

				 if (userSelection == JFileChooser.APPROVE_OPTION) {
					String Leave_ID = "";
			        String Emp_ID = "";
			 	    String Leave_Type = "";
			 	    String Reason = "";
			 	    String Leave_From = "";
				    String Leave_To = "";
				    String Leave_Info = "";
				    String Leave_Approved = "";
				    String Leave_Comments = "";
				    String username1 = tF_searchEmpID.getText().trim();
					 File fileToSave = fileChooser.getSelectedFile();	
				     
				     XSSFWorkbook workbook = new XSSFWorkbook();

				     //Create a blank sheet
				     XSSFSheet sheet = workbook.createSheet("Employee Data");

				     //This data needs to be written (Object[])
				     Map<String, Object[]> data = new TreeMap<String, Object[]>();
				     // column heading
				     data.put("1", new Object[]{"Leave_ID", "Emp_ID", "Leave_Type", "Reason", "Leave_From", 
				    		 "Leave_To", "Leave_Info", "Leave_Approved", "Leave_Comments"});
				     int i = 2;
				     String num = "";
				     try {
			        	 con = DriverManager.getConnection(url,userid,pwd);
			         	
			             PreparedStatement pst = con.prepareStatement("SELECT * FROM emp_leave_table WHERE Emp_ID ='"+username1+"'");
			             ResultSet rs = pst.executeQuery();
			             while(rs.next())
			  			{

			          	Leave_ID		= rs.getString("Leave_ID");
			          	Emp_ID			= rs.getString("Emp_ID");
			          	Leave_Type		= rs.getString("Leave_Type");           	   	  
			          	Reason			= rs.getString("Reason");
			          	Leave_From		= rs.getString("Leave_From");
			          	Leave_To		= rs.getString("Leave_To");
			          	Leave_Info		= rs.getString("Leave_Info");
			          	Leave_Approved	= rs.getString("Leave_Approved");
			          	Leave_Comments 	= rs.getString("Leave_Comments");
			         	    num = Integer.toString(i);
			                 data.put(""+num, new Object[]{Leave_ID, Emp_ID, Leave_Type, Reason, Leave_From, 
			                		 Leave_To, Leave_Info, Leave_Approved,  Leave_Comments});  
			                  i++;
			                 }
			             rs.close();
			             pst.close();
			         } 
			         catch (Exception ex) 
			         {
			             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			         }
				     
				
				     //Iterate over data and write to sheet
				     Set<String> keyset = data.keySet();

				     int rownum = 0;
				     for (String key : keyset) 
				     {
				         //create a row of excel sheet
				         Row row = sheet.createRow(rownum++);

				         //get object array of particular key
				         Object[] objArr = data.get(key);

				         int cellnum = 0;

				         for (Object obj : objArr) 
				         {
				             Cell cell = row.createCell(cellnum++);
				             if (obj instanceof String) 
				             {
				                 cell.setCellValue((String) obj);
				             }
				             else if (obj instanceof Integer) 
				             {
				                 cell.setCellValue((Integer) obj);
				             }
				         }
				     }
				     try 
				     {
				    	 String filename = fileToSave.getAbsolutePath();
				    	   if (!filename.endsWith("xlsx"))
				    	        filename += ".xlsx";
				         //Write the workbook in file system
				         FileOutputStream out = new FileOutputStream(new File( filename ));
				         
				         workbook.write(out);
				         out.close();
				         System.out.println(filename+".xlsx written successfully on disk. Location:"+fileToSave.getAbsolutePath());
				     } 
				     catch (Exception ex)
				     {
				         ex.printStackTrace();
				     }
				     
				     
				 }
				
				 }
				 
	        }// end of Save()

		
		// exitApp()
				final class Close implements ActionListener
				{
					 public void actionPerformed(ActionEvent e)
				        {
						 frmLeaveReport_by_EmpID.dispose();
				        }
				}
    
   
    

    
    public void clear(){
    	try
	   	{
    	tF_Emp_ID.setText(""); 
	    tF_Basic_Pay.setText("");
	   	tF_PF.setText("");
	   	tF_PF.setText("");
	   	tF_Salary.setText("");
	   	
	   	/*clear contents of arraylist*/
	    recordNumber = -1;

	   	EmpList.clear();
	   	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
     }
    
    public void refresh(){
    	try
	   	{	   	
	   	tF_UserNameSearch_UpdateForm.setText("");
	   	tF_UserName.setText("");
	   	pF_Pwd.setText("");
	   	pF_ConfirmPwd.setText("");
	   	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
     }
    
    private void updateCount()
    {
        remainingLabel.setText((300 -doc.getLength()) + " characters remaining");
    }
    
    public void previousRecord()
    {
    	try	{
			
		   String Leave_ID 			= "";
	   	   String Emp_ID 			= "";
	   	   String Leave_Type 		= "";
	   	   String Reason		 	= "";
	   	   String Leave_From 		= "";
	   	   String Leave_To 			= "";
	   	   String Leave_Info 		= "";
	   	   String Leave_Approved 	= "";
	   	   String Leave_Comments 			= "";
		    

	   	   if (rs.previous())
	   	   {
			   Leave_ID 		= rs.getString("Leave_ID");
			   Emp_ID 			= rs.getString("Emp_ID");
	           Leave_Type 		= rs.getString("Leave_Type");
	           Reason			= rs.getString("Reason");
	           Leave_From 		= rs.getString("Leave_From");
	           Leave_To 		= rs.getString("Leave_To");
	           Leave_Info 		= rs.getString("Leave_Info");
	           Leave_Approved 	= rs.getString("Leave_Approved");
	           Leave_Comments 	= rs.getString("Leave_Comments");

	           // displaying next record in text fields

		   		tF_LeaveID.setText(Leave_ID);
		   		tF_EmpID.setText(Emp_ID);
		   		tF_LeaveType.setText(Leave_Type);
		   		tF_Reason.setText(Reason);
		   		tF_LeaveFrom.setText(Leave_From);
		   		tF_LeaveTo.setText(Leave_To);
		   		tA_Info.setText(Leave_Info);
		   		tF_LeaveStatus.setText(Leave_Approved);
		   		tA_Comment.setText(Leave_Comments);
		   		flag = 0;
	   	   }
	   	   else
	   	   {
	   		rs.next();
	   		JOptionPane.showMessageDialog(null, "Beginning of File");
	   		flag = 1;
	   	   }
			 
			
		}
			catch(SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
			catch(Exception ex1) {
				System.err.println("Exception: " + ex1.getMessage());
			}					
    }
    
    public void nextRecord()
    {
    	try	{
			
		   String Leave_ID 			= "";
	   	   String Emp_ID 			= "";
	   	   String Leave_Type 		= "";
	   	   String Reason		 	= "";
	   	   String Leave_From 		= "";
	   	   String Leave_To 			= "";
	   	   String Leave_Info 		= "";
	   	   String Leave_Approved 	= "";
	   	   String Leave_Comments 			= "";
		    

	   	   if (rs.next())
	   	   {
			   Leave_ID 		= rs.getString("Leave_ID");
			   Emp_ID 			= rs.getString("Emp_ID");
	           Leave_Type 		= rs.getString("Leave_Type");
	           Reason			= rs.getString("Reason");
	           Leave_From 		= rs.getString("Leave_From");
	           Leave_To 		= rs.getString("Leave_To");
	           Leave_Info 		= rs.getString("Leave_Info");
	           Leave_Approved 	= rs.getString("Leave_Approved");
	           Leave_Comments 	= rs.getString("Leave_Comments");

	           // displaying next record in text fields

		   		tF_LeaveID.setText(Leave_ID);
		   		tF_EmpID.setText(Emp_ID);
		   		tF_LeaveType.setText(Leave_Type);
		   		tF_Reason.setText(Reason);
		   		tF_LeaveFrom.setText(Leave_From);
		   		tF_LeaveTo.setText(Leave_To);
		   		tA_Info.setText(Leave_Info);
		   		tF_LeaveStatus.setText(Leave_Approved);
		   		tA_Comment.setText(Leave_Comments);
		   		flag = 0;
	   	   }
			  
	   	  else
	   	   {
	   		rs.previous();
	   		JOptionPane.showMessageDialog(null, "End of File");
	   		flag = 2;
	   	   }
			
		}
			catch(SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
			catch(Exception ex1) {
				System.err.println("Exception: " + ex1.getMessage());
			}					
    }
    
    @SuppressWarnings("rawtypes")
	public void searchRecord()
    {
    	try	{
    		String search_leave_ID = tF_SearchLeaveID.getText().trim();
    		String Search_sql = "SELECT * FROM emp_leave_table WHERE Leave_ID = '"+search_leave_ID+"'";

    		// Create a prepared statement
    		Statement s2 = con2.createStatement();
    		ResultSet rs2 = s2.executeQuery(Search_sql);
    		System.out.println("SQL statement executed successfully...");
			System.out.println(Search_sql);
			EmpList = new ArrayList<Comparable>();
			
		   String Leave_ID 			= "";
	   	   String Emp_ID 			= "";
	   	   String Leave_Type 		= "";
	   	   String Reason		 	= "";
	   	   String Leave_From 		= "";
	   	   String Leave_To 			= "";
	   	   String Leave_Info 		= "";
	   	   String Leave_Approved 	= "";
	   	   String Leave_Comments 	= "";
		    while(rs2.next())
		    {
		    	Leave_ID 		= rs2.getString("Leave_ID");
			   Emp_ID 			= rs2.getString("Emp_ID");
	           Leave_Type 		= rs2.getString("Leave_Type");
	           Reason			= rs2.getString("Reason");
	           Leave_From 		= rs2.getString("Leave_From");
	           Leave_To 		= rs2.getString("Leave_To");
	           Leave_Info 		= rs2.getString("Leave_Info");
	           Leave_Approved 	= rs2.getString("Leave_Approved");
	           Leave_Comments 	= rs2.getString("Leave_Comments");
	           
	           // create a list to store the result set
	             EmpList.add(Leave_ID);
	    	     EmpList.add(Emp_ID);
				 EmpList.add(Leave_Type);
				 EmpList.add(Reason);												 
				 EmpList.add(Leave_From);
				 EmpList.add(Leave_To);
				 EmpList.add(Leave_Info);
				 EmpList.add(Leave_Approved);
				 EmpList.add(Leave_Comments);
		    }
			 if(EmpList.size() == 0){
		   			JOptionPane.showMessageDialog(null, "No records found for "+search_leave_ID);
		   			flag = 3;
		   		}
		   		else
		   		{

        // displaying next record in text fields

	   		tF_LeaveID.setText(Leave_ID);
	   		tF_EmpID.setText(Emp_ID);
	   		tF_LeaveType.setText(Leave_Type);
	   		tF_Reason.setText(Reason);
	   		tF_LeaveFrom.setText(Leave_From);
	   		tF_LeaveTo.setText(Leave_To);
	   		tA_Info.setText(Leave_Info);
	   		tF_LeaveStatus.setText(Leave_Approved);
	   		tA_Comment.setText(Leave_Comments);
	   			 flag = 0;
		   		}
				 
		
		}
			catch(SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
			catch(Exception ex1) {
				System.err.println("Exception: " + ex1.getMessage());
			}			
    	
    }
    
    
    @SuppressWarnings("rawtypes")
	public void searchLeaveRecord()
    {
    	try	{
    		String search_emp_ID = tF_searchEmpID.getText().trim();
    		String Search_sql = "SELECT * FROM emp_leave_table WHERE Emp_ID = '"+search_emp_ID+"'";

    		// Create a prepared statement
    		Statement s2 = con2.createStatement();
    		ResultSet rs2 = s2.executeQuery(Search_sql);
    		System.out.println("SQL statement executed successfully...");
			System.out.println(Search_sql);
			EmpList = new ArrayList<Comparable>();
			
		   String Leave_ID 			= "";
	   	   String Emp_ID 			= "";
	   	   String Leave_Type 		= "";
	   	   String Reason		 	= "";
	   	   String Leave_From 		= "";
	   	   String Leave_To 			= "";
	   	   String Leave_Info 		= "";
	   	   String Leave_Approved 	= "";
	   	   String Leave_Comments 	= "";
		    while(rs2.next())
		    {
		    	Leave_ID 		= rs2.getString("Leave_ID");
			   Emp_ID 			= rs2.getString("Emp_ID");
	           Leave_Type 		= rs2.getString("Leave_Type");
	           Reason			= rs2.getString("Reason");
	           Leave_From 		= rs2.getString("Leave_From");
	           Leave_To 		= rs2.getString("Leave_To");
	           Leave_Info 		= rs2.getString("Leave_Info");
	           Leave_Approved 	= rs2.getString("Leave_Approved");
	           Leave_Comments 	= rs2.getString("Leave_Comments");
	           
	           // create a list to store the result set
	             EmpList.add(Leave_ID);
	    	     EmpList.add(Emp_ID);
				 EmpList.add(Leave_Type);
				 EmpList.add(Reason);												 
				 EmpList.add(Leave_From);
				 EmpList.add(Leave_To);
				 EmpList.add(Leave_Info);
				 EmpList.add(Leave_Approved);
				 EmpList.add(Leave_Comments);
		    }
			 if(EmpList.size() == 0){
		   			//JOptionPane.showMessageDialog(null, "No records found for ["+search_emp_ID+"].");
		   			flag = 3;
		   		}
		   		else
		   		{

        // displaying next record in text fields

	   		tF_LeaveID.setText(Leave_ID);
	   		tF_EmpID.setText(Emp_ID);
	   		tF_LeaveType.setText(Leave_Type);
	   		tF_Reason.setText(Reason);
	   		tF_LeaveFrom.setText(Leave_From);
	   		tF_LeaveTo.setText(Leave_To);
	   		tA_Info.setText(Leave_Info);
	   		tF_LeaveStatus.setText(Leave_Approved);
	   		tA_Comment.setText(Leave_Comments);
	   			 flag = 0;
		   		}
				 
		
		}
			catch(SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
			catch(Exception ex1) {
				System.err.println("Exception: " + ex1.getMessage());
			}			
    	
    }
    
    
    @SuppressWarnings("rawtypes")
	public void reset() {
    	try
		{
    		String reset_sql = "SELECT * FROM emp_leave_table";

    		// Create a prepared statement
    		Statement s = con2.createStatement();
    		ResultSet rs = s.executeQuery(reset_sql);
    		System.out.println("SQL statement executed successfully...");
			System.out.println(reset_sql);
			EmpList = new ArrayList<Comparable>();
			
			 String Leave_ID 		= "";
	   	   String Emp_ID 			= "";
	   	   String Leave_Type 		= "";
	   	   String Reason		 	= "";
	   	   String Leave_From 		= "";
	   	   String Leave_To 			= "";
	   	   String Leave_Info 		= "";
	   	   String Leave_Approved 	= "";
	   	   String Leave_Comments 	= "";
		    while(rs.next())
		    {
		    	Leave_ID 		= rs.getString("Leave_ID");
			   Emp_ID 			= rs.getString("Emp_ID");
	           Leave_Type 		= rs.getString("Leave_Type");
	           Reason			= rs.getString("Reason");
	           Leave_From 		= rs.getString("Leave_From");
	           Leave_To 		= rs.getString("Leave_To");
	           Leave_Info 		= rs.getString("Leave_Info");
	           Leave_Approved 	= rs.getString("Leave_Approved");
	           Leave_Comments 	= rs.getString("Leave_Comments");
	           
	           // create a list to store the result set
	             EmpList.add(Leave_ID);
	    	     EmpList.add(Emp_ID);
				 EmpList.add(Leave_Type);
				 EmpList.add(Reason);												 
				 EmpList.add(Leave_From);
				 EmpList.add(Leave_To);
				 EmpList.add(Leave_Info);
				 EmpList.add(Leave_Approved);
				 EmpList.add(Leave_Comments);
		    }
			 if(EmpList.size() == 0){
		   			JOptionPane.showMessageDialog(null, "No records to be displayed.");
		   			
		   		}
		   		else
		   		{

        // displaying next record in text fields
		   			
	   		tF_LeaveID.setText("");
	   		tF_EmpID.setText("");
	   		tF_LeaveType.setText("");
	   		tF_Reason.setText("");
	   		tF_LeaveFrom.setText("");
	   		tF_LeaveTo.setText("");
	   		tA_Info.setText("");
	   		tF_LeaveStatus.setText("");
	   		tA_Comment.setText("");
		   		}
		}
		catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		catch(Exception ex1) {
			System.err.println("Exception: " + ex1.getMessage());
		}			
    }
    
    
}// end of Control_panel.java

