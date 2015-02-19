package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;

import common.Item;

public class CatalogReader {

	private static HashMap<String, Item> items;
	private static String filePath = "products.txt";
	private static BufferedReader bufferedReader;

	/*
	 * Reads products.txt which is a formatted text file that contains item
	 * information for all valid items.
	 * 
	 * @return true operation succeeded
	 * 
	 * @return false operation ended in error
	 */
	public static HashMap<String, Item> readCatalogFile() {
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			items = new HashMap<String, Item>();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			return null;
		}

		String line = " ";
		while (line != null) {
			try {
				line = bufferedReader.readLine();

				if (line != null) {
					String[] tokens = new String[3];
					tokens[0] = line.substring(0, 4);
					tokens[1] = line.substring(9, 29);
					tokens[2] = line.substring(34, 41);

					items.put(tokens[0], new ServerItem(tokens[0], tokens[1], new BigDecimal(tokens[2])));
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		return items;
	}
}
