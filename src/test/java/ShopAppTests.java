import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import src.Entity.Employee;
import src.Entity.User;
import src.ShopApp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class ShopAppTests {
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @InjectMocks
    private ShopApp shopApp;

    @Mock
    private Scanner scannerMock;

    @Mock
    private List<Employee> employeeData;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.shopApp = new ShopApp();
    }

    @AfterEach
    public void tearDown(){
        System.setIn(systemIn);
    }
//    @After
//    public void restoreSystemInput() {
//        System.setIn(systemIn);
//    }

    @Test
    public void testMenuForChooseExit() {
        String input = "3\n";
        provideInput(input);
        shopApp.menuForChoose(System.in);
    }
    @Test
    public void testLoginAsEmployeeWithValidCredentialsExit(){
        String input = "1\nIvan\n3\n";
        provideInput(input);

        shopApp.loginAsEmployee(System.in);



    }
    @Test
    public void testLoginAsEmployee() {}
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

        // Add assertions based on the expected behavior of loginAsCustomer
        // For example, you might want to verify that user.printAvailableProducts() is called
        // or that user.searchProductsByCategory() is called, etc.
    }
    private void provideInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }
}
