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

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher_name() {
		return publisher_name;
	}

	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	public int getPublish_year() {
		return publish_year;
	}

	public void setPublish_year(int publish_year) {
		this.publish_year = publish_year;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	
}
