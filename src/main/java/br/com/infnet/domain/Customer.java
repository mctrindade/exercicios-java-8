package br.com.infnet.domain;

public class Customer {

    private String name;
    private boolean signaturePaymentLate;    

    public Customer(String name) {
        this.name = name;
    }

    public boolean isSignaturePaymentLate() {
		return signaturePaymentLate;
	}

	public void setSignaturePaymentLate(boolean signaturePaymentLate) {
		this.signaturePaymentLate = signaturePaymentLate;
	}

	public String getName() {
        return name;
    }
}
