package EcommerceSystem;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		CustomerManager cmanager = new CustomerManager();
		ItemManager imanager = new ItemManager();
		InvoiceManager invmanager = new InvoiceManager();
		Scanner sc = new Scanner(System.in);
		
		imanager.initialize();
		cmanager.initialize(sc, imanager, invmanager);
		cmanager.checkout(sc, invmanager);
		cmanager.getTopCustomer();
		
	}

}
