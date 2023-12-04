import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import src.Entity.Employee;
import src.Entity.Product;
import src.Enums.ProductCategory;
import src.Printer;
import src.Store;
import src.Validate.IntegerValidator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StoreTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;

    private List<Product> unsortedProducts;


    @InjectMocks
    private Store store;
    @Mock
    private Printer printer;


    public List<Employee> employeeList;
    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        this.store = new Store();
        Product product1 = new Product(1,"A_TestProduct1",
                124, 2.5, ProductCategory.FOOD,"Green", LocalDate.now());
        Product product2 = new Product(2,"B_TestProduct2",
                14, 4.5, ProductCategory.DRINKS,"None", LocalDate.now());
        Product product3 = new Product(3,"D_TestProduct3",
                184, 4.5, ProductCategory.OTHERS,"None", LocalDate.now());
        Product product4 = new Product(4,"C_TestProduct2",
                14, 8.5, ProductCategory.MAKEUP,"None", LocalDate.now());
        Employee employee1 = new Employee(1,"Ivan", "Ivanov",36, 890);
        Employee employee2 = new Employee(2,"Petyr", "Todorov",46, 990);
        Employee employee3 = new Employee(3,"Emil", "Ignatov",25, 790);
        unsortedProducts = new ArrayList<>();
        employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        //
        unsortedProducts.add(product1);
        unsortedProducts.add(product2);
        unsortedProducts.add(product3);
        unsortedProducts.add(product4);
        store.setProducts(unsortedProducts);

    }

    @After
    public void restoreStreams(){
        System.setOut(System.out);
        System.setErr(System.err);
        System.setIn(originalIn);
    }

    @Test
    public void testSortEmployeeBySalary(){
//        StringBuilder stringBuilder = new StringBuilder();
//
//        employeeList.sort(Comparator.comparing(Employee::getSalary));
//        for (Employee employee:employeeList
//        ) {
//            stringBuilder.append(employee.getFirst_name()
//                    +" "+employee.getLast_name()+"; Заплата: "+employee.getSalary()
//                    );
////            System.out.printlemployee.getFirst_name()+" "+employee.getLast_name()+"; Заплата: "+employee.getSalary()n(employee.getFirst_name()+" "+employee.getLast_name()+"; Заплата: "+employee.getSalary());
//        }
//
//        store.sortEmployeeBySalary();
//
//
//        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }

    @Test
    public void testPrintAllProductsSortedByNamePriceExpirationDate(){
        StringBuilder stringBuilder = new StringBuilder();

        unsortedProducts.sort(Comparator.comparing(Product::getName)
                .thenComparing(Product::getPrice)
                .thenComparing(Product::getExpires_in));
        for (Product product:unsortedProducts
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

        unsortedProducts.sort(Comparator.comparing(Product::getExpires_in));
        for (Product product:unsortedProducts
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

        unsortedProducts.sort(Comparator.comparing(Product::getPrice));
        for (Product product:unsortedProducts
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

        unsortedProducts.sort(Comparator.comparing(Product::getName));
        for (Product product:unsortedProducts
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

        Product createdProduct = store.createProduct();
        var currentDate = createdProduct.getExpires_in();

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

        store.changeProductPriceById();

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

        store.changeProductQuantityById();

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
        store.changeProductNameById();
//        IntegerValidator integerValidator = new IntegerValidator();
//
//        Scanner mockScanner = new Scanner(System.in);
//
//        int validateInteger = integerValidator.validate(mockScanner, "ID");
//
//        assertEquals(3, validateInteger);

//        store.changeProductNameById();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Въведете ID на продукта: "+System.lineSeparator());
        stringBuilder.append("Текущо име на продукт с ID 3: " +currentProduct.getName()+System.lineSeparator());
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


}
