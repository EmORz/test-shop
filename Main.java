import java.util.Scanner;
//public Product createProduct (int product_id, String name, int quantity, double price, String color, String expires_in){
//        Product product = new Product(product_id,name,quantity, price, color, expires_in);
//        addProduct(product);
//        return product;
//        }
//3,lipstick,6.80,15,makeup,red,04-02-2025
//        4,dog leash,10.30,6,others,blue,
public class Main {

    public static void main(String[] args) {
//        В системата ще има два вида потребители - служители и обикновени клиенти.
//        При стартиране на програмата (след зареждане на данните, което се обяснява по-надолу в описанието на задачата)
//        на потребителя се дава възможност да избере като какъв иска да я използва.
//        Всеки служител има уникално employee_id. Всяка стока има уникално product_id.
//        Информация за клиентите не се съхранява.
        Store store = new Store();


        Scanner scanner = new Scanner(System.in);
//3,Angel,Petkov,50,750
        System.out.print("Въведете име на файл с продукти: ");
        String productsFileName = "product.CSV";
        var produtData = Store.readProductsFromCSV(productsFileName);
        var employeeFileName = "employee.CSV";
        var employeeData = Store.readEmployeeFromCSV(employeeFileName);

        System.out.println("Добре дошли в програма Магазин!");
        System.out.println("Изберете как искате да продължите - клиент-1 или служител-2 ?");
        int chooseTypeLogin = scanner.nextInt();

            if (chooseTypeLogin == 1) {
                while (true){
                    System.out.println("Добре дошли в програма Магазин!");
                    String command = scanner.next();
                }
            } else if (chooseTypeLogin == 2) {
                while (true){
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
                            break;
                        }
                    }
                    if (isHasEmployee == false) {
                        System.out.println("Няма служител с такива данни!");
                    }
                }



            }



//        store.createProduct(4, "Мюсли", 20, 6.25,"Н/А","01-02-2014");
//        store.createEmployee(4, "Ivan", "Ivanov", 35, 820);
//        store.printAllProducts();
//        store.deleteProductById(5);
//        store.deleteEmployeeById(2);
//        store.findProductById(5);





    }
}
