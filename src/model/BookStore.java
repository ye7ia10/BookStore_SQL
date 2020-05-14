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
	        		result.getString(2), result.getString(6), result.getString(3), result.getString(4), result.getInt(8));
	        res.setUser(user2);
	        return res;
		} catch (Exception e) {
			res.setError(e.toString());
			e.printStackTrace();
			return res;
		}
	}
	public boolean isAdmin(User user) {
		if (user == null) {
			return false;
		}
		try {
			String query = "select from user where username = (?)";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, user.getUsername());
			ResultSet result = p.executeQuery();
			if (result.next()) {
				if (result.getInt(8) == 1) {
					return true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	public UserRespond upgradeUser(User user, User upgrade_user) {
		UserRespond res = new UserRespond();
		if (!login(user).isSuccess() || !isAdmin(user)) {
			res.setError("you are not allowed to do this");
			return res;
		}
		try {
			String query = "update user set admin = 1 where username = (?)";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, upgrade_user.getUsername());
			p.execute();
		}catch (Exception e) {
			e.printStackTrace();
			res.setError("cant upgrade");
		}
		res.setUser(upgrade_user);
		return res;
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
	        			result.getString(6), result.getString(3), result.getString(4), result.getInt(8)) );
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
	

	public ArrayList<Publisher> getAllPublishers() {
		
		ArrayList<Publisher> publishers = new ArrayList<Publisher>();
		try {
			
			String queryString = "SELECT * FROM publisher";
	        PreparedStatement preparedStmt = con.prepareStatement(queryString);
	        ResultSet result = preparedStmt.executeQuery();
	        while (result.next()) {
	        	publishers.add(new Publisher(result.getString(1), result.getString(2), result.getInt(3)));
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return publishers;
	}
	
	
	
	
	
	/********************* Book *************************/
	public BookRespond addBook(Book book) {
		BookRespond res = new BookRespond();
		int ISBN = book.getISBN();
		String title = book.getTitle();
		ArrayList<String> authors = book.getAuthors();
		String publisher_name = book.getPublisher_name();
		int min_quantity = book.getMinQuantity();
		int publication_year = book.getPublish_year();
		int selling_price = book.getSellingPrice();
		String category_name = book.getCategory();
		int available = book.getAvailable();
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
	
		public ArrayList<Book> getAllBooks() {
		
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			
			String queryString = "SELECT * FROM book";
	        PreparedStatement preparedStmt = con.prepareStatement(queryString);
	        
	        ResultSet result = preparedStmt.executeQuery();
	        while (result.next()) {
	        	int ISBN = result.getInt(1);
	        	String title = result.getString(2);
	        	String publisher_name = result.getString(3);
	        	int publisher_year = result.getInt(4);
	        	int sellingPrice = result.getInt(5);
	        	int category_id = result.getInt(6);
	        	
	        	String queryString2 = "SELECT * FROM category WHERE id=(?)";
	            PreparedStatement preparedStmt2 = con.prepareStatement(queryString2);
	            preparedStmt2.setInt(1, category_id);
	            String category_name = null;
	            ResultSet result2 = preparedStmt2.executeQuery();
	            if (result2.next()) {
	            	 category_name  = result2.getString(2);
	            }
	            
	        
	            String queryString3 = "SELECT * FROM book_copies WHERE id=(?)";
	            PreparedStatement preparedStmt3 = con.prepareStatement(queryString3);
	            preparedStmt3.setInt(1, ISBN);
	            ResultSet result3 = preparedStmt3.executeQuery();
	            int minQ = 0;
	            int available = 0;
	            if (result3.next()) {
	            	minQ = result3.getInt(2);
	   	            available = result3.getInt(3);
	            }
	         
	            ArrayList<String> authors = getAuthorsWithId(ISBN);
	            
	            books.add(new Book(ISBN, title, publisher_name, publisher_year, sellingPrice, category_name,authors, minQ, available));
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
		
		
		public BookRespond updateBook(Book book, Book new_book) {
			
			
			BookRespond res = new BookRespond();
			ArrayList<String> authors = new_book.getAuthors();
			try {
				
				/* get Id of category */
	            String queryString = "SELECT * FROM category WHERE category_name=(?)";
	            PreparedStatement preparedStmt = con.prepareStatement(queryString);
	            preparedStmt.setString(1, new_book.getCategory());
	            ResultSet result = preparedStmt.executeQuery();
	            if (!result.next()) {
	            	/* category not found */
	            	res.setError("no category with this name");
	            	System.out.println("no category with this name");
	            	return res;
	            }
				int category_id = result.getInt(1);
				
				
				String query = "UPDATE book\n" + 
						"SET ISBN = (?) , title = (?) , publisher_name = (?) , publish_year = (?) "
						+ ", selling_price = (?) "
						+ ", category_id = (?)" + 
						"WHERE ISBN = (?);";
				
				System.out.println(new_book.getTitle() + "       prepa");
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt (1, new_book.getISBN());
				preparedStmt.setString (2, new_book.getTitle());
				preparedStmt.setString (3, new_book.getPublisher_name());
				preparedStmt.setInt (4, new_book.getPublish_year());
				preparedStmt.setInt (5, new_book.getSellingPrice());
				preparedStmt.setInt (6, category_id);
				preparedStmt.setInt (7, book.getISBN());
				preparedStmt.execute();
				
				
				
				/* update the authors of the book */
				query = "delete from book_authors where book_id = (?)";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt (1, new_book.getISBN());
				preparedStmt.execute();
				for (String s : authors) {
					System.out.println(s  + "loooooooooop");
					query = " insert into book_authors (book_id, author_name)"
					        + " values (?, ?)";
					preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt (1, new_book.getISBN());
					preparedStmt.setString(2, s);
					preparedStmt.execute();
				}
				
				/* update book copies and threshold */
				query = " update book_copies set id = (?) , thersold = (?) , available = (?) where "
						+ "id = (?)";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt (1, new_book.getISBN());
				preparedStmt.setInt(2, new_book.getMinQuantity());
				preparedStmt.setInt(3, new_book.getAvailable());
				preparedStmt.setInt(4, new_book.getISBN());
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
		
		ArrayList<Book> searchForBook(ArrayList<String>columns, ArrayList<String>attributes) {
			ArrayList<Book> res = new ArrayList<Book>();
			if (columns.size() == 0 || columns.size() != attributes.size()) {
				return res;
			}
			String query = "select from book where (?) = (?)";
			for (int i = 1; i < columns.size(); i++) {
				query += ", (?) = (?)";
			}
			int pointer = 3;
			//PreparedStatement prep = con.prepareStatement(query);
			
			
			return res;
		}
		
		/***************authors**************/
		public ArrayList<String> getAuthorsWithId(int id) {
			ArrayList<String> authors = new ArrayList<String>();
			try {
				String queryString = "SELECT * FROM book_authors where book_id = (?)";
		        PreparedStatement preparedStmt = con.prepareStatement(queryString);
		        preparedStmt.setInt(1, id);
		        ResultSet result = preparedStmt.executeQuery();
		        while (result.next()) {
		        	authors.add(result.getString(2));
		        }
			}catch (Exception e) {
				e.printStackTrace();
			}
			return authors;
		}
	
	
	
	/*****************Order*******************/
		public OrderRespond addOrder(Order order) {
			OrderRespond res = new OrderRespond();
			try {
				String query = " insert into orders (book_id, quantity, order_date)"
				        + " values (?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
		        preparedStmt.setInt(1,order.getISBN());
		        preparedStmt.setInt(2, order.getQuantity());
		        preparedStmt.setDate(3, order.getDate());
		        preparedStmt.execute();
		        query = "SELECT MAX(id) FROM orders";
		        preparedStmt = con.prepareStatement(query);
		        ResultSet result = preparedStmt.executeQuery();
		        if (result.next()) {
		        	order.setId(result.getInt(1));
		        }
			} catch (Exception e) {
				res.setError(e.toString());
			}
			res.setOrder(order);
			return res;
			
		}
		public OrderRespond approveOrder(Order order) {
			OrderRespond res = new OrderRespond();
			try {
				String query = " delete from orders where id = (?), book_id = (?), quantity = (?), order_date=(?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1,order.getId());
				preparedStmt.setInt(2,order.getISBN());
		        preparedStmt.setInt(3, order.getQuantity());
		        preparedStmt.setDate(4, order.getDate());
		        preparedStmt.execute();
			} catch (Exception e) {
				res.setError(e.toString());
			}
			res.setOrder(order);
			return res;
		}
		
		public ArrayList<Order> getAllOrders() {
			ArrayList<Order> orders = new ArrayList<Order>();
			try {
				String queryString = "SELECT * FROM orders";
		        PreparedStatement preparedStmt = con.prepareStatement(queryString);
		        ResultSet result = preparedStmt.executeQuery();
		        while (result.next()) {
		        	Order order = new Order(result.getInt(2), result.getInt(3), result.getDate(4));
		        	order.setId(result.getInt(1));
		        	orders.add(order);
		        }
			}catch (Exception e) {
				e.printStackTrace();
			}
			return orders;
		}


		
	
	
}
