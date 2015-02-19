package server;

import java.util.HashMap;

import common.Item;

/**
 * Reads products.txt and creates a singleton of all available items by UPC code
 * stored as a String.
 *
 */
public class ServerManager {

	private HashMap<String, Item> catalog;

	public ServerManager() {
		this.catalog = getCatalog();
	}

	public HashMap<String, Item> getCatalog() throws NullPointerException {
		if (catalog == null) {
			catalog = CatalogReader.readCatalogFile();
		}

		return catalog;
	}
}
