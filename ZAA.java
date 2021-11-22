package zaa;
import java.io.IOException;

public class ZAA {
    private static Authentication user,driver,admin;
    public static void main(String[] args) throws IOException {
        
        user=new UserAuthentication();
        user.register();
        if(user.login()){
            System.out.println("You logged in successfully!");
        }
        else{
            System.out.println("login failed");
        }
        driver=new DriverAuthentication();
        driver.register();
        driver.login();
        admin=new AdminAuthentication();
        admin.register();
        admin.login();
    }
}