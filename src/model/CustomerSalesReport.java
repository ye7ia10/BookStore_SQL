package model;


public class CustomerSalesReport {

	private String userName;
	private String email;
	private String phone;
	private int boughtItems;


	public CustomerSalesReport(String userName, String email, String phone, int boughtItems) {

		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.boughtItems = boughtItems;

	}


	public String getUserName() {
		return this.userName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhone() {
		return this.phone;
	}

	public int getBoughtItems() {
		return this.boughtItems;
	}
}
