package server;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Payment;
import common.PaymentType;

public abstract class ServerPayment extends UnicastRemoteObject implements Payment {

	private static final long serialVersionUID = 1L;

	private BigDecimal amount;
	private PaymentType type;

	protected ServerPayment(BigDecimal amount, PaymentType type) throws RemoteException {
		this.amount = amount;
		this.type = type;
	}

	@Override
	public BigDecimal getAmount() throws RemoteException {
		return amount;
	}

	public void setAmount(BigDecimal amount) throws RemoteException {
		this.amount = amount;
	}

	@Override
	public PaymentType getType() throws RemoteException {
		return type;
	}

	@Override
	public void setType(PaymentType type) throws RemoteException {
		this.type = type;
	}

}
