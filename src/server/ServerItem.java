package server;


import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.math.BigDecimal;
import java.rmi.RemoteException;

import common.Item;

public class ServerItem extends UnicastRemoteObject implements Item {
	private String upc;
	private String description;
	private String gif;
	private BigDecimal price;
	
	public ServerItem(String UPC, String description, BigDecimal price) {
		this.upc = UPC;
		this.description = description;
		this.price = price;
	}

	@Override
	public String getDescription() throws RemoteException {
		return this.description;
	
	}

	@Override
	public BigDecimal getPrice() throws RemoteException {
		return this.price;
	}

	@Override
	public String getUPC() throws RemoteException {
		return this.upc;
	}

}

