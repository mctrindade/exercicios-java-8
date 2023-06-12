package br.com.infnet.domain;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Objects;

public class Product {

    private String name;
    private Path file;
    private BigDecimal price;

    public Product(String name, Path file, BigDecimal price) {
        this.name = name;
        this.file = file;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(file, product.file) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, file, price);
    }
}
