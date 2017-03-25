<b>Employee Portal</b>

The application is developed to help keep track of employee details such as personal details, contact details, leave and payroll details.

The application mainly has two logins:

1> Employee user
  ♣ Edit / Update employee details
  ♣ Submit Leave application  
  ♣ Change password
  
2> Admin user
  ♣ Create a employee user
  ♣ Update / Delete employee user details
  ♣ Set payroll details
  ♣ View and apporve / deny leaves
  ♣ View reports and save to excel file.

The application is developed as a single frame style application that relies on the backend mostly as an api(to preload employee details), and should remember its state via the database when the page is reloaded (i.e. when log out and logged back in). The design is based on <i>gridbaglayout</i> and can fit accordingly to the screen size.


<b>Getting Started</b>

In Eclipse use the import option from the File menu. If you downloaded the whole project folder then under general choose existing project and select the root folder. Create SQL tables for employee details, admin login and employee leave applications.

In the project code change the connection settings (url,username,pwd) to match the settings of your machine.
Run the application from the login form.

<b>Requirements</b>

jdk1.8.0_111 and/or
jre1.8.0_121

Referenced libraries are already placed in the bin folder or you can individually search for them online
 ♣ org-jdesktop-layout.jar
 ♣ com.lowagie.text-2.1.7.jar
 ♣ swingx-1.6.1.jar
 ♣ dom4j-1.6.jar
 ♣ poi-3.5-FINAL.jar
 ♣ poi-3.7-20101029.jar
 ♣ poi-examples-3.7-20101029.jar
 ♣ poi-ooxml-3.7-20101029.jar
 ♣ poi-ooxml-schemas-3.7-20101029.jar
 ♣ poi-scratchpad-3.8.jar
 ♣ xmlbeans-2.3.0.jar
 ♣ mysql-connector-java-5.1.40-bin.jar


<i>Built With</i>

Eclipse IDE for Java Developers, Version: Kepler Service Release 1
     (using windows builder) <https://eclipse.org/windowbuilder/>
MySQL Workbench 6.3

<b>Author</b>

Raymond J. Chen.
