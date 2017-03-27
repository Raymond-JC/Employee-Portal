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



public class Leave_Report {

	public JFrame Leave_Report_Frame;
	Connection con;
	String url 	= "jdbc:mysql://localhost:3306/empdet_db?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
	String user ="root";
	String pwd 	= "password1234"; 
	 
	String Leave_ID = "";
    String Emp_ID = "";
	String Leave_Type = "";
	String Reason = "";
	String Leave_From = "";
    String Leave_To = "";
    String Leave_Info = "";
    String Leave_Approved = "";
    String Leave_Comments = "";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Leave_Report window = new Leave_Report();
					window.Leave_Report_Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Leave_Report() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Leave_Report_Frame = new JFrame("Employee Leave Details");
		Leave_Report_Frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Leave_Report.class.getResource("/res/cloud.jpg")));
		
		
		JMenuBar menuBar = new JMenuBar();
		 Leave_Report_Frame.setJMenuBar(menuBar);
		 
		
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
     	 
     	 
     	 
     	String[] columnNames = {"Leave ID","Emp ID","Leave Type", "Reason", "Leave From", "Leave To",
     			"Leave Information", "Leave Status", "Comments"};
     	

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
         	
             pst = con.prepareStatement("SELECT * FROM emp_leave_table ");
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
         	    
                 model.addRow(new Object[]{Leave_ID, Emp_ID, Leave_Type, Reason, 
                		 Leave_From, Leave_To, Leave_Info, Leave_Approved, Leave_Comments});  

                 }
             rs.close();
             pst.close();
         } 
         catch (Exception ex) 
         {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
         
         
         Leave_Report_Frame.getContentPane().add(scroll);
         Leave_Report_Frame.setVisible(true);
         Leave_Report_Frame.setSize(1000, 500);
		
	}
	
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
			        
				 int userSelection = fileChooser.showSaveDialog(Leave_Report_Frame);

				 if (userSelection == JFileChooser.APPROVE_OPTION) {
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
			        	 con = DriverManager.getConnection(url,user,pwd);
			         	
			             PreparedStatement pst = con.prepareStatement("SELECT * FROM emp_leave_table ");
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
				final class exitApp implements ActionListener
				{
					 public void actionPerformed(ActionEvent e)
				        {
						 Leave_Report_Frame.dispose();
				        }
				}
		


}
