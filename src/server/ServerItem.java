package server;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import common.Item;

public class ServerItem implements Item {

	public ServerItem(String UPC, String description, BigDecimal price) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getPrice() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUPC() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
