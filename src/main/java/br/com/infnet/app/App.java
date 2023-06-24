package br.com.infnet.app;

import br.com.infnet.domain.Customer;
import br.com.infnet.domain.Payment;
import br.com.infnet.domain.Product;
import br.com.infnet.domain.Signature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

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

        Payment payment1 = new Payment(productList1, LocalDate.now(), c1);
        Payment payment2 = new Payment(productList2, LocalDate.now().minusDays(1), c2);
        Payment payment3 = new Payment(productList3, LocalDate.now().minusMonths(1), c3);

        //2 - Ordene e imprima os pagamentos pela data de compra.

        List<Payment> payments = Arrays.asList(payment1, payment2, payment3);
        payments.sort(Comparator.comparing(p -> p.getBuyDate()));
        System.out.println(payments);

        System.out.println("--------------------------------------------------------------------------------");
        //3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.

        List<List<Product>> paymentProducts = payments.stream().map(Payment::getProducts).collect(Collectors.toList());
        paymentProducts.stream().forEach(p -> {
            OptionalDouble sumOpt = OptionalDouble.of(p.stream().mapToDouble(pro -> pro.getPrice().doubleValue()).reduce(0.0, (a, b) -> a + b));
            if (sumOpt.isPresent())
                System.out.println("SOMA DOS PRODUTOS: " + sumOpt.getAsDouble());
        });

        System.out.println("--------------------------------------------------------------------------------");

        // 4- Calcule o Valor de todos os pagamentos da Lista de pagamentos.

        List<Double> sumPaymentValues = new ArrayList<>();
        paymentProducts.stream().forEach(p -> {
            sumPaymentValues.add(p.stream().mapToDouble(pro -> pro.getPrice().doubleValue()).reduce(0.0, (a, b) -> a + b));
        });

        System.out.println("SOMA TOTAL PRODUTOS: " + sumPaymentValues.stream().reduce(0.0, (a, b) -> a + b));

        System.out.println("--------------------------------------------------------------------------------");

        // 5- Imprima a quantidade de cada Produto vendido.

        List<Product> allProducts = Arrays.asList(p1, p2, p3, p4, p5, p6);
        List<Product> allProductSold = new ArrayList<>();
        paymentProducts.stream().forEach(allProductSold::addAll);

        allProducts.stream().forEach(p -> {
            System.out.println("Quantidade vendida do produto " + p.getName() + " : " + allProductSold.stream().filter(ps -> ps.equals(p)).count());

        });

        System.out.println("--------------------------------------------------------------------------------");

        // 6 Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente.

        Map<Customer, List<List<Product>>> customerToProducts = payments.stream().collect(Collectors.groupingBy(Payment::getCustomer, Collectors.mapping(Payment::getProducts, Collectors.toList())));

        Map<Customer, List<Product>> costumerProductsMap = customerToProducts.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream).collect(Collectors.toList())));


        // 7 - Qual cliente gastou mais?

        Function<Payment, BigDecimal> reducingFunction = p -> p.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<Customer, BigDecimal> customerMoneySpentMap = payments.stream().collect(Collectors.groupingBy(Payment::getCustomer, Collectors.reducing(BigDecimal.ZERO, reducingFunction, BigDecimal::add)));

        List<Map.Entry<Customer, BigDecimal>> listSells = customerMoneySpentMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());

        System.out.println("Cliente que gastou mais " + listSells.get(0).getKey().getName());

        System.out.println("--------------------------------------------------------------------------------");

        //8 - Quanto foi faturado em um determinado mês?

        List<Payment> junePayments = payments.stream().filter(k -> k.getBuyDate().getMonth().equals(Month.JUNE)).collect(Collectors.toList());
        List<Product> allProductSoldInJune = new ArrayList<>();
        junePayments.stream().map( pay -> pay.getProducts()).forEach(allProductSoldInJune::addAll);
        BigDecimal sum = allProductSoldInJune.stream().map(p -> p.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Valor faturado em Junho: " + sum.toString());

        System.out.println("--------------------------------------------------------------------------------");

        // 9 - Crie 3 assinaturas com assinaturas de 99.98 reais, sendo 2 deles com assinaturas encerradas.

        Signature s1 = new Signature(new BigDecimal("99.98"), LocalDate.now().minusMonths(10), c1);
        Signature s2 = new Signature(new BigDecimal("99.98"), LocalDate.now().minusMonths(6),LocalDate.now().minusMonths(1), c2);
        Signature s3 = new Signature(new BigDecimal("99.98"),  LocalDate.now().minusMonths(2), LocalDate.now().minusMonths(1), c3);

        //10 - Imprima o tempo em meses de alguma assinatura ainda ativa.

        Period periodo = Period.between(s1.getBegin(), LocalDateTime.now().toLocalDate());

        System.out.println("Tempo em meses de assinatura ativa da assinatura s1: " + periodo.getMonths());

        System.out.println("--------------------------------------------------------------------------------");

        //11 - Imprima o tempo de meses entre o start e end de todas assinaturas. Não utilize IFs para assinaturas sem end Time.

        List<Signature> signaturesEnabled = Arrays.asList(s1,s2,s3);

        LocalDate hoje = LocalDate.now();

        signaturesEnabled.stream().forEach(s -> {
            Period periodo2 = Period.between(s.getBegin(), s.getEnd().orElse(hoje));
            System.out.println("Tempo em meses de assinatura entre start e end da assinatura do  " + s.getCustomer().getName() + " : " + periodo2.getMonths());
        });


        System.out.println("--------------------------------------------------------------------------------");

        //12 - Calcule o valor pago em cada assinatura até o momento.
        List<Signature> signatures =  Arrays.asList(s1, s2, s3 );

        signatures.stream().forEach(s -> {
            Period period =  Period.between(s.getBegin(), s.getEnd().orElse(hoje));
            BigDecimal valuePaid = s.getMonthlyPayment().multiply(BigDecimal.valueOf(period.getMonths()));
            System.out.println("Valor pago de assinatura do cliente " + s.getCustomer().getName() + " : "+valuePaid);
        });


    }
}