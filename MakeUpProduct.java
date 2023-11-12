import java.time.LocalDate;

public class MakeUpProduct extends Product{
    public MakeUpProduct(int product_id, String name, int quantity, int price, String color, LocalDate expires_in) {
        super(product_id, name, quantity, price, color, expires_in);
    }
}
