package br.com.infnet.exception;

public class CustomerWithSignaturePaymentLateException extends Exception {

    public CustomerWithSignaturePaymentLateException(String message) {
        super(message);
    }
}
