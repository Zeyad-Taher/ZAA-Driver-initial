package zaa;
import java.io.IOException;

public class ZAA {
    private static Authentication user,driver,admin;
    public static void main(String[] args) throws IOException {
        
        user=new UserAuthentication();
        user.register();
        UserAccount userAcc=(UserAccount) user.login();
        if(userAcc!=null){
            System.out.println("You logged in successfully!");
            System.out.println("Welcome "+userAcc.getUsername());
        }
        else{
            System.out.println("login failed");
        }
        /*driver=new DriverAuthentication();
        driver.register();
        driver.login();
        admin=new AdminAuthentication();
        admin.register();
        admin.login();*/
    }
}
