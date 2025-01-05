package EcommerceSystem;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ItemManager {
	public ArrayList<Item> item_list;
	
	public ItemManager() {
		this.item_list = new ArrayList<Item>();
	}
	
	public void initialize() {
		Map<String, Double> discounts = new HashMap<String, Double>();
		discounts.put("zoho", (double)100);
		this.item_list.add(new Item(101, "item1", "item1", 250, 400, discounts));
		this.item_list.add(new Item(102, "item2", "item2", 350, 550, discounts));
		this.item_list.add(new Item(103, "item3", "item3", 350, 400, discounts));
	}
	
}
