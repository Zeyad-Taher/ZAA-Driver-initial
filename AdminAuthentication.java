package zaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminAuthentication implements Authentication{

    private Database system;
    private Admin admin;

    public AdminAuthentication() throws IOException {
        system=Database.getInstance();
    }
    public Database getSystem(){
        return system;
    }
    @Override
    public boolean login() {
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
                    System.out.println("Welcome "+username);
                    return true;
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
        return false;
    }

    @Override
    public void register() {
        Scanner regRead=new Scanner(System.in);
        System.out.println("Admin Register:");
        System.out.print("Username: ");
        String username=regRead.nextLine();
        System.out.print("Password: ");
        String password=regRead.nextLine();
        try {
            admin=new Admin(username,password);
        } catch (IOException ex) {
            Logger.getLogger(AdminAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
