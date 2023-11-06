import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Store store = new Store();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Въведете име на файл с продукти: ");
        String productsFileName = "product.CSV";
        var produtData = Store.readProductsFromCSV(productsFileName);
        var employeeFileName = "employee.CSV";
        var employeeData = Store.readEmployeeFromCSV(employeeFileName);
        System.out.println();




    }
}
