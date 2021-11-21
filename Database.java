package zaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    private static Database uniqueInstance;
    private File userCredentials, driverCredentials, driverApplications, adminCredentials;
    private FileWriter userWriter, driverWriter, driverAppWriter, adminWriter;
    
    private Database () throws IOException{
        File databaseFolder=new File("D:\\Java\\ZAA\\src\\zaa\\Database");
        if(!databaseFolder.exists()){
            databaseFolder.mkdir();
        }
        userCredentials=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\UserCredentials.txt");
        userWriter = new FileWriter(userCredentials, true);
        driverCredentials=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\DriverCredentials.txt");
        driverWriter = new FileWriter(driverCredentials, true);
        driverApplications=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\DriverApplications.txt");
        driverAppWriter = new FileWriter(driverApplications, true);
        adminCredentials=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\AdminCredentials.txt");
        adminWriter = new FileWriter(adminCredentials, true);
    }
    
    public static Database getInstance() throws FileNotFoundException, IOException{
        if(uniqueInstance==null){
            uniqueInstance=new Database();
        }
        return uniqueInstance;
    }
    
    public FileWriter getUserCredentialsWriter(){
        return userWriter;
    }
    
    public FileWriter getDriverCredentialsWriter(){
        return driverWriter;
    }
    
    public FileWriter getDriverAppWriter(){
        return driverAppWriter;
    }
    
    public FileWriter getAdminCredentialsWriter(){
        return adminWriter;
    }
}