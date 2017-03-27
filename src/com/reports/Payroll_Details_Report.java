package com.reports;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Toolkit;



public class Payroll_Details_Report {

	public JFrame frmPayrollDetails_Report;
	
	String Emp_ID = "";
    String Department = "";
    String Bank_Name = "";
    String Bank_Address = "";
    String Bank_Account_No = "";
    
    int Basic_Pay 		 = 0;
    int PF 				 = 0;
    int ESI 			 = 0;
    int Salary			 = 0;
	
	Connection con;	    
    String url 	= "jdbc:mysql://localhost:3306/empdet_db?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
	String user ="root";
	String pwd 	= "password1234"; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payroll_Details_Report window = new Payroll_Details_Report();
					window.frmPayrollDetails_Report.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Payroll_Details_Report() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPayrollDetails_Report = new JFrame();
		frmPayrollDetails_Report.setIconImage(Toolkit.getDefaultToolkit().getImage(Payroll_Details_Report.class.getResource("/res/cloud.jpg")));
		frmPayrollDetails_Report.setTitle("Payroll Details ");
		frmPayrollDetails_Report.setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		 frmPayrollDetails_Report.setJMenuBar(menuBar);
		 
		
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
		exit.addActionListener(new exitApp());
		exit.setToolTipText("Close the current window.");
		mnNewMenu.add(exit);
		
		 PreparedStatement pst;
     	 JTable table;
     	 
     	 
     	 
     	String[] columnNames = {"Emp ID","Department","Basic Pay", "PF", "ESI", "Salary", "Bank Name", "Bank Address", "Bank Account No"};
     	

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
         
        
       


         try {
        	 con = DriverManager.getConnection(url,user,pwd);
         	
             pst = con.prepareStatement("SELECT * FROM emp_table ");
             ResultSet rs = pst.executeQuery();
             while(rs.next())
  			{

            	 Emp_ID 			= rs.getString("Emp_ID");
          	     Department			= rs.getString("Department");
                 Basic_Pay 			= rs.getInt("Basic_Pay");
                 PF					= rs.getInt("PF");
                 ESI 				= rs.getInt("ESI");
                 Salary 			= rs.getInt("Salary");
                 Bank_Name			= rs.getString("Bank_Name");
                 Bank_Address		= rs.getString("Bank_Address");
                 Bank_Account_No	= rs.getString("Bank_Account_No");
         	    
                 model.addRow(new Object[]{Emp_ID, Department, Basic_Pay, PF, ESI, Salary, Bank_Name, Bank_Address, Bank_Account_No});  
                 }
             rs.close();
             pst.close();
         } 
         catch (Exception ex) 
         {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
         
         
         frmPayrollDetails_Report.getContentPane().add(scroll);
         frmPayrollDetails_Report.setVisible(true);
         frmPayrollDetails_Report.setSize(1000, 500);
		
	}
	
	// Save()
		final class Save implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {
				 JFileChooser fileChooser = new JFileChooser();
				 fileChooser.setDialogTitle("Specify a file to save"); 
				 fileChooser.setSelectedFile(new File(""));
				 
				 FileNameExtensionFilter xlsxFilter = new FileNameExtensionFilter("xlsx files (*.xlsx)","xlsx");
			        // add filters
				 fileChooser.addChoosableFileFilter(xlsxFilter);
				 fileChooser.setFileFilter(xlsxFilter);
			        
				 int userSelection = fileChooser.showSaveDialog(frmPayrollDetails_Report);

				 if (userSelection == JFileChooser.APPROVE_OPTION) {
					 File fileToSave = fileChooser.getSelectedFile();	
				     
				     XSSFWorkbook workbook = new XSSFWorkbook();

				     //Create a blank sheet
				     XSSFSheet sheet = workbook.createSheet("Employee Data");

				     //This data needs to be written (Object[])
				     Map<String, Object[]> data = new TreeMap<String, Object[]>();
				     data.put("1", new Object[]{"Emp ID","Department","Basic Pay", "PF", "ESI", "Salary", "Bank Name", "Bank Address", "Bank Account No"});
				     int i = 2;
				     String num = "";
				     try {
			        	 con = DriverManager.getConnection(url,user,pwd);
			         	
			             PreparedStatement pst = con.prepareStatement("SELECT * FROM emp_table ");
			             ResultSet rs = pst.executeQuery();
			             while(rs.next())
			  			{

			            	 Emp_ID 			= rs.getString("Emp_ID");
			          	     Department			= rs.getString("Department");
			                 Basic_Pay 			= rs.getInt("Basic_Pay");
			                 PF					= rs.getInt("PF");
			                 ESI 				= rs.getInt("ESI");
			                 Salary 			= rs.getInt("Salary");
			                 Bank_Name			= rs.getString("Bank_Name");
			                 Bank_Address		= rs.getString("Bank_Address");
			                 Bank_Account_No	= rs.getString("Bank_Account_No");
			         	    num = Integer.toString(i);
			         	    data.put(""+num,new Object[]{Emp_ID, Department, Basic_Pay, PF, ESI, Salary, Bank_Name, Bank_Address, Bank_Account_No});
			                   
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
				         System.out.println(filename+" written successfully on disk. Location: "+fileToSave.getAbsolutePath());
				     } 
				     catch (Exception ex)
				     {
				         ex.printStackTrace();
				     }
				     
				     
				 }
				
				 }
				 
	        }// end of Save()

		
		// exitApp()
				final class exitApp implements ActionListener
				{
					 public void actionPerformed(ActionEvent e)
				        {
						 frmPayrollDetails_Report.dispose();
				        }
				}
		


}

