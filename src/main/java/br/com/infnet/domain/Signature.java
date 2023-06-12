package br.com.infnet.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Signature {

    private BigDecimal monthlyPayment;
    private LocalDateTime begin;
    private LocalDateTime end;
    private Customer customer;



    public Signature(BigDecimal monthlyPayment, LocalDateTime begin, LocalDateTime end, Customer customer) {
        this.monthlyPayment = monthlyPayment;
        this.begin = begin;
        this.end = end;
        this.customer = customer;
    }

    public Signature(LocalDateTime end) {
        this.end = end;
    }

    public Signature() {
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Customer getCustomer() {
        return customer;
    }
}
