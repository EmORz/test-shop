package src.Entity;

import src.Printer;
import src.Store;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//– userId;
//        – String userName;
//        - void login(int userId, String userName);
public class User implements src.Interfaces.User {
    private int userId;
    private String userName;
    private List<Product> shoppingCart;
    private Store store = new Store();
    private static Scanner scanner = new Scanner(System.in);
    private Printer printer = new Printer();

    public User() {
        this.shoppingCart = new ArrayList<>();
    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.shoppingCart = new ArrayList<>();
    }



    @Override
    public void login(int user_id, String first_name) {

        System.out.println("Добре дошли "+first_name+"!");
        System.out.println("Изберете опция:");
    }

    public void printAvailableProducts(){
        store.getProducts()
                .forEach(product -> printer.printProductDetails(product));
    }


    public void searchProductsByPartOfName(){
        this.scanner = new Scanner(System.in);

        System.out.println("Въведете текст за търсене: ");
        String search = scanner.nextLine().toLowerCase();

        List<Product> findProducts = store
                .getProducts()
                .stream()
                .filter(product -> product.getName().toLowerCase().contains(search))
                .collect(Collectors.toList());

        if (!findProducts.isEmpty()) {
            System.out.println("Намерени са " + findProducts.size() + " бр. продукти с "+search);
            findProducts
                    .forEach(printer::printProductDetails);
        }
        else {
            System.out.println("Няма продукт съдържащ - "+search);
        }
    }

    public void searchProductsByCategory(){
        this.scanner = new Scanner(System.in);
        System.out.println("Въведете категория на продукт: food, drinks, sanitary, others, makeup");
        String category = scanner.next();
        boolean isCategoryAvailable = false;
        for (Product product: store.getProducts()) {
            if(product instanceof DrinksProduct && category.equalsIgnoreCase("Drinks")
            || product instanceof  FoodProduct && category.equalsIgnoreCase("Food")
            ||product instanceof  MakeUpProduct && category.equalsIgnoreCase("Makeup")
            ||product instanceof  OthersProduct && category.equalsIgnoreCase("Others")
            || product instanceof  SanitaryProduct && category.equalsIgnoreCase("Sanitary")){
                this.printer.printProductDetails(product);
                isCategoryAvailable=true;
            }
        }
        if (isCategoryAvailable == false) {
            System.out.println("Търсената категория "+category+" не налична!");
        }
    }

    public void searchProductByName(){
        this.scanner = new Scanner(System.in);
        System.out.println("Въведете име на продукта: ");
        String name = scanner.nextLine().toLowerCase();

        boolean isProductAvailable = store.getProducts()
                .stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .peek(product -> printer.printProductDetails(product))
                .findFirst()
                .isPresent();

        if (isProductAvailable==false){
            System.out.println("Търсения продукт "+name+" не е наличен! ");
        }
    }

    public void addToShoppingCart(InputStream inputStream){
        this.scanner = new Scanner(inputStream);
        System.out.println("Въведете ID на продукта: ");
        int productId = this.scanner.nextInt();
        Product product=findProductById(productId);
        System.out.println("Въведете количество на продукта: ");
        int quantity = scanner.nextInt();
        if(product!=null && product.getQuantity()>=quantity){
            Product cartItme=new Product(product.getProduct_id(),product.getName(),quantity,product.getPrice(),
                    product.getCategory(),product.getColor(),product.getExpires_in());
            shoppingCart.add(cartItme);
            product.setQuantity(product.getQuantity()-quantity);
            System.out.println("Added "+quantity+" "+product.getName()+" to the shopping cart.");
        }else {
            System.out.println("src.Entity.Product not found or insufficient quantity.");
        }
    }

    public double calculateTotalPrice(){

        double totalPrice = shoppingCart
                .stream()
                .mapToDouble(product -> product.getPrice()*product.getQuantity())
                .reduce(0.0, (total, productTotal)-> total+productTotal);
        return totalPrice;
    }

    public void checkout(boolean testMode){
        if(shoppingCart.isEmpty()){
            System.out.println("Shopping cart is empty.");
            return;
        }
        String folderPath = testMode==false?"src\\ProductFolder":"src\\TestData";
        String timestamp= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileName= "";

        String text ="test";
        if (testMode == false) {
            fileName= folderPath+File.separator +"receipt_"+userId+"-"+timestamp+".csv";
        } else if (testMode == true) {
            timestamp = "20220101120000";
            fileName= folderPath+File.separator +"receipt_"+text+"-"+timestamp+".csv";
        }

        store.saveProductAfterShopping();

        try(PrintWriter writer=new PrintWriter(new FileWriter(fileName))){
            writer.println("src.Entity.Product ID;Name;Quantity;Price;Total Price");
            for (Product cartItem:shoppingCart) {
                writer.println(cartItem.getProduct_id()+";"+ cartItem.getName()+";"+cartItem.getQuantity()+
                        ";"+cartItem.getPrice()+";"+(cartItem.getPrice()*cartItem.getQuantity()));
            }

            double totalPrice=calculateTotalPrice();
            writer.println("Total Price: "+totalPrice);

            System.out.println("Checkout completed. Receipt saved as "+fileName);
            shoppingCart.clear();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public Product findProductById(int productId){
        Product product = store.getProducts().stream()
                .filter(product1 -> product1.getProduct_id()==productId)
                .findFirst()
                .orElse(null);

        return product;
    }
}
