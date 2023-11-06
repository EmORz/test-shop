import Interfaces.User;
//клас Employee implement Interfaces.User
//        - - employee_id,
//        - - first_name,
//        - - last_name,
//        - - age,
//        - - salary
public class Employee implements User {
    private int employee_id;
    private String first_name;
    private String last_name;
    private int age;
    private double salary;

    public Employee(int employee_id, String first_name, String last_name, int age, double salary) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public void login(int employee_id, String first_name) {

    }
}
