package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

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
	public User(ResultSet result) throws SQLException {
		try {
			this.username = result.getString(5);
			this.password = result.getString(7);
			this.fname = result.getString(1);
			this.lname = result.getString(2);
			this.email = result.getString(6);
			this.phone = result.getString(3);
			this.address = result.getString(4);
			this.admin = result.getInt(8);
		} catch (Exception e) {
			throw new SQLException("an error happened when constructing user");
		}
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
	public PreparedStatement preparedForInsert(PreparedStatement preparedStmt) throws SQLException {
		try {
		
		preparedStmt.setString (1, this.getFname());
		preparedStmt.setString (2, this.getLname());
		preparedStmt.setString (3, this.getPhone());
		preparedStmt.setString (4, this.getAddress());
		preparedStmt.setString (5, this.getUsername());
		preparedStmt.setString (6, this.getPassword());
		preparedStmt.setString (7, this.getEmail());
		}catch (Exception e) {
			throw new SQLException("an error happened when sign up user");
		}
		return preparedStmt;
	}
	
	
}
