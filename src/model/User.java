package model;

public class User {
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String address;
	private int admin;
	public User(String username, String password, String first_name, String last_name, String email, String phone,
			String address, int admin) {
		this.username = username;
		this.password = password;
		this.fname = first_name;
		this.lname = last_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.admin = admin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
}
