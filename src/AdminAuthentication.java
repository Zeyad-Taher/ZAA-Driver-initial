import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminAuthentication{

    private Connection system;
    private Admin admin;

    public AdminAuthentication() throws SQLException, ClassNotFoundException {
        system=Database.getInstance();
    }

    public Admin login() {
        Scanner read=new Scanner(System.in);
        System.out.println("Admin Login (default 'admin', 'admin'):");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        try {
            Statement stat = system.createStatement();
            String sql = "SELECT * FROM admins WHERE username = '"+username+"' AND password = '" + password + "'";
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()) {
                admin = new Admin(username, password);
            }
            else {
                System.out.println("Error: Admin not found");
            }
        }
        catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

//    public void register() throws FileNotFoundException, IOException {
//        Scanner regRead=new Scanner(System.in);
//        System.out.println("Admin Register:");
//        System.out.print("Username: ");
//        String username=regRead.nextLine();
//        System.out.print("Password: ");
//        String password=regRead.nextLine();
//        system=Database.getInstance();
//        adminCredentials=getSystem().getAdminCredentials();
//        Scanner read=new Scanner(adminCredentials);
//        String currentAdminLine="";
//        boolean found=false;
//        while (read.hasNextLine()){ //check if username is registered
//            currentAdminLine=read.nextLine();
//            String[] userInfo=currentAdminLine.split(" ");
//            if(userInfo[0].equals(username)){
//                found=true;
//                break;
//            }
//        }
//        if(!found){
//            Admin newAdmin=new Admin(username,password);
//            newAdmin.saveAccount();
//            System.out.println("Welcome "+newAdmin.getUsername());
//        }
//        else{
//            System.out.println("Username already registerd");
//        }
//    }

}
