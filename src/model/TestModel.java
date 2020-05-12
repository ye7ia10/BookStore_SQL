package model;


import java.util.ArrayList;

import client.BookStoreClient;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* test user and model Respond */
		BookStoreClient client = new BookStoreClient();
		User user = new User("ahmed", "123", "ahmed", "nabil", "ahmed@gmail.com", "01278918571", "here");
		client.login(user);
		User new_user = new User("ahmed", "123", "ahmed", "nabil", "ahmed@gmail.com", "01278918571", "here after update");
		client.updateUser(new_user);
		ArrayList<User> users = client.getAllUsers();
		for (User u : users) {
			System.out.println(u.getUsername());
		}
		/* test publisher */
		Publisher publisher = new Publisher("name", "address", 012);
		//client.addPublisher(publisher);
		Publisher new_publisher = new Publisher("nameagain", "address", 012);
		client.updatePublisher(publisher, new_publisher);
		
	}

}
