package br.com.infnet.service;


import br.com.infnet.domain.Customer;
import br.com.infnet.domain.Signature;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class SignatureService {

    private static LocalDate hoje = LocalDate.now();

    public static int timeInMonthsSignatureActive(Signature signature){
        Period period = Period.between(signature.getBegin(), LocalDateTime.now().toLocalDate());
        return  period.getMonths();
    }


    public static void printTheTimeInMonthsAllSignatures(List<Signature> signatures){


        signatures.stream().forEach(s -> {
            Period periodo2 = Period.between(s.getBegin(), s.getEnd().orElse(hoje));
            System.out.println("Tempo em meses de assinatura entre start e end da assinatura do  " + s.getCustomer().getName() + " : " + periodo2.getMonths());
        });

    }

    public static void printTheValuePaidInEachSignature(List<Signature> signatures){

        signatures.stream().forEach(s -> {
            Period period =  Period.between(s.getBegin(), s.getEnd().orElse(hoje));
            BigDecimal valuePaid = s.getMonthlyPayment().multiply(BigDecimal.valueOf(period.getMonths()));
            System.out.println("Valor pago de assinatura do cliente " + s.getCustomer().getName() + " : "+valuePaid);
        });

    }

    public static void checkIfCustomerSignatureIsLate(Customer customer){


    }



}
