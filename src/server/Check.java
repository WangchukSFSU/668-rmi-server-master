package server;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import common.PaymentType;

/**
 * Check payment
 *
 */
public class Check extends ServerPayment {

	private static final long serialVersionUID = 1L;

	public Check(BigDecimal amount) throws RemoteException {
		super(amount, PaymentType.CHECK);
	}
}
