package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import common.Customer;
import common.Item;
import common.Store;

/**
 * Process Customer objects for checkout .
 */
public class ServerStore extends UnicastRemoteObject implements Store {

	private Catalog catalog;

	/**
	 *
	 * @throws RemoteException
	 */
	public ServerStore() throws RemoteException {
		this.catalog = new Catalog();
	}

	/**
	 *
	 * @return particular item information from the Inventory using UPC as
	 *         String
	 * @throws RemoteException
	 */
	@Override
	public HashMap<String, Item> getInventory() throws RemoteException {
		return catalog.getCatalog();
	}

	/**
	 * Processes Customer objects and passes customer item list to checkout.
	 * 
	 * @param customer objects which is ready for checkout
	 * @return true if customer is ready for checkout and return false if there is any error in getting the item from the customer object.
	 * @throws RemoteException
	 */
	@Override
	public boolean processCustomer(Customer customer) throws RemoteException {
		return Authenticate.authenticate(customer.getPayment());
	}

}
