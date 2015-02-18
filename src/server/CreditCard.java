package server;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import common.PaymentType;

/**
 * CreditCard payment
 *
 */
public class CreditCard extends ServerPayment {

	private static final long serialVersionUID = 1L;

	private String cardNumber;

	public CreditCard(String cardNumber) throws RemoteException {
		super(new BigDecimal(0), PaymentType.CREDIT);
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
