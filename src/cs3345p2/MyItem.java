package cs3345p2;

import java.util.ArrayList;
import java.util.List;

public class MyItem implements IDedObject {
	private int itemID;
	private int itemPrice;
	private ArrayList<Integer> itemDescription;
	
	MyItem() {
		itemID = 0;
		itemPrice = 0;
		itemDescription = new ArrayList<>(0);
	}
	
	MyItem(int id, int price, List<Integer> name) {
		itemID = id;
		itemPrice = price;
		itemDescription = (ArrayList<Integer>) name;
	}
	
	public int getID() {
		return this.itemID;
	}
	
	public String printID() {
		String temp = itemID + " " + itemPrice + " ";
		for (int i = 0; i < this.itemDescription.size(); i++) {
			temp += this.itemDescription.get(i) + " ";
		}
		return temp;
	}
}
