package src;

import src.Entity.Employee;
import src.Entity.User;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ShopApp {

    private Scanner scanner;
    private List<Employee> employeeData;
    private User user;
    private Employee employee;
    private Store store;
    private Printer printer;
    private static String employeeFileName = "src/employee.CSV";
    public boolean exitRequested;
    private boolean isMenuForEmployee;



    public ShopApp() {
        this.scanner = new Scanner(System.in);
        this.printer = new Printer();
        this.employeeData = new ArrayList<>();
        this.exitRequested = false;
        this.isMenuForEmployee = false;
        this.store = new Store();
        employeeData = this.store.readEmployeeFromCSV(employeeFileName);
        this.user = new User();
        this.employee = new Employee();

    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public void menuForChoose(InputStream inputStream){
        this.scanner = new Scanner(inputStream);
                while (!exitRequested){

            System.out.println("1. Вход като служител");
            System.out.println("2. Вход като клиент");
            System.out.println("3. Изход");
            int chooseTypeLogin = getUserChoice();

            switch (chooseTypeLogin){
                case 1: loginAsEmployee(System.in);break;
                case 2:loginAsCustomer(System.in);break;
                case 3: exitRequested=true;break;
                default:
                    System.out.println("Невалиден избор. Моля, опитайте отново.");break;
            }
        }
        exitFromProgram();
    }
    private void exitFromProgram() {
        System.out.println("Излизане от програмата......");
        scanner.close();
    }

    public void start(){
        menuForChoose(System.in);
    }

    public void loginAsEmployee(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        System.out.println("Добре дошли в модул за служители!");
        System.out.println("За да влезете в системата въведете уникално Id и first_name.");
        System.out.print("Въведи Id: ");
        int enterId = scanner.nextInt();
        System.out.println("Въведи first_name: ");
        String enterFirstName = scanner.next();
        boolean isHasEmployee=false;

        for (Employee employee:employeeData
        ) {
            if (employee.getEmployee_id() == enterId
                    && employee.getFirst_name().equalsIgnoreCase(enterFirstName)) {
                isHasEmployee=true;
                employee.login(enterId, enterFirstName);
                while (!exitRequested){
                    isMenuForEmployee = true;
                    printMenu(isMenuForEmployee);

                    int employeeChoice = getUserChoice();

                    switch (employeeChoice){
                        case 1: store.printaAllProducts();break;
                        case 22: store.printExpiredProducts();break;
                        case 23: store.reducePriceForExpiringProducts();break;
                        case 4: store.findProductById(System.in);break;
                        case 5: store.findProductByName(System.in);break;
                        case 21: store.findProductsByPartOfName(System.in);break;
                        case 6: store.printProductsByPriceGreaterThanOrEqual();break;
                        case 7: store.printProductsByPriceLower();break;
                        case 8: store.printAllProductsByQuantityGreaterThanOrEqual();break;
                        case 9: store.printAllProductsByQuantityLower();break;
                        case 10: store.createProduct(false);break;
                        case 11: store.changeProductPriceById(false);break;
                        case 12: store.changeProductQuantityById(false);break;
                        case 13: store.changeProductNameById(false);break;
                        case 14: store.deleteProductById();break;
                        case 15: store.sortEmployeesByName();break;
                        case 16: store.sortEmployeeBySalary();break;
                        case 17: store.printProductsSortedByName();break;
                        case 18: store.pritProcutsSortedByPrice();break;
                        case 19: store.printProductsSortedByExpirationDate();break;
                        case 20: store.printProductsByCategory();break;
                        case 3: exitRequested=true;break;
                        case 2: store.printAllProductsSortedByNamePriceExpirationDate();break;
                        default:
                            System.out.println("Невалиден избор. Моля, опитайте отново.");break;
                    }
                }
            }
        }
        if (isHasEmployee == false) {
            System.out.println("Няма служител с такива данни!");

        }
    }

    public void loginAsCustomer(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        System.out.println("Добре дошли в модул за клиенти!");
        System.out.println("За да влезете в системата въведете user_name.");
        String enterUserName = scanner.next();
        //За потребителя не се пази информация. Опитвам генериране на уникално ID за всеки потребител.
        UUID userId = UUID.randomUUID();
        long mostSignificantBits = userId.getMostSignificantBits();
        long leastSignificanBits = userId.getLeastSignificantBits();
        int integerRepresentation = Math.abs((int) mostSignificantBits+(int) leastSignificanBits);
        user.login(integerRepresentation, enterUserName);
        while (!exitRequested){
            isMenuForEmployee = false;

            printMenu(isMenuForEmployee);

            int customerChoice = getUserChoice();

            switch (customerChoice){
                case 1:user.printAvailableProducts();break;
                case 2:user.searchProductsByCategory();break;
                case 4:user.searchProductByName();break;
                case 5:user.addToShoppingCart(System.in);break;
                case 6:
                    System.out.println("Обща цена на продуктите в кошницата: "+user.calculateTotalPrice()+" лв.");break;
                case 7: user.checkout(false);break;
                case 8: user.searchProductsByPartOfName();break;
                case 3: exitRequested=true;break;
                default:
                    System.out.println("Невалиден избор. Моля, опитайте отново.");break;
            }

        }
    }

    private void printMenu(boolean isMenuForEmployee) {

        if (isMenuForEmployee==true) {
            this.printer.printMenuEmployee();
        }else if (isMenuForEmployee == false) {
          this.printer.printMenuUser();
        }

    }

    public int getUserChoice() {
        System.out.print("Изберете опция: ");
        return scanner.nextInt();
    }
}
