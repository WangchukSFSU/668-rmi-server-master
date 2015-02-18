package server;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import common.PaymentType;

/**
 * Cash payment
 *
 */
public class Cash extends ServerPayment {

	private static final long serialVersionUID = 1L;

	public Cash(BigDecimal amount) throws RemoteException {
		super(amount, PaymentType.CASH);
	}

}
