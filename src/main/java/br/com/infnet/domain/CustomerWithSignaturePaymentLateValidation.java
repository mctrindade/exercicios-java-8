package br.com.infnet.domain;

import br.com.infnet.exception.CustomerWithSignaturePaymentLateException;

public class CustomerWithSignaturePaymentLateValidation implements ValidationPayment{

    @Override
    public void validate(Customer customer) throws CustomerWithSignaturePaymentLateException {
        if(customer.isSignaturePaymentLate()){
            throw new CustomerWithSignaturePaymentLateException("Customer with signature payment late");
        }
    }
}
