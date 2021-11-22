package zaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminAuthentication{

    private Database system;
    private Admin admin;
    private File adminCredentials;

    public AdminAuthentication() throws IOException {
        system=Database.getInstance();
    }
    public Database getSystem(){
        return system;
    }

    public Admin login() throws IOException {
        Scanner read=new Scanner(System.in);
        System.out.println("Admin Login:");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        File currentAdmins=getSystem().getAdminCredentials();
        boolean found=false;
        try {
            Scanner myReader=new Scanner(currentAdmins);
            String currentAdminLine="";
            while (myReader.hasNextLine()) {
                currentAdminLine=myReader.nextLine();
                String[] adminInfo=currentAdminLine.split(" ");
                if(adminInfo[0].equals(username) && adminInfo[1].equals(password)){
                    found=true;
                    admin=new Admin(username,password);
                    System.out.println("Welcome "+username);
                    return admin;
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(AdminAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!found){
            System.out.println("You are not registered");
        }
        return admin;
    }

    public void register() throws FileNotFoundException, IOException {
        Scanner regRead=new Scanner(System.in);
        System.out.println("Admin Register:");
        System.out.print("Username: ");
        String username=regRead.nextLine();
        System.out.print("Password: ");
        String password=regRead.nextLine();
        system=Database.getInstance();
        adminCredentials=getSystem().getAdminCredentials();
        Scanner read=new Scanner(adminCredentials);
        String currentAdminLine="";
        boolean found=false;
        while (read.hasNextLine()){ //check if username is registered
            currentAdminLine=read.nextLine();
            String[] userInfo=currentAdminLine.split(" ");
            if(userInfo[0].equals(username)){
                found=true;
                break;
            }
        }
        if(!found){
            Admin newAdmin=new Admin(username,password);
            newAdmin.saveAccount();
            System.out.println("Welcome "+newAdmin.getUsername());
        }
        else{
            System.out.println("Username already registerd");
        }
    }
    
}
