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
    private String expires_in;

    public Product(int product_id, String name, int quantity, double price, String color, String expires_in) {
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

    public String getExpires_in() {
        return expires_in;
    }

    public void setQuantity(int i){}
}
