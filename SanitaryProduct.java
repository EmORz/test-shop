import java.time.LocalDate;

public class SanitaryProduct extends Product{
    public SanitaryProduct(int product_id, String name, int quantity, int price,String type, String color, LocalDate expires_in) {
        super(product_id, name, quantity, price,type, color, expires_in);
    }
}
