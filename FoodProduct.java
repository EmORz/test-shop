import java.time.LocalDate;

public class FoodProduct extends Product{
    public FoodProduct(int product_id, String name, int quantity, double price,String type, String color, LocalDate expires_in) {
        super(product_id, name, quantity, price,type, color, expires_in);
    }

    public String toString() {
        return String.format("***\nFoodProduct{\nid=%d\nname=%s\nquantity=%d\n" +
                "price=%.2f\ntype=%s\nexpires date=%s\n}***", getProduct_id(), getName(),getQuantity(),getPrice(), getType(), getExpires_in());
    }
}
