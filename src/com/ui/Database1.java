package com.ui;
import java.sql.*;

public class Database1 {
	
	    Connection con;
	    PreparedStatement pst;
	    ResultSet rs;
	    Database1()
	    {
	        try{
	             
	            //MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
	            //ALSO SET THE CLASSPATH
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdet_db","root","password1234");
	                        pst = con.prepareStatement("select * from emp_table where Emp_ID=? and Emp_Password=?");
	             
	           }
	        catch (Exception e) 
	        {
	            System.out.println(e);
	        }
	    }
	        //ip:username,password
	        //return boolean
	    public Boolean checkLogin(String Emp_ID,String Emp_Password)
	    {
	        try {
	                        
	            pst.setString(1, Emp_ID); //this replaces the 1st  "?" in the query for username
	            pst.setString(2, Emp_Password);    //this replaces the 2st  "?" in the query for password
	            //executes the prepared statement
	            rs = pst.executeQuery();
	            if(rs.next())
	            {
	                //TRUE iff the query founds any corresponding data
	                return true;
	            }
	            else
	            {
	                return false;
	            }
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            System.out.println("error while validating"+e);
	            return false;
	        }
	    }
}
