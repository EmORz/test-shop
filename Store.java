import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
    private static List<Product> products;
    private List<Product> shoppingCart;
    private static List<Employee> employees;

    public Store() {
        this.products = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void printAllProducts(){
        for (Product product:products
             ) {
            System.out.println("*".repeat(10));

            System.out.println("ID на продукта: "+product.getProduct_id());
            System.out.println("Име на продукта: "+product.getName());
            System.out.println("Количество на продукта: "+product.getQuantity());
            System.out.println("Цена на продукта: "+product.getPrice());
            System.out.println("Цвят на продукта: "+product.getColor());
            System.out.println("Срок на годност на продукта: "+product.getExpires_in());

            System.out.println("*".repeat(10));
            System.out.println("-".repeat(10));

        }
    }

    private void saveEmployeeToCSV(String csvFilePath, List<Employee> employees){
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            writer.write("employee_id,first_name,last_name,age,salary\n");
            for (Employee employee:employees
                 ) {
                writer.write(String.format("%d,%s,%s,%d,%.2f\n",
                        employee.getEmployee_id(),employee.getFirst_name(),
                        employee.getLast_name(), employee.getAge(),
                        employee.getSalary()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void saveProductsToCSV(String csvFilePath, List<Product> products){
        try (FileWriter writer = new FileWriter(csvFilePath)){
            writer.write("product_id,name,price,quantity,type,color,expires_in\n");

            for (Product product:products
                 ) {
                writer.write(String.format("%d,%s,%.2f,%d,%s,%s\n",
                        product.getProduct_id(), product.getName(),
                        product.getPrice(), product.getQuantity(),
                        product.getColor(), product.getExpires_in()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addProduct(Product product){
        products.add(product);
        saveProductsToCSV("product.CSV",products);
    }
    private void addEmployee(Employee employee){
        employees.add(employee);
        saveEmployeeToCSV("employee.CSV",employees);
    }
    public Product createProduct (int product_id, String name, int quantity, double price, String color, String expires_in){
        Product product = new Product(product_id,name,quantity, price, color, expires_in);
        addProduct(product);
        return product;
    }
    public Employee createEmployee(int employee_id, String first_name, String last_name, int age, double salary){
        Employee employee = new Employee(employee_id, first_name, last_name, age,salary);
        addEmployee(employee);
        return employee;
    }

    public static List<Employee> readEmployeeFromCSV(String csvFilePath){
//        List<Employee> employees = new ArrayList<>();
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
//        List<Product> products = new ArrayList<>();

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
