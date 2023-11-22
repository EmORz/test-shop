package src.Entity;

import src.Store;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//– userId;
//        – String userName;
//        - void login(int userId, String userName);
public class User implements src.Interfaces.User {
    private int userId;
    private String userName;
    private List<Product> shoppingCart;
    private Store store = new Store();

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.shoppingCart = new ArrayList<>();
    }

    @Override
    public void login(int employee_id, String first_name) {
        System.out.println("Добре дошли "+first_name+"!");
    }

    public void printAvailableProducts(){
        for (Product product: store.getProducts()) {
            System.out.println(product.toString());
        }
    }

//    public void searchProductsByCategory(String category){
//        for (src.Entity.Product product:Store.getProducts()) {
//            System.out.println(product.toString());
//        }
//    }

    public void searchProductsByCategory(String category){
        for (Product product: store.getProducts()) {
            if(product instanceof DrinksProduct && category.equalsIgnoreCase("Drinks")
            || product instanceof  FoodProduct && category.equalsIgnoreCase("Food")
            ||product instanceof  MakeUpProduct && category.equalsIgnoreCase("Makeup")
            ||product instanceof  OthersProduct && category.equalsIgnoreCase("Others")
            || product instanceof  SanitaryProduct && category.equalsIgnoreCase("Sanitary")){
                System.out.println(product.toString());
            }
        }
    }

    public void searchProductByName(String name){
        for (Product product: store.getProducts()) {
            if (product.getName().equalsIgnoreCase(name)){
                System.out.println(product.toString());
            }
        }
    }

    public void addToShoppingCart(int productId,int quantity){
        Product product=findProductById(productId);
        if(product!=null && product.getQuantity()>=quantity){
            Product cartItme=new Product(product.getProduct_id(),product.getName(),quantity,product.getPrice(),
                    product.getCategory(),product.getColor(),product.getExpires_in());
            shoppingCart.add(cartItme);
            product.setQuantity(product.getQuantity()-quantity);
            System.out.println("Added"+quantity+" "+product.getName()+"to the shopping cart.");
        }else {
            System.out.println("src.Entity.Product not found or insufficient quantity.");
        }
    }

    public double calculateTotalPrice(){
        double totalPrice=0.0;
        for (Product cartItem:shoppingCart) {
            totalPrice+=cartItem.getPrice()*cartItem.getQuantity();
        }
        return totalPrice;
    }

    public void checkout(){
        if(shoppingCart.isEmpty()){
            System.out.println("Shopping cart is empty.");
            return;
        }
        String timestamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileName="receipt_"+userId+"-"+timestamp+".csv";

        try(PrintWriter writer=new PrintWriter(new FileWriter(fileName))){
            writer.println("src.Entity.Product ID, Name, Quantity, Price, Total Price");
            for (Product cartItem:shoppingCart) {
                writer.println(cartItem.getProduct_id()+", "+ cartItem.getName()+", "+cartItem.getQuantity()+", "+cartItem.getPrice()+", "+(cartItem.getPrice()*cartItem.getQuantity()));
            }

            double totalPrice=calculateTotalPrice();
            writer.println("Total Price: "+totalPrice);

            System.out.println("Checkout completed. Receipt saved as "+fileName);
            shoppingCart.clear();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private Product findProductById(int productId){
        for (Product product: store.getProducts()) {
            if(product.getProduct_id()==productId){
                return product;
            }
        }
        return null;
    }
}
