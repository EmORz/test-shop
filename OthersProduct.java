import java.time.LocalDate;

public class OthersProduct extends Product{
    public OthersProduct(int product_id, String name, int quantity, double price,String type, String color, LocalDate expires_in) {
        super(product_id, name, quantity, price,type , color, expires_in);
    }

    @Override
    public String getType() {
        return super.getType();
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

    public String toString() {
        return String.format("***\nOthersProduct{\nid=%d\nname=%s\nquantity=%d\n" +
                "price=%.2f\ntype=%s\nexpires date=%s\n}***", getProduct_id(), getName(),getQuantity(),getPrice(), getType(), getExpires_in());
    }
}
