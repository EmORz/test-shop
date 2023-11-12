import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
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
    private static Scanner scanner = new Scanner(System.in);
    private static List<Product> products;
    private List<Product> shoppingCart;
    private static List<Employee> employees;

    public Store() {
        this.products = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
        this.employees = new ArrayList<>();
    }



    public Employee loginEmployee(int employee_id, String firstName){

        for (Employee employee:employees
             ) {
            if (employee.getEmployee_id() == employee_id
            && employee.getFirst_name().equalsIgnoreCase(firstName)) {
                employee.login(employee_id, firstName);
                break;
            }
        }

        return null;
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

    public void changeProductNameById() {
        System.out.println("Въведете ID на продукта: ");
        int productId = scanner.nextInt();
        System.out.println("Въедете ново име на продукта: ");
        String newName = scanner.next();
        for (Product product : products) {
            if (product.getProduct_id() == productId) {
                product.setName(newName);
                addProduct(product);
                System.out.println("Името на продукт с ID " + productId + " е променено на " + newName);
                return;
            }
        }
        System.out.println("Продукт с ID " + productId + " не беше намерен.");
    }

    public void changeProductQuantityById() {
        System.out.println("Въведете ID на продукта: ");
        int productId = scanner.nextInt();
        System.out.println("Въедете ново количество на продукта: ");
        int newQuantity = scanner.nextInt();
        for (Product product : products) {
            if (product.getProduct_id() == productId) {
                product.setQuantity(newQuantity);
                addProduct(product);
                System.out.println("Количествo на продукт с ID " + productId + " е променено на " + newQuantity);
                break;
            }
        }
        System.out.println("Продукт с ID " + productId + " не беше намерен.");
    }

    public void changeProductPriceById() {
        System.out.println("Въведете ID на продукта: ");
        int productId = scanner.nextInt();
        System.out.println("Въедете нова цена на продукта: ");
        double newPrice = scanner.nextDouble();
        for (Product product : products) {
            if (product.getProduct_id() == productId) {
                product.setPrice(newPrice);
                addProduct(product);
                System.out.println("Цената на продукт с ID " + productId + " е променена на " + newPrice);
                return;
            }
        }
        System.out.println("Продукт с ID " + productId + " не беше намерен.");
    }

    public void printAllProductsByQuantityLower() {
        System.out.print("Въведи количество за продукти: ");
        int quantity = scanner.nextInt();

        System.out.println("Продукти с количество по-малко от " + quantity + ":");
        for (Product product:products
        ) {
            if (product.getQuantity() < quantity) {
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
    }
    public void printAllProductsByQuantityGreaterThanOrEqual() {
        System.out.print("Въведи количество за продукти: ");
        int quantity = scanner.nextInt();

        System.out.println("Продукти с количество по-високо или равно на " + quantity + ":");
        for (Product product:products
             ) {
            if (product.getQuantity() >= quantity) {
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
    }

    public void printProductsByPriceGreaterThanOrEqual() {
        System.out.print("Въведи цена за търсене на продукти: ");
        double price = scanner.nextDouble();

        System.out.println("Продукти с цена по-висока или равна на " + price + ":");

        for (Product product : products) {
            if (product.getPrice() >= price) {

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
    }
    public void printProductsByPriceLower() {
        System.out.print("Въведи цена за търсене на продукти: ");
        double price = scanner.nextDouble();

        System.out.println("Продукти с цена по-ниска от " + price + ":");

        for (Product product : products) {
            if (product.getPrice() < price) {

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
    }

    public List<Product> findProductById(){
        System.out.print("Въведи Id на продукт: ");
        int product_id = scanner.nextInt();
        List<Product> findProducts = new ArrayList<>();

        for (Product product:products
             ) {
            if (product.getProduct_id() == product_id) {
                findProducts.add(product);
            }
        }

        if (findProducts.size()!=0) {
            for (Product product:findProducts
                 ) {
                System.out.println("Намерени са "+findProducts.size()+" бр. продукти.");
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
        }else {
            System.out.println("Продукт с ID "+product_id+" не е намерен!");
            return null;
        }
        return findProducts;

    }
    public List<Product> findProductByName(){
        List<Product> findProducts = new ArrayList<>();
        System.out.print("Въведи име на продукт: ");
        String product_name = scanner.next();

        for (Product product:products
        ) {
            if (product.getName().equalsIgnoreCase(product_name)) {
                findProducts.add(product);
            }
        }

        if (findProducts.size()!=0) {
            for (Product product:findProducts
            ) {
                System.out.println("Намерени са "+findProducts.size()+" бр. продукти.");
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
        }else {
            System.out.println("Продукт с име "+product_name+" не е намерен!");
            return null;
        }
        return findProducts;

    }
    public void deleteEmployeeById(int employee_id){
        List<Employee> employeeForDelete = new ArrayList<>();
        boolean isHasEmployeeForDelete = false;
        for (Employee employee:employees
             ) {
            if (employee.getEmployee_id() == employee_id) {
                employeeForDelete.add(employee);
                isHasEmployeeForDelete = true;
            }
        }
        if (employeeForDelete != null&& isHasEmployeeForDelete) {
            int id = employeeForDelete.get(0).getEmployee_id();
            employees.removeAll(employeeForDelete);
            saveEmployeeToCSV("employee.CSV", employees);
            System.out.println("Служител/и с ID "+id+" са изтрит!");
        }else {
            System.out.println("Служител с ID "+employee_id+" не е намерен!");
        }
    }
    public void deleteProductById(){
        List<Product> productForRemove = new ArrayList<>();
        System.out.print("Въведи ID на продукт за изтриване: ");
        int product_id = scanner.nextInt();

        boolean isHasProductForDelete = false;
        for (Product product:products
             ) {
            if (product.getProduct_id()==product_id) {
                productForRemove.add(product);
                isHasProductForDelete = true;
            }
        }

        if (productForRemove != null && isHasProductForDelete) {
            int id = productForRemove.get(0).getProduct_id();
            products.removeAll(productForRemove);
            saveProductsToCSV();
            System.out.println("Продукт/и с ID "+id+" са изтрит!");
        }else {
            System.out.println("Продукт с ID "+product_id+" не е намерен!");
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
    private void saveProductsToCSV(){
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fileName = "products_" + timestamp.format(formatter) + ".CSV";

        try (FileWriter writer = new FileWriter(fileName)){
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
        int id = product.getProduct_id();
        boolean isHasProduct = true;
        for (Product productt:products
             ) {
            if (productt.getProduct_id() == id) {
                System.out.println("Променяте продукт с Id "+product.getProduct_id());
                saveProductsToCSV();
                isHasProduct=false;
                return;
            }
        }
        if (isHasProduct) {
            products.add(product);
            saveProductsToCSV();
            System.out.println("Успешно добавихте продукт!");
        }

    }
    private void addEmployee(Employee employee){
        int id = employee.getEmployee_id();
        boolean isHasEmployee = true;
        for (Employee employee1:employees
        ) {
            if (employee1.getEmployee_id() == id) {
                System.out.println("Вече има запис с Id "+employee1.getEmployee_id());
                isHasEmployee=false;
                break;
            }
        }
        if (isHasEmployee) {
            employees.add(employee);
            saveEmployeeToCSV("employee.CSV",employees);
        }
    }
    public Product createProduct (){
        int current_id =0;
        for (Product product:products
             ) {
            if (product.getProduct_id() > current_id) {
                current_id = product.getProduct_id();
            }
        }
        int product_id = current_id+1;
        System.out.println("Id на продукта: "+product_id);
        System.out.println("Добавяне на име на продукта: ");
        String name = scanner.next();
        System.out.println("Добавяне на количество на продукта: ");
        int quantity = scanner.nextInt();
        System.out.println("Добавяне на цена на продукта: ");
        double price = scanner.nextDouble();
        System.out.println("Добавяне вид на продукта: ");
        String type = scanner.next();
        System.out.println("Добавяне на цвят на продукта: ");
        String color = scanner.next();
        System.out.println("Добавяне на срок на годност на продукта в дни: ");
        LocalDate currentDate = LocalDate.now();
        int additionalDays = scanner.nextInt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expires_in = currentDate.plusDays(additionalDays);
        Product product = new Product(product_id,name,quantity, price,type, color, expires_in);
        addProduct(product);
        return product;
    }
    public Employee createEmployee(int employee_id, String first_name, String last_name, int age, double salary){
        Employee employee = new Employee(employee_id, first_name, last_name, age,salary);
        addEmployee(employee);
        return employee;
    }

    private void printEmployees(){
        for (Employee employee:employees
        ) {
            System.out.println(employee.getFirst_name()+" "+employee.getLast_name()+"; Заплата: "+employee.getSalary());
        }
    }

    public void sortEmployeesByName(){
        Collections.sort(employees, Comparator.comparing(Employee::getFirst_name)
                .thenComparing(Employee::getLast_name));
        System.out.println("Служителите са сортирани по първо име и фамилия.");
        printEmployees();
    }

    public void sortEmployeeBySalary(){
        Collections
                .sort(employees, Comparator.comparing(Employee::getSalary));
        System.out.println("Служителите са сортирани по заплата.");
        printEmployees();
    }

    public void printProductsSortedByName(){
        var sortedProducts = products.stream().
                sorted(Comparator.comparing(Product::getName)).
                collect(Collectors.toList());
        printProductList(sortedProducts);
    }
    public void pritProcutsSortedByPrice(){
        var sortedProducts = products.stream().
                sorted(Comparator.comparing(Product::getPrice)).
                collect(Collectors.toList());
        printProductList(sortedProducts);
    }
    public void printProductsSortedByExpirationDate(){
        var sortedProducts = products.stream()
                .filter(product -> product.getExpires_in() != null)
                .sorted(Comparator.comparing(Product::getExpires_in))
                .collect(Collectors.toList());
        printProductList(sortedProducts);
    }
    private void printProductList(List<Product> productList) {
        for (Product product : productList) {

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

    public static List<Product> readProductsFromCSV() {
        File directory = new File(".");
        File[] files = directory.listFiles((dir, name) -> name.startsWith("products_") && name.endsWith(".CSV"));

        if (files != null && files.length > 0) {
            Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
            File lastFile = files[0];
            try (BufferedReader br = new BufferedReader(new FileReader(lastFile))) {
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
                        String expires_inStr = "01-01-1000";

                        if (data.length == 7) {
                            color = data[5];
                            expires_inStr=data[6];

                        }

                        if (color == null||color=="") {
                            color = "N/A";
                        }
//
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate expires_in = LocalDate.parse(expires_inStr, formatter);


                        Product product = new Product(product_id,name,quantity, price,type, color, expires_in);
                        products.add(product);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Няма налични файлове с продукти.");
        }
        return products;

    }

    public static List<Product>getProducts(){
        return products;
    }
    public List<Employee>getEmployees(){return employees;}

}
