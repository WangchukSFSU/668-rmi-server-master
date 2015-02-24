package server;

import java.util.ArrayList;
import java.util.Date;

import common.ItemTuple;
import common.Payment;

/**
 * Records sales log once the sales process has been processed and authorized.
 */
public class SalesLog {

	private String storeName;
	private String customerName;
	private ArrayList<ItemTuple> items;
	private Payment payment;
	private Date date;

	public SalesLog(String storeName, String customerName, ArrayList<ItemTuple> items, Payment payment) {
		this.storeName = storeName;
		this.customerName = customerName;
		this.items = items;
		this.payment = payment;
		this.date = new Date();
	}

	public String writeLog() {
		return SalesLogWriter.writeLog(storeName, customerName, items, payment, date);
	}
}
