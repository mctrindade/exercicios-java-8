package br.com.infnet.domain;

import br.com.infnet.exception.CustomerWithSignaturePaymentLateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Payment {

    private List<Product> products;
    private LocalDate buyDate;
    private Customer customer;
    List<ValidationPayment>  validations = new ArrayList<>();

    public Payment(List<Product> products, LocalDate buyDate, Customer customer,   List<ValidationPayment>  validations ) throws CustomerWithSignaturePaymentLateException {
        this.products = products;
        this.buyDate = buyDate;
        this.customer = customer;
        this.validations = validations;
        applyValidations();
    }

   private void applyValidations() throws CustomerWithSignaturePaymentLateException {
        for(ValidationPayment vp : validations){
            vp.validate(customer);
        }
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "products=" + products +
                ", buyDate=" + buyDate +
                ", customer=" + customer +
                '}' + "\n";
    }
}
