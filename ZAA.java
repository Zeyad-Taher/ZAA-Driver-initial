package zaa;
import java.io.IOException;
import java.util.Scanner;

public class ZAA {
    public static void main(String[] args) throws IOException {
        Scanner read=new Scanner(System.in);
        String username,password,mobilePhone,email,nationalID,drivingLicense;
        System.out.println("Create User Account");
        System.out.print("Username: ");
        username=read.nextLine();
        System.out.print("Password: ");
        password=read.nextLine();
        System.out.print("Mobile Phone: ");
        mobilePhone=read.nextLine();
        System.out.print("Email: ");
        email=read.nextLine();
        Account user1=new UserAccount(username,password,mobilePhone,email);
        
        System.out.println("Create User Account");
        System.out.print("Username: ");
        username=read.nextLine();
        System.out.print("Password: ");
        password=read.nextLine();
        System.out.print("Mobile Phone: ");
        mobilePhone=read.nextLine();
        System.out.print("Email: ");
        email=read.nextLine();
        Account user2=new UserAccount(username,password,mobilePhone,email);

        System.out.println("Create Driver Account");
        System.out.print("Username: ");
        username=read.nextLine();
        System.out.print("Password: ");
        password=read.nextLine();
        System.out.print("Mobile Phone: ");
        mobilePhone=read.nextLine();
        System.out.print("Email: ");
        email=read.nextLine();
        System.out.print("National ID: ");
        nationalID=read.nextLine();
        System.out.print("Driving License: ");
        drivingLicense=read.nextLine();
        Account driver1=new DriverAccount(username,password,mobilePhone,email,nationalID,drivingLicense);
        
        System.out.println("Create Admin Account");
        System.out.print("Username: ");
        username=read.nextLine();
        System.out.print("Password: ");
        password=read.nextLine();
        Admin admin=new Admin(username,password);
        admin.listPendingDrivers();
        System.out.print("\nEnter driver username to verify: ");
        String verifiedDriver=read.nextLine();
        admin.verifyDriver(verifiedDriver);
        System.out.print("Enter Person username to suspend: ");
        String suspendedPerson=read.nextLine();
        admin.suspendPerson(suspendedPerson);
    }
}
