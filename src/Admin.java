import java.sql.Connection;
import java.sql.SQLException;

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
            driver.setActive(true);
        }
    }

    public void suspendPerson(String username) {
        if(Load.findActiveDriver(username) != null){
            Load.findActiveDriver(username).setActive(false);
        }
        else if(Load.findUser(username) != null) {
            Load.findUser(username).setActive(false);
        }
        else {
            System.out.println("Error: This person is either suspended or doesn't exist");
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
