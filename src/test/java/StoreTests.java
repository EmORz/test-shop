import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import src.Entity.*;
import src.Enums.ProductCategory;
import src.Printer;
import src.Store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class StoreTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;

    private List<Product> products;
    public List<Employee> employeeList;

    @InjectMocks
    public Store store;
    @Mock
    private Printer printer;
    @Before
    public void setUpStreams(){
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        store.setTestMode(true);

        products = Arrays.asList(
                new FoodProduct(1, "A_TestProduct1", 124, 2.5, ProductCategory.FOOD, "Green", LocalDate.now()),
                new DrinksProduct(2, "B_TestProduct2", 14, 4.5, ProductCategory.DRINKS, "None", LocalDate.now()),
                new OthersProduct(3, "C_TestProduct3", 123, 4.5, ProductCategory.OTHERS, "None", LocalDate.now()),
                new MakeUpProduct(4, "D_TestProduct4", 14, 8.5, ProductCategory.MAKEUP, "None", LocalDate.now()),
                new SanitaryProduct(5, "E_TestProduct5", 100, 3.0, ProductCategory.SANITARY, "Blue", LocalDate.now()),
                new FoodProduct(6, "F_TestProduct6", 50, 6.0, ProductCategory.FOOD, "Red", LocalDate.now()),
                new DrinksProduct(7, "G_TestProduct7", 200, 1.5, ProductCategory.DRINKS, "Yellow", LocalDate.now()),
                new MakeUpProduct(8, "H_TestProduct8", 30, 10.0, ProductCategory.MAKEUP, "None", LocalDate.now()),
                new SanitaryProduct(9, "I_TestProduct9", 75, 5.0, ProductCategory.SANITARY, "White", LocalDate.now()),
                new OthersProduct(10, "J_TestProduct10", 20, 7.5, ProductCategory.OTHERS, "None", LocalDate.now())
        );
        store.setProducts(products);
        store.saveProductsToCSV(true);

        employeeList = Arrays.asList(
                new Employee(1,"Ivan", "Ivanov",36, 890),
                new Employee(2,"Anton", "Todorov",46, 990),
                new Employee(3,"Emil", "Ignatov",25, 790)

        );
        store.setEmployees(employeeList);
        store.saveEmployeeToCSV(employeeList, true);

    }

    @After
    public void restoreStreams(){
        System.setOut(System.out);
        System.setErr(System.err);
        System.setIn(originalIn);
    }

    @Test
    public void testPrintAllProducts(){
        StringBuilder stringBuilder = new StringBuilder();

        products.stream()
                .forEach(product -> {
                    stringBuilder.append("*".repeat(10)+System.lineSeparator());
                    stringBuilder.append("ID на продукта: "+ product.getProduct_id()+System.lineSeparator());
                    stringBuilder.append("Име на продукта: "+ product.getName()+System.lineSeparator());
                    stringBuilder.append("Количество на продукта: "+ product.getQuantity()+System.lineSeparator());
                    stringBuilder.append("Цена на продукта: "+ product.getPrice()+System.lineSeparator());
                    stringBuilder.append("Цвят на продукта: "+ product.getColor()+System.lineSeparator());
                    stringBuilder.append("Срок на годност на продукта: "+ product.getExpires_in()+System.lineSeparator());
                    stringBuilder.append("*".repeat(10)+System.lineSeparator());
                    stringBuilder.append("-".repeat(10)+System.lineSeparator());
                });
        store.printaAllProducts();
        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());

    }
    @Test
    public void testPrintEmployees(){
        StringBuilder stringBuilder = new StringBuilder();
        employeeList
                .stream()
                .forEach(employee -> {
            stringBuilder.append(employee.getFirst_name()+" "+employee.getLast_name()+"; Заплата: "+employee.getSalary()
            +System.lineSeparator());
        });

        store.printEmployees(employeeList);

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());

    }
    @Test
    public void testCreateEmployee(){
        Employee employee = store.createEmployee(4,
                "IvanRz", "ivanov", 24, 1200, true);

        Assert.assertNotNull(employee);
        Assert.assertEquals(4, employee.getEmployee_id());
        Assert.assertEquals("IvanRz", employee.getFirst_name());
        Assert.assertEquals("ivanov", employee.getLast_name());
        Assert.assertEquals(24, employee.getAge());
        Assert.assertEquals(1200, employee.getSalary(), 0.0001);
    }
    @Test
    public void testSortEmployeesByName(){
        StringBuilder stringBuilder = new StringBuilder();
        employeeList.sort(Comparator.comparing(Employee::getFirst_name)
                .thenComparing(Employee::getLast_name));

        stringBuilder.append("Служителите са сортирани по "+"име."+System.lineSeparator());
        for (Employee employee:employeeList
        ) {
            stringBuilder.append(employee.getFirst_name()
                    +" "+employee.getLast_name()+"; Заплата: "+employee.getSalary()+System.lineSeparator()
            );
        }
        store.sortEmployeesByName();

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }
    @Test
    public void testSortEmployeeBySalary(){
        StringBuilder stringBuilder = new StringBuilder();
        employeeList.sort(Comparator.comparing(Employee::getSalary));

        stringBuilder.append("Служителите са сортирани по "+"заплата."+System.lineSeparator());
        for (Employee employee:employeeList
        ) {
            stringBuilder.append(employee.getFirst_name()
                    +" "+employee.getLast_name()+"; Заплата: "+employee.getSalary()+System.lineSeparator()
                    );
       }
        store.sortEmployeeBySalary();

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }

    @Test
    public void testPrintProductsByCategory(){
        String input = "food";
        provideInput(input);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Въведете категория на продукт: food, drinks, sanitary, others, makeup"+System.lineSeparator());
        products.stream().filter(product -> product.getCategory()==ProductCategory.FOOD)
                .sorted(Comparator.comparing(Product::getProduct_id))
                .forEach(product -> {
                    stringBuilder.append("*".repeat(10)+System.lineSeparator());
                    stringBuilder.append("ID на продукта: "+ product.getProduct_id()+System.lineSeparator());
                    stringBuilder.append("Име на продукта: "+ product.getName()+System.lineSeparator());
                    stringBuilder.append("Количество на продукта: "+ product.getQuantity()+System.lineSeparator());
                    stringBuilder.append("Цена на продукта: "+ product.getPrice()+System.lineSeparator());
                    stringBuilder.append("Цвят на продукта: "+ product.getColor()+System.lineSeparator());
                    stringBuilder.append("Срок на годност на продукта: "+ product.getExpires_in()+System.lineSeparator());
                    stringBuilder.append("*".repeat(10)+System.lineSeparator());
                    stringBuilder.append("-".repeat(10)+System.lineSeparator());
                });


        store.printProductsByCategory();

        var temp = outContent.toString().trim();

        Assert.assertEquals(stringBuilder.toString().trim(), temp);
    }

    @Test
    public void testPrintAllProductsSortedByNamePriceExpirationDate(){
        StringBuilder stringBuilder = new StringBuilder();

        products.sort(Comparator.comparing(Product::getName)
                .thenComparing(Product::getPrice)
                .thenComparing(Product::getExpires_in));
        for (Product product: products
        ) {
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("ID на продукта: "+ product.getProduct_id()+System.lineSeparator());
            stringBuilder.append("Име на продукта: "+ product.getName()+System.lineSeparator());
            stringBuilder.append("Количество на продукта: "+ product.getQuantity()+System.lineSeparator());
            stringBuilder.append("Цена на продукта: "+ product.getPrice()+System.lineSeparator());
            stringBuilder.append("Цвят на продукта: "+ product.getColor()+System.lineSeparator());
            stringBuilder.append("Срок на годност на продукта: "+ product.getExpires_in()+System.lineSeparator());
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("-".repeat(10)+System.lineSeparator());
        }

        store.printAllProductsSortedByNamePriceExpirationDate();

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }
    @Test
    public void testPrintProductsSortedByExpirationDate(){
        StringBuilder stringBuilder = new StringBuilder();

        products.sort(Comparator.comparing(Product::getExpires_in));
        for (Product product: products
        ) {
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("ID на продукта: "+ product.getProduct_id()+System.lineSeparator());
            stringBuilder.append("Име на продукта: "+ product.getName()+System.lineSeparator());
            stringBuilder.append("Количество на продукта: "+ product.getQuantity()+System.lineSeparator());
            stringBuilder.append("Цена на продукта: "+ product.getPrice()+System.lineSeparator());
            stringBuilder.append("Цвят на продукта: "+ product.getColor()+System.lineSeparator());
            stringBuilder.append("Срок на годност на продукта: "+ product.getExpires_in()+System.lineSeparator());
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("-".repeat(10)+System.lineSeparator());
        }

        store.printProductsSortedByExpirationDate();

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }
    @Test
    public void testPritProcutsSortedByPrice(){
        StringBuilder stringBuilder = new StringBuilder();

        products.sort(Comparator.comparing(Product::getPrice));
        for (Product product: products
        ) {
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("ID на продукта: "+ product.getProduct_id()+System.lineSeparator());
            stringBuilder.append("Име на продукта: "+ product.getName()+System.lineSeparator());
            stringBuilder.append("Количество на продукта: "+ product.getQuantity()+System.lineSeparator());
            stringBuilder.append("Цена на продукта: "+ product.getPrice()+System.lineSeparator());
            stringBuilder.append("Цвят на продукта: "+ product.getColor()+System.lineSeparator());
            stringBuilder.append("Срок на годност на продукта: "+ product.getExpires_in()+System.lineSeparator());
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("-".repeat(10)+System.lineSeparator());
        }

        store.pritProcutsSortedByPrice();

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }
    @Test
    public void testPrintProductsSortedByName(){
        StringBuilder stringBuilder = new StringBuilder();

        products.sort(Comparator.comparing(Product::getName));
        for (Product product: products
             ) {
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("ID на продукта: "+ product.getProduct_id()+System.lineSeparator());
            stringBuilder.append("Име на продукта: "+ product.getName()+System.lineSeparator());
            stringBuilder.append("Количество на продукта: "+ product.getQuantity()+System.lineSeparator());
            stringBuilder.append("Цена на продукта: "+ product.getPrice()+System.lineSeparator());
            stringBuilder.append("Цвят на продукта: "+ product.getColor()+System.lineSeparator());
            stringBuilder.append("Срок на годност на продукта: "+ product.getExpires_in()+System.lineSeparator());
            stringBuilder.append("*".repeat(10)+System.lineSeparator());
            stringBuilder.append("-".repeat(10)+System.lineSeparator());
        }

        store.printProductsSortedByName();

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }
    @Test
    public void testCreateProduct (){
        String input = "TestProduct\n5\n10,19\nFOOD\nRed\n30\n";
        provideInput(input);

        // Симулиране на действието на createProduct
        Product createdProduct = store.createProduct(true);
        var currentDate = createdProduct.getExpires_in();

        // Проверка на създадения продукт
        Assert.assertNotNull(createdProduct);
        Assert.assertEquals("TestProduct", createdProduct.getName());
        Assert.assertEquals(5, createdProduct.getQuantity());
        Assert.assertEquals(10.19, createdProduct.getPrice(),0.001);
        Assert.assertEquals(ProductCategory.FOOD, createdProduct.getCategory());
        Assert.assertEquals("Red", createdProduct.getColor());
        Assert.assertEquals(currentDate, createdProduct.getExpires_in());

    }
    @Test
    public void testChangeProductPriceById() {
        String input = "3\n3,12\n";
        provideInput(input);

        String[] parts = input.split("\n");

        var currentProduct = store.getProducts().get(Integer.parseInt(parts[0])-1);
        var currentPrice = currentProduct.getPrice();

        store.changeProductPriceById(true);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Въведете ID на продукта: "+System.lineSeparator());
        stringBuilder.append("Текуща цена на продукта с ID " +currentProduct.getProduct_id()+ ": " +currentPrice+System.lineSeparator());
        stringBuilder.append("Въведете нова цена на продукта: "+System.lineSeparator());
        stringBuilder.append("Променяте продукт с Id "+currentProduct.getProduct_id()+System.lineSeparator());
        var replaceDot = parts[1].replace(',','.');
        stringBuilder.append("Цената на продукт с ID "+currentProduct.getProduct_id()+ " е променена на "+replaceDot+System.lineSeparator());

        String temp = outContent.toString().trim();

        Assert.assertEquals( temp, stringBuilder.toString().trim());
    }

    @Test
    public void testChangeProductQuantityById() {
        String input = "3\n123\n";
        provideInput(input);

        String[] parts = input.split("\n");
        var currentProduct = store.getProducts().get(Integer.parseInt(parts[0])-1);
        var currentQuantity = currentProduct.getQuantity();

        store.changeProductQuantityById(true);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Въведете ID на продукта: "+System.lineSeparator());
        stringBuilder.append("Текущо количество на продукт с ID "+currentProduct.getProduct_id()+ ": " +currentQuantity+System.lineSeparator());
        stringBuilder.append("Въведете ново количество на продукта: "+System.lineSeparator());
        stringBuilder.append("Променяте продукт с Id "+currentProduct.getProduct_id()+System.lineSeparator());
        stringBuilder.append("Количествo на продукт с ID 3 е променено на "+parts[1]+System.lineSeparator());

        String temp = outContent.toString().trim();

        Assert.assertEquals( temp, stringBuilder.toString().trim());

    }
    @Test
    public void testChangeProductNameById() {
        String input = "3\nD_TestProduct3\n";
        provideInput(input);

        String[] parts = input.split("\n");

        var currentProduct = store.getProducts().get(Integer.parseInt(parts[0])-1);
        var currentProductName = currentProduct.getName();
        store.changeProductNameById(true);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Въведете ID на продукта: "+System.lineSeparator());
        stringBuilder.append("Текущо име на продукт с ID 3: " +currentProductName+System.lineSeparator());
        stringBuilder.append("Въведете ново име на продукта: "+System.lineSeparator());
        stringBuilder.append("Променяте продукт с Id 3"+System.lineSeparator());
        stringBuilder.append("Името на продукт с ID 3 е променено на "+parts[1]+System.lineSeparator());

        String temp = outContent.toString().trim();

        Assert.assertEquals( temp, stringBuilder.toString().trim());
    }

    @Test
    public void testFindProductByName() {
        String input = "B_TestProduct2\n";
        provideInput(input);

        List<Product> foundProducts = store.findProductByName(System.in);


        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
    }

    private void provideInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    private void provideInput(Scanner scanner) {
        // Настройка на System.in с мокнатия Scanner
        System.setIn(new InputStream() {
            @Override
            public int read() {
                return scanner.next().getBytes()[0];
            }
        });
    }


}
