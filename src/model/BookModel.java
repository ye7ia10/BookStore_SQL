package model;

import java.sql.*;
import java.util.ArrayList;
public class BookModel {
	private String username = "root";
	private String password = "mypass";
	private String dataBasse_name = "project";
	private String host_url = "jdbc:mysql://localhost:3306/";
	private Connection con;
	public BookModel() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection(  
					host_url + dataBasse_name, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void addAuthors(int ISBN, ArrayList<String> authors) {
		String query = " insert into book_authors (book_id, author_name)"
		        + " values (?, ?)";
		for (String s : authors) {
			try {
				PreparedStatement preparedStmt = con.prepareStatement(query);
	            preparedStmt.setInt(1, ISBN);
	            preparedStmt.setString(2, s);
	            preparedStmt.execute();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}



	public void addBook(int ISBN, String title, ArrayList<String> authors,
			String publisher_name, int min_quantity, int publication_year,
			int selling_price, String category_name, int available) {
		
		System.out.println("add book");
		
		try {
            /* get Id of category */
            String queryString = "SELECT * FROM category WHERE category_name=(?)";
            PreparedStatement preparedStmt = con.prepareStatement(queryString);
            preparedStmt.setString(1, category_name);
            ResultSet result = preparedStmt.executeQuery();
            if (!result.next()) {
            	/* category not found */
            	System.out.println("no category with this name");
            	return;
            }
			int category_id = result.getInt(1);
			
			
			/* add book to the table */
			String query = " insert into book (ISBN, title, publisher_Name, publish_year, selling_price, category_id)"
			        + " values (?, ?, ?, ?, ?, ?)";
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt (1, ISBN);
			preparedStmt.setString (2, title);
			preparedStmt.setString (3, publisher_name);
			preparedStmt.setInt (4, publication_year);
			preparedStmt.setInt (5, selling_price);
			preparedStmt.setInt (6, category_id);
			preparedStmt.execute();    
			
			/* add the authers of the book */
			for (String s : authors) {
				System.out.println(s);
				query = " insert into book_authors (book_id, author_name)"
				        + " values (?, ?)";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt (1, ISBN);
				preparedStmt.setString(2, s);
				preparedStmt.execute();
			}
			
			/* add book copies and threshold */
			query = " insert into book_copies (id, thersold, available)"
			        + " values (?, ?, ?)";
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt (1, ISBN);
			preparedStmt.setInt(2, min_quantity);
			preparedStmt.setInt(3, available);
			preparedStmt.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}
	public void addPublisher(String publisherName, String address, int phone) {
		
		try {
			System.out.println("Try add publisher");
			  
			String query = " insert into publisher (Publisher_Name, address, phone)"
			        + " values (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (1, publisherName);
			preparedStmt.setString (2,address);
			preparedStmt.setInt    (3, phone);
			preparedStmt.execute();      
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}
}
