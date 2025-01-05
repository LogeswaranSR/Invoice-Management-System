package EcommerceSystem;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class CustomerManager {
	public ArrayList<Customer> customer_list;
	public Map<Long, Integer> invoice_count;
	
	public CustomerManager() {
		this.customer_list = new ArrayList<Customer>();
		this.invoice_count = new HashMap<Long, Integer>();
	}
	
	public void addCustomer(Customer c) {
		this.customer_list.add(c);
		this.invoice_count.put(c.cust_id, c.invoices.size());
	}
	
	private void update_invoice_count() {
		for(int i = 0; i < this.customer_list.size(); i++) {
			Customer c = this.customer_list.get(i);
			this.invoice_count.put(c.cust_id, c.invoices.size());
		}
	}
	
	public void getTopCustomer() {
		this.update_invoice_count();
		
		int max_invoices = 0;
		int max_cust_ind = -1;
		for(int i = 0; i < this.customer_list.size(); i++) {
			Customer c = this.customer_list.get(i);
			int invoices = this.invoice_count.get(c.cust_id);
			if(c.checkBalance() == 0 && invoices > max_invoices) {
				max_invoices = invoices;
				max_cust_ind = i;
			}
		}
		System.out.println("Top Customer:");
		System.out.println(this.customer_list.get(max_cust_ind).toString());
	}
	
	public void initialize(Scanner sc, ItemManager imanager, InvoiceManager invmanager) {
		Customer c1 = new Customer(1,"abc",'M',20,"123, xyz street");
		Customer c2 = new Customer(2,"def",'M',24,"124, xyz street");
		Item i1 = imanager.item_list.get(0);
		Item i2 = imanager.item_list.get(1);
		Item i3 = imanager.item_list.get(2);
		c1.cart.add(i3);
		c1.cart.add(i2);
		System.out.println("Customer Details: \n"+c1.toString()+"Cart Checkout:");
		c1.checkout(sc, "zoho", new Date(6, 6, 2024), invmanager);
		c1.cart.add(i1);
		c1.cart.add(i2);
		c1.cart.add(i3);
		c2.cart.add(i3);
		c2.cart.add(i1);
		this.addCustomer(c1);
		this.addCustomer(c2);
		
	}
	
	public void checkout(Scanner sc, InvoiceManager imanager) {
		for(int i = 0; i < this.customer_list.size(); i++) {
			Customer c = this.customer_list.get(i);
			Date date = new Date(7, 6, 2024);
			System.out.println("Customer Details: \n"+c.toString()+"Cart Checkout:");
			c.checkout(sc, "" , date, imanager);
		}
	}
}
