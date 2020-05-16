package model;

public class CartItem {
	private Book book;
	private String Title;
	private String publisher;
	private int PublishYear;
	private int price;
	private int quantity;
	private int totalPrice;
	public CartItem(Book book, String title, String publisher, int publishYear, int price, int quantity,
			int totalPrice) {
		super();
		this.book = book;
		Title = title;
		this.publisher = publisher;
		PublishYear = publishYear;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPublishYear() {
		return PublishYear;
	}
	public void setPublishYear(int publishYear) {
		PublishYear = publishYear;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
