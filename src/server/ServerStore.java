package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import common.Constants;
import common.Customer;
import common.Item;
import common.Store;

/**
 * Process Customer objects for checkout .
 */
public class ServerStore extends UnicastRemoteObject implements Store {

	private ServerManager manager;
	private String name;

	/**
	 *
	 * @throws RemoteException
	 */
	public ServerStore() throws RemoteException {
		this.manager = new ServerManager();
		this.name = "MyStore";
	}

	public static void main(String[] args) {
		try {
			ServerStore store = new ServerStore();

			Registry registry = LocateRegistry.createRegistry(Constants.REGISTRY_PORT);
			registry.rebind(Constants.STORE_ID, store);

			System.out.println("Server is running");
		} catch (RemoteException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 *
	 * @return particular item information from the Inventory using UPC as
	 *         String
	 * @throws RemoteException
	 */
	@Override
	public HashMap<String, Item> getCatalog() throws RemoteException {
		return manager.getCatalog();
	}

	/**
	 * 
	 * Records the sale
	 * 
	 * @param customer
	 *            sale
	 * @return true if customer sale is successfully recorded and return false
	 *         it is not recorded.
	 * @throws RemoteException
	 */
	@Override
	public boolean recordSale(Customer customer) throws RemoteException {
		SalesLog sales = new SalesLog(name, customer.getName(), customer.getItems(), customer.getPaymentType(), customer.getPaymentAmount());
		System.out.println(sales.writeLog());
		return true;
	}

}
