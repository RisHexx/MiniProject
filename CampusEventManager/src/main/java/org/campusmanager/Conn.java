package org.campusmanager;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
public class Conn {
     static final String url = "jdbc:mysql://localhost:3306/campusapp";
     static final String username = "root";
     static final String password = "";
     Connection connection;
     Statement s;
   public Conn(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            s = connection.createStatement();
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
   }
}
