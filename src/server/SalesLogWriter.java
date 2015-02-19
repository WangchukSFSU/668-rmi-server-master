package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import common.Cash;
import common.Check;
import common.CreditCard;
import common.Item;
import common.ItemTuple;
import common.Payment;

public class SalesLogWriter {
	private static final int DESCRIPTION_PADDING = 20;
	private static final int QUANTITY_PADDING = 19;
	private static final int PRICE_PADDING = 10;

	// Writer
	private static BufferedWriter writer;

	/*
	 * Writes the log as a String first, then sysout that string to show that
	 * transaction was completed, and then writes to disk that same information
	 * for more permanent record.
	 * 
	 * @return true operation succeeded
	 * 
	 * @return false operation ended in error
	 */
	public static boolean writeLog(String storeName, String customerName, ArrayList<ItemTuple> items, Payment payment, Date date) {
		try {
			writer = new BufferedWriter(new FileWriter("sales.log", true));
		} catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}
		String salesLog;
		BigDecimal total = new BigDecimal(0);

		salesLog = storeName + '\n';
		salesLog += '\n';
		salesLog += padRight(customerName, DESCRIPTION_PADDING) + padLeft(date.toString() + '\n', +QUANTITY_PADDING + PRICE_PADDING);
		ServerManager catalog = new ServerManager();
		for (int i = 0; i < items.size(); i++) {
			try {
				Item currentItem = (Item) catalog.getCatalog().get(items.get(i).getUPC());
				total = total.add(currentItem.getPrice().multiply(new BigDecimal(items.get(i).getQuantity())));
				salesLog += padRight(currentItem.getDescription(), DESCRIPTION_PADDING)

				+ padRight(items.get(i).getQuantity() + " @ " + currentItem.getPrice(), QUANTITY_PADDING)

				+ padLeft("$" + (currentItem.getPrice().multiply(new BigDecimal(items.get(i).getQuantity())).toString()) + '\n', PRICE_PADDING);
			} catch (RemoteException exception) {
				exception.printStackTrace();
				return false;
			}
		}

		for (int i = 0; i < DESCRIPTION_PADDING + QUANTITY_PADDING + PRICE_PADDING; i++) {
			salesLog += "-";
		}

		salesLog += "\n";
		salesLog += padLeft("Total: $" + total.toString() + '\n', DESCRIPTION_PADDING + QUANTITY_PADDING + PRICE_PADDING);
		BigDecimal change = new BigDecimal(0);

		try {
			if (payment instanceof Cash) {
				salesLog += "Amount Tendered: " + payment.getAmount() + '\n';
				change = payment.getAmount().subtract(total);
			} else if (payment instanceof Check) {
				salesLog += "Paid by Check: " + ((Check) payment).getAmount() + '\n';
			} else {
				salesLog += "Paid by Credit Card: " + ((CreditCard) payment).getCardNumber() + "\n";
			}
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return false;
		}
		salesLog += "Amount Returned: " + change.toString() + '\n';
		salesLog += '\n';

		System.out.println(salesLog);

		try {
			writer.write(salesLog);
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Testing the SalesLogClass public static void main(String[] args) {
	 * ArrayList<ItemTuple> items = new ArrayList<ItemTuple>(); items.add(new
	 * ItemTuple("0001", 10)); items.add(new ItemTuple("0002", 1));
	 * items.add(new ItemTuple("0003", 1)); items.add(new ItemTuple("0004", 1));
	 * items.add(new ItemTuple("0005", 1));
	 * 
	 * Payment payment = new CreditCard("123456");
	 * 
	 * SalesLog log = new SalesLog("MyStore Name", "Test Test", items, payment);
	 * log.writeLog(); } <<<<<<< HEAD
	 */

	private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	private static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}
}
