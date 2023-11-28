import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import src.Entity.FoodProduct;
import src.Entity.Product;
import src.Enums.ProductCategory;
import src.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;




public class PrinterTests {


//    @Rule
//    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPrintProductDetails(){
        Printer printer = new Printer();
        Product product = Mockito.mock(Product.class);
        when(product.getProduct_id()).thenReturn(1);
        when(product.getName()).thenReturn("Test product");
        when(product.getQuantity()).thenReturn(10);
        when(product.getPrice()).thenReturn(20.4);
        when(product.getColor()).thenReturn("Red");
        when(product.getExpires_in()).thenReturn(LocalDate.parse("2023-12-31"));

        printer.printProductDetails(product);

        verify(product, times(1)).getProduct_id();
        verify(product, times(1)).getName();
        verify(product,times(1)).getQuantity();
        verify(product, times(1)).getPrice();
        verify(product,times(1)).getColor();
        verify(product,times(1)).getExpires_in();
    }

    @Test
    public void printProductList(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Product product1 = new FoodProduct(1, "Sugar",13, 2.54,
                ProductCategory.FOOD,"none", LocalDate.parse("2023-11-30"));
        Product product2 = new FoodProduct(2, "Tomatto",13, 2.54,
                ProductCategory.FOOD,"none", LocalDate.parse("2023-12-30"));
        List<Product> products = Arrays.asList(product1,product2);

        Printer printer = new Printer();
        printer.printProductList(products);

        String expectedOutput = buildExpectedOutput(product1)+buildExpectedOutput(product2);
        Assert.assertEquals(expectedOutput, outputStreamCaptor.toString());

        System.setOut(System.out);


    }

    @Test
    public void testPrintMenuEmployee(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Printer printer = new Printer();
        printer.printMenuEmployee();

        String expectedOutput = "1. Принтиране на всички продукти"  + System.lineSeparator() +
                "2. Принтиране на всички продукти, сортирани по: име;цена;срок на годност"  + System.lineSeparator() +
                "4. Принтиране на определен продукт (по id)"  + System.lineSeparator() +
                "5. Принтиране на определен продукт (по име)"  + System.lineSeparator() +
                "6. Принтиране на всички продукти с цена, по-висока или равна на зададена от потребителя цена"  + System.lineSeparator() +
                "7. Принтиране на всички продукти с цена, по-ниска от зададена от потребителя цена"  + System.lineSeparator() +
                "8. Принтиране на всички продукти с количество, по-голямо или равно на зададено от потребителя количество"  + System.lineSeparator() +
                "9. Принтиране на всички продукти с количество, по-малко от зададено от потребителя количество"  + System.lineSeparator() +
                "10. Добавяне на продукт"  + System.lineSeparator() +
                "11. Промяна на цената на продукт (по id)"  + System.lineSeparator() +
                "12. Промяна на количеството на продукт (по id)"  + System.lineSeparator() +
                "13. Промяна на името на продукт (по id)"  + System.lineSeparator() +
                "14. Изтриване на продукт (по id)"  + System.lineSeparator() +
                "15. Сортиране на служителите по:име"  + System.lineSeparator() +
                "16. Сортиране на служителите по:заплата"  + System.lineSeparator() +
                "17. Принтиране на всички продукти, сортирани по: име"   + System.lineSeparator()+
                "18. Принтиране на всички продукти, сортирани по: цена"   + System.lineSeparator()+
                "19. Принтиране на всички продукти, сортирани по: срок на годност"  + System.lineSeparator()+
                "20. Принтиране на всички продукти по категория"  + System.lineSeparator()+
                "3. Изход" + System.lineSeparator();
        Assert.assertEquals(expectedOutput, outputStreamCaptor.toString() );

        System.setOut(System.out);
    }
    @Test
    public void testPrintMenuUser(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Printer printer = new Printer();
        printer.printMenuUser();

        String expectedOutput = "1. Принтиране на всички продукти"  + System.lineSeparator() +
                "2. Принтиране на всички продукти по категория"  + System.lineSeparator() +
                "4. Търсене на продукти по име"  + System.lineSeparator() +
                "5. Добавяне на продукт към потребителска количка /по ID и к-во/"  + System.lineSeparator() +
                "6. Изчисляване на цената на продуктите в количката"  + System.lineSeparator() +
                "7. Покупка на продуктите в количката"  + System.lineSeparator() +

                "3. Изход" + System.lineSeparator();
        Assert.assertEquals(expectedOutput, outputStreamCaptor.toString() );

        System.setOut(System.out);
    }

    /*     System.out.println("1. Принтиране на всички продукти");
        System.out.println("2. Принтиране на всички продукти по категория");
        System.out.println("4. Търсене на продукти по име");
        System.out.println("5. Добавяне на продукт към потребителска количка /по ID и к-во/");
        System.out.println("6. Изчисляване на цената на продуктите в количката");
        System.out.println("7. Покупка на продуктите в количката");
        System.out.println("3. Изход");*/
    private String buildExpectedOutput(Product product) {
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("*".repeat(10)).append(System.lineSeparator());
        expectedOutput.append("ID на продукта: ").append(product.getProduct_id()).append(System.lineSeparator());
        expectedOutput.append("Име на продукта: ").append(product.getName()).append(System.lineSeparator());
        expectedOutput.append("Количество на продукта: ").append(product.getQuantity()).append(System.lineSeparator());
        expectedOutput.append("Цена на продукта: ").append(product.getPrice()).append(System.lineSeparator());
        expectedOutput.append("Цвят на продукта: ").append(product.getColor()).append(System.lineSeparator());
        expectedOutput.append("Срок на годност на продукта: ").append(product.getExpires_in()).append(System.lineSeparator());
        expectedOutput.append("*".repeat(10)).append(System.lineSeparator());
        expectedOutput.append("-".repeat(10)).append(System.lineSeparator());

        return expectedOutput.toString();
    }

}
