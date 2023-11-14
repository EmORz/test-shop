import java.time.LocalDate;

public class SanitaryProduct extends Product{
    public SanitaryProduct(int product_id, String name, int quantity, double price,String type, String color, LocalDate expires_in) {
        super(product_id, name, quantity, price,type, color, expires_in);
    }

    @Override
    public String toString() {
        return String.format("***\nSanitaryProduct{\nid=%d\nname=%s\nquantity=%d\n" +
                "price=%.2f\ntype=%s\nexpires date=%s\n}***", getProduct_id(), getName(),getQuantity(),getPrice(), getType(), getExpires_in());
    }}
