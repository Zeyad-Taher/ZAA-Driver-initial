import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserAuthentication implements Authentication{
    private Connection system;
    private Account user;

    public UserAuthentication() throws SQLException, ClassNotFoundException {
        system=Database.getInstance();
    }
    public Connection getSystem(){
        return system;
    }

    @Override
    public Account login() {
        Scanner read=new Scanner(System.in);
        System.out.println("User Login:");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        user = Load.findUser(username);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                System.out.println("Logged in as: " + username);
            } else {
                user = null;
                System.out.println("Error : Password incorrect");
            }
        }
        return user;
    }

    @Override
    public void register() {
        Scanner regRead=new Scanner(System.in);
        System.out.println("User Register:");
        System.out.print("Username: ");
        String username=regRead.nextLine();
        System.out.print("Password: ");
        String password=regRead.nextLine();
        System.out.print("Mobile Phone: ");
        String mobilePhone=regRead.nextLine();
        System.out.print("Email: ");
        String email=regRead.nextLine();
        try {
            Statement stat = system.createStatement();
            String sql = "INSERT INTO users" +
                "(username," +
                "password," +
                "mobile_number," +
                "email," +
                "is_driver," +
                "natID," +
                "drivingLicense," +
                "is_active," +
                "is_pending," +
                "areaSubscriptionID)" +
                "VALUES" +
                "('"+ username +"'," +
                "'"+password+"'," +
                "'"+mobilePhone+"'," +
                "'"+email+"'," +
                "0," +
                "null," +
                "null," +
                "1," +
                "0," +
                "null);";
            stat.executeUpdate(sql);
            Load.users.add(new UserAccount(username, password, mobilePhone, email));
            System.out.println("User account created successfully");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Username already registered");
        }
    }
}