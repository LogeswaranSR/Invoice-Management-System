package EcommerceSystem;

import java.util.ArrayList;

public class InvoiceManager {
	public ArrayList<SaleInvoice> invoice_list;
	
	public InvoiceManager() {
		this.invoice_list = new ArrayList<SaleInvoice>();
	}
	
	public void add_invoice(SaleInvoice i) {
		this.invoice_list.add(i);
	}
	
}
