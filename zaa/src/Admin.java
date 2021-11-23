import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
    private String username;
    private String password;
    private Connection system;

    public Admin(String username,String password) throws SQLException, ClassNotFoundException {
        this.username=username;
        this.password=password;
        system = Database.getInstance();
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void listPendingDrivers() {
        Statement stat = null;
        try {
            stat = system.createStatement();
            String sql = "SELECT * FROM users WHERE is_pending = 1";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Username: "+rs.getString("username")+" Driving License: " + rs.getString("drivingLicense") );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void verifyDriver(String username) {
        try {
            Statement stat = system.createStatement();
            String sql = "UPDATE users SET is_pending = 0, is_active = 1  WHERE username = '"+ username +"'";
            stat.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void suspendPerson(String username) {
        Statement stat = null;
        try {
            stat = system.createStatement();
            String sql = "UPDATE users SET is_active = 0 WHERE username = '"+ username +"' AND is_active = 1";
            stat.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getSystem(){
        return system;
    }

    public String activateDriver(String driver){
        String[] driverInfo=driver.split(" ");
        String newDriver="";
        for(int i=0;i<driverInfo.length-1;i++){
            newDriver+=driverInfo[i]+" ";
        }
        newDriver+="true";
        return newDriver;
    }

    public String deActivatePerson(String person){
        String[] driverInfo=person.split(" ");
        String newPerson="";
        for(int i=0;i<driverInfo.length-1;i++){
            newPerson+=driverInfo[i]+" ";
        }
        newPerson+="false";
        return newPerson;
    }
}
