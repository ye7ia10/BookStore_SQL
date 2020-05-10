package model;

import java.util.ArrayList;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Helooooo");
		BookModel model = new BookModel();
		model.addPublisher("m nabil", "gnbk b7ba", 1278918571);
		ArrayList<String> arr = null;
		model.addBook(3, "title2", arr, "ahmed nabil", 3, 1997, 5, "ARt");
	}

}
