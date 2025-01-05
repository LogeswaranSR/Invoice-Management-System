package EcommerceSystem;

public class Payment {
	public long payment_id;
	public long invoice_id;
	public double amount;
	public String payment_method;
	
	public static long ref_payment_id;
	
	public Payment(long invoice_id, double amount, String payment_method) {
		this.payment_id = ref_payment_id + 1;
		this.invoice_id = invoice_id;
		this.amount = amount;
		this.payment_method = payment_method;
		ref_payment_id++;
	}
	
	public Payment(long payment_id, long invoice_id, double amount, String payment_method) {
		this.payment_id = payment_id;
		this.invoice_id = invoice_id;
		this.amount = amount;
		this.payment_method = payment_method;
		ref_payment_id = payment_id+1;
	}
}
