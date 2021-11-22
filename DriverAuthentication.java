package zaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverAuthentication implements Authentication{

    private Database system;

    public DriverAuthentication() throws IOException {
        system=Database.getInstance();
    }
    public Database getSystem(){
        return system;
    }
    
    @Override
    public boolean login() {
        Scanner read=new Scanner(System.in);
        System.out.println("Driver Login:");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        File currentDrivers=getSystem().getDriverCredentials();
        boolean found=false;
        try {
            Scanner myReader=new Scanner(currentDrivers);
            String currentDriverLine="";
            while (myReader.hasNextLine()) {
                currentDriverLine=myReader.nextLine();
                String[] driverInfo=currentDriverLine.split(" ");
                if(driverInfo[0].equals(username) && driverInfo[1].equals(password)){
                    found=true;
                    if(driverInfo[4].equals("true")){
                        System.out.println("Welcome "+username);
                        return true;
                    }
                    else{
                        System.out.println("You are suspended");
                        return false;
                    }
                }
            }
            myReader.close();
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!found){
            System.out.println("You are not registered");
        }
        return false;
    }

    @Override
    public void register() {
        Scanner read=new Scanner(System.in);
        System.out.println("Driver Register:");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        System.out.print("Mobile Phone: ");
        String mobilePhone=read.nextLine();
        System.out.print("Email: ");
        String email=read.nextLine();
        System.out.print("National ID: ");
        String nationalID=read.nextLine();
        System.out.print("Driving License: ");
        String drivingLicense=read.nextLine();
        try {
            Account driver=new DriverAccount(username,password,mobilePhone,email,nationalID,drivingLicense);
        } catch (IOException ex) {
            Logger.getLogger(DriverAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
