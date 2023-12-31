package src;

import src.Entity.Employee;
import src.Entity.Product;
import java.util.List;

public class Printer {


    public Printer()  {

    }

    public void printEmployeeList(List<Employee> employeeList, String text) {
        System.out.println("Служителите са сортирани по "+text);
        for (Employee employee : employeeList) {
            printEmployeeDetails(employee);
        }
    }
    public void printEmployeeDetails(Employee employee){

        System.out.println(employee.getFirst_name()+" "+employee.getLast_name()+"; Заплата: "+employee.getSalary());

    }
    public void printProductDetails(Product product) {
        System.out.println("*".repeat(10));

        System.out.println("ID на продукта: "+ product.getProduct_id());
        System.out.println("Име на продукта: "+ product.getName());
        System.out.println("Количество на продукта: "+ product.getQuantity());
        System.out.println("Цена на продукта: "+ product.getPrice());
        System.out.println("Цвят на продукта: "+ product.getColor());
        System.out.println("Срок на годност на продукта: "+ product.getExpires_in());

        System.out.println("*".repeat(10));
        System.out.println("-".repeat(10));
    }

    public void printProductList(List<Product> productList) {
        for (Product product : productList) {
            printProductDetails(product);
        }
    }

    public void printMenuEmployee(){
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
        System.out.println("21. Принтиране на всички продукти по част от името");
        System.out.println("22. Принтиране на всички продукти с изтекъл срок на годност");
        System.out.println("23. Намаляване цената на всички продукти с 20 %, с изтичащ срок на годност < 10 дни");
        System.out.println("3. Изход");
    }

    public void printMenuUser(){
        System.out.println("1. Принтиране на всички продукти");
        System.out.println("2. Принтиране на всички продукти по категория");
        System.out.println("4. Търсене на продукти по име");
        System.out.println("5. Добавяне на продукт към потребителска количка /по ID и к-во/");
        System.out.println("6. Изчисляване на цената на продуктите в количката");
        System.out.println("7. Покупка на продуктите в количката");
        System.out.println("8. Търсене на продукти по част от име");
        System.out.println("3. Изход");
    }

}
