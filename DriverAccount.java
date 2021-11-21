package zaa;

import java.io.FileWriter;
import java.io.IOException;

public class DriverAccount extends Account {
    private String nationalID;
    private String drivingLicense;
    private FileWriter driverAppWriter;
    
    public DriverAccount(String username,String password,String mobilePhone,String email,String nationalID,String drivingLicense) throws IOException{
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        this.nationalID=nationalID;
        this.drivingLicense=drivingLicense;
        setActive(false);
        setType(this);
        Database system=getSystem();
        driverAppWriter=system.getDriverAppWriter();
        driverAppWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getNationalID()+" "+getDrivingLicense()+" "+getActive()+"\n");
        driverAppWriter.flush();
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