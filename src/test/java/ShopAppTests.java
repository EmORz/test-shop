import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import src.Entity.Employee;
import src.Entity.User;
import src.ShopApp;
import src.Store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class ShopAppTests {
    private final InputStream systemIn = System.in;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @InjectMocks
    private ShopApp shopApp;

    @Mock
    private Scanner scannerMock;

    @Mock
    private List<Employee> employeeData;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(provideInput(""));
        this.shopApp = new ShopApp();
    }

    @AfterEach
    public void tearDown(){
        System.setOut(System.out);
        System.setErr(System.err);
        System.setIn(systemIn);
    }


    @Test
    public void testMenuForChooseExit() {
        String input = "3\n";
        provideInput(input);
        shopApp.menuForChoose(System.in);

        String expectedOutput = "1. Вход като служител"+System.lineSeparator() +
                "2. Вход като клиент"+System.lineSeparator() +
                "3. Изход"+System.lineSeparator() +
                "Изберете опция: Излизане от програмата......";
        String actualOutput = outContent.toString().trim();
        Assert.assertEquals(expectedOutput.trim(), actualOutput);
    }
    @Test
    public void testLoginAsEmployeeWithValidCredentialsExit(){
        String input = "1\nIvan\n3\n";
        provideInput(input);

        shopApp.loginAsEmployee(System.in);
        String actualOutput = outContent.toString().trim();
        String expectedOutput = "Добре дошли в модул за служители!" + System.lineSeparator() +
                "За да влезете в системата въведете уникално Id и first_name." + System.lineSeparator() +
                "Въведи Id: Въведи first_name: " + System.lineSeparator() +
                "Добре дошли Ivan!" + System.lineSeparator() +
                "Изберете опция: " + System.lineSeparator() +
                "1. Принтиране на всички продукти" + System.lineSeparator() +
                "2. Принтиране на всички продукти, сортирани по: име;цена;срок на годност" + System.lineSeparator() +
                "4. Принтиране на определен продукт (по id)" + System.lineSeparator() +
                "5. Принтиране на определен продукт (по име)" + System.lineSeparator() +
                "6. Принтиране на всички продукти с цена, по-висока или равна на зададена от потребителя цена" + System.lineSeparator() +
                "7. Принтиране на всички продукти с цена, по-ниска от зададена от потребителя цена" + System.lineSeparator() +
                "8. Принтиране на всички продукти с количество, по-голямо или равно на зададено от потребителя количество" + System.lineSeparator() +
                "9. Принтиране на всички продукти с количество, по-малко от зададено от потребителя количество" + System.lineSeparator() +
                "10. Добавяне на продукт" + System.lineSeparator() +
                "11. Промяна на цената на продукт (по id)" + System.lineSeparator() +
                "12. Промяна на количеството на продукт (по id)" + System.lineSeparator() +
                "13. Промяна на името на продукт (по id)" + System.lineSeparator() +
                "14. Изтриване на продукт (по id)" + System.lineSeparator() +
                "15. Сортиране на служителите по:име" + System.lineSeparator() +
                "16. Сортиране на служителите по:заплата" + System.lineSeparator() +
                "17. Принтиране на всички продукти, сортирани по: име" + System.lineSeparator() +
                "18. Принтиране на всички продукти, сортирани по: цена" + System.lineSeparator() +
                "19. Принтиране на всички продукти, сортирани по: срок на годност" + System.lineSeparator() +
                "20. Принтиране на всички продукти по категория" + System.lineSeparator() +
                "3. Изход" + System.lineSeparator() +
                "Изберете опция: Добре дошли Ivan!" + System.lineSeparator() +
                "Изберете опция:";

        Assert.assertEquals(expectedOutput.trim(), actualOutput);

    }

    @Test
    public void testLoginAsEmployeeInvalidName() {
        String input = "1\nIan\n4\n";
        provideInput(input);

        Employee employee = mock(Employee.class);
        shopApp.setEmployee(employee);

        employee.login(1, "Ian");
        shopApp.loginAsEmployee(System.in);

        String actualOutput = outContent.toString().trim();
        String expectedOutput = "Добре дошли в модул за служители!" + System.lineSeparator() +
                "За да влезете в системата въведете уникално Id и first_name." + System.lineSeparator() +
                "Въведи Id: Въведи first_name: " + System.lineSeparator() +
                "Няма служител с такива данни!";

        Assert.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testLoginAsCustomer() {
        User user = mock(User.class);
        shopApp.setUser(user);

        String input = "JohnDoe\n3\n";
        provideInput(input);

        shopApp.loginAsCustomer(System.in);
        user.printAvailableProducts();
        user.searchProductsByCategory();

        verify(user, times(1)).printAvailableProducts();
        verify(user, times(1)).searchProductsByCategory();

    }
    private InputStream provideInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
        return testInput;
    }
}
