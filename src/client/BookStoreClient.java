package client;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.BookRespond;
import model.BookStore;
import model.Order;
import model.OrderRespond;
import model.Publisher;
import model.PublisherRespond;
import model.User;
import model.UserRespond;

public class BookStoreClient {
	BookStore bookServer;
	private User user;
	public BookStoreClient() {
		bookServer = new BookStore();
	}
	/******************* user **********************/
	public User getUser() {
		return user;
	}
	public UserRespond login(User user) {
		UserRespond res = bookServer.login(user);
		if (res.isSuccess()) {
			this.user = res.getUser();
		}
		return res;
	}
	public UserRespond signUp(User user) {
		UserRespond res = bookServer.signUp(user);
		if (res.isSuccess()) {
			this.user = res.getUser();
		}
		return res;
	}
	public void logout() {
		user = null;
	}
	
	public UserRespond updateUser(User user) {
		UserRespond res = bookServer.updateUser(this.user, user);
		if (res.isSuccess()) {
			this.user = res.getUser();
		}
		return res;
	}
	public ArrayList<User> getAllUsers() {
		return bookServer.getAllUsers();
	}
	
	
	
	/************** publisher *******************/
	
	public PublisherRespond addPublisher(Publisher publisher) {
		return bookServer.addPublisher(publisher);
	}
	public PublisherRespond updatePublisher(Publisher publisher, Publisher new_publisher) {
		return bookServer.updatePublisher(publisher, new_publisher);
	}
	public ArrayList<Publisher> getAllPublishers() {
		return bookServer.getAllPublishers();
	}
	
	/************** book *******************/
	public ArrayList<Book> getAllBooks() {
		return bookServer.getAllBooks();
	}
	
	public BookRespond addBook(Book book) {
		return bookServer.addBook(book);
	}
	public BookRespond updateBook(Book book, Book new_book) {
		return bookServer.updateBook(book, new_book);
	}
	
	/**********Order*****************/
	public OrderRespond addOrder(Order order) {
		return bookServer.addOrder(order);
	}
	public OrderRespond approveOrder(Order order) {
		return bookServer.approveOrder(order);
	}
	public ArrayList<Order> getAllOrders() {
		return bookServer.getAllOrders();
	}
	
	public UserRespond upgradeUser(User upgrade_user) {
		UserRespond res = new UserRespond();
		if (user == null) {
			res.setError("not signed in");
			return res;
		}
		return bookServer.upgradeUser(user, upgrade_user);
	}
	
}
