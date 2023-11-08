import java.util.Scanner;
//public Product createProduct (int product_id, String name, int quantity, double price, String color, String expires_in){
//        Product product = new Product(product_id,name,quantity, price, color, expires_in);
//        addProduct(product);
//        return product;
//        }
//3,lipstick,6.80,15,makeup,red,04-02-2025
//        4,dog leash,10.30,6,others,blue,
public class Main {

    public static void main(String[] args) {

        Store store = new Store();

        Scanner scanner = new Scanner(System.in);
//3,Angel,Petkov,50,750
        System.out.print("Въведете име на файл с продукти: ");
        String productsFileName = "product.CSV";
        var produtData = Store.readProductsFromCSV(productsFileName);
        var employeeFileName = "employee.CSV";
        var employeeData = Store.readEmployeeFromCSV(employeeFileName);
        System.out.println();
//        store.createProduct(4, "Мюсли", 20, 6.25,"Н/А","01-02-2014");
//        store.createEmployee(4, "Ivan", "Ivanov", 35, 820);
//        store.printAllProducts();
        store.deleteProductById(5);
//        store.deleteEmployeeById(2);
//        store.findProductById(5);





    }
}
