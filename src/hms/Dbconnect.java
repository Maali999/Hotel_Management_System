/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hms;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

/**
 *
 * @author Full Time
 */
public class Dbconnect {
public static Connection connect(){
Connection con=null;
try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms","root","");
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
}    
return con;
}
}