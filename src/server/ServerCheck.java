package server;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import common.Check;
import common.PaymentType;

/**
 * Check payment
 *
 */
public class ServerCheck extends ServerPayment implements Check {

	private static final long serialVersionUID = 1L;

	public ServerCheck(BigDecimal amount) throws RemoteException {
		super(amount, PaymentType.CHECK);
	}
}
