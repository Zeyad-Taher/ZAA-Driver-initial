package zaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAuthentication implements Authentication{
    private Database system;
    private Account user;
    private File userCredentials;

    public UserAuthentication() throws IOException {
        system=Database.getInstance();
    }
    public Database getSystem(){
        return system;
    }
    
    @Override
    public Account login() {
        Scanner read=new Scanner(System.in);
        System.out.println("User Login:");
        System.out.print("Username: ");
        String username=read.nextLine();
        System.out.print("Password: ");
        String password=read.nextLine();
        File currentUsers=getSystem().getUserCredentials();
        boolean found=false;
        try {
            Scanner myReader=new Scanner(currentUsers);
            String currentUserLine="";
            while (myReader.hasNextLine()) {
                currentUserLine=myReader.nextLine();
                String[] userInfo=currentUserLine.split(" ");
                if(userInfo[0].equals(username) && userInfo[1].equals(password)){
                    found=true;
                    if(userInfo[4].equals("true")){
                        System.out.println("Welcome "+username);
                        user=new UserAccount(userInfo[0],userInfo[1],userInfo[2],userInfo[3]);
                        return user;
                    }
                    else{
                        System.out.println("You are suspended");
                        return user;
                    }
                }
            }
            myReader.close();
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!found){
            System.out.println("You are not registered");
        }
        return user;
    }

    @Override
    public void register() {
        Scanner regRead=new Scanner(System.in);
        System.out.println("User Register:");
        System.out.print("Username: ");
        String username=regRead.nextLine();
        System.out.print("Password: ");
        String password=regRead.nextLine();
        System.out.print("Mobile Phone: ");
        String mobilePhone=regRead.nextLine();
        System.out.print("Email: ");
        String email=regRead.nextLine();
        try {
            userCredentials=getSystem().getUserCredentials();
            Scanner read=new Scanner(userCredentials);
            String currentUserLine="";
            boolean found=false;
            while (read.hasNextLine()){ //check if username is registered
                currentUserLine=read.nextLine();
                String[] userInfo=currentUserLine.split(" ");
                if(userInfo[0].equals(username)){
                    found=true;
                    break;
                }
            }
            if(!found){
                Account newUser=new UserAccount(username,password,mobilePhone,email);
                newUser.saveAccount();
                System.out.println("User acoount created successfully");
            }
            else{
                System.out.println("Username already registerd");
            }
        } catch (IOException ex) {
            Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}