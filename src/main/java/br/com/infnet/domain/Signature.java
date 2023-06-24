package br.com.infnet.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class Signature {

    private BigDecimal monthlyPayment;
    private LocalDate begin;
    private Optional<LocalDate> end;
    private Customer customer;
    private SignatureTypeEnum signatureType;


    public Signature(BigDecimal monthlyPayment, LocalDate begin,  LocalDate end, Customer customer) {
        this.monthlyPayment = monthlyPayment;
        this.begin = begin;
        this.end = Optional.of(end);
        this.customer = customer;
    }

    public Signature(BigDecimal monthlyPayment, LocalDate begin, Customer customer) {
        this.monthlyPayment = monthlyPayment;
        this.begin = begin;
        this.customer = customer;
        this.end = Optional.empty();
    }

    public BigDecimal calculateSignatureRate(){

       int amountOfMonthsBetweenSignatureStartAndCurrentDate = Period.between(this.begin, LocalDate.now()).getMonths();
       BigDecimal totalPaid =  monthlyPayment.multiply(BigDecimal.valueOf(amountOfMonthsBetweenSignatureStartAndCurrentDate));
          switch (signatureType){
              case SEMESTER:
                  return totalPaid.divide(new BigDecimal("0.03"));
              case QUARTERLY:
                  return totalPaid.divide(new BigDecimal("0.05"));
              default:
                  return BigDecimal.ZERO;
          }
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
