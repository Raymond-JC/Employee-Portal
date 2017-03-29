package com.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.event.*;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

import org.jdesktop.swingx.JXDatePicker;

import components.DocumentSizeFilter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainWindow {

	JFrame frmEmployeePortal;
	private JTextField tF_FirstName;
	private JTextField tF_SurName;
	private JTextField tF_AadharNo;
	private JTextField tF_DriversLicenceNo;
	private JTextField tF_Nationality;
	private JTextField tF_Skills;
	private JTextField tF_Reason;
	private JTextArea tF_AdditionalInfo;
	private JTextField tF_EmpID3;
	private JTextField tF_Website;
	private JTextField tF_PinCode;
	private JTextField tF_EmailID;
	private JTextField tF_LandlineNo;
	private JTextArea tF_Address;
	private JTextField tF_PhoneNo;
	private JTextField tF_EmpID4; 
	private JTextField tF_Job;
	private JTextField tF_PreviousJob;
	private JTextField tF_Experience;
	private JTextField tF_OfficialEmailID;
	private JTextField tF_WorkPhoneNo;
	private JTextField tF_EmpID1;
	private JTextField tF_EmpID2;
	private JPasswordField tF_CurrentPwd;
	private JPasswordField tF_NewPwd;
	private JPasswordField tF_ConfirmPwd;
	private JTextField tF_AltPhoneNo;
	private JTextField tF_State;
	private JTextField tF_WorkLandline;
	private JTextField tf_Emp_ID_Notifications;
	private DefaultStyledDocument doc;
	private DefaultStyledDocument doc2;
	private JLabel remainingLabel = new JLabel();
	private JLabel remainingLabel2 = new JLabel();
	
	private JTextField tF_EmpID_Payroll;
	private JTextArea tF_Basic;
	private JTextArea tF_PF;
	private JTextArea tF_ESI;
	private JTextArea tF_BankName;
	private JTextArea tF_Bank_Address;
	private JTextArea tF_Bank_Account_No;
	private JTextArea tF_Salary;
	private JTextArea textField_1;
	private JTextArea textField_2;
	private JTextArea textField_3;
	private JTextArea textField_4;
	private JTextArea textField_5;
	private JTextArea textField_6;
	private JTextArea textField;
	private JTextField tF_CompanyName;
	private JTextArea tA_AddressPreviousWork;
	private JTextField tF_CompanyContact;
	private JButton btnEmpDet;
	private JButton btnLeaveForm;

	
	@SuppressWarnings("rawtypes")
	private ArrayList EmpList;
	private Connection con;	
	
	private JTextField textField_7;
	private JTextField tF_Website_PA;
	private JTextField tF_EmailID_PA;
	private JTextField tF_LandlineNo_PA;
	private JTextField tF_PhoneNo_PA;
	private JTextField tF_Pin_Code_PA;
	private JTextField tF_AltPhoneNo_PA;
	private JTextField tF_State_PA;
	private JTextArea tA_Address_PA; 
	
    private String user_name = "root"; // User name
	private String pwd = "password1234"; // password required to connect to MySql server
	private String url = "jdbc:mysql://localhost:3306/empdet_db?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
  
			
	private JButton btnContactDetails;
	private JButton btnResidentialDetails;
	private JButton btnWorkContactDetails;
	private JButton btnWorkDet;
	private JButton btnPayrollDetails;
	private JButton btnMisc;
		
	private JCheckBox checkbox_SmsWorkPhone;
	private JCheckBox checkbox_SmsPersonalPhone;
	private JCheckBox chckbxSmsEmailwork;
	private JCheckBox checkbox_EmailPersonal;
	

	// Panels
	private JPanel WorkDet_Panel;
	private JPanel ContactDet_Panel;
	private JPanel Notifications_Panel;
	private JPanel EmpDet_Panel;
	private JPanel PayrollDet_Panel;
	private JPanel LeaveForm_Panel;
	private JPanel ResidentialDet_Panel;
	private JPanel WorkContactDet_Panel;
	private JPanel Perm_Address_Details_Panel;
			

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(null);
					window.frmEmployeePortal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param name 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MainWindow(String name) throws ClassNotFoundException, SQLException {
		String user = name;
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(final String user) throws SQLException, ClassNotFoundException {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frmEmployeePortal = new JFrame();
		frmEmployeePortal.getContentPane().setBackground(UIManager.getColor("inactiveCaption"));
		frmEmployeePortal.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/res/employee-screening.jpg")));
		frmEmployeePortal.setTitle("Employee Portal");
		// the following is custom to a 15?~17? inch screen
		frmEmployeePortal.setBounds( screenSize.width / 6, screenSize.height / 16, 1016, 680);
		frmEmployeePortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// -------- Retrieve Table data --------
		
		// String Emp_ID = "" , Password = "";
		String First_Name = "" , Sur_Name = "" , DOB = "" , Aadhar_Card_No = "" , Drivers_Licence_No = "" , Nationality = "" , Skills = "";
		String Website = "";
		String Address = "";
		String Pin_Code = "";
		String State = "", Email_ID_Personal = "";
		String Current_Job = "" , Department = "" , Date_Of_Joining = "" , Previous_Job = "" , Years_Of_Experience = "" , Email_ID_Work = "";
		String SMS_Work = "" , SMS_Personal = "" , Email_Work = "" , Email_Personal = "";
		String Company_Name = "" , Company_Address = "" , Company_Contact = "";
		String Bank_Name = "" , Bank_Address = "" , Bank_Account_No = "";
		int Phone_No = 0;
		int Landline_No  = 0, Alt_Phone_No  = 0, Phone_No_Work  = 0, Landline_No_Work = 0;
		int Basic_Pay = 0, PF = 0, ESI = 0, Salary = 0;
		String Website_PA = "" , Address_PA = "" , Pin_Code_PA = "" , State_PA = "" , EmailID_PA = "" ;
		int LandlineNo_PA = 0 , PhoneNo_PA = 0 , AltPhoneNo_PA = 0;  
		
		
		System.out.println("Connecting to a selected database...");
		Class.forName("com.mysql.jdbc.Driver");	

		con = DriverManager.getConnection(url , user_name , pwd);
		System.out.println("Connected database successfully...");
		//String sql = "SELECT First_Name FROM emp_table WHERE Emp_ID like '%"+user+"%'";
		String sql = "SELECT * FROM emp_table WHERE Emp_ID = '"+user+"'";
		System.out.println("SQL statement executed successfully...");
		System.out.println(sql);
		// Create a prepared statement
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);

		EmpList = new ArrayList();
		while(rs.next())
		{
			/*
			Emp_ID 				= rs.getString("Emp_ID");
            Password			= rs.getString("Password");
            */
			
    	    First_Name 			= rs.getString("First_Name");
    	    Sur_Name 			= rs.getString("Sur_Name");
    	    DOB 				= rs.getString("Date_Of_Birth");
    	    Aadhar_Card_No 		= rs.getString("Aadhar_Card_No");
    	    Drivers_Licence_No	= rs.getString("Drivers_Licence_No");
    	    Nationality 		= rs.getString("Nationality");
    	    Skills 				= rs.getString("Skills");
    	    
    	    Website				= rs.getString("Website");
    	    Address 			= rs.getString("Address");
    	    Pin_Code 			= rs.getString("Pin_Code");
    	    State 				= rs.getString("State");
    	    Email_ID_Personal 	= rs.getString("Email_ID_Personal");
    	    Landline_No			= rs.getInt("Landline_No");
            Phone_No 			= rs.getInt("Phone_No");
            Alt_Phone_No 		= rs.getInt("Alt_Phone_No");
            
            
    	    Current_Job			= rs.getString("Current_Job");
    	    Department			= rs.getString("Department");
    	    Date_Of_Joining 	= rs.getString("Date_Of_Joining");
    	    Previous_Job 		= rs.getString("Previous_Job");
    	    Years_Of_Experience = rs.getString("Years_Of_Experience");
    	    Company_Name 		= rs.getString("Company_Name"); 
    	    Company_Address 	= rs.getString("Company_Address"); 
    	    Company_Contact 	= rs.getString("Company_Contact");
    	    Email_ID_Work 		= rs.getString("Email_ID_Work");            
            Phone_No_Work 		= rs.getInt("Phone_No_Work");
            Landline_No_Work 	= rs.getInt("Landline_No_Work");
            
            Website_PA				= rs.getString("Website_PA");
    	    Address_PA 				= rs.getString("Address_PA");
    	    Pin_Code_PA 			= rs.getString("Pin_Code_PA");
    	    State_PA 				= rs.getString("State_PA");
    	    EmailID_PA 				= rs.getString("EmailID_PA");
            PhoneNo_PA 				= rs.getInt("PhoneNo_PA");            
            AltPhoneNo_PA	 		= rs.getInt("AltPhoneNo_PA");
            LandlineNo_PA			= rs.getInt("LandlineNo_PA");
            
            Basic_Pay	 		= rs.getInt("Basic_Pay");
            PF 					= rs.getInt("PF");
            ESI				 	= rs.getInt("ESI");
            Salary		 		= rs.getInt("Salary");
            Bank_Name 			= rs.getString("Bank_Name"); 
            Bank_Address 		= rs.getString("Bank_Address"); 
            Bank_Account_No		= rs.getString("Bank_Account_No");

    	    
    	    // notifications
    	    SMS_Work 		= rs.getString("SMS_Work");
    	    SMS_Personal 	= rs.getString("SMS_Personal");
    	    Email_Work 		= rs.getString("Email_Work");
    	    Email_Personal 	= rs.getString("Email_Personal");


			//Add the emp_table object to array list either individually or using constructor of EmpInfo
 
			 EmpList.add(First_Name);
			 EmpList.add(Sur_Name);
			 EmpList.add(DOB);
			 EmpList.add(Aadhar_Card_No);
			 EmpList.add(Drivers_Licence_No);
			 EmpList.add(Nationality);
			 EmpList.add(Skills);
			 
			 EmpList.add(Website);
			 EmpList.add(Address);
			 EmpList.add(Pin_Code);
			 EmpList.add(State);
			 EmpList.add(Email_ID_Personal);
			 EmpList.add(Landline_No);  
			 EmpList.add(Phone_No);
			 EmpList.add(Alt_Phone_No);
			 
			 EmpList.add(Website_PA);
			 EmpList.add(Address_PA);
			 EmpList.add(Pin_Code_PA);
			 EmpList.add(State_PA);
			 EmpList.add(EmailID_PA);
			 EmpList.add(LandlineNo_PA);  
			 EmpList.add(PhoneNo_PA);
			 EmpList.add(AltPhoneNo_PA);
			 
			 EmpList.add(Current_Job);
			 EmpList.add(Department);
			 EmpList.add(Date_Of_Joining);
			 EmpList.add(Previous_Job);
			 EmpList.add(Years_Of_Experience);
			 EmpList.add(Company_Name);
			 EmpList.add(Company_Address);
			 EmpList.add(Company_Contact);
			 EmpList.add(Email_ID_Work);
			 EmpList.add(Phone_No_Work);
			 EmpList.add(Landline_No_Work);
			 
			 EmpList.add(Basic_Pay);
			 EmpList.add(PF);  
			 EmpList.add(ESI);
			 EmpList.add(Salary);
			 EmpList.add(Bank_Name);
			 EmpList.add(Bank_Address);
			 EmpList.add(Bank_Account_No);				 
			 
			 EmpList.add(SMS_Work);
			 EmpList.add(SMS_Personal);
			 EmpList.add(Email_Work);
			 EmpList.add(Email_Personal);
			
		}
		// panel width and height 
		@SuppressWarnings("unused")
		int panel_width = frmEmployeePortal.getWidth();
		@SuppressWarnings("unused")
		int panel_height = frmEmployeePortal.getHeight();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{65, 115, 0, 71, 110, 110, 110, 165, 128, 61, 0};
		gridBagLayout.rowHeights = new int[]{23, 23, 0, 530, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmEmployeePortal.getContentPane().setLayout(gridBagLayout);
		
		
		
		
		
		// design
		final JLabel lblTime = new JLabel("Time : ");
		lblTime.setIcon(new ImageIcon(MainWindow.class.getResource("/res/ico_clock_small.jpg")));
		lblTime.setToolTipText("Time - IST");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.fill = GridBagConstraints.BOTH;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridwidth = 4;
		gbc_lblTime.gridx = 1;
		gbc_lblTime.gridy = 0;
		frmEmployeePortal.getContentPane().add(lblTime, gbc_lblTime);
		lblTime.setText(new Date().toString()); 
		
		JButton btn_LogOut = new JButton("Log Out");
		btn_LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window1  =  new Login();
                window1.c.setVisible(true); // c is the content pane from the login form
                frmEmployeePortal.setVisible(false);
			}
		});
		
		JLabel lbl_User = new JLabel("user");
		lbl_User.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl_User = new GridBagConstraints();
		gbc_lbl_User.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_User.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_User.gridwidth = 2;
		gbc_lbl_User.gridx = 7;
		gbc_lbl_User.gridy = 0;
		frmEmployeePortal.getContentPane().add(lbl_User, gbc_lbl_User);
		lbl_User.setText("Welcome " +user+ "!");
		btn_LogOut.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GridBagConstraints gbc_btn_LogOut = new GridBagConstraints();
		gbc_btn_LogOut.fill = GridBagConstraints.BOTH;
		gbc_btn_LogOut.insets = new Insets(0, 0, 5, 0);
		gbc_btn_LogOut.gridx = 9;
		gbc_btn_LogOut.gridy = 0;
		frmEmployeePortal.getContentPane().add(btn_LogOut, gbc_btn_LogOut);
		
		 btnLeaveForm = new JButton("Leave Form");
		 btnLeaveForm.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		LeaveForm_Panel.setVisible(true);
		 		EmpDet_Panel.setVisible(false);
		 		ContactDet_Panel.setVisible(false);
		 		ResidentialDet_Panel.setVisible(false);
		 		WorkContactDet_Panel.setVisible(false);	
		 		WorkDet_Panel.setVisible(false);
		 		Notifications_Panel.setVisible(false);
		 		PayrollDet_Panel.setVisible(false);
		 		frmEmployeePortal.repaint();
		 	}
		 });
		 
		  btnEmpDet = new JButton("Emp. Details");
		  btnEmpDet.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		EmpDet_Panel.setVisible(true);
		  		LeaveForm_Panel.setVisible(false);
		  		ContactDet_Panel.setVisible(false);
		  		ResidentialDet_Panel.setVisible(false);
		  		WorkContactDet_Panel.setVisible(false);	
		  		WorkDet_Panel.setVisible(false);
		  		Notifications_Panel.setVisible(false);
		  		PayrollDet_Panel.setVisible(false);
		  		frmEmployeePortal.repaint();
		  	}
		  });
		  GridBagConstraints gbc_btnEmpDet = new GridBagConstraints();
		  gbc_btnEmpDet.fill = GridBagConstraints.HORIZONTAL;
		  gbc_btnEmpDet.anchor = GridBagConstraints.NORTH;
		  gbc_btnEmpDet.insets = new Insets(0, 0, 5, 5);
		  gbc_btnEmpDet.gridx = 2;
		  gbc_btnEmpDet.gridy = 1;
		  frmEmployeePortal.getContentPane().add(btnEmpDet, gbc_btnEmpDet);
		 GridBagConstraints gbc_btnLeaveForm = new GridBagConstraints();
		 gbc_btnLeaveForm.anchor = GridBagConstraints.NORTH;
		 gbc_btnLeaveForm.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnLeaveForm.insets = new Insets(0, 0, 5, 5);
		 gbc_btnLeaveForm.gridx = 3;
		 gbc_btnLeaveForm.gridy = 1;
		 frmEmployeePortal.getContentPane().add(btnLeaveForm, gbc_btnLeaveForm);
		
		 btnContactDetails = new JButton("Contact Details");
		 btnContactDetails.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		ContactDet_Panel.setVisible(true);
		 		LeaveForm_Panel.setVisible(false);
		 		EmpDet_Panel.setVisible(false);
		 		WorkDet_Panel.setVisible(false);
		 		Notifications_Panel.setVisible(false);
		 		PayrollDet_Panel.setVisible(false);
		 	}
		 });
		 GridBagConstraints gbc_btnContactDetails = new GridBagConstraints();
		 gbc_btnContactDetails.anchor = GridBagConstraints.NORTH;
		 gbc_btnContactDetails.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnContactDetails.insets = new Insets(0, 0, 5, 5);
		 gbc_btnContactDetails.gridx = 4;
		 gbc_btnContactDetails.gridy = 1;
		 frmEmployeePortal.getContentPane().add(btnContactDetails, gbc_btnContactDetails);
		 
	
		
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

		doc2 = new DefaultStyledDocument();
        doc2.setDocumentFilter(new DocumentSizeFilter(300));
        doc2.addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e) { updateCount();}
            @Override
            public void insertUpdate(DocumentEvent e) { updateCount();}
            @Override
            public void removeUpdate(DocumentEvent e) { updateCount();}
        });

        updateCount_PreviousWorkAddress();
		 
		  btnWorkDet = new JButton("Work Details");
		  btnWorkDet.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		WorkDet_Panel.setVisible(true);
		  		LeaveForm_Panel.setVisible(false);
		  		EmpDet_Panel.setVisible(false);
		  		ContactDet_Panel.setVisible(false);
		  		ResidentialDet_Panel.setVisible(false);
		  		WorkContactDet_Panel.setVisible(false);	
		  		Notifications_Panel.setVisible(false);
		  		PayrollDet_Panel.setVisible(false);
		  		frmEmployeePortal.repaint();
		  	}
		  });
		  GridBagConstraints gbc_btnWorkDet = new GridBagConstraints();
		  gbc_btnWorkDet.anchor = GridBagConstraints.NORTH;
		  gbc_btnWorkDet.fill = GridBagConstraints.HORIZONTAL;
		  gbc_btnWorkDet.insets = new Insets(0, 0, 5, 5);
		  gbc_btnWorkDet.gridx = 5;
		  gbc_btnWorkDet.gridy = 1;
		  frmEmployeePortal.getContentPane().add(btnWorkDet, gbc_btnWorkDet);
		
		 btnPayrollDetails = new JButton("Payroll Details");
		 btnPayrollDetails.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		PayrollDet_Panel.setVisible(true);
		 		EmpDet_Panel.setVisible(false);
		 		LeaveForm_Panel.setVisible(false);
		 		ContactDet_Panel.setVisible(false);
		 		ResidentialDet_Panel.setVisible(false);
		 		WorkContactDet_Panel.setVisible(false);	
		 		WorkDet_Panel.setVisible(false);
		 		Notifications_Panel.setVisible(false);
		 		frmEmployeePortal.repaint();
		 	}
		 });
		 GridBagConstraints gbc_btnPayrollDetails = new GridBagConstraints();
		 gbc_btnPayrollDetails.anchor = GridBagConstraints.NORTH;
		 gbc_btnPayrollDetails.fill = GridBagConstraints.HORIZONTAL;
		 gbc_btnPayrollDetails.insets = new Insets(0, 0, 5, 5);
		 gbc_btnPayrollDetails.gridx = 6;
		 gbc_btnPayrollDetails.gridy = 1;
		 frmEmployeePortal.getContentPane().add(btnPayrollDetails, gbc_btnPayrollDetails);
		
		btnMisc = new JButton("Notifications & Settings");
		btnMisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notifications_Panel.setVisible(true);
				WorkDet_Panel.setVisible(false);
				LeaveForm_Panel.setVisible(false);
				EmpDet_Panel.setVisible(false);
				PayrollDet_Panel.setVisible(false);
				ContactDet_Panel.setVisible(false);
				ResidentialDet_Panel.setVisible(false);
				WorkContactDet_Panel.setVisible(false);	
				frmEmployeePortal.repaint();
			}
		});
		GridBagConstraints gbc_btnMisc = new GridBagConstraints();
		gbc_btnMisc.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisc.anchor = GridBagConstraints.NORTH;
		gbc_btnMisc.insets = new Insets(0, 0, 5, 5);
		gbc_btnMisc.gridx = 7;
		gbc_btnMisc.gridy = 1;
		frmEmployeePortal.getContentPane().add(btnMisc, gbc_btnMisc);
		
		/*
		Date Today = new Date();
		Calendar time  = Calendar.getInstance();
		time.set(Calendar.YEAR, 0);
		time.set(Calendar.MONTH, 0);
		time.set(Calendar.DAY_OF_MONTH, 0);
		Today = time.getTime();
		*/
		//long DOJ  = tF_DOJ.getText().toString();
		
		
		// <---------------->
		
		JPanel Outer_panel = new JPanel();
		Outer_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_Outer_panel = new GridBagConstraints();
		gbc_Outer_panel.insets = new Insets(0, 0, 5, 5);
		gbc_Outer_panel.fill = GridBagConstraints.BOTH;
		gbc_Outer_panel.gridwidth = 8;
		gbc_Outer_panel.gridx = 1;
		gbc_Outer_panel.gridy = 3;
		frmEmployeePortal.getContentPane().add(Outer_panel, gbc_Outer_panel);
		Outer_panel.setLayout(new CardLayout(0, 0));
		
		EmpDet_Panel = new JPanel();
		Outer_panel.add(EmpDet_Panel, "Employee_Details");
		GridBagLayout gbl_EmpDet_Panel = new GridBagLayout();
		gbl_EmpDet_Panel.columnWidths = new int[]{49, 102, 132, 37, 60, 0};
		gbl_EmpDet_Panel.rowHeights = new int[]{0, 14, 20, 20, 20, 20, 20, 20, 20, 50, 23, 0, 0};
		gbl_EmpDet_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_EmpDet_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		EmpDet_Panel.setLayout(gbl_EmpDet_Panel);
		
		JLabel label_4 = new JLabel("Personal Details");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridwidth = 2;
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 1;
		EmpDet_Panel.add(label_4, gbc_label_4);
		
		JLabel label_35 = new JLabel("Employee ID");
		GridBagConstraints gbc_label_35 = new GridBagConstraints();
		gbc_label_35.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_35.insets = new Insets(0, 0, 5, 5);
		gbc_label_35.gridx = 1;
		gbc_label_35.gridy = 2;
		EmpDet_Panel.add(label_35, gbc_label_35);
		
		tF_EmpID1 = new JTextField();
		tF_EmpID1.setEditable(false);
		tF_EmpID1.setColumns(10);
		tF_EmpID1.setText(user);
		GridBagConstraints gbc_tF_EmpID1 = new GridBagConstraints();
		gbc_tF_EmpID1.anchor = GridBagConstraints.NORTH;
		gbc_tF_EmpID1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_EmpID1.insets = new Insets(0, 0, 5, 0);
		gbc_tF_EmpID1.gridwidth = 3;
		gbc_tF_EmpID1.gridx = 2;
		gbc_tF_EmpID1.gridy = 2;
		EmpDet_Panel.add(tF_EmpID1, gbc_tF_EmpID1);
		
		JLabel label = new JLabel("First Name");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 3;
		EmpDet_Panel.add(label, gbc_label);
		
		tF_FirstName = new JTextField();
		tF_FirstName.setColumns(10);
		tF_FirstName.setText(First_Name);
		GridBagConstraints gbc_tF_FirstName = new GridBagConstraints();
		gbc_tF_FirstName.anchor = GridBagConstraints.NORTH;
		gbc_tF_FirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_FirstName.insets = new Insets(0, 0, 5, 0);
		gbc_tF_FirstName.gridwidth = 3;
		gbc_tF_FirstName.gridx = 2;
		gbc_tF_FirstName.gridy = 3;
		EmpDet_Panel.add(tF_FirstName, gbc_tF_FirstName);
		
		JLabel label_1 = new JLabel("Sur Name");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 4;
		EmpDet_Panel.add(label_1, gbc_label_1);
		
		tF_SurName = new JTextField();
		tF_SurName.setColumns(10);
		tF_SurName.setText(Sur_Name);
		GridBagConstraints gbc_tF_SurName = new GridBagConstraints();
		gbc_tF_SurName.anchor = GridBagConstraints.NORTH;
		gbc_tF_SurName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_SurName.insets = new Insets(0, 0, 5, 0);
		gbc_tF_SurName.gridwidth = 3;
		gbc_tF_SurName.gridx = 2;
		gbc_tF_SurName.gridy = 4;
		EmpDet_Panel.add(tF_SurName, gbc_tF_SurName);
		
		JLabel label_2 = new JLabel("Date of Birth");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 5;
		EmpDet_Panel.add(label_2, gbc_label_2);
		
		final JXDatePicker jxdp_DOB = new JXDatePicker();
		jxdp_DOB.setToolTipText("Date of Birth\r\n");
		jxdp_DOB.getEditor().setText(DOB);
		// -> cant use the following as it overwrites the DOB
		// jxdp_DOB.setDate(Calendar.getInstance().getTime());
		// -> this doesn't allow the editor to set the text as default format is dd/mm/yy
		// jxdp_DOB.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		GridBagConstraints gbc_jxdp_DOB = new GridBagConstraints();
		gbc_jxdp_DOB.fill = GridBagConstraints.BOTH;
		gbc_jxdp_DOB.insets = new Insets(0, 0, 5, 5);
		gbc_jxdp_DOB.gridx = 2;
		gbc_jxdp_DOB.gridy = 5;
		EmpDet_Panel.add(jxdp_DOB, gbc_jxdp_DOB);
		
		JLabel label_3 = new JLabel("dd/mm/yy");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 4;
		gbc_label_3.gridy = 5;
		EmpDet_Panel.add(label_3, gbc_label_3);
		
		JLabel label_5 = new JLabel("Aadhar Card No.");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 6;
		EmpDet_Panel.add(label_5, gbc_label_5);
		
		tF_AadharNo = new JTextField();
		tF_AadharNo.setColumns(10);
		tF_AadharNo.setText(Aadhar_Card_No);
		GridBagConstraints gbc_tF_AadharNo = new GridBagConstraints();
		gbc_tF_AadharNo.anchor = GridBagConstraints.NORTH;
		gbc_tF_AadharNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_AadharNo.insets = new Insets(0, 0, 5, 0);
		gbc_tF_AadharNo.gridwidth = 3;
		gbc_tF_AadharNo.gridx = 2;
		gbc_tF_AadharNo.gridy = 6;
		EmpDet_Panel.add(tF_AadharNo, gbc_tF_AadharNo);
		
		JLabel label_6 = new JLabel("Drivers Licence No.");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 7;
		EmpDet_Panel.add(label_6, gbc_label_6);
		
		tF_DriversLicenceNo = new JTextField();
		tF_DriversLicenceNo.setColumns(10);
		tF_DriversLicenceNo.setText(Drivers_Licence_No);
		GridBagConstraints gbc_tF_DriversLicenceNo = new GridBagConstraints();
		gbc_tF_DriversLicenceNo.anchor = GridBagConstraints.NORTH;
		gbc_tF_DriversLicenceNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_DriversLicenceNo.insets = new Insets(0, 0, 5, 0);
		gbc_tF_DriversLicenceNo.gridwidth = 3;
		gbc_tF_DriversLicenceNo.gridx = 2;
		gbc_tF_DriversLicenceNo.gridy = 7;
		EmpDet_Panel.add(tF_DriversLicenceNo, gbc_tF_DriversLicenceNo);
		
		JLabel label_7 = new JLabel("Nationality");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 1;
		gbc_label_7.gridy = 8;
		EmpDet_Panel.add(label_7, gbc_label_7);
		
		tF_Nationality = new JTextField();
		tF_Nationality.setColumns(10);
		tF_Nationality.setText(Nationality);
		GridBagConstraints gbc_tF_Nationality = new GridBagConstraints();
		gbc_tF_Nationality.anchor = GridBagConstraints.NORTH;
		gbc_tF_Nationality.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_Nationality.insets = new Insets(0, 0, 5, 0);
		gbc_tF_Nationality.gridwidth = 3;
		gbc_tF_Nationality.gridx = 2;
		gbc_tF_Nationality.gridy = 8;
		EmpDet_Panel.add(tF_Nationality, gbc_tF_Nationality);
		
		JButton btn_SaveEmpDet = new JButton("Save");
		btn_SaveEmpDet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String IQuery = "UPDATE `empdet_db`.`emp_table` SET `First_Name` = '"+tF_FirstName.getText()+"' ,  `Sur_Name`  =  '"+tF_SurName.getText()+"' , "
							+ " `Date_Of_Birth`  =  '"+jxdp_DOB.getEditor().getText()+"'  , `Aadhar_Card_No`  =  '"+tF_AadharNo.getText()+"'  "
							+ "  , `Drivers_Licence_No` ='"+tF_DriversLicenceNo.getText()+"' , `Nationality` ='"+tF_Nationality.getText()+"' , "
									+ "`Skills` ='"+tF_Skills.getText()+"' WHERE `Emp_ID` =  '"+user+"'  ";
					System.out.println(IQuery);//print on console
					System.out.println("Connecting to a selected database...");

					// Open a connection
					Class.forName("com.mysql.jdbc.Driver");
					// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
					// here schema is the database name and we use no data truncation so that we can store any size of data
		            con = DriverManager.getConnection(url , user_name , pwd);
					System.out.println("Connected database successfully...");
					((Connection)con).createStatement().execute(IQuery);//select the rows
					// define SMessage variable
					String SMessage = "Empployee Details updated for "+user;

                   // create dialog ox which is print message
					JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
					//close connection
					((java.sql.Connection)con).close();
					frmEmployeePortal.repaint();

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
		
		JLabel lblSkillstalents = new JLabel("Skills/Talents");
		GridBagConstraints gbc_lblSkillstalents = new GridBagConstraints();
		gbc_lblSkillstalents.anchor = GridBagConstraints.NORTH;
		gbc_lblSkillstalents.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSkillstalents.insets = new Insets(0, 0, 5, 5);
		gbc_lblSkillstalents.gridx = 1;
		gbc_lblSkillstalents.gridy = 9;
		EmpDet_Panel.add(lblSkillstalents, gbc_lblSkillstalents);
		
		tF_Skills = new JTextField();
		tF_Skills.setColumns(10);
		tF_Skills.setText(Skills);
		GridBagConstraints gbc_tF_Skills = new GridBagConstraints();
		gbc_tF_Skills.fill = GridBagConstraints.BOTH;
		gbc_tF_Skills.insets = new Insets(0, 0, 5, 0);
		gbc_tF_Skills.gridwidth = 3;
		gbc_tF_Skills.gridx = 2;
		gbc_tF_Skills.gridy = 9;
		EmpDet_Panel.add(tF_Skills, gbc_tF_Skills);
		GridBagConstraints gbc_btn_SaveEmpDet = new GridBagConstraints();
		gbc_btn_SaveEmpDet.anchor = GridBagConstraints.NORTH;
		gbc_btn_SaveEmpDet.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SaveEmpDet.gridwidth = 2;
		gbc_btn_SaveEmpDet.gridx = 1;
		gbc_btn_SaveEmpDet.gridy = 10;
		EmpDet_Panel.add(btn_SaveEmpDet, gbc_btn_SaveEmpDet);
		
		LeaveForm_Panel = new JPanel();
		Outer_panel.add(LeaveForm_Panel, "Leave_Application_Form");
		GridBagLayout gbl_LeaveForm_Panel = new GridBagLayout();
		gbl_LeaveForm_Panel.columnWidths = new int[]{32, 0, 102, 147, 72, 222, 0};
		gbl_LeaveForm_Panel.rowHeights = new int[]{0, 20, 20, 20, 20, 20, 103, 43, 0};
		gbl_LeaveForm_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_LeaveForm_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		LeaveForm_Panel.setLayout(gbl_LeaveForm_Panel);
		
		JLabel label_36 = new JLabel("Employee ID");
		GridBagConstraints gbc_label_36 = new GridBagConstraints();
		gbc_label_36.gridwidth = 2;
		gbc_label_36.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_36.insets = new Insets(0, 0, 5, 5);
		gbc_label_36.gridx = 1;
		gbc_label_36.gridy = 1;
		LeaveForm_Panel.add(label_36, gbc_label_36);
		
		tF_EmpID2 = new JTextField();
		tF_EmpID2.setEditable(false);
		tF_EmpID2.setColumns(10);
		tF_EmpID2.setText(user);
		GridBagConstraints gbc_tF_EmpID2 = new GridBagConstraints();
		gbc_tF_EmpID2.anchor = GridBagConstraints.NORTH;
		gbc_tF_EmpID2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_EmpID2.insets = new Insets(0, 0, 5, 5);
		gbc_tF_EmpID2.gridwidth = 2;
		gbc_tF_EmpID2.gridx = 3;
		gbc_tF_EmpID2.gridy = 1;
		LeaveForm_Panel.add(tF_EmpID2, gbc_tF_EmpID2);
		
		JLabel label_9 = new JLabel("Leave Type");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.gridwidth = 2;
		gbc_label_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 2;
		LeaveForm_Panel.add(label_9, gbc_label_9);
		
		final JComboBox cB_LeaveType = new JComboBox();
		cB_LeaveType.setEnabled(true);
		cB_LeaveType.setModel(new DefaultComboBoxModel(new String[] {"Other", "Annual Leave", "Sick Leave", "Bereavement", "Parental Leave", "Maternity Leave"}));
		GridBagConstraints gbc_cB_LeaveType = new GridBagConstraints();
		gbc_cB_LeaveType.anchor = GridBagConstraints.NORTH;
		gbc_cB_LeaveType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cB_LeaveType.insets = new Insets(0, 0, 5, 5);
		gbc_cB_LeaveType.gridwidth = 2;
		gbc_cB_LeaveType.gridx = 3;
		gbc_cB_LeaveType.gridy = 2;
		LeaveForm_Panel.add(cB_LeaveType, gbc_cB_LeaveType);
		
		JLabel label_10 = new JLabel("*If other specify down below");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.WEST;
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.gridx = 5;
		gbc_label_10.gridy = 2;
		LeaveForm_Panel.add(label_10, gbc_label_10);
		
		JLabel label_11 = new JLabel("Reason");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.gridwidth = 2;
		gbc_label_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 1;
		gbc_label_11.gridy = 3;
		LeaveForm_Panel.add(label_11, gbc_label_11);
		
		tF_Reason = new JTextField();
		tF_Reason.setColumns(10);
		GridBagConstraints gbc_tF_Reason = new GridBagConstraints();
		gbc_tF_Reason.anchor = GridBagConstraints.NORTH;
		gbc_tF_Reason.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_Reason.insets = new Insets(0, 0, 5, 5);
		gbc_tF_Reason.gridwidth = 2;
		gbc_tF_Reason.gridx = 3;
		gbc_tF_Reason.gridy = 3;
		LeaveForm_Panel.add(tF_Reason, gbc_tF_Reason);
		
		JLabel label_12 = new JLabel("Leave from");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.gridwidth = 2;
		gbc_label_12.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 1;
		gbc_label_12.gridy = 4;
		LeaveForm_Panel.add(label_12, gbc_label_12);
		
		final JXDatePicker jxdp_LeaveFrom = new JXDatePicker();
		GridBagConstraints gbc_jxdp_LeaveFrom = new GridBagConstraints();
		gbc_jxdp_LeaveFrom.fill = GridBagConstraints.BOTH;
		gbc_jxdp_LeaveFrom.insets = new Insets(0, 0, 5, 5);
		gbc_jxdp_LeaveFrom.gridx = 3;
		gbc_jxdp_LeaveFrom.gridy = 4;
		LeaveForm_Panel.add(jxdp_LeaveFrom, gbc_jxdp_LeaveFrom);
		
		JLabel label_13 = new JLabel("dd / mm / yy");
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_13.insets = new Insets(0, 0, 5, 5);
		gbc_label_13.gridx = 4;
		gbc_label_13.gridy = 4;
		LeaveForm_Panel.add(label_13, gbc_label_13);
		
		JLabel label_15 = new JLabel("To");
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.gridwidth = 2;
		gbc_label_15.anchor = GridBagConstraints.NORTH;
		gbc_label_15.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_15.insets = new Insets(0, 0, 5, 5);
		gbc_label_15.gridx = 1;
		gbc_label_15.gridy = 5;
		LeaveForm_Panel.add(label_15, gbc_label_15);
		
		final JXDatePicker jxdp_LeaveTo = new JXDatePicker();
		GridBagConstraints gbc_jxdp_LeaveTo = new GridBagConstraints();
		gbc_jxdp_LeaveTo.fill = GridBagConstraints.BOTH;
		gbc_jxdp_LeaveTo.insets = new Insets(0, 0, 5, 5);
		gbc_jxdp_LeaveTo.gridx = 3;
		gbc_jxdp_LeaveTo.gridy = 5;
		LeaveForm_Panel.add(jxdp_LeaveTo, gbc_jxdp_LeaveTo);
		
		JLabel label_16 = new JLabel("Additional Information");
		GridBagConstraints gbc_label_16 = new GridBagConstraints();
		gbc_label_16.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_16.insets = new Insets(0, 0, 5, 5);
		gbc_label_16.gridwidth = 2;
		gbc_label_16.gridx = 1;
		gbc_label_16.gridy = 6;
		LeaveForm_Panel.add(label_16, gbc_label_16);
		
		JButton btn_SubmitLeaveApp = new JButton("Submit");
		btn_SubmitLeaveApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String username = tF_EmpID1.getText();
					//String Leave_type = cB_LeaveType.getSelectedIndex();
					int Leave_value =cB_LeaveType.getSelectedIndex();
					String Leave_type= "";
					switch (Leave_value)
					{
					// start with zero as it follows similar counting as arrays
					case 0: Leave_type = "Other";
					        break;
					case 1: Leave_type = "Annual Leave";
			        		break;
					case 2: Leave_type = "Sick Leave";
			        		break;
					case 3: Leave_type = "Bereavement";
			        		break;
					case 4: Leave_type = "Parental Leave";
			        		break;
					case 5: Leave_type = "Maternity Leave";
	        				break;
	        		default: Leave_type = "Other";
    						break;
		        
					}

					// Duration duration = new Duration(spinner_LeaveFrom.getValue(),spinner_LeaveTo.getValue());
					if (tF_EmpID1.getText().equals("") || tF_Reason.getText().equals("") || tF_AdditionalInfo.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null,"One or more fields are empty","Error",JOptionPane.ERROR_MESSAGE);
					}
					else	
					{
						String IQuery = "INSERT INTO `empdet_db`.`emp_leave_table`(`Emp_ID`,`Leave_Type`,`Reason`,`Leave_From`,`Leave_To`,`Leave_Info`) "
								+ "VALUES('"+tF_EmpID1.getText()+"', '"+Leave_type+"','"+tF_Reason.getText()+"',"
										+ "'"+jxdp_LeaveFrom.getEditor().getText()+"','"+jxdp_LeaveTo.getEditor().getText()+"','"+tF_AdditionalInfo.getText()+"')";
						System.out.println(IQuery);//print on console
						System.out.println("Connecting to a selected database...");

						// Open a connection
						Class.forName("com.mysql.jdbc.Driver");
						// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
						// here schema is the database name and we use no data truncation so that we can store any size of data
						con = DriverManager.getConnection(url , user_name , pwd);
						System.out.println("Connected database successfully...");
						((Connection)con).createStatement().execute(IQuery);//select the rows
						// define SMessage variable
						String SMessage = "Leave submitted for "+username;

						// create dialog ox which is print message
						JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
						//close connection
						((java.sql.Connection)con).close();
						
						// clear()
						tF_Reason.setText("");
						tF_AdditionalInfo.setText("");
						jxdp_LeaveFrom.getEditor().setText("");
						jxdp_LeaveTo.getEditor().setText("");
						cB_LeaveType.resetKeyboardActions();
						

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
		
		tF_AdditionalInfo = new JTextArea();
		tF_AdditionalInfo.setLineWrap(true);
		tF_AdditionalInfo.setRows(1);
		tF_AdditionalInfo.setWrapStyleWord(true);
		tF_AdditionalInfo.setColumns(2);
		GridBagConstraints gbc_tF_AdditionalInfo = new GridBagConstraints();
		gbc_tF_AdditionalInfo.fill = GridBagConstraints.BOTH;
		gbc_tF_AdditionalInfo.insets = new Insets(0, 0, 5, 5);
		gbc_tF_AdditionalInfo.gridwidth = 2;
		gbc_tF_AdditionalInfo.gridx = 3;
		gbc_tF_AdditionalInfo.gridy = 6;
		LeaveForm_Panel.add(tF_AdditionalInfo, gbc_tF_AdditionalInfo);
		tF_AdditionalInfo.setDocument(doc);
		GridBagConstraints gbc_btn_SubmitLeaveApp = new GridBagConstraints();
		gbc_btn_SubmitLeaveApp.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btn_SubmitLeaveApp.insets = new Insets(0, 0, 0, 5);
		gbc_btn_SubmitLeaveApp.gridx = 3;
		gbc_btn_SubmitLeaveApp.gridy = 7;
		LeaveForm_Panel.add(btn_SubmitLeaveApp, gbc_btn_SubmitLeaveApp);
		GridBagConstraints gbc_remainingLabel = new GridBagConstraints();
		gbc_remainingLabel.insets = new Insets(0, 0, 0, 5);
		gbc_remainingLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_remainingLabel.gridx = 4;
		gbc_remainingLabel.gridy = 7;
		LeaveForm_Panel.add(remainingLabel, gbc_remainingLabel);
		
		ContactDet_Panel = new JPanel();
		Outer_panel.add(ContactDet_Panel, "Contact_Details");
		GridBagLayout gbl_ContactDet_Panel = new GridBagLayout();
		gbl_ContactDet_Panel.columnWidths = new int[]{97, 0, 0, 0, 0, 347, 0};
		gbl_ContactDet_Panel.rowHeights = new int[]{14, 23, 442, 0};
		gbl_ContactDet_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_ContactDet_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		ContactDet_Panel.setLayout(gbl_ContactDet_Panel);
		
		JLabel label_19 = new JLabel("Employee Contact Details");
		GridBagConstraints gbc_label_19 = new GridBagConstraints();
		gbc_label_19.gridwidth = 3;
		gbc_label_19.anchor = GridBagConstraints.NORTH;
		gbc_label_19.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_19.insets = new Insets(0, 0, 5, 5);
		gbc_label_19.gridx = 0;
		gbc_label_19.gridy = 0;
		ContactDet_Panel.add(label_19, gbc_label_19);
		
		btnWorkContactDetails = new JButton("Work Contact Details");
		btnWorkContactDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResidentialDet_Panel.setVisible(false);
				WorkContactDet_Panel.setVisible(true);
				Perm_Address_Details_Panel.setVisible(false);
				frmEmployeePortal.repaint();
			}
		});
		
		btnResidentialDetails = new JButton("Residential Details");
		btnResidentialDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResidentialDet_Panel.setVisible(true);
				WorkContactDet_Panel.setVisible(false);
				Perm_Address_Details_Panel.setVisible(false);
				frmEmployeePortal.repaint();
			}
		});
		GridBagConstraints gbc_btnResidentialDetails = new GridBagConstraints();
		gbc_btnResidentialDetails.anchor = GridBagConstraints.NORTH;
		gbc_btnResidentialDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnResidentialDetails.insets = new Insets(0, 0, 5, 5);
		gbc_btnResidentialDetails.gridx = 2;
		gbc_btnResidentialDetails.gridy = 1;
		ContactDet_Panel.add(btnResidentialDetails, gbc_btnResidentialDetails);
		GridBagConstraints gbc_btnWorkContactDetails = new GridBagConstraints();
		gbc_btnWorkContactDetails.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnWorkContactDetails.insets = new Insets(0, 0, 5, 5);
		gbc_btnWorkContactDetails.gridx = 3;
		gbc_btnWorkContactDetails.gridy = 1;
		ContactDet_Panel.add(btnWorkContactDetails, gbc_btnWorkContactDetails);
		
		JButton btnNewButton = new JButton("Permanent Address Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perm_Address_Details_Panel.setVisible(true);
				ResidentialDet_Panel.setVisible(false);
				WorkContactDet_Panel.setVisible(false);
				frmEmployeePortal.repaint();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 1;
		ContactDet_Panel.add(btnNewButton, gbc_btnNewButton);
		
		
		JPanel panel_cover = new JPanel();
		GridBagConstraints gbc_panel_cover = new GridBagConstraints();
		gbc_panel_cover.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_cover.anchor = GridBagConstraints.NORTH;
		gbc_panel_cover.gridwidth = 6;
		gbc_panel_cover.gridx = 0;
		gbc_panel_cover.gridy = 2;
		ContactDet_Panel.add(panel_cover, gbc_panel_cover);
		panel_cover.setLayout(new CardLayout(0, 0));
		
		
		ResidentialDet_Panel = new JPanel();
		panel_cover.add(ResidentialDet_Panel, "Residential_Details");
		ResidentialDet_Panel.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		GridBagLayout gbl_ResidentialDet_Panel = new GridBagLayout();
		gbl_ResidentialDet_Panel.columnWidths = new int[]{47, 150, 229, 120, 40, 229, 0};
		gbl_ResidentialDet_Panel.rowHeights = new int[]{14, 20, 29, 20, 20, 20, 20, 31, 0, 227, 0};
		gbl_ResidentialDet_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_ResidentialDet_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		ResidentialDet_Panel.setLayout(gbl_ResidentialDet_Panel);
		
		JLabel lblResidentialDetails = new JLabel("Residential Details");
		GridBagConstraints gbc_lblResidentialDetails = new GridBagConstraints();
		gbc_lblResidentialDetails.anchor = GridBagConstraints.NORTH;
		gbc_lblResidentialDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblResidentialDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblResidentialDetails.gridx = 1;
		gbc_lblResidentialDetails.gridy = 0;
		ResidentialDet_Panel.add(lblResidentialDetails, gbc_lblResidentialDetails);
		
		JLabel label_18 = new JLabel("Employee ID");
		GridBagConstraints gbc_label_18 = new GridBagConstraints();
		gbc_label_18.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_18.insets = new Insets(0, 0, 5, 5);
		gbc_label_18.gridx = 1;
		gbc_label_18.gridy = 1;
		ResidentialDet_Panel.add(label_18, gbc_label_18);
		
		tF_EmpID3 = new JTextField();
		tF_EmpID3.setEditable(false);
		tF_EmpID3.setColumns(10);
		tF_EmpID3.setText(user);
		GridBagConstraints gbc_tF_EmpID3 = new GridBagConstraints();
		gbc_tF_EmpID3.anchor = GridBagConstraints.NORTH;
		gbc_tF_EmpID3.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_EmpID3.insets = new Insets(0, 0, 5, 5);
		gbc_tF_EmpID3.gridx = 2;
		gbc_tF_EmpID3.gridy = 1;
		ResidentialDet_Panel.add(tF_EmpID3, gbc_tF_EmpID3);
		
		JLabel lblWebsite = new JLabel("Website");
		GridBagConstraints gbc_lblWebsite = new GridBagConstraints();
		gbc_lblWebsite.anchor = GridBagConstraints.NORTH;
		gbc_lblWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_lblWebsite.gridx = 3;
		gbc_lblWebsite.gridy = 1;
		ResidentialDet_Panel.add(lblWebsite, gbc_lblWebsite);
		
		tF_Website = new JTextField();
		tF_Website.setToolTipText("Personal website or web contact page (Facebook, twitter, etc).");
		tF_Website.setColumns(10);
		tF_Website.setText(Website);
		GridBagConstraints gbc_tF_Website = new GridBagConstraints();
		gbc_tF_Website.anchor = GridBagConstraints.NORTH;
		gbc_tF_Website.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_Website.insets = new Insets(0, 0, 5, 5);
		gbc_tF_Website.gridx = 4;
		gbc_tF_Website.gridy = 1;
		ResidentialDet_Panel.add(tF_Website, gbc_tF_Website);
		
		JLabel lblAddress_residential = new JLabel("Address");
		GridBagConstraints gbc_lblAddress_residential = new GridBagConstraints();
		gbc_lblAddress_residential.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddress_residential.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress_residential.gridx = 1;
		gbc_lblAddress_residential.gridy = 2;
		ResidentialDet_Panel.add(lblAddress_residential, gbc_lblAddress_residential);
		
		tF_Address = new JTextArea();
		tF_Address.setLineWrap(true);
		tF_Address.setWrapStyleWord(true);
		tF_Address.setColumns(10);
		tF_Address.setText(Address);
		tF_Address.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_Address.getText().length() >= 210 ) // limit Address to 210 characters
		            e.consume(); 
		    }  
		});
		
		GridBagConstraints gbc_tF_Address = new GridBagConstraints();
		gbc_tF_Address.fill = GridBagConstraints.BOTH;
		gbc_tF_Address.insets = new Insets(0, 0, 5, 5);
		gbc_tF_Address.gridheight = 3;
		gbc_tF_Address.gridx = 2;
		gbc_tF_Address.gridy = 2;
		ResidentialDet_Panel.add(tF_Address, gbc_tF_Address);
		
		JLabel label_22 = new JLabel("Email ID (personal)");
		GridBagConstraints gbc_label_22 = new GridBagConstraints();
		gbc_label_22.anchor = GridBagConstraints.WEST;
		gbc_label_22.insets = new Insets(0, 0, 5, 5);
		gbc_label_22.gridx = 3;
		gbc_label_22.gridy = 2;
		ResidentialDet_Panel.add(label_22, gbc_label_22);
		
		tF_EmailID = new JTextField();
		tF_EmailID.setColumns(10);
		tF_EmailID.setText(Email_Personal);
		GridBagConstraints gbc_tF_EmailID = new GridBagConstraints();
		gbc_tF_EmailID.anchor = GridBagConstraints.SOUTH;
		gbc_tF_EmailID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_EmailID.insets = new Insets(0, 0, 5, 5);
		gbc_tF_EmailID.gridx = 4;
		gbc_tF_EmailID.gridy = 2;
		ResidentialDet_Panel.add(tF_EmailID, gbc_tF_EmailID);
		
		JLabel label_23 = new JLabel("Landline no.");
		GridBagConstraints gbc_label_23 = new GridBagConstraints();
		gbc_label_23.anchor = GridBagConstraints.NORTH;
		gbc_label_23.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_23.insets = new Insets(0, 0, 5, 5);
		gbc_label_23.gridx = 3;
		gbc_label_23.gridy = 3;
		ResidentialDet_Panel.add(label_23, gbc_label_23);
		
		tF_LandlineNo = new JTextField();
		tF_LandlineNo.setColumns(10);
		tF_LandlineNo.setText(""+Landline_No);
		tF_LandlineNo.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_LandlineNo.getText().length() >= 15 ) // limit LandLine No. to 15 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_LandlineNo = new GridBagConstraints();
		gbc_tF_LandlineNo.anchor = GridBagConstraints.NORTH;
		gbc_tF_LandlineNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_LandlineNo.insets = new Insets(0, 0, 5, 5);
		gbc_tF_LandlineNo.gridx = 4;
		gbc_tF_LandlineNo.gridy = 3;
		ResidentialDet_Panel.add(tF_LandlineNo, gbc_tF_LandlineNo);
		
		JLabel label_24 = new JLabel("Phone no.");
		GridBagConstraints gbc_label_24 = new GridBagConstraints();
		gbc_label_24.anchor = GridBagConstraints.NORTH;
		gbc_label_24.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_24.insets = new Insets(0, 0, 5, 5);
		gbc_label_24.gridx = 3;
		gbc_label_24.gridy = 4;
		ResidentialDet_Panel.add(label_24, gbc_label_24);
		
		tF_PhoneNo = new JTextField();
		tF_PhoneNo.setColumns(10);
		tF_PhoneNo.setText(""+Phone_No);
		tF_PhoneNo.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_PhoneNo.getText().length() >= 10 ) // limit Phone No. to 10 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_PhoneNo = new GridBagConstraints();
		gbc_tF_PhoneNo.anchor = GridBagConstraints.NORTH;
		gbc_tF_PhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_PhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_tF_PhoneNo.gridx = 4;
		gbc_tF_PhoneNo.gridy = 4;
		ResidentialDet_Panel.add(tF_PhoneNo, gbc_tF_PhoneNo);
		
		JLabel label_21 = new JLabel("Pin Code / Zip Code");
		GridBagConstraints gbc_label_21 = new GridBagConstraints();
		gbc_label_21.anchor = GridBagConstraints.NORTH;
		gbc_label_21.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_21.insets = new Insets(0, 0, 5, 5);
		gbc_label_21.gridx = 1;
		gbc_label_21.gridy = 5;
		ResidentialDet_Panel.add(label_21, gbc_label_21);
		
		tF_PinCode = new JTextField();
		tF_PinCode.setColumns(10);
		tF_PinCode.setText(Pin_Code);
		tF_PinCode.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_PinCode.getText().length() >= 6 ) // limit Pin Code to 6 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_PinCode = new GridBagConstraints();
		gbc_tF_PinCode.anchor = GridBagConstraints.NORTH;
		gbc_tF_PinCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_PinCode.insets = new Insets(0, 0, 5, 5);
		gbc_tF_PinCode.gridx = 2;
		gbc_tF_PinCode.gridy = 5;
		ResidentialDet_Panel.add(tF_PinCode, gbc_tF_PinCode);
		
		JLabel lblAlternatePhoneNo = new JLabel("Alternate Phone no.");
		GridBagConstraints gbc_lblAlternatePhoneNo = new GridBagConstraints();
		gbc_lblAlternatePhoneNo.anchor = GridBagConstraints.NORTH;
		gbc_lblAlternatePhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAlternatePhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlternatePhoneNo.gridx = 3;
		gbc_lblAlternatePhoneNo.gridy = 5;
		ResidentialDet_Panel.add(lblAlternatePhoneNo, gbc_lblAlternatePhoneNo);
		
		tF_AltPhoneNo = new JTextField();
		tF_AltPhoneNo.setToolTipText("Leave blank if not necessary");
		tF_AltPhoneNo.setColumns(10);
		tF_AltPhoneNo.setText(""+Alt_Phone_No);
		tF_AltPhoneNo.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_AltPhoneNo.getText().length() >= 6 ) // limit Phone No. to 10 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_AltPhoneNo = new GridBagConstraints();
		gbc_tF_AltPhoneNo.anchor = GridBagConstraints.NORTH;
		gbc_tF_AltPhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_AltPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_tF_AltPhoneNo.gridx = 4;
		gbc_tF_AltPhoneNo.gridy = 5;
		ResidentialDet_Panel.add(tF_AltPhoneNo, gbc_tF_AltPhoneNo);
		
		JLabel lblState = new JLabel("State");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.NORTH;
		gbc_lblState.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 1;
		gbc_lblState.gridy = 6;
		ResidentialDet_Panel.add(lblState, gbc_lblState);
		
		tF_State = new JTextField();
		tF_State.setColumns(10);
		tF_State.setText(State);
		GridBagConstraints gbc_tF_State = new GridBagConstraints();
		gbc_tF_State.anchor = GridBagConstraints.NORTH;
		gbc_tF_State.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_State.insets = new Insets(0, 0, 5, 5);
		gbc_tF_State.gridx = 2;
		gbc_tF_State.gridy = 6;
		ResidentialDet_Panel.add(tF_State, gbc_tF_State);
		
		JButton btn_SaveContactDet = new JButton("Save");
		btn_SaveContactDet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String IQuery = "UPDATE `empdet_db`.`emp_table` SET `Website` = '"+tF_Website.getText()+"' , `Address` = '"+tF_Address.getText()+"' ,   `Pin_Code`  =  '"+tF_PinCode.getText()+"' ,  `State`  =  '"+tF_State.getText()+"'  "
							+ ", `Email_ID_Personal`  =  '"+tF_EmailID.getText()+"' , `Landline_No` ='"+tF_LandlineNo.getText()+"' , `Phone_No` ='"+tF_PhoneNo.getText()+"' , `Alt_Phone_No` ='"+tF_AltPhoneNo.getText()+"'"
									+ " WHERE `Emp_ID` =  '"+user+"'  ";
					System.out.println(IQuery);//print on console
					System.out.println("Connecting to a selected database...");

					// Open a connection
					Class.forName("com.mysql.jdbc.Driver");
					// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
					// here schema is the database name and we use no data truncation so that we can store any size of data
		            con = DriverManager.getConnection(url , user_name , pwd);
					System.out.println("Connected database successfully...");
					((Connection)con).createStatement().execute(IQuery);//select the rows
					// define SMessage variable
					String SMessage = "Employee Details updated for "+user;

                   // create dialog ox which is print message
					JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
					//close connection
					((java.sql.Connection)con).close();
					frmEmployeePortal.repaint();

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
		GridBagConstraints gbc_btn_SaveContactDet = new GridBagConstraints();
		gbc_btn_SaveContactDet.fill = GridBagConstraints.VERTICAL;
		gbc_btn_SaveContactDet.anchor = GridBagConstraints.WEST;
		gbc_btn_SaveContactDet.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SaveContactDet.gridx = 1;
		gbc_btn_SaveContactDet.gridy = 8;
		ResidentialDet_Panel.add(btn_SaveContactDet, gbc_btn_SaveContactDet);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Copy to Permanent Details");
		chckbxNewCheckBox.setToolTipText("Copy Residential Details to Permanent Details");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 3;
		gbc_chckbxNewCheckBox.gridy = 8;
		ResidentialDet_Panel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JButton btn_CopyDetails = new JButton("Copy Details");
		btn_CopyDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected())
				{
					try
					{
						String IQuery = "UPDATE `empdet_db`.`emp_table` SET `Website_PA` = '"+tF_Website.getText()+"' , `Address_PA` = '"+tF_Address.getText()+"' ,  `Pin_Code_PA`  =  '"+tF_PinCode.getText()+"' ,  `State_PA`  =  '"+tF_State.getText()+"'  "
								+ ", `EmailID_PA`  =  '"+tF_EmailID.getText()+"' , `LandlineNo_PA` ='"+tF_LandlineNo.getText()+"' , `PhoneNo_PA` ='"+tF_PhoneNo.getText()+"' , `AltPhoneNo_PA` ='"+tF_AltPhoneNo.getText()+"'"
										+ " WHERE `Emp_ID` =  '"+user+"'  ";
						System.out.println(IQuery);//print on console
						System.out.println("Connecting to a selected database...");

						// Open a connection
						Class.forName("com.mysql.jdbc.Driver");
						// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
						// here schema is the database name and we use no data truncation so that we can store any size of data
			            con = DriverManager.getConnection(url , user_name , pwd);
						System.out.println("Connected database successfully...");
						((Connection)con).createStatement().execute(IQuery);//select the rows
						// define SMessage variable
						String SMessage = "Permanent Address updated for "+user;

	                   // create dialog ox which is print message
						JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
						//close connection
						((java.sql.Connection)con).close();
						
						tA_Address_PA.setText(tF_Address.getText());
						tF_Pin_Code_PA.setText(tF_PinCode.getText());
						tF_State_PA.setText(tF_State.getText());
						tF_Website_PA.setText(tF_Website.getText());
						tF_EmailID_PA.setText(tF_EmailID.getText());
						tF_LandlineNo_PA.setText(""+tF_LandlineNo.getText());
						tF_PhoneNo_PA.setText(""+tF_PhoneNo.getText());
						tF_AltPhoneNo_PA.setText(""+tF_AltPhoneNo.getText());
						frmEmployeePortal.repaint();

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
				else
				{
					// define SMessage variable
					String SMessage = "Please tick the checkbox if you wish to use the residential address details as Permanent address details for ["+user+"] .";

                   // create dialog ox which is print message
					JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
				}
					
			}
		});
		GridBagConstraints gbc_btn_CopyDetails = new GridBagConstraints();
		gbc_btn_CopyDetails.insets = new Insets(0, 0, 5, 5);
		gbc_btn_CopyDetails.gridx = 4;
		gbc_btn_CopyDetails.gridy = 8;
		ResidentialDet_Panel.add(btn_CopyDetails, gbc_btn_CopyDetails);
		
		WorkContactDet_Panel = new JPanel();
		WorkContactDet_Panel.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		panel_cover.add(WorkContactDet_Panel, "Work_Contact_Details");
		GridBagLayout gbl_WorkContactDet_Panel = new GridBagLayout();
		gbl_WorkContactDet_Panel.columnWidths = new int[]{47, 150, 229, 120, 40, 229, 0};
		gbl_WorkContactDet_Panel.rowHeights = new int[]{14, 23, 20, 24, 29, 20, 20, 0, 0};
		gbl_WorkContactDet_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_WorkContactDet_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		WorkContactDet_Panel.setLayout(gbl_WorkContactDet_Panel);
		
		JLabel lblWorkContactDetails = new JLabel("Work Contact Details");
		GridBagConstraints gbc_lblWorkContactDetails = new GridBagConstraints();
		gbc_lblWorkContactDetails.anchor = GridBagConstraints.NORTH;
		gbc_lblWorkContactDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWorkContactDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblWorkContactDetails.gridx = 1;
		gbc_lblWorkContactDetails.gridy = 0;
		WorkContactDet_Panel.add(lblWorkContactDetails, gbc_lblWorkContactDetails);
		
		JLabel label_33 = new JLabel("Address");
		GridBagConstraints gbc_label_33 = new GridBagConstraints();
		gbc_label_33.anchor = GridBagConstraints.SOUTH;
		gbc_label_33.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_33.insets = new Insets(0, 0, 5, 5);
		gbc_label_33.gridx = 1;
		gbc_label_33.gridy = 1;
		WorkContactDet_Panel.add(label_33, gbc_label_33);
		
		JTextArea tA_Address = new JTextArea();
		tA_Address.setEditable(false);
		tA_Address.setWrapStyleWord(true);
		tA_Address.setText("450, Thudiyalur Road, Thudiyalur Road, Near Mosque, Saravanam Patti.");
		tA_Address.setLineWrap(true);
		tA_Address.setColumns(10);
		GridBagConstraints gbc_tA_Address = new GridBagConstraints();
		gbc_tA_Address.fill = GridBagConstraints.BOTH;
		gbc_tA_Address.insets = new Insets(0, 0, 5, 5);
		gbc_tA_Address.gridheight = 3;
		gbc_tA_Address.gridx = 2;
		gbc_tA_Address.gridy = 1;
		WorkContactDet_Panel.add(tA_Address, gbc_tA_Address);
		
		JLabel label_32 = new JLabel("Website");
		GridBagConstraints gbc_label_32 = new GridBagConstraints();
		gbc_label_32.anchor = GridBagConstraints.NORTH;
		gbc_label_32.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_32.insets = new Insets(0, 0, 5, 5);
		gbc_label_32.gridx = 3;
		gbc_label_32.gridy = 1;
		WorkContactDet_Panel.add(label_32, gbc_label_32);
		
		textField_1 = new JTextArea();
		textField_1.setEditable(false);
		textField_1.setToolTipText("");
		textField_1.setText("http://www.kgkite.ac.in/");
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.NORTH;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 1;
		WorkContactDet_Panel.add(textField_1, gbc_textField_1);
		
		JLabel lblEmailId = new JLabel("Email ID ");
		GridBagConstraints gbc_lblEmailId = new GridBagConstraints();
		gbc_lblEmailId.anchor = GridBagConstraints.NORTH;
		gbc_lblEmailId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmailId.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailId.gridx = 3;
		gbc_lblEmailId.gridy = 2;
		WorkContactDet_Panel.add(lblEmailId, gbc_lblEmailId);
		
		textField_4 = new JTextArea();
		textField_4.setEditable(false);
		textField_4.setText("rr@kgisl.com");
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.BOTH;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 5;
		gbc_textField_4.gridy = 2;
		WorkContactDet_Panel.add(textField_4, gbc_textField_4);
		
		JLabel label_40 = new JLabel("Landline no.");
		GridBagConstraints gbc_label_40 = new GridBagConstraints();
		gbc_label_40.anchor = GridBagConstraints.NORTH;
		gbc_label_40.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_40.insets = new Insets(0, 0, 5, 5);
		gbc_label_40.gridx = 3;
		gbc_label_40.gridy = 3;
		WorkContactDet_Panel.add(label_40, gbc_label_40);
		
		textField_5 = new JTextArea();
		textField_5.setEditable(false);
		textField_5.setText("0422 266 6187");
		textField_5.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.anchor = GridBagConstraints.NORTH;
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.gridx = 5;
		gbc_textField_5.gridy = 3;
		WorkContactDet_Panel.add(textField_5, gbc_textField_5);
		
		JLabel lblCity = new JLabel("City");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 1;
		gbc_lblCity.gridy = 4;
		WorkContactDet_Panel.add(lblCity, gbc_lblCity);
		
		textField_2 = new JTextArea();
		textField_2.setEditable(false);
		textField_2.setText("Coimbatore");
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.SOUTH;
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 4;
		WorkContactDet_Panel.add(textField_2, gbc_textField_2);
		
		JLabel lblPhoneNo = new JLabel("Phone no.");
		GridBagConstraints gbc_lblPhoneNo = new GridBagConstraints();
		gbc_lblPhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNo.gridx = 3;
		gbc_lblPhoneNo.gridy = 4;
		WorkContactDet_Panel.add(lblPhoneNo, gbc_lblPhoneNo);
		
		textField_6 = new JTextArea();
		textField_6.setEditable(false);
		textField_6.setText("0422 441 9999");
		textField_6.setColumns(10);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.anchor = GridBagConstraints.NORTH;
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.gridx = 5;
		gbc_textField_6.gridy = 4;
		WorkContactDet_Panel.add(textField_6, gbc_textField_6);
		
		JLabel lblPinCode = new JLabel("Pin Code / Zip Code");
		GridBagConstraints gbc_lblPinCode = new GridBagConstraints();
		gbc_lblPinCode.anchor = GridBagConstraints.NORTH;
		gbc_lblPinCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPinCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPinCode.gridx = 1;
		gbc_lblPinCode.gridy = 5;
		WorkContactDet_Panel.add(lblPinCode, gbc_lblPinCode);
		
		textField_3 = new JTextArea();
		textField_3.setEditable(false);
		textField_3.setText("641035");
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 5;
		WorkContactDet_Panel.add(textField_3, gbc_textField_3);
		
		JLabel label_43 = new JLabel("State");
		GridBagConstraints gbc_label_43 = new GridBagConstraints();
		gbc_label_43.anchor = GridBagConstraints.NORTH;
		gbc_label_43.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_43.insets = new Insets(0, 0, 5, 5);
		gbc_label_43.gridx = 1;
		gbc_label_43.gridy = 6;
		WorkContactDet_Panel.add(label_43, gbc_label_43);
		
		textField = new JTextArea();
		textField.setEditable(false);
		textField.setText("Tamil Nadu ");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 6;
		WorkContactDet_Panel.add(textField, gbc_textField);
		
		Perm_Address_Details_Panel = new JPanel();
		Perm_Address_Details_Panel.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		panel_cover.add(Perm_Address_Details_Panel, "name_437936429076");
		GridBagLayout gbl_Perm_Address_Details_Panel = new GridBagLayout();
		gbl_Perm_Address_Details_Panel.columnWidths = new int[]{47, 150, 0, 229, 120, 40, 229, 0};
		gbl_Perm_Address_Details_Panel.rowHeights = new int[]{14, 20, 29, 20, 20, 20, 20, 31, 0, 227, 0};
		gbl_Perm_Address_Details_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_Perm_Address_Details_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		Perm_Address_Details_Panel.setLayout(gbl_Perm_Address_Details_Panel);
		
		JLabel lblPermanentAddressDetails = new JLabel("Permanent Address Details");
		GridBagConstraints gbc_lblPermanentAddressDetails = new GridBagConstraints();
		gbc_lblPermanentAddressDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPermanentAddressDetails.anchor = GridBagConstraints.NORTH;
		gbc_lblPermanentAddressDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblPermanentAddressDetails.gridx = 1;
		gbc_lblPermanentAddressDetails.gridy = 0;
		Perm_Address_Details_Panel.add(lblPermanentAddressDetails, gbc_lblPermanentAddressDetails);
		
		JLabel label_31 = new JLabel("Employee ID");
		GridBagConstraints gbc_label_31 = new GridBagConstraints();
		gbc_label_31.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_31.insets = new Insets(0, 0, 5, 5);
		gbc_label_31.gridx = 1;
		gbc_label_31.gridy = 1;
		Perm_Address_Details_Panel.add(label_31, gbc_label_31);
		
		textField_7 = new JTextField();
		textField_7.setText("<dynamic>");
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.anchor = GridBagConstraints.NORTH;
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 3;
		gbc_textField_7.gridy = 1;
		Perm_Address_Details_Panel.add(textField_7, gbc_textField_7);
		
		JLabel label_37 = new JLabel("Website");
		GridBagConstraints gbc_label_37 = new GridBagConstraints();
		gbc_label_37.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_37.anchor = GridBagConstraints.NORTH;
		gbc_label_37.insets = new Insets(0, 0, 5, 5);
		gbc_label_37.gridx = 4;
		gbc_label_37.gridy = 1;
		Perm_Address_Details_Panel.add(label_37, gbc_label_37);
		
		tF_Website_PA = new JTextField();
		tF_Website_PA.setToolTipText("Personal website or web contact page (Facebook, twitter, etc).");
		tF_Website_PA.setText(Website_PA);
		tF_Website_PA.setColumns(10);
		GridBagConstraints gbc_tF_Website_PA = new GridBagConstraints();
		gbc_tF_Website_PA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_Website_PA.anchor = GridBagConstraints.NORTH;
		gbc_tF_Website_PA.insets = new Insets(0, 0, 5, 0);
		gbc_tF_Website_PA.gridx = 6;
		gbc_tF_Website_PA.gridy = 1;
		Perm_Address_Details_Panel.add(tF_Website_PA, gbc_tF_Website_PA);
		
		JLabel lblPermanentAddress = new JLabel("Permanent Address");
		GridBagConstraints gbc_lblPermanentAddress = new GridBagConstraints();
		gbc_lblPermanentAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPermanentAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblPermanentAddress.gridx = 1;
		gbc_lblPermanentAddress.gridy = 2;
		Perm_Address_Details_Panel.add(lblPermanentAddress, gbc_lblPermanentAddress);
		
		tA_Address_PA = new JTextArea();
		tA_Address_PA.setWrapStyleWord(true);
		tA_Address_PA.setText(Address_PA);
		tA_Address_PA.setLineWrap(true);
		tA_Address_PA.setColumns(10);
		tA_Address_PA.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tA_Address_PA.getText().length() >= 210 ) // limit Address to 210 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tA_Address_PA = new GridBagConstraints();
		gbc_tA_Address_PA.fill = GridBagConstraints.BOTH;
		gbc_tA_Address_PA.gridheight = 3;
		gbc_tA_Address_PA.insets = new Insets(0, 0, 5, 5);
		gbc_tA_Address_PA.gridx = 3;
		gbc_tA_Address_PA.gridy = 2;
		Perm_Address_Details_Panel.add(tA_Address_PA, gbc_tA_Address_PA);
		
		JLabel label_39 = new JLabel("Email ID (personal)");
		GridBagConstraints gbc_label_39 = new GridBagConstraints();
		gbc_label_39.anchor = GridBagConstraints.WEST;
		gbc_label_39.insets = new Insets(0, 0, 5, 5);
		gbc_label_39.gridx = 4;
		gbc_label_39.gridy = 2;
		Perm_Address_Details_Panel.add(label_39, gbc_label_39);
		
		tF_EmailID_PA = new JTextField();
		tF_EmailID_PA.setText(EmailID_PA);
		tF_EmailID_PA.setColumns(10);
		tF_EmailID_PA.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_EmailID_PA.getText().length() >= 85 ) // limit Email id to 85 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_EmailID_PA = new GridBagConstraints();
		gbc_tF_EmailID_PA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_EmailID_PA.anchor = GridBagConstraints.SOUTH;
		gbc_tF_EmailID_PA.insets = new Insets(0, 0, 5, 0);
		gbc_tF_EmailID_PA.gridx = 6;
		gbc_tF_EmailID_PA.gridy = 2;
		Perm_Address_Details_Panel.add(tF_EmailID_PA, gbc_tF_EmailID_PA);
		
		JLabel label_41 = new JLabel("Landline no.");
		GridBagConstraints gbc_label_41 = new GridBagConstraints();
		gbc_label_41.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_41.anchor = GridBagConstraints.NORTH;
		gbc_label_41.insets = new Insets(0, 0, 5, 5);
		gbc_label_41.gridx = 4;
		gbc_label_41.gridy = 3;
		Perm_Address_Details_Panel.add(label_41, gbc_label_41);
		
		tF_LandlineNo_PA = new JTextField();
		tF_LandlineNo_PA.setText(""+LandlineNo_PA);
		tF_LandlineNo_PA.setColumns(10);
		tF_LandlineNo_PA.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_LandlineNo_PA.getText().length() >= 15 ) // limit LandLine No to 15 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_LandlineNo_PA = new GridBagConstraints();
		gbc_tF_LandlineNo_PA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_LandlineNo_PA.anchor = GridBagConstraints.NORTH;
		gbc_tF_LandlineNo_PA.insets = new Insets(0, 0, 5, 0);
		gbc_tF_LandlineNo_PA.gridx = 6;
		gbc_tF_LandlineNo_PA.gridy = 3;
		Perm_Address_Details_Panel.add(tF_LandlineNo_PA, gbc_tF_LandlineNo_PA);
		
		JLabel label_42 = new JLabel("Phone no.");
		GridBagConstraints gbc_label_42 = new GridBagConstraints();
		gbc_label_42.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_42.anchor = GridBagConstraints.NORTH;
		gbc_label_42.insets = new Insets(0, 0, 5, 5);
		gbc_label_42.gridx = 4;
		gbc_label_42.gridy = 4;
		Perm_Address_Details_Panel.add(label_42, gbc_label_42);
		
		tF_PhoneNo_PA = new JTextField();
		tF_PhoneNo_PA.setText(""+PhoneNo_PA);
		tF_PhoneNo_PA.setColumns(10);
		tF_PhoneNo_PA.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_PhoneNo_PA.getText().length() >= 10 ) // limit Phone No to 10 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_PhoneNo_PA = new GridBagConstraints();
		gbc_tF_PhoneNo_PA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_PhoneNo_PA.anchor = GridBagConstraints.NORTH;
		gbc_tF_PhoneNo_PA.insets = new Insets(0, 0, 5, 0);
		gbc_tF_PhoneNo_PA.gridx = 6;
		gbc_tF_PhoneNo_PA.gridy = 4;
		Perm_Address_Details_Panel.add(tF_PhoneNo_PA, gbc_tF_PhoneNo_PA);
		
		JLabel label_44 = new JLabel("Pin Code / Zip Code");
		GridBagConstraints gbc_label_44 = new GridBagConstraints();
		gbc_label_44.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_44.anchor = GridBagConstraints.NORTH;
		gbc_label_44.insets = new Insets(0, 0, 5, 5);
		gbc_label_44.gridx = 1;
		gbc_label_44.gridy = 5;
		Perm_Address_Details_Panel.add(label_44, gbc_label_44);
		
		tF_Pin_Code_PA = new JTextField();
		tF_Pin_Code_PA.setText(""+Pin_Code_PA);
		tF_Pin_Code_PA.setColumns(10);
		tF_Pin_Code_PA.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_Pin_Code_PA.getText().length() >= 6 ) // limit Pin Code to 6 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_Pin_Code_PA = new GridBagConstraints();
		gbc_tF_Pin_Code_PA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_Pin_Code_PA.anchor = GridBagConstraints.NORTH;
		gbc_tF_Pin_Code_PA.insets = new Insets(0, 0, 5, 5);
		gbc_tF_Pin_Code_PA.gridx = 3;
		gbc_tF_Pin_Code_PA.gridy = 5;
		Perm_Address_Details_Panel.add(tF_Pin_Code_PA, gbc_tF_Pin_Code_PA);
		
		JLabel label_45 = new JLabel("Alternate Phone no.");
		GridBagConstraints gbc_label_45 = new GridBagConstraints();
		gbc_label_45.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_45.anchor = GridBagConstraints.NORTH;
		gbc_label_45.insets = new Insets(0, 0, 5, 5);
		gbc_label_45.gridx = 4;
		gbc_label_45.gridy = 5;
		Perm_Address_Details_Panel.add(label_45, gbc_label_45);
		
		tF_AltPhoneNo_PA = new JTextField();
		tF_AltPhoneNo_PA.setToolTipText("Leave blank if not necessary");
		tF_AltPhoneNo_PA.setText(""+AltPhoneNo_PA);
		tF_AltPhoneNo_PA.setColumns(10);
		tF_AltPhoneNo_PA.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (tF_AltPhoneNo_PA.getText().length() >= 10 ) // limit Alt Phone No to 10 characters
		            e.consume(); 
		    }  
		});
		GridBagConstraints gbc_tF_AltPhoneNo_PA = new GridBagConstraints();
		gbc_tF_AltPhoneNo_PA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_AltPhoneNo_PA.anchor = GridBagConstraints.NORTH;
		gbc_tF_AltPhoneNo_PA.insets = new Insets(0, 0, 5, 0);
		gbc_tF_AltPhoneNo_PA.gridx = 6;
		gbc_tF_AltPhoneNo_PA.gridy = 5;
		Perm_Address_Details_Panel.add(tF_AltPhoneNo_PA, gbc_tF_AltPhoneNo_PA);
		
		JLabel label_46 = new JLabel("State");
		GridBagConstraints gbc_label_46 = new GridBagConstraints();
		gbc_label_46.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_46.anchor = GridBagConstraints.NORTH;
		gbc_label_46.insets = new Insets(0, 0, 5, 5);
		gbc_label_46.gridx = 1;
		gbc_label_46.gridy = 6;
		Perm_Address_Details_Panel.add(label_46, gbc_label_46);
		
		tF_State_PA = new JTextField();
		tF_State_PA.setText(State_PA);
		tF_State_PA.setColumns(10);
		GridBagConstraints gbc_tF_State_PA = new GridBagConstraints();
		gbc_tF_State_PA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_State_PA.anchor = GridBagConstraints.NORTH;
		gbc_tF_State_PA.insets = new Insets(0, 0, 5, 5);
		gbc_tF_State_PA.gridx = 3;
		gbc_tF_State_PA.gridy = 6;
		Perm_Address_Details_Panel.add(tF_State_PA, gbc_tF_State_PA);
		
		JButton btn_SavePermAddressDetails = new JButton("Save");
		btn_SavePermAddressDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String IQuery = "UPDATE `empdet_db`.`emp_table` SET `Website_PA` = '"+tF_Website_PA.getText()+"' , `Address_PA` = '"+tA_Address_PA.getText()+"' ,  `Pin_Code_PA`  =  '"+tF_Pin_Code_PA.getText()+"' ,  `State_PA`  =  '"+tF_State_PA.getText()+"'  "
							+ ", `EmailID_PA`  =  '"+tF_EmailID_PA.getText()+"' , `LandlineNo_PA` ='"+tF_LandlineNo_PA.getText()+"' , `PhoneNo_PA` ='"+tF_PhoneNo_PA.getText()+"' , `AltPhoneNo_PA` ='"+tF_AltPhoneNo_PA.getText()+"'"
									+ " WHERE `Emp_ID` =  '"+user+"'  ";
					System.out.println(IQuery);//print on console
					System.out.println("Connecting to a selected database...");

					// Open a connection
					Class.forName("com.mysql.jdbc.Driver");
					// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
					// here schema is the database name and we use no data truncation so that we can store any size of data
		            con = DriverManager.getConnection(url , user_name , pwd);
					System.out.println("Connected database successfully...");
					((Connection)con).createStatement().execute(IQuery);//select the rows
					// define SMessage variable
					String SMessage = "Employee Details updated for "+user;

                   // create dialog ox which is print message
					JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
					//close connection
					((java.sql.Connection)con).close();
					frmEmployeePortal.repaint();

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
		GridBagConstraints gbc_btn_SavePermAddressDetails = new GridBagConstraints();
		gbc_btn_SavePermAddressDetails.fill = GridBagConstraints.VERTICAL;
		gbc_btn_SavePermAddressDetails.anchor = GridBagConstraints.WEST;
		gbc_btn_SavePermAddressDetails.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SavePermAddressDetails.gridx = 1;
		gbc_btn_SavePermAddressDetails.gridy = 8;
		Perm_Address_Details_Panel.add(btn_SavePermAddressDetails, gbc_btn_SavePermAddressDetails);
		
		WorkDet_Panel = new JPanel();
		Outer_panel.add(WorkDet_Panel, "Work_Details");
		GridBagLayout gbl_WorkDet_Panel = new GridBagLayout();
		gbl_WorkDet_Panel.columnWidths = new int[]{150, 229, 150, 127, 150, 0};
		gbl_WorkDet_Panel.rowHeights = new int[]{14, 20, 20, 20, 133, 25, 29, 20, 20, 45, 23, 0};
		gbl_WorkDet_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_WorkDet_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		WorkDet_Panel.setLayout(gbl_WorkDet_Panel);
		
		JLabel label_25 = new JLabel("Employee Work Details");
		GridBagConstraints gbc_label_25 = new GridBagConstraints();
		gbc_label_25.anchor = GridBagConstraints.NORTH;
		gbc_label_25.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_25.insets = new Insets(0, 0, 5, 5);
		gbc_label_25.gridx = 0;
		gbc_label_25.gridy = 0;
		WorkDet_Panel.add(label_25, gbc_label_25);
		
		JLabel label_26 = new JLabel("Employee ID");
		GridBagConstraints gbc_label_26 = new GridBagConstraints();
		gbc_label_26.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_26.insets = new Insets(0, 0, 5, 5);
		gbc_label_26.gridx = 0;
		gbc_label_26.gridy = 1;
		WorkDet_Panel.add(label_26, gbc_label_26);
		
		tF_EmpID4 = new JTextField();
		tF_EmpID4.setEditable(false);
		tF_EmpID4.setColumns(10);
		tF_EmpID4.setText(user);
		GridBagConstraints gbc_tF_EmpID4 = new GridBagConstraints();
		gbc_tF_EmpID4.anchor = GridBagConstraints.NORTH;
		gbc_tF_EmpID4.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_EmpID4.insets = new Insets(0, 0, 5, 5);
		gbc_tF_EmpID4.gridx = 1;
		gbc_tF_EmpID4.gridy = 1;
		WorkDet_Panel.add(tF_EmpID4, gbc_tF_EmpID4);
		
		JLabel label_30 = new JLabel("Previous Job");
		GridBagConstraints gbc_label_30 = new GridBagConstraints();
		gbc_label_30.anchor = GridBagConstraints.NORTH;
		gbc_label_30.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_30.insets = new Insets(0, 0, 5, 5);
		gbc_label_30.gridx = 2;
		gbc_label_30.gridy = 1;
		WorkDet_Panel.add(label_30, gbc_label_30);
		
		tF_PreviousJob = new JTextField();
		tF_PreviousJob.setColumns(10);
		tF_PreviousJob.setText(Previous_Job);
		GridBagConstraints gbc_tF_PreviousJob = new GridBagConstraints();
		gbc_tF_PreviousJob.anchor = GridBagConstraints.NORTH;
		gbc_tF_PreviousJob.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_PreviousJob.insets = new Insets(0, 0, 5, 0);
		gbc_tF_PreviousJob.gridwidth = 2;
		gbc_tF_PreviousJob.gridx = 3;
		gbc_tF_PreviousJob.gridy = 1;
		WorkDet_Panel.add(tF_PreviousJob, gbc_tF_PreviousJob);
		
		JLabel label_27 = new JLabel("Current Post (job)");
		GridBagConstraints gbc_label_27 = new GridBagConstraints();
		gbc_label_27.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_27.insets = new Insets(0, 0, 5, 5);
		gbc_label_27.gridx = 0;
		gbc_label_27.gridy = 2;
		WorkDet_Panel.add(label_27, gbc_label_27);
		
		tF_Job = new JTextField();
		tF_Job.setEnabled(false);
		tF_Job.setColumns(10);
		tF_Job.setText(Current_Job);
		GridBagConstraints gbc_tF_Job = new GridBagConstraints();
		gbc_tF_Job.anchor = GridBagConstraints.NORTH;
		gbc_tF_Job.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_Job.insets = new Insets(0, 0, 5, 5);
		gbc_tF_Job.gridx = 1;
		gbc_tF_Job.gridy = 2;
		WorkDet_Panel.add(tF_Job, gbc_tF_Job);
		
		JLabel lblYearsOfExperience = new JLabel("Years of experience");
		GridBagConstraints gbc_lblYearsOfExperience = new GridBagConstraints();
		gbc_lblYearsOfExperience.anchor = GridBagConstraints.NORTH;
		gbc_lblYearsOfExperience.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblYearsOfExperience.insets = new Insets(0, 0, 5, 5);
		gbc_lblYearsOfExperience.gridx = 2;
		gbc_lblYearsOfExperience.gridy = 2;
		WorkDet_Panel.add(lblYearsOfExperience, gbc_lblYearsOfExperience);
		
		tF_Experience = new JTextField();
		tF_Experience.setColumns(10);
		tF_Experience.setText(Years_Of_Experience);
		GridBagConstraints gbc_tF_Experience = new GridBagConstraints();
		gbc_tF_Experience.anchor = GridBagConstraints.NORTH;
		gbc_tF_Experience.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_Experience.insets = new Insets(0, 0, 5, 0);
		gbc_tF_Experience.gridwidth = 2;
		gbc_tF_Experience.gridx = 3;
		gbc_tF_Experience.gridy = 2;
		WorkDet_Panel.add(tF_Experience, gbc_tF_Experience);
		
		JLabel label_28 = new JLabel("Date of Joining");
		GridBagConstraints gbc_label_28 = new GridBagConstraints();
		gbc_label_28.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_28.insets = new Insets(0, 0, 5, 5);
		gbc_label_28.gridx = 0;
		gbc_label_28.gridy = 3;
		WorkDet_Panel.add(label_28, gbc_label_28);
		
		final JTextField tF_DOJ = new JTextField();
		tF_DOJ.setForeground(SystemColor.textText);
		tF_DOJ.setEnabled(false);
		tF_DOJ.setText(Date_Of_Joining);
		GridBagConstraints gbc_tF_DOJ = new GridBagConstraints();
		gbc_tF_DOJ.anchor = GridBagConstraints.NORTH;
		gbc_tF_DOJ.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_DOJ.insets = new Insets(0, 0, 5, 5);
		gbc_tF_DOJ.gridx = 1;
		gbc_tF_DOJ.gridy = 3;
		WorkDet_Panel.add(tF_DOJ, gbc_tF_DOJ);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
		gbc_lblCompanyName.anchor = GridBagConstraints.NORTH;
		gbc_lblCompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCompanyName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompanyName.gridx = 2;
		gbc_lblCompanyName.gridy = 3;
		WorkDet_Panel.add(lblCompanyName, gbc_lblCompanyName);
		
		tF_CompanyName = new JTextField();
		tF_CompanyName.setText("");
		tF_CompanyName.setColumns(10);
		tF_CompanyName.setText(Company_Name);
		GridBagConstraints gbc_tF_CompanyName = new GridBagConstraints();
		gbc_tF_CompanyName.anchor = GridBagConstraints.NORTH;
		gbc_tF_CompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_CompanyName.insets = new Insets(0, 0, 5, 0);
		gbc_tF_CompanyName.gridwidth = 2;
		gbc_tF_CompanyName.gridx = 3;
		gbc_tF_CompanyName.gridy = 3;
		WorkDet_Panel.add(tF_CompanyName, gbc_tF_CompanyName);
		
		JLabel label_17 = new JLabel("Address (Work place)");
		GridBagConstraints gbc_label_17 = new GridBagConstraints();
		gbc_label_17.anchor = GridBagConstraints.NORTH;
		gbc_label_17.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_17.insets = new Insets(0, 0, 5, 5);
		gbc_label_17.gridx = 0;
		gbc_label_17.gridy = 4;
		WorkDet_Panel.add(label_17, gbc_label_17);
		
		JTextArea txtrThudiyalurRoad = new JTextArea();
		txtrThudiyalurRoad.setEditable(false);
		txtrThudiyalurRoad.setWrapStyleWord(true);
		txtrThudiyalurRoad.setText("450, Thudiyalur Road, Thudiyalur Road, Near Mosque, Saravanam Patti. Coimbatore - 641035. \r\nTamil Nadu.");
		txtrThudiyalurRoad.setLineWrap(true);
		txtrThudiyalurRoad.setColumns(10);
		GridBagConstraints gbc_txtrThudiyalurRoad = new GridBagConstraints();
		gbc_txtrThudiyalurRoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtrThudiyalurRoad.insets = new Insets(0, 0, 5, 5);
		gbc_txtrThudiyalurRoad.gridheight = 2;
		gbc_txtrThudiyalurRoad.gridx = 1;
		gbc_txtrThudiyalurRoad.gridy = 4;
		WorkDet_Panel.add(txtrThudiyalurRoad, gbc_txtrThudiyalurRoad);
		
		tA_AddressPreviousWork = new JTextArea();
		tA_AddressPreviousWork.setWrapStyleWord(true);
		tA_AddressPreviousWork.setLineWrap(true);		
		tA_AddressPreviousWork.setColumns(10);
		tA_AddressPreviousWork.setDocument(doc2);
		tA_AddressPreviousWork.setText(Company_Address);
		
		GridBagConstraints gbc_tA_AddressPreviousWork = new GridBagConstraints();
		gbc_tA_AddressPreviousWork.fill = GridBagConstraints.BOTH;
		gbc_tA_AddressPreviousWork.insets = new Insets(0, 0, 5, 0);
		gbc_tA_AddressPreviousWork.gridwidth = 2;
		gbc_tA_AddressPreviousWork.gridx = 3;
		gbc_tA_AddressPreviousWork.gridy = 4;
		WorkDet_Panel.add(tA_AddressPreviousWork, gbc_tA_AddressPreviousWork);
		
		JLabel lblAddress = new JLabel("Previous Address Work place");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.NORTH;
		gbc_lblAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 2;
		gbc_lblAddress.gridy = 4;
		WorkDet_Panel.add(lblAddress, gbc_lblAddress);
		GridBagConstraints gbc_remainingLabel2 = new GridBagConstraints();
		gbc_remainingLabel2.fill = GridBagConstraints.BOTH;
		gbc_remainingLabel2.insets = new Insets(0, 0, 5, 0);
		gbc_remainingLabel2.gridx = 4;
		gbc_remainingLabel2.gridy = 5;
		WorkDet_Panel.add(remainingLabel2, gbc_remainingLabel2);
		
		JLabel lblOfficialEmailId = new JLabel("Official Email ID (work)");
		GridBagConstraints gbc_lblOfficialEmailId = new GridBagConstraints();
		gbc_lblOfficialEmailId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOfficialEmailId.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfficialEmailId.gridx = 0;
		gbc_lblOfficialEmailId.gridy = 6;
		WorkDet_Panel.add(lblOfficialEmailId, gbc_lblOfficialEmailId);
		
		tF_OfficialEmailID = new JTextField();
		tF_OfficialEmailID.setColumns(10);
		tF_OfficialEmailID.setText(Email_ID_Work);
		GridBagConstraints gbc_tF_OfficialEmailID = new GridBagConstraints();
		gbc_tF_OfficialEmailID.anchor = GridBagConstraints.SOUTH;
		gbc_tF_OfficialEmailID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_OfficialEmailID.insets = new Insets(0, 0, 5, 5);
		gbc_tF_OfficialEmailID.gridx = 1;
		gbc_tF_OfficialEmailID.gridy = 6;
		WorkDet_Panel.add(tF_OfficialEmailID, gbc_tF_OfficialEmailID);
		
		tF_CompanyContact = new JTextField();
		tF_CompanyContact.setText("");
		tF_CompanyContact.setColumns(10);
		tF_CompanyContact.setText(Company_Contact);
		GridBagConstraints gbc_tF_CompanyContact = new GridBagConstraints();
		gbc_tF_CompanyContact.anchor = GridBagConstraints.NORTH;
		gbc_tF_CompanyContact.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_CompanyContact.insets = new Insets(0, 0, 5, 0);
		gbc_tF_CompanyContact.gridwidth = 2;
		gbc_tF_CompanyContact.gridx = 3;
		gbc_tF_CompanyContact.gridy = 6;
		WorkDet_Panel.add(tF_CompanyContact, gbc_tF_CompanyContact);
		
		JLabel lblContactNo = new JLabel("Contact No. / Email");
		GridBagConstraints gbc_lblContactNo = new GridBagConstraints();
		gbc_lblContactNo.anchor = GridBagConstraints.NORTH;
		gbc_lblContactNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblContactNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblContactNo.gridx = 2;
		gbc_lblContactNo.gridy = 6;
		WorkDet_Panel.add(lblContactNo, gbc_lblContactNo);
		
		JLabel lblWorkPhoneNo = new JLabel("Work Phone no.");
		GridBagConstraints gbc_lblWorkPhoneNo = new GridBagConstraints();
		gbc_lblWorkPhoneNo.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWorkPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblWorkPhoneNo.gridx = 0;
		gbc_lblWorkPhoneNo.gridy = 7;
		WorkDet_Panel.add(lblWorkPhoneNo, gbc_lblWorkPhoneNo);
		
		tF_WorkPhoneNo = new JTextField();
		tF_WorkPhoneNo.setColumns(10);
		tF_WorkPhoneNo.setText(""+Phone_No_Work);
		GridBagConstraints gbc_tF_WorkPhoneNo = new GridBagConstraints();
		gbc_tF_WorkPhoneNo.anchor = GridBagConstraints.NORTH;
		gbc_tF_WorkPhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_WorkPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_tF_WorkPhoneNo.gridx = 1;
		gbc_tF_WorkPhoneNo.gridy = 7;
		WorkDet_Panel.add(tF_WorkPhoneNo, gbc_tF_WorkPhoneNo);
		
		JButton btn_SaveWorkDet = new JButton("Save");
		btn_SaveWorkDet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String IQuery = "UPDATE `empdet_db`.`emp_table` SET `Date_Of_Joining` = '"+tF_DOJ.getText()+"' , `Previous_Job` = '"+tF_PreviousJob.getText()+"' "
							+ ", `Years_Of_Experience`  =  '"+tF_Experience.getText()+"' ,  `Email_ID_Work`  =  '"+tF_OfficialEmailID.getText()+"'   "
							+ "  , `Phone_No_Work` ='"+tF_WorkPhoneNo.getText()+"' , `Landline_No_Work` ='"+tF_WorkLandline.getText()+"' "
							+ " , `Company_Name` ='"+tF_CompanyName.getText()+"' , `Company_Address` ='"+tA_AddressPreviousWork.getText()+"' ,"
									+ " `Company_Contact` ='"+tF_CompanyContact.getText()+"' WHERE `Emp_ID` =  '"+user+"'  ";
					System.out.println(IQuery);//print on console
					System.out.println("Connecting to a selected database...");

					// Open a connection
					Class.forName("com.mysql.jdbc.Driver");
					// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
					// here schema is the database name and we use no data truncation so that we can store any size of data
		            con = DriverManager.getConnection(url , user_name , pwd);
					System.out.println("Connected database successfully...");
					((Connection)con).createStatement().execute(IQuery);//select the rows
					// define SMessage variable
					String SMessage = "Employee Details updated for "+user;

                   // create dialog ox which is print message
					JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
					//close connection
					((java.sql.Connection)con).close();
					frmEmployeePortal.repaint();

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
		
		JLabel lblWorkLandline = new JLabel("Work Landline");
		GridBagConstraints gbc_lblWorkLandline = new GridBagConstraints();
		gbc_lblWorkLandline.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWorkLandline.insets = new Insets(0, 0, 5, 5);
		gbc_lblWorkLandline.gridx = 0;
		gbc_lblWorkLandline.gridy = 8;
		WorkDet_Panel.add(lblWorkLandline, gbc_lblWorkLandline);
		
		tF_WorkLandline = new JTextField();
		tF_WorkLandline.setColumns(10);
		tF_WorkLandline.setText(""+Landline_No_Work);
		GridBagConstraints gbc_tF_WorkLandline = new GridBagConstraints();
		gbc_tF_WorkLandline.anchor = GridBagConstraints.NORTH;
		gbc_tF_WorkLandline.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_WorkLandline.insets = new Insets(0, 0, 5, 5);
		gbc_tF_WorkLandline.gridx = 1;
		gbc_tF_WorkLandline.gridy = 8;
		WorkDet_Panel.add(tF_WorkLandline, gbc_tF_WorkLandline);
		GridBagConstraints gbc_btn_SaveWorkDet = new GridBagConstraints();
		gbc_btn_SaveWorkDet.anchor = GridBagConstraints.NORTHEAST;
		gbc_btn_SaveWorkDet.insets = new Insets(0, 0, 0, 5);
		gbc_btn_SaveWorkDet.gridx = 1;
		gbc_btn_SaveWorkDet.gridy = 10;
		WorkDet_Panel.add(btn_SaveWorkDet, gbc_btn_SaveWorkDet);
		
		PayrollDet_Panel = new JPanel();	
		Outer_panel.add(PayrollDet_Panel, "name_6156483555528");
		GridBagLayout gbl_PayrollDet_Panel = new GridBagLayout();
		gbl_PayrollDet_Panel.columnWidths = new int[]{186, 236, 0};
		gbl_PayrollDet_Panel.rowHeights = new int[]{14, 34, 20, 25, 20, 20, 20, 20, 20, 20, 20, 0};
		gbl_PayrollDet_Panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_PayrollDet_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		PayrollDet_Panel.setLayout(gbl_PayrollDet_Panel);
		
		JLabel lblNewLabel_1 = new JLabel("Payroll Details");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		PayrollDet_Panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel label_14 = new JLabel("Employee ID");
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_14.insets = new Insets(0, 0, 5, 5);
		gbc_label_14.gridx = 0;
		gbc_label_14.gridy = 2;
		PayrollDet_Panel.add(label_14, gbc_label_14);
		
		tF_EmpID_Payroll = new JTextField();
		tF_EmpID_Payroll.setText(user);
		tF_EmpID_Payroll.setEditable(false);
		tF_EmpID_Payroll.setColumns(10);
		GridBagConstraints gbc_tF_EmpID_Payroll = new GridBagConstraints();
		gbc_tF_EmpID_Payroll.anchor = GridBagConstraints.NORTH;
		gbc_tF_EmpID_Payroll.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_EmpID_Payroll.insets = new Insets(0, 0, 5, 0);
		gbc_tF_EmpID_Payroll.gridx = 1;
		gbc_tF_EmpID_Payroll.gridy = 2;
		PayrollDet_Panel.add(tF_EmpID_Payroll, gbc_tF_EmpID_Payroll);
		
		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.WEST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 3;
		PayrollDet_Panel.add(lblDepartment, gbc_lblDepartment);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(Department);
		textArea.setColumns(10);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		PayrollDet_Panel.add(textArea, gbc_textArea);
		
		JLabel lblBasicPay = new JLabel("Basic Pay");
		GridBagConstraints gbc_lblBasicPay = new GridBagConstraints();
		gbc_lblBasicPay.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBasicPay.insets = new Insets(0, 0, 5, 5);
		gbc_lblBasicPay.gridx = 0;
		gbc_lblBasicPay.gridy = 4;
		PayrollDet_Panel.add(lblBasicPay, gbc_lblBasicPay);
		
		tF_Basic = new JTextArea();
		tF_Basic.setEditable(false);
		tF_Basic.setText(""+Basic_Pay);
		tF_Basic.setColumns(10);
		GridBagConstraints gbc_tF_Basic = new GridBagConstraints();
		gbc_tF_Basic.fill = GridBagConstraints.BOTH;
		gbc_tF_Basic.insets = new Insets(0, 0, 5, 0);
		gbc_tF_Basic.gridx = 1;
		gbc_tF_Basic.gridy = 4;
		PayrollDet_Panel.add(tF_Basic, gbc_tF_Basic);
		
		JLabel lblPf = new JLabel("Provident Fund (PF)");
		GridBagConstraints gbc_lblPf = new GridBagConstraints();
		gbc_lblPf.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPf.insets = new Insets(0, 0, 5, 5);
		gbc_lblPf.gridx = 0;
		gbc_lblPf.gridy = 5;
		PayrollDet_Panel.add(lblPf, gbc_lblPf);
		
		tF_PF = new JTextArea();
		tF_PF.setEditable(false);
		tF_PF.setText(""+PF);
		tF_PF.setColumns(10);
		GridBagConstraints gbc_tF_PF = new GridBagConstraints();
		gbc_tF_PF.fill = GridBagConstraints.BOTH;
		gbc_tF_PF.insets = new Insets(0, 0, 5, 0);
		gbc_tF_PF.gridx = 1;
		gbc_tF_PF.gridy = 5;
		PayrollDet_Panel.add(tF_PF, gbc_tF_PF);
		
		JLabel lblEsi = new JLabel("Employees' State Insurance (ESI)");
		GridBagConstraints gbc_lblEsi = new GridBagConstraints();
		gbc_lblEsi.anchor = GridBagConstraints.NORTH;
		gbc_lblEsi.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEsi.insets = new Insets(0, 0, 5, 5);
		gbc_lblEsi.gridx = 0;
		gbc_lblEsi.gridy = 6;
		PayrollDet_Panel.add(lblEsi, gbc_lblEsi);
		
		tF_ESI = new JTextArea();
		tF_ESI.setEditable(false);
		tF_ESI.setText(""+ESI);
		tF_ESI.setColumns(10);
		GridBagConstraints gbc_tF_ESI = new GridBagConstraints();
		gbc_tF_ESI.fill = GridBagConstraints.BOTH;
		gbc_tF_ESI.insets = new Insets(0, 0, 5, 0);
		gbc_tF_ESI.gridx = 1;
		gbc_tF_ESI.gridy = 6;
		PayrollDet_Panel.add(tF_ESI, gbc_tF_ESI);
		
		JLabel lblSalary = new JLabel("Salary");
		GridBagConstraints gbc_lblSalary = new GridBagConstraints();
		gbc_lblSalary.anchor = GridBagConstraints.NORTH;
		gbc_lblSalary.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSalary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalary.gridx = 0;
		gbc_lblSalary.gridy = 7;
		PayrollDet_Panel.add(lblSalary, gbc_lblSalary);
		
		tF_Salary = new JTextArea();
		tF_Salary.setEditable(false);
		tF_Salary.setText(""+Salary);
		tF_Salary.setColumns(10);
		GridBagConstraints gbc_tF_Salary = new GridBagConstraints();
		gbc_tF_Salary.fill = GridBagConstraints.BOTH;
		gbc_tF_Salary.insets = new Insets(0, 0, 5, 0);
		gbc_tF_Salary.gridx = 1;
		gbc_tF_Salary.gridy = 7;
		PayrollDet_Panel.add(tF_Salary, gbc_tF_Salary);
		
		JLabel lblBankAccountDetails = new JLabel("Bank Name");
		GridBagConstraints gbc_lblBankAccountDetails = new GridBagConstraints();
		gbc_lblBankAccountDetails.anchor = GridBagConstraints.NORTH;
		gbc_lblBankAccountDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBankAccountDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblBankAccountDetails.gridx = 0;
		gbc_lblBankAccountDetails.gridy = 8;
		PayrollDet_Panel.add(lblBankAccountDetails, gbc_lblBankAccountDetails);
		
		tF_BankName = new JTextArea();
		tF_BankName.setEditable(false);
		tF_BankName.setText(Bank_Name);
		tF_BankName.setColumns(10);
		GridBagConstraints gbc_tF_BankName = new GridBagConstraints();
		gbc_tF_BankName.fill = GridBagConstraints.BOTH;
		gbc_tF_BankName.insets = new Insets(0, 0, 5, 0);
		gbc_tF_BankName.gridx = 1;
		gbc_tF_BankName.gridy = 8;
		PayrollDet_Panel.add(tF_BankName, gbc_tF_BankName);
		
		JLabel lblBankAddress = new JLabel("Bank Address");
		GridBagConstraints gbc_lblBankAddress = new GridBagConstraints();
		gbc_lblBankAddress.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBankAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblBankAddress.gridx = 0;
		gbc_lblBankAddress.gridy = 9;
		PayrollDet_Panel.add(lblBankAddress, gbc_lblBankAddress);
		
		tF_Bank_Address = new JTextArea();
		tF_Bank_Address.setEditable(false);
		tF_Bank_Address.setText(Bank_Address);
		tF_Bank_Address.setColumns(10);
		GridBagConstraints gbc_tF_Bank_Address = new GridBagConstraints();
		gbc_tF_Bank_Address.fill = GridBagConstraints.BOTH;
		gbc_tF_Bank_Address.insets = new Insets(0, 0, 5, 0);
		gbc_tF_Bank_Address.gridx = 1;
		gbc_tF_Bank_Address.gridy = 9;
		PayrollDet_Panel.add(tF_Bank_Address, gbc_tF_Bank_Address);
		
		JLabel lblBankAccountNo = new JLabel("Bank Account No.");
		GridBagConstraints gbc_lblBankAccountNo = new GridBagConstraints();
		gbc_lblBankAccountNo.anchor = GridBagConstraints.NORTH;
		gbc_lblBankAccountNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBankAccountNo.insets = new Insets(0, 0, 0, 5);
		gbc_lblBankAccountNo.gridx = 0;
		gbc_lblBankAccountNo.gridy = 10;
		PayrollDet_Panel.add(lblBankAccountNo, gbc_lblBankAccountNo);
		
		tF_Bank_Account_No = new JTextArea();
		tF_Bank_Account_No.setEditable(false);
		tF_Bank_Account_No.setText(Bank_Account_No);
		tF_Bank_Account_No.setColumns(10);
		GridBagConstraints gbc_tF_Bank_Account_No = new GridBagConstraints();
		gbc_tF_Bank_Account_No.fill = GridBagConstraints.BOTH;
		gbc_tF_Bank_Account_No.gridx = 1;
		gbc_tF_Bank_Account_No.gridy = 10;
		PayrollDet_Panel.add(tF_Bank_Account_No, gbc_tF_Bank_Account_No);
		
		
		Notifications_Panel = new JPanel();
		Outer_panel.add(Notifications_Panel, "Notification_Settings");
		GridBagLayout gbl_Notifications_Panel = new GridBagLayout();
		gbl_Notifications_Panel.columnWidths = new int[]{40, 102, 38, 5, 213, 0};
		gbl_Notifications_Panel.rowHeights = new int[]{32, 14, 20, 23, 23, 23, 42, 14, 32, 20, 20, 20, 32, 23, 0};
		gbl_Notifications_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_Notifications_Panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		Notifications_Panel.setLayout(gbl_Notifications_Panel);
		
		JLabel label_34 = new JLabel("Notification Settings");
		GridBagConstraints gbc_label_34 = new GridBagConstraints();
		gbc_label_34.anchor = GridBagConstraints.NORTH;
		gbc_label_34.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_34.insets = new Insets(0, 0, 5, 5);
		gbc_label_34.gridwidth = 2;
		gbc_label_34.gridx = 1;
		gbc_label_34.gridy = 1;
		Notifications_Panel.add(label_34, gbc_label_34);
		
		JLabel label_8 = new JLabel("Employee ID");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 1;
		gbc_label_8.gridy = 2;
		Notifications_Panel.add(label_8, gbc_label_8);
		
		tf_Emp_ID_Notifications = new JTextField();
		tf_Emp_ID_Notifications.setEditable(false);
		tf_Emp_ID_Notifications.setColumns(10);
		tf_Emp_ID_Notifications.setText(user);
		GridBagConstraints gbc_tf_Emp_ID_Notifications = new GridBagConstraints();
		gbc_tf_Emp_ID_Notifications.anchor = GridBagConstraints.NORTH;
		gbc_tf_Emp_ID_Notifications.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_Emp_ID_Notifications.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Emp_ID_Notifications.gridwidth = 3;
		gbc_tf_Emp_ID_Notifications.gridx = 2;
		gbc_tf_Emp_ID_Notifications.gridy = 2;
		Notifications_Panel.add(tf_Emp_ID_Notifications, gbc_tf_Emp_ID_Notifications);
		
		// notification check boxes
		checkbox_SmsWorkPhone = new JCheckBox("SMS (Work Phone No.)");
		GridBagConstraints gbc_checkbox_SmsWorkPhone = new GridBagConstraints();
		gbc_checkbox_SmsWorkPhone.anchor = GridBagConstraints.NORTH;
		gbc_checkbox_SmsWorkPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkbox_SmsWorkPhone.insets = new Insets(0, 0, 5, 5);
		gbc_checkbox_SmsWorkPhone.gridwidth = 3;
		gbc_checkbox_SmsWorkPhone.gridx = 1;
		gbc_checkbox_SmsWorkPhone.gridy = 3;
		Notifications_Panel.add(checkbox_SmsWorkPhone, gbc_checkbox_SmsWorkPhone);
		
		checkbox_SmsPersonalPhone = new JCheckBox("SMS (Personal Phone No.)");
		GridBagConstraints gbc_checkbox_SmsPersonalPhone = new GridBagConstraints();
		gbc_checkbox_SmsPersonalPhone.anchor = GridBagConstraints.NORTHWEST;
		gbc_checkbox_SmsPersonalPhone.insets = new Insets(0, 0, 5, 0);
		gbc_checkbox_SmsPersonalPhone.gridx = 4;
		gbc_checkbox_SmsPersonalPhone.gridy = 3;
		Notifications_Panel.add(checkbox_SmsPersonalPhone, gbc_checkbox_SmsPersonalPhone);
		
		chckbxSmsEmailwork = new JCheckBox("Email (Work)");
		GridBagConstraints gbc_chckbxSmsEmailwork = new GridBagConstraints();
		gbc_chckbxSmsEmailwork.anchor = GridBagConstraints.NORTH;
		gbc_chckbxSmsEmailwork.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxSmsEmailwork.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSmsEmailwork.gridwidth = 2;
		gbc_chckbxSmsEmailwork.gridx = 1;
		gbc_chckbxSmsEmailwork.gridy = 4;
		Notifications_Panel.add(chckbxSmsEmailwork, gbc_chckbxSmsEmailwork);
		
		checkbox_EmailPersonal = new JCheckBox("Email (Personal)");
		GridBagConstraints gbc_checkbox_EmailPersonal = new GridBagConstraints();
		gbc_checkbox_EmailPersonal.anchor = GridBagConstraints.NORTH;
		gbc_checkbox_EmailPersonal.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkbox_EmailPersonal.insets = new Insets(0, 0, 5, 0);
		gbc_checkbox_EmailPersonal.gridx = 4;
		gbc_checkbox_EmailPersonal.gridy = 4;
		Notifications_Panel.add(checkbox_EmailPersonal, gbc_checkbox_EmailPersonal);
		
		if (SMS_Work.contains("Yes"))
			checkbox_SmsWorkPhone.setSelected(true);
		if (Email_Work.contains("Yes"))
			chckbxSmsEmailwork.setSelected(true);
		if (Email_Personal.contains("Yes"))
			checkbox_EmailPersonal.setSelected(true);
		if (SMS_Personal.contains("Yes"))
			checkbox_SmsPersonalPhone.setSelected(true);
		
		// <---------------->
		
		JButton btn_SaveSettings = new JButton("Save Settings");
		btn_SaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try  //try block
                {
                //declare variables
					String username = "";
					String SMS_WorkPhone = "" , EmailWork = "", SMS_PersonalPhone = "", EmailPersonal = "" ;

					//get values using getText() method
					username = tf_Emp_ID_Notifications.getText().trim();
					SMS_WorkPhone = "No";
					EmailWork = "No";
					SMS_PersonalPhone = "No";
					EmailPersonal = "No";

					if (checkbox_SmsWorkPhone.isSelected())
						EmailWork = "Yes";
					if (chckbxSmsEmailwork.isSelected())
						EmailWork = "Yes";
					if (checkbox_SmsPersonalPhone.isSelected())
						SMS_PersonalPhone = "Yes";
					if (checkbox_EmailPersonal.isSelected())
						EmailPersonal = "Yes";
					
						String IQuery = "UPDATE `empdet_db`.`emp_table` SET `SMS_Work` = '"+SMS_WorkPhone+"' ,  `SMS_Personal`  =  '"+EmailWork+"' , "
								+ " `Email_Work`  =  '"+SMS_PersonalPhone+"'  , `Email_Personal`  =  '"+EmailPersonal+"'  WHERE `Emp_ID` =  '"+username+"'  ";
						System.out.println(IQuery);//print on console
						System.out.println("Connecting to a selected database...");

						// Open a connection
						Class.forName("com.mysql.jdbc.Driver");
						// jdbc:mysql://portName:portNumber/SCHEMA?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
						// here schema is the database name and we use no data truncation so that we can store any size of data
			            con = DriverManager.getConnection(url , user_name , pwd);
						System.out.println("Connected database successfully...");
						((Connection)con).createStatement().execute(IQuery);//select the rows
						// define SMessage variable
						String SMessage = "Notification settings updated for "+username;
	
                       // create dialog ox which is print message
						JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
						//close connection
						((java.sql.Connection)con).close();
						frmEmployeePortal.repaint();

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
		GridBagConstraints gbc_btn_SaveSettings = new GridBagConstraints();
		gbc_btn_SaveSettings.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_SaveSettings.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SaveSettings.gridwidth = 2;
		gbc_btn_SaveSettings.gridx = 1;
		gbc_btn_SaveSettings.gridy = 5;
		Notifications_Panel.add(btn_SaveSettings, gbc_btn_SaveSettings);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 7;
		Notifications_Panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblCurrentPassword = new JLabel("Current Password");
		GridBagConstraints gbc_lblCurrentPassword = new GridBagConstraints();
		gbc_lblCurrentPassword.anchor = GridBagConstraints.NORTH;
		gbc_lblCurrentPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCurrentPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentPassword.gridwidth = 2;
		gbc_lblCurrentPassword.gridx = 1;
		gbc_lblCurrentPassword.gridy = 9;
		Notifications_Panel.add(lblCurrentPassword, gbc_lblCurrentPassword);
		
		tF_CurrentPwd = new JPasswordField();
		tF_CurrentPwd.setColumns(10);
		GridBagConstraints gbc_tF_CurrentPwd = new GridBagConstraints();
		gbc_tF_CurrentPwd.anchor = GridBagConstraints.NORTH;
		gbc_tF_CurrentPwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_CurrentPwd.insets = new Insets(0, 0, 5, 0);
		gbc_tF_CurrentPwd.gridwidth = 2;
		gbc_tF_CurrentPwd.gridx = 3;
		gbc_tF_CurrentPwd.gridy = 9;
		Notifications_Panel.add(tF_CurrentPwd, gbc_tF_CurrentPwd);
		
		JLabel lblNewPassword = new JLabel("New password");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.anchor = GridBagConstraints.NORTH;
		gbc_lblNewPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPassword.gridx = 1;
		gbc_lblNewPassword.gridy = 10;
		Notifications_Panel.add(lblNewPassword, gbc_lblNewPassword);
		
		tF_NewPwd = new JPasswordField();
		tF_NewPwd.setColumns(10);
		GridBagConstraints gbc_tF_NewPwd = new GridBagConstraints();
		gbc_tF_NewPwd.anchor = GridBagConstraints.NORTH;
		gbc_tF_NewPwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_NewPwd.insets = new Insets(0, 0, 5, 0);
		gbc_tF_NewPwd.gridwidth = 2;
		gbc_tF_NewPwd.gridx = 3;
		gbc_tF_NewPwd.gridy = 10;
		Notifications_Panel.add(tF_NewPwd, gbc_tF_NewPwd);
		
		JButton btn_SavePwd = new JButton("Save");
		btn_SavePwd.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try  //try block
                {
                //declare variables
					String username = "";
					String password;

					//get values using getText() method
					username = user;
					password = tF_NewPwd.getText().trim();

                // check condition it field equals to blank throw error message
					if (username.equals("")|| password.equals(""))
					{
						JOptionPane.showMessageDialog(null," UserName or Password field is blank","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(Arrays.equals(tF_NewPwd.getPassword(), tF_ConfirmPwd.getPassword())) //else if insert query is run properly
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
			            con = DriverManager.getConnection(url , user_name , pwd);
						System.out.println("Connected database successfully...");
						((Connection)con).createStatement().execute(IQuery);//select the rows
						// define SMessage variable
						String SMessage = "Record added for "+username;
	
                       // create dialog ox which is print message
						JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
						//close connection
						((java.sql.Connection)con).close();
						frmEmployeePortal.repaint();
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
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
		gbc_lblConfirmPassword.anchor = GridBagConstraints.NORTH;
		gbc_lblConfirmPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmPassword.gridx = 1;
		gbc_lblConfirmPassword.gridy = 11;
		Notifications_Panel.add(lblConfirmPassword, gbc_lblConfirmPassword);
		
		tF_ConfirmPwd = new JPasswordField();
		tF_ConfirmPwd.setColumns(10);
		GridBagConstraints gbc_tF_ConfirmPwd = new GridBagConstraints();
		gbc_tF_ConfirmPwd.anchor = GridBagConstraints.NORTH;
		gbc_tF_ConfirmPwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_tF_ConfirmPwd.insets = new Insets(0, 0, 5, 0);
		gbc_tF_ConfirmPwd.gridwidth = 2;
		gbc_tF_ConfirmPwd.gridx = 3;
		gbc_tF_ConfirmPwd.gridy = 11;
		Notifications_Panel.add(tF_ConfirmPwd, gbc_tF_ConfirmPwd);
		GridBagConstraints gbc_btn_SavePwd = new GridBagConstraints();
		gbc_btn_SavePwd.anchor = GridBagConstraints.NORTH;
		gbc_btn_SavePwd.gridwidth = 4;
		gbc_btn_SavePwd.gridx = 1;
		gbc_btn_SavePwd.gridy = 13;
		Notifications_Panel.add(btn_SavePwd, gbc_btn_SavePwd);
		
		JPanel ToDo_panel = new JPanel();
		ToDo_panel.setVisible(false);
		Outer_panel.add(ToDo_panel, "name_4449077568578");
		ToDo_panel.setLayout(null);
		
		JTextArea txtr_ToDo = new JTextArea();
		txtr_ToDo.setWrapStyleWord(true);
		txtr_ToDo.setText("Complete the reports and link them to the admin control panel\r\n(remove redundant code)\r\n\r\nImprove or add a better save / export option to save the tables to pdf, doc , etc.\r\n \r\nadd a smtp and/ or sms protocol for notifications\r\n");
		txtr_ToDo.setBounds(41, 32, 764, 314);
		ToDo_panel.add(txtr_ToDo);
		
		JLabel lblToDO = new JLabel("Things to do");
		lblToDO.setBounds(41, 7, 299, 14);
		ToDo_panel.add(lblToDO);
			
			// -> This would be static
			// LocalDateTime ldt = LocalDateTime.now();
			// lblTime.setText(ldt.toString());
			
			ActionListener updateClockAction = new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
				      // Assumes clock is a custom component
				      // yourClock.setTime(System.currentTimeMillis()); 
				      // OR
				      // Assumes clock is a JLabel
				      lblTime.setText(new Date().toString()); 
				    }
				};
				// 1000 = 1 second, i.e. 1000 milliseconds
			Timer t = new Timer(1000, updateClockAction);
			t.start();
			
			

	}
	
	// <-- update count of characters for Textboxes -->

	  public void updateCount()
      {
		  remainingLabel.setText((300 -doc.getLength()) + " characters remaining");
      }
	  
	  public void updateCount_PreviousWorkAddress()
      {
		  remainingLabel2.setText((300 -doc2.getLength()) + " characters remaining");
      }
	  
	
}

