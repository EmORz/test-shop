import java.time.LocalDate;

//клас Product
//        - - product_id,
//        - - name,
//        - - price,
//        - - quantity,
//        - - color,
//        - - expires_in
public class Product {
    private int product_id;
    private  String name;
    private int quantity;
    private double price;
    private String color;
    private LocalDate expires_in;

    public Product(int product_id, String name, int quantity, double price, String color, LocalDate expires_in) {
        this.product_id = product_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.expires_in = expires_in;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public LocalDate getExpires_in() {
        return expires_in;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
