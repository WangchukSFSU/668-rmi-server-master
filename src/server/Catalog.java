package server;

import java.util.HashMap;

/**
 * Reads products.txt and creates a singleton of all available items by UPC code
 * stored as a String.
 *
 */
public class Catalog {

	private HashMap<String, ServerItem> catalog;

	public Catalog() {
		this.catalog = getCatalog();
	}

	public HashMap<String, ServerItem> getCatalog() throws NullPointerException {
		if (catalog == null) {
			catalog = CatalogReader.readCatalogFile();
		}

		return catalog;
	}
}
