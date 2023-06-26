package br.com.infnet.service;

import static br.com.infnet.util.PrintConsole.print;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import br.com.infnet.domain.Signature;

public class SignatureService {
	
	private SignatureService(){}
	
    private static LocalDate hoje = LocalDate.now();

    public static int timeInMonthsSignatureActive(Signature signature){
        Period period = Period.between(signature.getBegin(), LocalDateTime.now().toLocalDate());
        return  period.getMonths();
    }


    public static void printTheTimeInMonthsAllSignatures(List<Signature> signatures){
        signatures.stream().forEach(s -> {
            Period periodo2 = Period.between(s.getBegin(), s.getEnd().orElse(hoje));
            print("Tempo em meses de assinatura entre start e end da assinatura do  " + s.getCustomer().getName() + " : " + periodo2.getMonths());
        });

    }

    public static void printTheValuePaidInEachSignature(List<Signature> signatures){

        signatures.stream().forEach(s -> {
            Period period =  Period.between(s.getBegin(), s.getEnd().orElse(hoje));
            BigDecimal valuePaid = s.getMonthlyPayment().multiply(BigDecimal.valueOf(period.getMonths()));
            print("Valor pago de assinatura do cliente " + s.getCustomer().getName() + " : "+valuePaid);
        });

    }



}
