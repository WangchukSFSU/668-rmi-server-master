package server;

import java.util.HashMap;

/**
 * Reads products.txt and creates a singleton of all available items by UPC code
 * stored as a String.
 *
 */
public class Catalog {

	private static HashMap<String, ServerItem> catalog;

	public static HashMap<String, ServerItem> getCatalog() throws NullPointerException {
		catalog = CatalogReader.readCatalogFile();

		if (catalog == null) {
			throw new NullPointerException();
		}

		return catalog;
	}
}
