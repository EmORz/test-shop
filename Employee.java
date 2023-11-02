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
    @Override
    public void login(int employee_id, String first_name) {

    }
}
