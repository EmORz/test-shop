import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import src.Entity.Product;
import src.Enums.ProductCategory;
import src.Store;
import src.Validate.IntegerValidator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StoreTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;

    @InjectMocks
    private Store store;

    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        this.store = new Store();
    }

    @After
    public void restoreStreams(){
        System.setOut(System.out);
        System.setErr(System.err);
        System.setIn(originalIn);
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
        String input = "3\nNewProduct\n";
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
        String input = "test1\n";
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
