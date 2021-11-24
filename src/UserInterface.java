import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {
    private static Authentication user,driver;
    private static AdminAuthentication admin;
    public void chooseEntityMenu() throws IOException, SQLException, ClassNotFoundException {
        Scanner read=new Scanner(System.in);
        System.out.println("Please choose from the following menu:");
        System.out.println("\n-----Choosing Entity Menu-----\n"+
                "1- Admin\n" + "2- Driver\n" + "3- User\n" + "4- Exit\n");
        int choice = read.nextInt();
        read.nextLine();

        if (choice == 1)
        {
            adminMainMenu();
        }
        else if (choice == 2)
        {
            driverMainMenu();
        }
        else if (choice == 3)
        {
            userMainMenu();
        }
        else if (choice == 4)
        {
            System.out.println("Thanks for using our App, We hope to see you soon =)\n");
        }
    }

    public void adminMainMenu() throws IOException, SQLException, ClassNotFoundException {
        Scanner read=new Scanner(System.in);
        System.out.println("Please choose from the following menu:");
        System.out.println("\n-----Choosing Admin Main Menu-----\n"+
                "1- Login\n" + "2- Back\n" + "3- Exit\n");
        int choice = read.nextInt();
        read.nextLine();

        if (choice == 1)
        {
            admin = new AdminAuthentication();
            System.out.println("Please login:");
            Admin adminAcc=admin.login();
            afterLoginMenu(adminAcc);
        }
        else if (choice == 2)
        {
            chooseEntityMenu();
        }
        else if (choice == 3)
        {
            System.out.println("Thanks for using our App, We hope to see you soon =)\n");
        }
    }

    public void afterLoginMenu(Admin admin) throws IOException, SQLException, ClassNotFoundException {
        Scanner read = new Scanner(System.in);
        System.out.println("Please choose from the following menu:");
        System.out.println("\n-----Choosing from Admin functions-----\n"+
                "1- List pending drivers\n" + "2- Verify pending drivers\n" + "3- Suspend account\n"
                + "4- Logout\n" + "5- Exit\n");
        int choice = read.nextInt();
        read.nextLine();

        if (choice == 1)
        {
            admin.listPendingDrivers();
            afterLoginMenu(admin);
        }
        else if (choice == 2)
        {
            Scanner ned = new Scanner(System.in);
            System.out.println("Please enter the username of the account you want to verify:\n");
            String driverName = ned.nextLine();
            admin.verifyDriver(driverName);
            afterLoginMenu(admin);
        }
        else if (choice == 3)
        {
            Scanner ned = new Scanner(System.in);
            System.out.println("Please enter the username of the account you want to suspend:\n");
            String accountName = ned.nextLine();
            admin.suspendPerson(accountName);
            afterLoginMenu(admin);
        }
        else if (choice == 4)
        {
            adminMainMenu();
        }
        else if (choice == 5)
        {
            System.out.println("Thanks for using our App, We hope to see you soon =)\n");
        }
    }

    public void driverMainMenu () throws IOException, SQLException, ClassNotFoundException {
        Scanner read=new Scanner(System.in);
        System.out.println("Please choose from the following menu:");
        System.out.println("\n-----Choosing Driver Main Menu-----\n"+
                "1- Register\n" + "2- Login\n" + "3- Back\n" + "4- Exit\n");
        driver =new DriverAuthentication();
        int choice = read.nextInt();
        read.nextLine();

        if (choice == 1)
        {
            driver.register();
//            DriverAccount driverAccount = (DriverAccount) driver.login();
//            afterLoginMenu(driverAccount);
            driverMainMenu();
        }
        else if (choice == 2)
        {
            DriverAccount driverAccount = (DriverAccount) driver.login();
            if(driverAccount != null) {
                afterLoginMenu(driverAccount);
            }
            else {
                System.out.println("Driver not registered or not active");
                driverMainMenu();
            }
        }
        else if (choice == 3)
        {
            chooseEntityMenu();
        }
        else if (choice == 4)
        {
            System.out.println("Thanks for using our App, We hope to see you soon =)\n");
        }
    }

    public void afterLoginMenu(DriverAccount driverAccount) throws IOException, SQLException, ClassNotFoundException {
        Scanner read = new Scanner(System.in);
        System.out.println("Please choose from the following menu:");
        System.out.println("\n-----Choosing from Driver functions-----\n"+
                "1- List Ratings\n" + "2- View average rating\n" + "3- List notifications\n"
                + "4- Logout\n" + "5- Exit\n");
        int choice = read.nextInt();
        read.nextLine();

        if (choice == 1)
        {
            driverAccount.getRatingList();
            afterLoginMenu(driverAccount);
        }
        else if (choice == 2)
        {
            System.out.println("Your average rating is " + driverAccount.getAverageRating() + " stars");
            afterLoginMenu(driverAccount);
        }
        else if (choice == 3)
        {
            int count = 1;
            for (Notification notification : driverAccount.notifications) {
                System.out.println(count + "- " + notification);
                count++;
            }
            System.out.print("Choose notification: ");
            int notificationNumber = read.nextInt();
            read.nextLine();
            Ride ride = (Ride) driverAccount.getNotifications().get(notificationNumber - 1).getPayload();
            int price = read.nextInt();
            read.nextLine();
            driverAccount.makeOffer(price, ride);
            afterLoginMenu(driverAccount);
        }
        else if (choice == 4)
        {
            driverMainMenu();
        }
        else if (choice == 5)
        {
            System.out.println("Thanks for using our App, We hope to see you soon =)\n");
        }
    }
    public void userMainMenu () throws IOException, SQLException, ClassNotFoundException {
        Scanner read=new Scanner(System.in);
        System.out.println("Please choose from the following menu:");
        System.out.println("\n-----Choosing User Main Menu-----\n"+
                "1- Register\n" + "2- Login\n" + "3- Back\n" + "4- Exit\n");
        user =new UserAuthentication();
        int choice = read.nextInt();
        read.nextLine();

        if (choice == 1)
        {
            user.register();
//            UserAccount userAccount = (UserAccount) user.login();
//            afterLoginMenu(userAccount);
            userMainMenu();
        }
        else if (choice == 2)
        {
            UserAccount userAccount = (UserAccount) user.login();
            afterLoginMenu(userAccount);
        }
        else if (choice == 3)
        {
            chooseEntityMenu();
        }
        else if (choice == 4)
        {
            System.out.println("Thanks for using our App, We hope to see you soon =)\n");
        }
    }
    public void afterLoginMenu(UserAccount userAccount) throws IOException, SQLException, ClassNotFoundException {
        Scanner read = new Scanner(System.in);
        System.out.println("Please choose from the following menu:");
        System.out.println("\n-----Choosing from User functions-----\n" +
                "1- Show areas\n" + "2- Request ride\n" + "3- View offers\n" + "4- Accept offer\n" + "5- Rate driver\n"
                + "6- Logout\n" + "7- Exit\n");
        int choice = read.nextInt();
        read.nextLine();

        if (choice == 1) {
            for(Area area : Load.areas) {
                System.out.print(area + " - ");
            }
            afterLoginMenu(userAccount);
        }
        else if (choice == 2) {
            System.out.print("Enter your current area's name: ");
            String sourceName = read.nextLine();
            Area sourceArea = Load.findArea(sourceName);
            System.out.print("Enter your destination area's name: ");
            String destinationName = read.nextLine();
            Area destinationArea = Load.findArea(destinationName);
            if(sourceArea != null && destinationArea != null) {
                userAccount.requestRide(sourceArea, destinationArea);
            }
            else {
                System.out.println("Areas entered don't seem to be supported yet");
            }
            afterLoginMenu(userAccount);
        }
        else if (choice == 3) {
            for (Notification notification : userAccount.notifications) {
                System.out.println(notification);
            }
            afterLoginMenu(userAccount);
        }
        else if (choice == 4) {

        }
        else if (choice == 5) {
            System.out.print("Enter driver's username: ");
            String driverUsername = read.nextLine();
            DriverAccount driver = Load.findActiveDriver(driverUsername);
            if(driver != null) {
                userAccount.rateDriver(driver);
            }
            else {
                System.out.println("Error: Driver not found");
            }
            afterLoginMenu(userAccount);
        }
        else if (choice == 6){
            userMainMenu();
        }
        else if (choice == 7) {
            System.out.println("Thanks for using our App, We hope to see you soon =)\n");
        }
    }
}