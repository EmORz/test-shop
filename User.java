//– userId;
//        – String userName;
//        - void login(int userId, String userName);
public class User implements Interfaces.User {
    private int userId;
    private String userName;

    @Override
    public void login(int employee_id, String first_name) {
        System.out.println("Добре дошли "+first_name+"!");
    }
}
