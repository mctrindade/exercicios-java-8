package br.com.infnet.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class Signature {

    private BigDecimal monthlyPayment;
    private LocalDate begin;
    private Optional<LocalDate> end;
    private Customer customer;

    public Signature(BigDecimal monthlyPayment, LocalDate begin, Optional<LocalDate> end, Customer customer) {
        this.monthlyPayment = monthlyPayment;
        this.begin = begin;
        this.end = end;
        this.customer = customer;
    }

    public Signature(BigDecimal monthlyPayment, LocalDate begin, Customer customer) {
        this.monthlyPayment = monthlyPayment;
        this.begin = begin;
        this.customer = customer;
    }

    public Signature() {
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public Optional<LocalDate> getEnd() {
        return end;
    }

    public Customer getCustomer() {
        return customer;
    }
}
