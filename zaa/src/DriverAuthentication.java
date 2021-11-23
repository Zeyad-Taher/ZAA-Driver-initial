import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverAuthentication implements Authentication{

    private Connection system;
    private Account driver;

    public DriverAuthentication() throws SQLException, ClassNotFoundException {
        system=Database.getInstance();
    }
    public Connection getSystem(){
        return system;
    }

    @Override
    public Account login() {
        Scanner read=new Scanner(System.in);
        System.out.println("Driver Login:");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        try {
            Statement stat = system.createStatement();
            String sql = "SELECT * FROM users WHERE is_driver = 1 AND username = '"+username+"' AND password = '" + password + "'";
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next() && rs.getBoolean("is_active") && !rs.getBoolean("is_pending")) {
                driver = new DriverAccount(username, password, rs.getString("mobile_number"), rs.getString("email"), rs.getString("natID"), rs.getString("drivingLicense"));
                ((DriverAccount)driver).setFavAreas(loadDriverAreas(username));
                System.out.println("Logged in as: " + username);
            }
            else if(rs.getBoolean("is_pending")){
                System.out.println("Error: Driver's account is pending");
            }
            else if(!rs.getBoolean("is_active")){
                System.out.println("Error: Driver is suspended");
            } else {
                System.out.println("Error: Driver not registered");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DriverAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return driver;
    }

    @Override
    public void register() {
        Scanner read=new Scanner(System.in);
        System.out.println("Driver Register:");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        System.out.print("Mobile Phone: ");
        String mobilePhone=read.nextLine();
        System.out.print("Email: ");
        String email=read.nextLine();
        System.out.print("National ID: ");
        String nationalID=read.nextLine();
        System.out.print("Driving License: ");
        String drivingLicense=read.nextLine();
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
                    "'"+nationalID+"'," +
                    "'"+drivingLicense+"'," +
                    "0," +
                    "1," +
                    "null);";
            stat.executeUpdate(sql);
            System.out.println("Driver account created successfully");
        } catch (SQLException throwables) {
            System.out.println("Error: User already exists");
        }
    }

    //Helper methods
    private ArrayList<Area> loadDriverAreas(String username) {
        ArrayList<Area> favAreas = new ArrayList<>();
        try {
            Statement stat = system.createStatement();
            String sql = "SELECT * FROM areas WHERE name = (SELECT areaName from fav_areas WHERE driverUsername = '"+username+"')";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                favAreas.add(new Area(rs.getString("name")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return favAreas;
    }
}
