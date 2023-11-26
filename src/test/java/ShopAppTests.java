//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import src.Entity.Employee;
//import src.ShopApp;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
//import static org.mockito.Mockito.*;
//
//public class ShopAppTests {
//    @InjectMocks
//    private ShopApp shopApp;
//
//    @Mock
//    private Scanner scannerMock;
//
//    @Mock
//    private List<Employee> employeeData;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testMenuForChoose() {
//        int enterId = 1;
//        when(scannerMock.nextInt()).thenReturn(enterId);
//
//        shopApp.menuForChoose();
//
//        verify(shopApp, times(1)).loginAsEmployee();
//    }
//    @Test
//    public void testLoginAsEmployee() {
//        // Подготовка на данните за вход
//        int enterId = 1;
//        String enterFirstName = "Ivan";
//
//        // Подготовка на моковете
//        Employee employee = mock(Employee.class);
//        when(employee.getEmployee_id()).thenReturn(enterId);
//        when(employee.getFirst_name()).thenReturn(enterFirstName);
//        when(employeeData.iterator()).thenReturn(Arrays.asList(employee).iterator());
//
//        // Подготовка на входните данни от конзолата
//        when(scannerMock.nextInt()).thenReturn(enterId);
//        when(scannerMock.next()).thenReturn(enterFirstName);
//
//        // Извикване на метода, който искате да тествате
//        shopApp.loginAsEmployee();
//
//        // Проверка на извикванията на методите на моковете
//        verify(employee, times(1)).getEmployee_id();
//        verify(employee, times(1)).getFirst_name();
//        verify(employee, times(1)).login(enterId, enterFirstName);
//
//        // Проверка на всякакви други неща, които искате да тествате
//        // ...
//
//    }
//}
