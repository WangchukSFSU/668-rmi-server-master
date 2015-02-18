package server;

import java.util.HashMap;

/**
 * Reads products.txt and creates a singleton of all available items by UPC code
 * stored as a String.
 *
 */
public class Catalog {

	private static HashMap<String, ServerItem> items;

	public static HashMap<String, ServerItem> getItems() throws NullPointerException {
		items = CatalogReader.readCatalogFile();

		if (items == null) {
			throw new NullPointerException();
		}

		return items;
	}
}
