package model;

import java.util.ArrayList;

public class Book {
	private int ISBN;
	private String title;
	private String publisher_name;
	private int publish_year;
	private int sellingPrice;
	private String category;
	private ArrayList<String> authors;
	private int minQuantity;
	private int available;
	public Book(int ISBN, String title, String publisher_name, int publish_year, int sellingprice, String category,
			ArrayList<String> authors, int minQuantity, int available) {
		this.ISBN = ISBN;
		this.title = title;
		this.publisher_name = publisher_name;
		this.publish_year = publish_year;
		this.sellingPrice = sellingprice;
		this.category = category;
		this.authors = authors;
		this.minQuantity = minQuantity;
		this.available = available;
	}
	
	public int getISBN() {
		return ISBN;
	}
	public String getTitle() {
		return title;
	}
	public String getPublisherName() {
		return publisher_name;
	}
	public int getPublisherYear() {
		return publish_year;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public String getCategory() {
		return category;
	}
	ArrayList<String> getAuthors() {
		return authors;
	}
	int getMinQuantity() {
		return minQuantity;
	}
	int getAvaialable() {
		return available;
	}
}
