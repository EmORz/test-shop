import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//методи
//        - - методи за записване на продуктите - в името да има timestamp (точното време на записване на документа)
//        - - метод за зареждане на продуктите
//        – Методи на клиент
//        –void printAvailableProducts()
//        –void searchProductsByCategory(String category)
//        –void searchProductsByName(String name)
//        –void addToShoppingCart(int productId, int quantity)
//        –double calculateTotalPrice()
//        –void checkout() - изчиства потребителската количка и записва данните от покупката в timestamp .CSV файл - нещо като касова бележка за потребителя.
//        – Методи на служител
//        – void printAllProducts()
//        –  void sortProductsByName()
//        — void sortProductsByPrice()
//        – void sortProductsByExpirationDate()
//        – void printProductById(int productId)
//        — void printProductByName(String productName)
//        — void printProductsByPriceGreaterThan(double price)
//        –void printProductsByPriceLessThan(double price)
//        – void printProductsByQuantityGreaterThanOrEqual(int quantity)
//        – void printProductsByQuantityLessThan(int quantity)
//        – Product createProduct(int product_id, String name, double price, int quantity, String type, String color, String expires_in) - може да се добави валидация на данните.
//        –void addProduct(Product product)
//        – void changeProductPriceById(int productId, double newPrice)
//        – void changeProductQuantityById(int productId, int newQuantity)
//        –void changeProductNameById(int productId, String newName)
//        – void deleteProductById(int productId)
//        –void sortEmployeesByName()
//        –void sortEmployeesBySalary()

public class Store {
    private List<Product> products;
    private List<Product> shoppingCart;
    private List<Employee> employees;

    public static List<Employee> readEmployeeFromCSV(String csvFilePath){
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            boolean firstLine = true;
            while ((line=br.readLine()) != null){
                if (firstLine) {
                    firstLine= false;
                    continue;
                }
                //employee_id,first_name,last_name,age,salary
                String[] data = line.split(",");
                int employee_id = Integer.parseInt(data[0]);
                String first_name = data[1];
                String last_name = data[2];
                int age = Integer.parseInt(data[3]);
                double salary = Double.parseDouble(data[4]);

                Employee employee = new Employee(employee_id, first_name, last_name, age, salary);
                employees.add(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employees;
    }

    public static List<Product> readProductsFromCSV(String csvFilePath) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // Прескочете първия ред (заглавния ред)
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length == 7 || data.length == 5 || data.length == 6) {
                    int product_id = Integer.parseInt(data[0]);
                    String name = data[1];
                    double price = Double.parseDouble(data[2]);
                    int quantity = Integer.parseInt(data[3]);
                    String type = data[4];

                    String color = "";
                    String expires_in = "";

                    if (data.length == 7) {
                        color = data[5];
                        expires_in=data[6];

                    }

                    if (color == null||color=="") {
                        color = "N/A";
                    }
                    if (expires_in == null||expires_in=="") {
                        expires_in = "N/A";
                    }

                    Product product = new Product(product_id,name,quantity, price, color, expires_in);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

}
