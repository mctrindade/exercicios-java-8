package br.com.infnet.service;

import br.com.infnet.domain.Customer;
import br.com.infnet.domain.Payment;
import br.com.infnet.domain.Product;


import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentService {


    public static void printOrderedPayments(List<Payment> payments){
        payments.sort(Comparator.comparing(p -> p.getBuyDate()));
        System.out.println(payments);
    }

    public static void printsSumPayments(List<Payment> payments){

        List<List<Product>> paymentProducts = payments.stream().map(Payment::getProducts).collect(Collectors.toList());
        paymentProducts.stream().forEach(p -> {
            OptionalDouble sumOpt = OptionalDouble.of(p.stream().mapToDouble(pro -> pro.getPrice().doubleValue()).reduce(0.0, (a, b) -> a + b));
            if (sumOpt.isPresent())
                System.out.println("SOMA DOS PRODUTOS: " + sumOpt.getAsDouble());
        });

    }

    public static void printSumOfAllPayments(List<Payment> payments){

        List<List<Product>> paymentProducts = payments.stream().map(Payment::getProducts).collect(Collectors.toList());
        List<Double> sumPaymentValues = new ArrayList<>();
        paymentProducts.stream().forEach(p -> {
            sumPaymentValues.add(p.stream().mapToDouble(pro -> pro.getPrice().doubleValue()).reduce(0.0, (a, b) -> a + b));
        });

        System.out.println("SOMA TOTAL PRODUTOS: " + sumPaymentValues.stream().reduce(0.0, (a, b) -> a + b));
    }

    public static void printTheAmountOfEachProductSelled(List<Payment> payments, List<Product> allProducts){
        List<List<Product>> paymentProducts = payments.stream().map(Payment::getProducts).collect(Collectors.toList());
        List<Product> allProductSold = new ArrayList<>();
        paymentProducts.stream().forEach(allProductSold::addAll);

        allProducts.stream().forEach(p -> {
            System.out.println("Quantidade vendida do produto " + p.getName() + " : " + allProductSold.stream().filter(ps -> ps.equals(p)).count());

        });

    }

    public static Map<Customer, List<Product>> createMapCustomerProducts(List<Payment> payments){
        Map<Customer, List<List<Product>>> customerToProducts = payments.stream().collect(Collectors.groupingBy(Payment::getCustomer, Collectors.mapping(Payment::getProducts, Collectors.toList())));
        return customerToProducts.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream).collect(Collectors.toList())));
    }


    public static Customer getTheConsumerWhoSpentMoreMoney(List<Payment> payments){

        Map<Customer, List<Product>> costumerProductsMap = createMapCustomerProducts(payments);
        Map<Customer, BigDecimal> customerWithTheValueSpent = costumerProductsMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().map(p -> p.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add)));
        List<Map.Entry<Customer, BigDecimal>> listSells = customerWithTheValueSpent.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());
        return listSells.get(0).getKey();

    }

    public static BigDecimal HowMuchWasSelledByMonth(List<Payment> payments, Month month){
        List<Payment> junePayments = payments.stream().filter(k -> k.getBuyDate().getMonth().equals(month)).collect(Collectors.toList());
        List<Product> allProductSoldInJune = new ArrayList<>();
        junePayments.stream().map( pay -> pay.getProducts()).forEach(allProductSoldInJune::addAll);
        return  allProductSoldInJune.stream().map(p -> p.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
