package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Publisher {
	private String name;
	private String address;
	private int phone;
	
	public Publisher(String name, String address, int phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public PreparedStatement setPrepared(PreparedStatement preparedStmt) throws SQLException {
		try {
			preparedStmt.setString (1, name);
			preparedStmt.setString (2,address);
			preparedStmt.setInt    (3, phone);
		} catch (SQLException e) {
			throw new SQLException("can not add publisher");
		}
		
		return preparedStmt;
	}
	
	
}
