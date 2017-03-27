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


public class Permanent_Address_Report {

	public JFrame Permanent_Address_Details_Report;
	Connection con;
	String url 	= "jdbc:mysql://localhost:3306/empdet_db?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
	String user ="root";
	String pwd 	= "password1234"; 
	 
	String Emp_ID = "";
    String Website_PA = "";
	String Address_PA = "";
	String Pin_Code_PA = "";
	String State_PA = "";
    String EmailID_PA = "";
    String LandlineNo_PA = "";
    String PhoneNo_PA = "";
    String AltPhoneNo_PA = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Permanent_Address_Report window = new Permanent_Address_Report();
					window.Permanent_Address_Details_Report.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Permanent_Address_Report() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Permanent_Address_Details_Report = new JFrame();
		Permanent_Address_Details_Report.setIconImage(Toolkit.getDefaultToolkit().getImage(Permanent_Address_Report.class.getResource("/res/cloud.jpg")));
		Permanent_Address_Details_Report.setTitle("Permanent Address Details");
		Permanent_Address_Details_Report.setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		 Permanent_Address_Details_Report.setJMenuBar(menuBar);
		 
		
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
     	 
     	 
     	 
     	String[] columnNames = {"Emp ID","Website_PA","Address_PA", "Pin Code", "State_PA", "Email ID Personal", 
     			"Landline No", "Phone No", "Alt Phone No"};
     	

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
          	Website_PA			= rs.getString("Website_PA");  
          	Address_PA			= rs.getString("Address_PA");           	   	  
          	Pin_Code_PA			= rs.getString("Pin_Code_PA");
          	State_PA			= rs.getString("State_PA");
          	EmailID_PA			= rs.getString("EmailID_PA");
          	LandlineNo_PA		= rs.getString("LandlineNo_PA");
          	PhoneNo_PA			= rs.getString("PhoneNo_PA");
          	AltPhoneNo_PA		= rs.getString("AltPhoneNo_PA");
         	    
                 model.addRow(new Object[]{Emp_ID, Website_PA, Address_PA, Pin_Code_PA,
                		 State_PA, EmailID_PA, LandlineNo_PA, PhoneNo_PA, AltPhoneNo_PA});  
                 }
             rs.close();
             pst.close();
         } 
         catch (Exception ex) 
         {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
         
         
         Permanent_Address_Details_Report.getContentPane().add(scroll);
         Permanent_Address_Details_Report.setVisible(true);
         Permanent_Address_Details_Report.setSize(1000, 500);
		
	}
	
	// Save()
		final class Save implements ActionListener
		{
			 public void actionPerformed(ActionEvent e)
		        {
				 JFileChooser fileChooser = new JFileChooser();
				 fileChooser.setDialogTitle("Specify a file to save"); 
				 fileChooser.setSelectedFile(new File("*.xlsx"));
				 
				 FileNameExtensionFilter xlsxFilter = new FileNameExtensionFilter("xlsx files (*.xlsx)","xlsx");
			        // add filters
				 fileChooser.addChoosableFileFilter(xlsxFilter);
				 fileChooser.setFileFilter(xlsxFilter);
			        
				 int userSelection = fileChooser.showSaveDialog(Permanent_Address_Details_Report);

				 if (userSelection == JFileChooser.APPROVE_OPTION) {
					 File fileToSave = fileChooser.getSelectedFile();	
				     
				     XSSFWorkbook workbook = new XSSFWorkbook();

				     //Create a blank sheet
				     XSSFSheet sheet = workbook.createSheet("Employee Data");

				     //This data needs to be written (Object[])
				     Map<String, Object[]> data = new TreeMap<String, Object[]>();
				     // column heading
				     data.put("1", new Object[]{"Emp ID","Website","Permanent Address", "Pin Code", "State", "Email ID", 
				    		 "Landline No", "Phone No", "Alt Phone No"});
				     int i = 2;
				     String num = "";
				     try {
			        	 con = DriverManager.getConnection(url,user,pwd);
			         	
			             PreparedStatement pst = con.prepareStatement("SELECT * FROM emp_table ");
			             ResultSet rs = pst.executeQuery();
			             while(rs.next())
			  			{

			            	Emp_ID				= rs.getString("Emp_ID");
			               	Website_PA				= rs.getString("Website_PA");  
			               	Address_PA				= rs.getString("Address_PA");           	   	  
			               	Pin_Code_PA			= rs.getString("Pin_Code_PA");
			               	State_PA				= rs.getString("State_PA");
			               	EmailID_PA	= rs.getString("EmailID_PA");
			               	LandlineNo_PA			= rs.getString("LandlineNo_PA");
			               	PhoneNo_PA			= rs.getString("PhoneNo_PA");
			               	AltPhoneNo_PA		= rs.getString("AltPhoneNo_PA");
			         	    num = Integer.toString(i);
			                 data.put(""+num, new Object[]{Emp_ID, Website_PA, Address_PA, Pin_Code_PA, State_PA, EmailID_PA, LandlineNo_PA, 
			                		 PhoneNo_PA, AltPhoneNo_PA});  
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
						 Permanent_Address_Details_Report.dispose();
				        }
				}
		


}

