import java.sql.Connection;
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
        for(DriverAccount driver : Load.pendingDrivers) {
            System.out.println("Username: " + driver.getUsername() + ", License number: " + driver.getDrivingLicense());
        }
    }

    public void verifyDriver(String username) {
        DriverAccount driver = Load.findPendingDriver(username);
        if(driver != null) {
            try{
                Statement stat = Database.getInstance().createStatement();
                driver.setActive(true);
                String sql = "UPDATE users SET is_active = 1, is_pending = 0 WHERE username = '" + username +"'";
                stat.executeUpdate(sql);
                Load.pendingDrivers.remove(driver);
                Load.drivers.add(driver);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void suspendPerson(String username) {
        if(Load.findActiveDriver(username) != null){
            DriverAccount driver = Load.findActiveDriver(username);
            try{
                Statement stat = Database.getInstance().createStatement();
                driver.setActive(false);
                String sql = "UPDATE users SET is_active = 0, is_pending = 0 WHERE username = '" + username +"'";
                stat.executeUpdate(sql);
                Load.drivers.remove(driver);
                Load.suspendedUsers.add(driver);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(Load.findUser(username) != null) {
            UserAccount user = Load.findUser(username);
            try{
                Statement stat = Database.getInstance().createStatement();
                user.setActive(false);
                String sql = "UPDATE users SET is_active = 0, is_pending = 0 WHERE username = '" + username +"'";
                stat.executeUpdate(sql);
                Load.users.remove(user);
                Load.suspendedUsers.add(user);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            System.out.println("Error: This person is either suspended or doesn't exist");
        }
    }

    public Connection getSystem(){
        return system;
    }
}
