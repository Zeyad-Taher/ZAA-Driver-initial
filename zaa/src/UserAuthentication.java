import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAuthentication implements Authentication{
    private Connection system;
    private Account user;
    private File userCredentials;

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
        try {
            Statement stat = system.createStatement();
            String sql = "SELECT * FROM users WHERE username = '"+username+"' AND password = '" + password + "'";
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next() && rs.getBoolean("is_active")) {
                user = new UserAccount(username, password, rs.getString("mobile_number"), rs.getString("email"));
                System.out.println("Logged in as: " + username);
            }
            else if(!rs.getBoolean("is_active")){
                System.out.println("Error: User is suspended");
            } else {
                System.out.println("Error: User not registered");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, ex);
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
                "1," +
                "null," +
                "null," +
                "1," +
                "0," +
                "null);";
            stat.executeUpdate(sql);
            System.out.println("User account created successfully");
        } catch (SQLException throwables) {
            System.out.println("Username already registered");
        }
    }
}