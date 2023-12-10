import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import src.Entity.*;
import src.Enums.ProductCategory;
import src.Store;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private List<Product> products;
    @InjectMocks
    private User user;
    @Mock
    private Store store;

    @Before
    public void setUpStreams() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        products = Arrays.asList(
                new FoodProduct(1, "A_TestProduct1", 124, 2.6, ProductCategory.FOOD, "Green", LocalDate.now()),
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
        Mockito.when(store.getProducts()).thenReturn(products);
    }
    @After
    public void restoreStreams(){
        System.setOut(System.out);
        System.setErr(System.err);
        System.setIn(originalIn);
    }

    @Test
    public void testLoginUser(){
        User user = new User(1, "Petyr");

        user.login(1, "Petyr");

        String expectedOutput = "Добре дошли Petyr!"+System.lineSeparator()+"Изберете опция:";
        String actualOutput = outContent.toString().trim();

        Assert.assertEquals(expectedOutput.trim(), actualOutput);

    }

    @Test
    public void testCheckoutWithProductsShoppingCart(){
        String input = "1\n2\n";
        provideInput(input);

        user.addToShoppingCart(System.in);

        String input2 = "2\n2\n";
        provideInput(input2);
        user.addToShoppingCart(System.in);

        String expectedFileName = "src\\TestData\\receipt_test-20220101120000.csv";

        user.checkout(true);

        Assert.assertTrue(Files.exists(Paths.get(expectedFileName)));


    }

    @Test
    public void testCheckoutEmptyShoppingCart(){
        String expectedOutput = "Shopping cart is empty.";

        user.checkout(true);

        String actualOutput = outContent.toString().trim();

        Assert.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testCalculateTotalPrice(){
        String input = "1\n2\n";
        provideInput(input);

        user.addToShoppingCart(System.in);

        String input2 = "2\n2\n";
        provideInput(input2);
        user.addToShoppingCart(System.in);


        var price1 = products.get(0).getPrice();
        var price2 = products.get(1).getPrice();


        // Act
        double totalPrice = user.calculateTotalPrice();

        // Assert
        Assert.assertEquals( price1 * 2+price2*2, totalPrice, 0.001);

    }

    @Test
    public void testAddToShoppingCartInsufficientQuantity() {
        String input = "1\n1000\n";
        provideInput(input);

        var quantity = 1000;

        StringBuilder stringBuilder = new StringBuilder();
        user.addToShoppingCart(System.in);

        stringBuilder.append("Въведете ID на продукта: "+System.lineSeparator());
        stringBuilder.append("Въведете количество на продукта: "+System.lineSeparator());
        stringBuilder.append("src.Entity.Product not found or insufficient quantity."+System.lineSeparator());

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }

    @Test
    public void testAddToShoppingCart(){
        String input = "1\n100\n";
        provideInput(input);

        var quantity = 100;

        StringBuilder stringBuilder = new StringBuilder();
        user.addToShoppingCart(System.in);

        stringBuilder.append("Въведете ID на продукта: "+System.lineSeparator());
        stringBuilder.append("Въведете количество на продукта: "+System.lineSeparator());
        stringBuilder.append("Added "+quantity+" "+"A_TestProduct1"+" to the shopping cart."+System.lineSeparator());

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());
    }

    @Test
    public void testSearchProductByName(){
        String input = "A_TestProduct1";
        provideInput(input);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Въведете име на продукта: "+System.lineSeparator());

        products.stream()
                .filter(product -> product.getName()==input)
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

        user.searchProductByName();

        var temp = outContent.toString().trim();

        Assert.assertEquals(stringBuilder.toString().trim(), temp);

    }
    @Test
    public void testSearchProductsByCategory(){
        String input = "Food";
        provideInput(input);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Въведете категория на продукт: food, drinks, sanitary, others, makeup"+System.lineSeparator());

        products.stream()
                .filter(product -> product.getCategory()==ProductCategory.FOOD)
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
        user.searchProductsByCategory();
        var temp = outContent.toString().trim();

        Assert.assertEquals(stringBuilder.toString().trim(), temp);
    }
    @Test
    public void testPrintAvailableProducts(){
        StringBuilder stringBuilder = new StringBuilder();

        products.forEach(product -> {
            stringBuilder.append("*".repeat(10)).append(System.lineSeparator());
            stringBuilder.append("ID на продукта: ").append(product.getProduct_id()).append(System.lineSeparator());
            stringBuilder.append("Име на продукта: ").append(product.getName()).append(System.lineSeparator());
            stringBuilder.append("Количество на продукта: ").append(product.getQuantity()).append(System.lineSeparator());
            stringBuilder.append("Цена на продукта: ").append(product.getPrice()).append(System.lineSeparator());
            stringBuilder.append("Цвят на продукта: ").append(product.getColor()).append(System.lineSeparator());
            stringBuilder.append("Срок на годност на продукта: ").append(product.getExpires_in()).append(System.lineSeparator());
            stringBuilder.append("*".repeat(10)).append(System.lineSeparator());
            stringBuilder.append("-".repeat(10)).append(System.lineSeparator());
        });
        user.printAvailableProducts();

        Assert.assertEquals(stringBuilder.toString().trim(), outContent.toString().trim());

    }


    private void provideInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }
}
