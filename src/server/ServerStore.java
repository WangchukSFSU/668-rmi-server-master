package server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;

import common.Store;
import common.Customer;

/**
 * Process Customer objects for checkout
 * .
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
     * Processes Customer objects
     * and passes customer item list to checkout.
     * @param customer objects which is ready for checkout
     * @return true if customer is ready for checkout and
     * return false if there is any error in getting the item 
     * from the customer object.
     * @throws RemoteException
     */
    public boolean helpCustomer(Customer customer) throws RemoteException {
        return Authenticate.authenticate(customer.getPayment());
    }

    /**
     *
     * @return particular item information from the Inventory using UPC
     * as String
     * @throws RemoteException
     */
    public HashMap<String, ServerItem> getInventory() throws RemoteException {
        return catalog.getCatalog();
    }    
    
}
