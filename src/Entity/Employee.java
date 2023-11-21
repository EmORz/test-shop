package src.Entity;

import src.Interfaces.User;
//клас src.Entity.Employee implement src.Interfaces.src.Entity.User
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
        System.out.println("Добре дошли "+first_name+"!");
        System.out.println("Изберете опция: ");

    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
}
