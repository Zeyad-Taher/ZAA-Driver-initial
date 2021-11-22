import java.io.IOException;
import java.util.Scanner;

public class ZAA {
    private static Authentication user,driver;
    private static AdminAuthentication admin;
    public static void main(String[] args) throws IOException {
        System.out.println("This is a static test case, instructions will be written before executing them (instructions start with '**':-");
        Area haram = new Area("haram");
        Area dokki = new Area("dokki");
        System.out.println("**2 areas were created");

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

        admin= new AdminAuthentication();
        admin.register();
        Admin adminAcc=admin.login();
        if(adminAcc!=null){
            System.out.println("You logged in successfully!");
            adminAcc.listPendingDrivers();
            System.out.print("Enter driver's username to verify: ");
            String name=read.nextLine();
            adminAcc.verifyDriver(name);
//            adminAcc.listPendingDrivers();
//            System.out.print("Enter driver's username to suspend: ");
//            name=read.nextLine();
//            adminAcc.suspendPerson(name);
        }
        else{
            System.out.println("login failed");
        }

        DriverAccount driverAcc = (DriverAccount) driver.login();
        if(driverAcc!=null){
            System.out.println("You logged in successfully!");
        }
        else{
            System.out.println("login failed");
        }

        System.out.println("**Setting haram as a fav area for this driver");
        driverAcc.addFavArea(haram);
        System.out.println("**User requesting a ride to haram");
        Ride userRide = userAcc.requestRide(haram, dokki);
        System.out.println("**Driver making an offer to that ride");
        Offer driverOffer = driverAcc.makeOffer(25, userRide);
        System.out.println("**User accepts offer");
        userAcc.acceptOffer(driverOffer);
        System.out.println("**driver's notifications array:");
        System.out.println(userAcc.getNotifications());
        System.out.println("**driver notifications array:");
        System.out.println(driverAcc.getNotifications());
    }
}
