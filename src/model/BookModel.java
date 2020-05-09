package model;

import java.sql.*;
public class BookModel {
	BookModel() {
		try {
			System.out.println(" Reach there");
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/project","root","new_password");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from book");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
