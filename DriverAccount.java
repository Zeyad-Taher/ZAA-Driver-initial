package zaa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverAccount extends Account {
    private String nationalID;
    private String drivingLicense;
    private FileWriter driverAppWriter;
    
    public DriverAccount(String username,String password,String mobilePhone,String email,String nationalID,String drivingLicense) throws IOException{
        super();
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        this.nationalID=nationalID;
        this.drivingLicense=drivingLicense;
        setActive(false);
        setType(this);
    }
    
    public void saveAccount(){
        driverAppWriter=getSystem().getDriverAppWriter();
        try {
            driverAppWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getNationalID()+" "+getDrivingLicense()+" "+getActive()+"\n");
            driverAppWriter.flush();
            File driver=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\Drivers\\"+getUsername()+".txt");
            FileWriter driverFile = new FileWriter(driver, true);
        } catch (IOException ex) {
            Logger.getLogger(DriverAccount.class.getName()).log(Level.SEVERE, null, ex);
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