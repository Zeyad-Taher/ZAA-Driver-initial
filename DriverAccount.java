package zaa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DriverAccount extends Account {
    private String nationalID;
    private String drivingLicense;
    private FileWriter driverAppWriter;
    private File driverCredentials;
    
    public DriverAccount(String username,String password,String mobilePhone,String email,String nationalID,String drivingLicense) throws IOException{
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        this.nationalID=nationalID;
        this.drivingLicense=drivingLicense;
        setActive(false);
        setType(this);
        driverCredentials=getSystem().getDriverCredentials();
        Scanner read=new Scanner(driverCredentials);
        String currentDriverLine="";
        boolean found=false;
        while (read.hasNextLine()){ //check if username is registered
            currentDriverLine=read.nextLine();
            String[] driverInfo=currentDriverLine.split(" ");
            if(driverInfo[0].equals(username)){
                found=true;
                break;
            }
        }
        if(!found){
            driverAppWriter=getSystem().getDriverAppWriter();
            driverAppWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getNationalID()+" "+getDrivingLicense()+" "+getActive()+"\n");
            driverAppWriter.flush();
            File driver=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\Drivers\\"+getUsername()+".txt");
            FileWriter driverFile = new FileWriter(driver, true);
            System.out.println("Account creation request has been made");
        }
        else{
            System.out.println("Username already registered");
        }
    }
    
    public void setNationalID(String nationalID){
        this.nationalID=nationalID;
    }
    
    public void setDrivingLicense(String drivingLicense){
        this.drivingLicense=drivingLicense;
    }
    
    public String getNationalID(){
        return nationalID;
    }
    
    public String getDrivingLicense(){
        return drivingLicense;
    }
    
}