package model;

public class SalesReport {
	
	private int ISBN;
	private String title;
	private String publisherName;
	private int sellingPrice;
	private int soldQuantity;
	
	public SalesReport(int ISBN, String title, String publisherName, int sellingPrice,
			int soldQuantity) {
		
		this.ISBN = ISBN;
		this.title = title;
		this.publisherName = publisherName;
		this.sellingPrice = sellingPrice;
		this.soldQuantity = soldQuantity;
		
	}
	
	public int getISBN() {
		return this.ISBN;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getPublisherName() {
		return this.publisherName;
	}
	
	public int getSellingPrice() {
		return this.sellingPrice;
	}
	
	public int getSoldQuantity() {
		return this.soldQuantity;
	}

}
