package model;

import java.sql.*;
import java.util.ArrayList;
public class BookStore {
	private String username = "root";
	private String password = "new_password";
	private String dataBasse_name = "project";
	private String host_url = "jdbc:mysql://localhost:3306/";
	private Connection con;
	public BookStore() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection(  
					host_url + dataBasse_name, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/***************** USER *****************************/
	public UserRespond login(User user) {
		UserRespond res = new UserRespond();
		String queryString = "SELECT * FROM user WHERE username=(?) and password=(?)";
		try {
	        PreparedStatement preparedStmt = con.prepareStatement(queryString);
	        preparedStmt.setString(1, user.getUsername());
	        preparedStmt.setString(2, user.getPassword());
	        ResultSet result = preparedStmt.executeQuery();
		
	        if (!result.next()) {
	        	/* category not found */
	        	System.out.println("Cant Sign In");
	        	res.setError("invalid Username or Password");
	        	return res;
	        }
	        User user2 = new User(result.getString(5), result.getString(7), result.getString(1),
	        		result.getString(2), result.getString(6), result.getString(3), result.getString(4));
	        res.setUser(user2);
	        return res;
		} catch (Exception e) {
			res.setError(e.toString());
			e.printStackTrace();
			return res;
		}
	}
	
	public UserRespond signUp(User user) {
			
		UserRespond res = new UserRespond();
		try {
			  
			String query = " insert into user (first_name, Last_name, phone_number, address, username, password, email)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (1, user.getFirstName());
			preparedStmt.setString (2, user.getLastName());
			preparedStmt.setString (3, user.getPhone());
			preparedStmt.setString (4, user.getAddress());
			preparedStmt.setString (5, user.getUsername());
			preparedStmt.setString (6,user.getPassword());
			preparedStmt.setString (7, user.getEmail());
			preparedStmt.execute();  
		} catch (Exception e) {
			// TODO: handle exception
			res.setError(e.toString());
			System.out.println(e);
		}
		res.setUser(user);
		return res;
	}
	public UserRespond updateUser(User user, User new_user) {
		
		UserRespond res = new UserRespond();
		try {
			  
			String query = "UPDATE user " + 
					"SET username = (?) , password = (?) , first_name = (?) , last_name = (?) "
					+ ", phone_number = (?) , address = (?) , email = (?)" + 
					"WHERE username=(?);";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (3, new_user.getFirstName());
			preparedStmt.setString (4, new_user.getLastName());
			preparedStmt.setString (5, new_user.getPhone());
			preparedStmt.setString (6, new_user.getAddress());
			preparedStmt.setString (1, new_user.getUsername());
			preparedStmt.setString (2, new_user.getPassword());
			preparedStmt.setString (7, new_user.getEmail());
			preparedStmt.setString (8, user.getUsername());
			preparedStmt.execute();  
		} catch (Exception e) {
			// TODO: handle exception
			res.setError(e.toString());
			System.out.println(e);
			return res;
		}
		res.setUser(new_user);
		return res;
	}
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			
			String queryString = "SELECT * FROM user";
	        PreparedStatement preparedStmt = con.prepareStatement(queryString);
	        
	        ResultSet result = preparedStmt.executeQuery();
	        while (result.next()) {
	        	users.add(new User(result.getString(5), result.getString(7), result.getString(1),result.getString(2),
	        			result.getString(6), result.getString(3), result.getString(4)) );
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return users;
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

	/*********************publisher*********************/
	public PublisherRespond addPublisher(Publisher publisher) {
		PublisherRespond res = new PublisherRespond();
		String publisherName = publisher.getName();
		String address = publisher.getAddress();
		int phone = publisher.getPhone();
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
			res.setError(e.toString());
			System.out.println(e);
			return res;
		}
		res.setPublisher(publisher);
		return res;
	}
	public PublisherRespond updatePublisher(Publisher publisher, Publisher new_publisher) {
		PublisherRespond res = new PublisherRespond();
		try {
			String queryString = "update publisher set publisher_name = (?) , address = (?) , phone = (?)"
					+ " where publisher_name = (?)";
            PreparedStatement preparedStmt = con.prepareStatement(queryString);
            preparedStmt.setString(1, new_publisher.getName());
            preparedStmt.setString(2, new_publisher.getAddress());
            preparedStmt.setInt(3, new_publisher.getPhone());
            preparedStmt.setString(4, publisher.getName());
            preparedStmt.execute();
		}catch (Exception e) {
			res.setError(e.toString());
			e.printStackTrace();
		}
		res.setPublisher(new_publisher);
		return res;
	}
	
	
	/********************* Book *************************/
	public BookRespond addBook(Book book) {
		BookRespond res = new BookRespond();
		int ISBN = book.getISBN();
		String title = book.getTitle();
		ArrayList<String> authors = book.getAuthors();
		String publisher_name = book.getPublisherName();
		int min_quantity = book.getMinQuantity();
		int publication_year = book.getPublisherYear();
		int selling_price = book.getSellingPrice();
		String category_name = book.getCategory();
		int available = book.getAvaialable();
		System.out.println("add book");
		
		try {
            /* get Id of category */
            String queryString = "SELECT * FROM category WHERE category_name=(?)";
            PreparedStatement preparedStmt = con.prepareStatement(queryString);
            preparedStmt.setString(1, category_name);
            ResultSet result = preparedStmt.executeQuery();
            if (!result.next()) {
            	/* category not found */
            	res.setError("no category with this name");
            	System.out.println("no category with this name");
            	return res;
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
			res.setError(e.toString());
			System.out.println(e);
			return res;
		}
		res.setBook(book);
		return res;
		
	}
	public BookRespond updateBook(Book book, Book new_book) {
		BookRespond res = new BookRespond();
		ArrayList<String> authors = new_book.getAuthors();
		try {
			
			/* get Id of category */
            String queryString = "SELECT * FROM category WHERE category_name=(?)";
            PreparedStatement preparedStmt = con.prepareStatement(queryString);
            preparedStmt.setString(1, book.getCategory());
            ResultSet result = preparedStmt.executeQuery();
            if (!result.next()) {
            	/* category not found */
            	res.setError("no category with this name");
            	System.out.println("no category with this name");
            	return res;
            }
			int category_id = result.getInt(1);
			
			
			String query = "UPDATE book\n" + 
					"SET ISBN = (?) and title = (?) and pubisher_name = (?) and publisher_year = (?) "
					+ "and selling_Price = (?) "
					+ "and category_id = (?)" + 
					"WHERE ISBN = (?);";
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt (1, new_book.getISBN());
			preparedStmt.setString (2, new_book.getTitle());
			preparedStmt.setString (3, new_book.getPublisherName());
			preparedStmt.setInt (4, new_book.getPublisherYear());
			preparedStmt.setInt (5, new_book.getSellingPrice());
			preparedStmt.setInt (6, category_id);
			preparedStmt.setInt (7, book.getISBN());
			
			
			
			/* update the authers of the book */
			query = "delete from book_authors where id = (?)";
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt (1, book.getISBN());
			preparedStmt.execute();
			for (String s : authors) {
				System.out.println(s);
				query = " insert into book_authors (book_id, author_name)"
				        + " values (?, ?)";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt (1, new_book.getISBN());
				preparedStmt.setString(2, s);
				preparedStmt.execute();
			}
			/* update book copies and threshold */
			query = " update book_copies set id = (?) , thersold = (?) , available = (?) where"
					+ "id = (?)";
			preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt (1, new_book.getISBN());
			preparedStmt.setInt(2, new_book.getMinQuantity());
			preparedStmt.setInt(3, new_book.getAvaialable());
			preparedStmt.setInt(3, book.getISBN());
			preparedStmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			res.setError(e.toString());
			System.out.println(e);
			return res;
		}
		
		
		
		
		res.setBook(new_book);
		return res;
	}
	
	
	
	
	
	
	
}
