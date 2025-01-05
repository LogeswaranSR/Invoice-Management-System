package EcommerceSystem;

import java.util.Map;

public class Item {
	public long item_id;
	public String item_name;
	public String item_desc;
	public double item_cp;
	public double item_price;
	public Map<String, Double> coupon_discounts;
	
	public Item(long item_id, String item_name, String item_desc, double item_cp, double item_price, Map<String, Double> coupons) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_desc = item_desc;
		this.item_cp = item_cp;
		this.item_price = item_price;
		this.coupon_discounts = coupons;
	}
	
	public String toString() {
		String res = "";
		res += "Item: "+this.item_id+"\n";
		res += "Name: "+this.item_name+"\n";
		res += "Price: "+this.item_price+"\n\n";
		return res;
	}
	
	public void add_discount(String code, double price) {
		this.coupon_discounts.put(code, price);
	}
	
	public double getProfitOrLoss(String discount) {
		double final_price = this.getFinalPrice(discount);
		return final_price - this.item_cp;
	}
	
	public double getFinalPrice(String discount) {
		double discount_price = this.coupon_discounts.containsKey(discount) ? this.coupon_discounts.get(discount) : 0;
		double final_price = this.item_price - discount_price;
		return final_price;
	}
}
