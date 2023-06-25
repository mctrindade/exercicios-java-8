package br.com.infnet.domain;

import br.com.infnet.exception.CustomerWithSignaturePaymentLateException;

public interface ValidationPayment {

    void validate(Customer customer) throws CustomerWithSignaturePaymentLateException;

}
