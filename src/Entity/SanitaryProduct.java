package src.Entity;

import src.Enums.ProductCategory;

import java.time.LocalDate;

public class SanitaryProduct extends Product{
    public SanitaryProduct(int product_id, String name, int quantity, double price, ProductCategory category, String color, LocalDate expires_in) {
        super(product_id, name, quantity, price, category, color, expires_in);
    }

    @Override
    public ProductCategory getCategory() {
        return super.getCategory();
    }

    @Override
    public void setCategory(ProductCategory category) {
        super.setCategory(category);
    }

    @Override
    public int getProduct_id() {
        return super.getProduct_id();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public LocalDate getExpires_in() {
        return super.getExpires_in();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public String toString() {
        return String.format("***\nsrc.Entity.SanitaryProduct{\nid=%d\nname=%s\nquantity=%d\n" +
                "price=%.2f\ntype=%s\nexpires date=%s\n}***", getProduct_id(), getName(),getQuantity(),getPrice(), getCategory(), getExpires_in());
    }}
