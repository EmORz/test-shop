import java.time.LocalDate;

public class OthersProduct extends Product{
    public OthersProduct(int product_id, String name, int quantity, int price, String color, LocalDate expires_in) {
        super(product_id, name, quantity, price, color, expires_in);
    }
}
