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
 * Provides access to catalog information and records sales
 */
public class ServerStore extends UnicastRemoteObject implements Store {

	private ServerManager manager;
	private String name;

	/**
	 * @throws RemoteException
	 */
	public ServerStore() throws RemoteException {
		this.manager = new ServerManager();
		this.name = "MyStore";
	}

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

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
     * @return HashMap of Item UPCs and Items
     * @throws RemoteException
     */
	@Override
	public HashMap<String, Item> getCatalog() throws RemoteException {
		return manager.getCatalog();
	}

	/**
     * @param customer object with sale information to be recorded
     * @return a String formatted as a Sales Receipt
     * @throws RemoteException
     */
	@Override
	public String recordSale(Customer customer) throws RemoteException {
		SalesLog sales = new SalesLog(name, customer.getName(), customer.getItems(), customer.getPaymentType(), customer.getPaymentAmount());
		return sales.writeLog();
	}

}
