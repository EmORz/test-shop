package src;

import src.Entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.Main.*;

public class ShopApp {

    private Scanner scanner;
    private List<Employee> employeeData;

    public ShopApp() {
        this.scanner = new Scanner(System.in);
        this.employeeData = new ArrayList<>();
    }

    public void start(){
        loginAsEmployee();
    }

    public void loginAsEmployee() {
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
//                while (!exitRequested){
//                    isMenuForEmployee = true;
//                    printMenu(isMenuForEmployee);
//
//                    int employeeChoice = getUserChoice();
//
//                    switch (employeeChoice){
//                        case 1: store.printAllProducts();break;
//                        case 4: store.findProductById();break;
//                        case 5: store.findProductByName();break;
//                        case 6: store.printProductsByPriceGreaterThanOrEqual();break;
//                        case 7: store.printProductsByPriceLower();break;
//                        case 8: store.printAllProductsByQuantityGreaterThanOrEqual();break;
//                        case 9: store.printAllProductsByQuantityLower();break;
//                        case 10: store.createProduct();break;
//                        case 11: store.changeProductPriceById();break;
//                        case 12: store.changeProductQuantityById();break;
//                        case 13: store.changeProductNameById();break;
//                        case 14: store.deleteProductById();break;
//                        case 15: store.sortEmployeesByName();break;
//                        case 16: store.sortEmployeeBySalary();break;
//                        case 17: store.printProductsSortedByName();break;
//                        case 18: store.pritProcutsSortedByPrice();break;
//                        case 19: store.printProductsSortedByExpirationDate();break;
//                        case 20: store.printProductsByCategory();break;
//                        case 3: exitRequested=true;break;
//                        case 2: store.printAllProductsSortedByNamePriceExpirationDate();break;
//                        default:
//                            System.out.println("Невалиден избор. Моля, опитайте отново.");break;
//                    }
//                }
            }
        }
        if (isHasEmployee == false) {
            System.out.println("Няма служител с такива данни!");

        }
    }
}
