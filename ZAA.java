package zaa;
import java.io.IOException;
import java.util.Scanner;

public class ZAA {
    private static Authentication user,driver;
    private static AdminAuthentication admin;
    public static void main(String[] args) throws IOException {
        
        Scanner read=new Scanner(System.in);
        
        user=new UserAuthentication();
        user.register();
        UserAccount userAcc=(UserAccount) user.login();
        if(userAcc!=null){
            System.out.println("You logged in successfully!");
        }
        else{
            System.out.println("login failed");
        }
        driver=new DriverAuthentication();
        driver.register();
        DriverAccount driverAcc = (DriverAccount) driver.login();
        if(driverAcc!=null){
            System.out.println("You logged in successfully!");
        }
        else{
            System.out.println("login failed");
        }
        admin= new AdminAuthentication();
        admin.register();
        Admin adminAcc=admin.login();
        if(adminAcc!=null){
            System.out.println("You logged in successfully!");
            adminAcc.listPendingDrivers();
            String name=read.nextLine();
            adminAcc.verifyDriver(name);
            adminAcc.listPendingDrivers();
            name=read.nextLine();
            adminAcc.suspendPerson(name);
        }
        else{
            System.out.println("login failed");
        }
    }
}
