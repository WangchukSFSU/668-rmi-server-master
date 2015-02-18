package server;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import common.Cash;
import common.PaymentType;

/**
 * Cash payment
 *
 */
public class ServerCash extends ServerPayment implements Cash {

	private static final long serialVersionUID = 1L;

	public ServerCash(BigDecimal amount) throws RemoteException {
		super(amount, PaymentType.CASH);
	}
}
