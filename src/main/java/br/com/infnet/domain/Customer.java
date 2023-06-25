package br.com.infnet.domain;

public class Customer {

    private String name;
    private Boolean signaturePaymentLate;

    public Boolean  hasMonthlySignaturePaymentLate() {
        return signaturePaymentLate;
    }

    public void setSignaturePaymentLate(Boolean signaturePaymentLate) {
        this.signaturePaymentLate = signaturePaymentLate;
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
