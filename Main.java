import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
//public Product createProduct (int product_id, String name, int quantity, double price, String color, String expires_in){
//        Product product = new Product(product_id,name,quantity, price, color, expires_in);
//        addProduct(product);
//        return product;
//        }
//3,lipstick,6.80,15,makeup,red,04-02-2025
//        4,dog leash,10.30,6,others,blue,
public class Main {
    private static   Store store = new Store();
//    private static User user = new User();
    private static String employeeFileName = "employee.CSV";
    private static List<Employee> employeeData = Store.readEmployeeFromCSV(employeeFileName);
    private static boolean exitRequested = false;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws ParseException {
//        В системата ще има два вида потребители - служители и обикновени клиенти.
//        При стартиране на програмата (след зареждане на данните, което се обяснява по-надолу в описанието на задачата)
//        на потребителя се дава възможност да избере като какъв иска да я използва.
//        Всеки служител има уникално employee_id. Всяка стока има уникално product_id.
//        Информация за клиентите не се съхранява.

        System.out.println("Въведете име на файл с продукти: ");
        String productsFileName = "product.CSV";
        var produtData = Store.readProductsFromCSV();
//        var employeeFileName = "employee.CSV";
//        var employeeData = Store.readEmployeeFromCSV(employeeFileName);

        while (!exitRequested){

            System.out.println("1. Вход като служител");
            System.out.println("2. Вход като клиент");
            System.out.println("3. Изход");
            int chooseTypeLogin = getUserChoice();

            switch (chooseTypeLogin){
                case 1: loginAsEmployee();break;
                case 2:loginAsCustomer();break;
                case 3: exitRequested=true;break;
                default:
                    System.out.println("Невалиден избор. Моля, опитайте отново.");break;
            }
        }
        exitFromProgram();

    }

    private static void exitFromProgram() {
        System.out.println("Излизане от програмата......");
        scanner.close();
    }

    private static void loginAsCustomer() {
        System.out.println("Добре дошли в модул за клиенти!");
        System.out.println("За да влезете в системата въведете user_name.");
        String enterUserName = scanner.next();
//        user.login(0, enterUserName);
        while (!exitRequested){
            System.out.println("1. Команда");
            System.out.println("2. Команда");
            System.out.println("3. Изход");

            int customerChoice = getUserChoice();

            switch (customerChoice){

                case 3: exitRequested=true;break;
                default:
                    System.out.println("Невалиден избор. Моля, опитайте отново.");break;
            }

        }
    }

    private static void loginAsEmployee() {
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

                    System.out.println("1. Принтиране на всички продукти");
                    System.out.println("2. Принтиране на всички продукти, сортирани по: име;цена;срок на годност");
                    System.out.println("4. Принтиране на определен продукт (по id)");
                    System.out.println("5. Принтиране на определен продукт (по име)");
                    System.out.println("6. Принтиране на всички продукти с цена, по-висока или равна на зададена от потребителя цена");
                    System.out.println("7. Принтиране на всички продукти с цена, по-ниска от зададена от потребителя цена");
                    System.out.println("8. Принтиране на всички продукти с количество, по-голямо или равно на зададено от потребителя количество");
                    System.out.println("9. Принтиране на всички продукти с количество, по-малко от зададено от потребителя количество");
                    System.out.println("10. Добавяне на продукт");
                    System.out.println("11. Промяна на цената на продукт (по id)");
                    System.out.println("12. Промяна на количеството на продукт (по id)");
                    System.out.println("13. Промяна на името на продукт (по id)");
                    System.out.println("14. Изтриване на продукт (по id)");
                    System.out.println("15. Сортиране на служителите по:" +"име");
                    System.out.println("16. Сортиране на служителите по:" +"заплата");
                    System.out.println("17. Принтиране на всички продукти, сортирани по:" +" име");
                    System.out.println("18. Принтиране на всички продукти, сортирани по:" +" цена");
                    System.out.println("19. Принтиране на всички продукти, сортирани по:" +" срок на годност");
                    System.out.println("20. Принтиране на всички продукти по категория");
                    System.out.println("3. Изход");

                    int employeeChoice = getUserChoice();

                    switch (employeeChoice){
                        case 1: store.printAllProducts();break;
                        case 4: store.findProductById();break;
                        case 5: store.findProductByName();break;
                        case 6: store.printProductsByPriceGreaterThanOrEqual();break;
                        case 7: store.printProductsByPriceLower();break;
                        case 8: store.printAllProductsByQuantityGreaterThanOrEqual();break;
                        case 9: store.printAllProductsByQuantityLower();break;
                        case 10: store.createProduct();break;
                        case 11: store.changeProductPriceById();break;
                        case 12: store.changeProductQuantityById();break;
                        case 13: store.changeProductNameById();break;
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

    private static int getUserChoice() {
        System.out.print("Изберете опция: ");
        return scanner.nextInt();
    }
}
