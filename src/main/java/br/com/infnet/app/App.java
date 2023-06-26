package br.com.infnet.app;

import static br.com.infnet.util.PrintConsole.print;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.infnet.domain.Customer;
import br.com.infnet.domain.CustomerWithSignaturePaymentLateValidation;
import br.com.infnet.domain.Payment;
import br.com.infnet.domain.Product;
import br.com.infnet.domain.Signature;
import br.com.infnet.exception.CustomerWithSignaturePaymentLateException;
import br.com.infnet.service.PaymentService;
import br.com.infnet.service.SignatureService;


public class App {

    private static final String PRICE_SIGNATURE = "99.98";
	private static final String PRINT_LINE = "--------------------------------------------------------------------------------";

	public static void main(String[] args) throws CustomerWithSignaturePaymentLateException {

        //1 Crie uma Classe com um método main para criar alguns produtos, clientes e pagamentos.
        //  Crie Pagamentos com:  a data de hoje, ontem e um do mês passado.

        Product p1 = new Product("So far away", null, new BigDecimal("18.00"));
        Product p2 = new Product("Hold the line", null, new BigDecimal("23.00"));
        Product p3 = new Product("Highway to Hell", null, new BigDecimal("15.00"));
        Product p4 = new Product("Have you ever seen the rain", null, new BigDecimal("30.00"));
        Product p5 = new Product("Fortunate Son", null, new BigDecimal("16.00"));
        Product p6 = new Product("Simple Man", null, new BigDecimal("50.00"));


        Customer c1 = new Customer("João Pedro");
        Customer c2 = new Customer("Maria José");
        Customer c3 = new Customer("Carolina da Silva");

        List<Product> productList1 = new ArrayList<>();
        productList1.add(p1);
        productList1.add(p2);
        List<Product> productList2 = new ArrayList<>();
        productList2.add(p3);
        productList2.add(p4);
        List<Product> productList3 = new ArrayList<>();
        productList3.add(p5);
        productList3.add(p6);

        Payment payment1 = new Payment(productList1, LocalDate.now(), c1, new ArrayList<>());
        Payment payment2 = new Payment(productList2, LocalDate.now().minusDays(1), c2,  new ArrayList<>());
        Payment payment3 = new Payment(productList3, LocalDate.now().minusMonths(1), c3,  Arrays.asList(new CustomerWithSignaturePaymentLateValidation()));

        //2 - Ordene e imprima os pagamentos pela data de compra.

        List<Payment> payments = Arrays.asList(payment1, payment2, payment3);

        PaymentService.printOrderedPayments(payments);

        print(PRINT_LINE);
        //3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.

        PaymentService.printsSumPayments(payments);

        print(PRINT_LINE);

        // 4- Calcule o Valor de todos os pagamentos da Lista de pagamentos.

       PaymentService.printSumOfAllPayments(payments);

        print(PRINT_LINE);

        // 5- Imprima a quantidade de cada Produto vendido.

        List<Product> allProducts = Arrays.asList(p1, p2, p3, p4, p5, p6);

        PaymentService.printTheAmountOfEachProductSelled(payments, allProducts);

        print(PRINT_LINE);

        // 6 Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente.

        PaymentService.createMapCustomerProducts(payments);

        // 7 - Qual cliente gastou mais?

        print("Cliente que gastou mais " + PaymentService.getTheConsumerWhoSpentMoreMoney(payments).getName());
        print(PRINT_LINE);

        //8 - Quanto foi faturado em um determinado mês?

        print("Valor faturado em Junho: " + PaymentService.howMuchWasSelledByMonth(payments, Month.JUNE));

        print(PRINT_LINE);

        // 9 - Crie 3 assinaturas com assinaturas de 99.98 reais, sendo 2 deles com assinaturas encerradas.

        Signature s1 = new Signature(new BigDecimal(PRICE_SIGNATURE), LocalDate.now().minusMonths(10), c1);
        Signature s2 = new Signature(new BigDecimal(PRICE_SIGNATURE), LocalDate.now().minusMonths(6),LocalDate.now().minusMonths(1), c2);
        Signature s3 = new Signature(new BigDecimal(PRICE_SIGNATURE),  LocalDate.now().minusMonths(2), LocalDate.now().minusMonths(1), c3);

        //10 - Imprima o tempo em meses de alguma assinatura ainda ativa.

        print("Tempo em meses de assinatura ativa da assinatura s1: " +  SignatureService.timeInMonthsSignatureActive(s1));

        print(PRINT_LINE);

        //11 - Imprima o tempo de meses entre o start e end de todas assinaturas. Não utilize IFs para assinaturas sem end Time.

        List<Signature> signatures = Arrays.asList(s1,s2,s3);
        SignatureService.printTheTimeInMonthsAllSignatures(signatures);

        print(PRINT_LINE);

        //12 - Calcule o valor pago em cada assinatura até o momento.
        SignatureService.printTheValuePaidInEachSignature(signatures);

    }
	
}