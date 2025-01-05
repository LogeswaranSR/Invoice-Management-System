package EcommerceSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class SaleInvoice {
	
	public long invoice_id;
	public long cust_id;
	public Date date_of_invoice;
	
	public Item[] purchased_items;
	public int total_qty;
	public double total_price;
	public double balance;
	
	public ArrayList<Payment> payments;
	public String coupon_applied;
	public double discounted_amount = 0;
	
	public static long ref_invoice_id = 1001;
	
	public SaleInvoice(long invoice_id, long cust_id, Date date_of_invoice, Item[] purchased_items, int tot_qty, double tot_price, Payment payment, String coupon) {
		this.cust_id = cust_id;
		this.invoice_id = invoice_id;
		this.date_of_invoice = date_of_invoice;
		
		this.purchased_items = purchased_items;
		this.total_qty = tot_qty;
		this.total_price = tot_price;
		
		payments = new ArrayList<Payment>();
		payments.add(payment);
		this.coupon_applied = coupon;
		this.settled();
	}
	
	public SaleInvoice(long cust_id, Date date_of_invoice, Item[] purchased_items, int tot_qty, double tot_price, String coupon) {
		this.cust_id = cust_id;
		this.invoice_id = ref_invoice_id + 1;
		this.date_of_invoice = date_of_invoice;
		
		this.purchased_items = purchased_items;
		this.total_qty = tot_qty;
		this.total_price = tot_price;
		
		payments = new ArrayList<Payment>();
		this.coupon_applied = coupon;
		
		this.settled();
		ref_invoice_id++;
	}
	
	public void addPayment(Payment p) {
		this.payments.add(p);
	}
	
	public void discounted_amount() {
		for(int i = 0; i < this.purchased_items.length; i++) {
			Item it = this.purchased_items[i];
		}
	}
	
	public boolean settled() {
		double paid_amount = 0;
		Payment p;
		for(int i = 0; i < payments.size(); i++) {
			p = payments.get(i);
			paid_amount += p.amount;
		}
		this.balance = this.total_price - paid_amount; 
		return this.balance == 0;
	}
	
	public double check_balance() {
		this.settled();
		return this.balance;
	}
	
	public void displayItems() {
		for(int i=0;i < this.purchased_items.length; i++) {
			System.out.println(purchased_items[i].toString());
		}
	}
	
	public void settleBalance(Scanner sc) {
		System.out.print("Enter amount to be settled:");
		double amount = sc.nextDouble();
		System.out.print("Enter payment_method:");
		String payment_method = sc.nextLine();
		Payment p = new Payment(this.invoice_id, amount, payment_method);
		this.payments.add(p);
		System.out.println("Payment Success!!");
		this.settled();
	}
}
