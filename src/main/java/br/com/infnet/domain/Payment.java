package br.com.infnet.domain;

import java.time.LocalDate;
import java.util.List;

public class Payment {

    private List<Product> products;
    private LocalDate buyDate;
    private Customer customer;


    public Payment(List<Product> products, LocalDate buyDate, Customer customer) {
        this.products = products;
        this.buyDate = buyDate;
        this.customer = customer;
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
