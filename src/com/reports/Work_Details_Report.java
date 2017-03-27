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


public class Work_Details_Report {

	public JFrame frmWorkDetails_Report;
	
	String Emp_ID = "";
    String Current_Job = "";
    String Date_Of_Joining = "";
    String Email_ID_Work = "";
    String Phone_No_Work = "";
    String Landline_No_Work = "";
    String Previous_Job = "";
    String Years_Of_Experience = "";
    String Company_Name = "";
    String Company_Address = "";
    String Company_Contact = "";
    
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
					Work_Details_Report window = new Work_Details_Report();
					window.frmWorkDetails_Report.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Work_Details_Report() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWorkDetails_Report = new JFrame();
		frmWorkDetails_Report.setIconImage(Toolkit.getDefaultToolkit().getImage(Work_Details_Report.class.getResource("/res/cloud.jpg")));
		frmWorkDetails_Report.setTitle("Work Details");
		frmWorkDetails_Report.setBounds(100, 100, 450, 300);

		
		JMenuBar menuBar = new JMenuBar();
		 frmWorkDetails_Report.setJMenuBar(menuBar);
		 
		
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
     	 
     	 
     	 
     	String[] columnNames = {"Emp ID","Current Job","Date Of Joining", "Email ID Work", "Phone No Work", "Landline No Work", "Previous Job", 
     			"Years Of Experience", "Company Name", "Company Address", "Company Contact"};

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
            	 Emp_ID				= rs.getString("Emp_ID");
             	Current_Job			= rs.getString("Current_Job");  
             	Date_Of_Joining		= rs.getString("Date_Of_Joining");           	   	  
             	Previous_Job		= rs.getString("Previous_Job");
             	Years_Of_Experience	= rs.getString("Years_Of_Experience");
             	Email_ID_Work		= rs.getString("Email_ID_Work");
             	Phone_No_Work		= rs.getString("Phone_No_Work");
             	Landline_No_Work	= rs.getString("Landline_No_Work");
             	Company_Name		= rs.getString("Company_Name");
             	Company_Address		= rs.getString("Company_Address");
             	Company_Contact		= rs.getString("Company_Contact");

            	    
                    model.addRow(new Object[]{Emp_ID, Current_Job, Date_Of_Joining, Email_ID_Work, Phone_No_Work, Landline_No_Work, 
                    		Previous_Job, Years_Of_Experience, Company_Name, Company_Address, Company_Contact}); 
                 }
             rs.close();
             pst.close();
         } 
         catch (Exception ex) 
         {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
         
         
         frmWorkDetails_Report.getContentPane().add(scroll);
         frmWorkDetails_Report.setVisible(true);
         frmWorkDetails_Report.setSize(1000, 500);
		
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
			        
				 int userSelection = fileChooser.showSaveDialog(frmWorkDetails_Report);

				 if (userSelection == JFileChooser.APPROVE_OPTION) {
					 File fileToSave = fileChooser.getSelectedFile();	
				     
				     XSSFWorkbook workbook = new XSSFWorkbook();

				     //Create a blank sheet
				     XSSFSheet sheet = workbook.createSheet("Employee Data");

				     //This data needs to be written (Object[])
				     Map<String, Object[]> data = new TreeMap<String, Object[]>();
				     data.put("1", new Object[]{"Emp ID","Current Job","Date Of Joining", "Email ID Work", "Phone No Work", "Landline No Work", 
				    		 "Previous Job", "Years Of Experience", "Company Name", "Company Address", "Company Contact"});
				     int i = 2;
				     String num = "";
				     try {
			        	 con = DriverManager.getConnection(url,user,pwd);
			         	
			             PreparedStatement pst = con.prepareStatement("SELECT * FROM emp_table ");
			             ResultSet rs = pst.executeQuery();
			             while(rs.next())
			  			{

			            	 Emp_ID				= rs.getString("Emp_ID");
			              	Current_Job			= rs.getString("Current_Job");  
			              	Date_Of_Joining		= rs.getString("Date_Of_Joining");           	   	  
			              	Previous_Job		= rs.getString("Previous_Job");
			              	Years_Of_Experience	= rs.getString("Years_Of_Experience");
			              	Email_ID_Work		= rs.getString("Email_ID_Work");
			              	Phone_No_Work		= rs.getString("Phone_No_Work");
			              	Landline_No_Work	= rs.getString("Landline_No_Work");
			              	Company_Name		= rs.getString("Company_Name");
			              	Company_Address		= rs.getString("Company_Address");
			              	Company_Contact		= rs.getString("Company_Contact");
			         	    num = Integer.toString(i);
			         	    data.put(""+num,new Object[]{Emp_ID, Current_Job, Date_Of_Joining, Email_ID_Work, Phone_No_Work, Landline_No_Work, 
		                    		Previous_Job, Years_Of_Experience, Company_Name, Company_Address, Company_Contact});
			                   
			                  i++;
			                 }
			             rs.close();
			             pst.close();
			         } 
			         catch (Exception ex) 
			         {
			             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			         }
				     
				     /* Example data
				      *  data. put ( row , new Object[] { column data1, 2 , 3..});
				     data.put("2", new Object[]{1, "Amit", "Shukla"});
				     data.put("3", new Object[]{2, "Lokesh", "Gupta"});
				     data.put("4", new Object[]{3, "John", "Adwards"});
				     data.put("5", new Object[]{4, "Brian", "Schultz"});
				     */

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
						 frmWorkDetails_Report.dispose();
				        }
				}
		


}
