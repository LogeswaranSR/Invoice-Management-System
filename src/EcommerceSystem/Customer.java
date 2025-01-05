package EcommerceSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
	public long cust_id;
	public String cust_name;
	public char gender;
	public int age;
	public String address;
	
	public ArrayList<SaleInvoice> invoices;
	public String last_payment_method;
	public double Balance;
	public ArrayList<Item> cart;
	
	public Customer(long cust_id, String cust_name, char gender, int age, String address) {
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		
		this.invoices = new ArrayList<SaleInvoice>();
		this.Balance = -1;
		this.last_payment_method = "";
		this.cart = new ArrayList<Item>();
	}
	
	public double checkBalance() {
		if(this.Balance < 0) {
			this.Balance = 0;
			for(int i=0; i<this.invoices.size(); i++) {
				this.Balance += this.invoices.get(i).check_balance();
			}
		}
		return this.Balance;
	}
	
	public void addItem(Item i) {
		this.cart.add(i);
	}
	
	public void deleteItem(Item i) {
		this.cart.remove(i);
	}
	
	private Payment transaction(Scanner sc, long invoice_id) {
		System.out.print("\nEnter amount to be settled:");
		double amount = sc.nextDouble();
		System.out.print("Payment Method: UPI\n");
		String payment_method = "UPI";
		Payment p = new Payment(invoice_id, amount, payment_method);
		System.out.println("Payment Success!!\n\n");
		return p;
	}
	
	private Item[] returnItemArray() {
		Item[] arr = new Item[this.cart.size()];
		for(int i=0; i<this.cart.size(); i++) {
			arr[i] = this.cart.get(i);
		}
		return arr;
	}
	
	public void checkout(Scanner sc, String coupon, Date date, InvoiceManager invmanager) {
		Item[] items = this.returnItemArray();
		int tot_price = 0;
		if(coupon.length() > 0) {
			System.out.println("Coupon Applied: "+coupon);
		}
		System.out.println("Item Name -   Price");
		for(int i=0; i<items.length; i++) {
			double price = items[i].getFinalPrice(coupon);
			System.out.printf("%s - %.2f\n", items[i].item_name, price);
			tot_price += price;
		}
		System.out.print("Total Price:"+tot_price);
		SaleInvoice inv = new SaleInvoice(this.cust_id, date, items, items.length, tot_price, coupon);
		Payment p = this.transaction(sc, inv.invoice_id);
		inv.addPayment(p);
		this.last_payment_method = p.payment_method;
		this.invoices.add(inv);
		invmanager.add_invoice(inv);
		this.cart.clear();
		System.out.println("Invoice added successfully!!\n");
	}
	
	public String toString() {
		String res = "";
		res += "Item: "+this.cust_id+"\n";
		res += "Name: "+this.cust_name+"\n";
		return res;
	}
}
