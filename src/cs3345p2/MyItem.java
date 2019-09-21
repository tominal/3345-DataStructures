package cs3345p2;

import java.util.ArrayList;
import java.util.List;

public class MyItem implements IDedObject {
	int itemID;
	int itemPrice;
	List<Integer> itemDescription;
	
	MyItem() {
		itemID = 0;
		itemPrice = 0;
		itemDescription = new ArrayList<>(0);
	}
	
	MyItem(int id, int price, List<Integer> desc) {
		itemID = id;
		itemPrice = price;
		itemDescription = desc;
	}
	
	public int getID() {
		return itemID;
	}
	
	public String printID() {
		String temp = "Item ID: " + itemID + " Price: $" + itemPrice + " Description: ";
		for (int i = 0; i < itemDescription.size(); i++) {
			temp += itemDescription.get(i);
		}
		return temp;
	}
}
