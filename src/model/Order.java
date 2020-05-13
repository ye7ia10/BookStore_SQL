package model;

import java.sql.Date;

public class Order {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int ISBN;
	private int quantity;
	private Date date;
	public Order(int ISBN, int quantity, Date date) {
		this.ISBN = ISBN;
		this.quantity = quantity;
		this.date = date;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
