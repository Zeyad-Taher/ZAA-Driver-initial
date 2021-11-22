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
        File dsersFolder=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\Users");
        dsersFolder.mkdir();
        File driversFolder=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\Drivers");
        driversFolder.mkdir();
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
    
    public File getUserCredentials(){
        return userCredentials;
    }
    
    public FileWriter getDriverCredentialsWriter(){
        return driverWriter;
    }
    
    public File getDriverCredentials(){
        return driverCredentials;
    }
    
    public FileWriter getDriverAppWriter(){
        return driverAppWriter;
    }
    
    public File getDriverAppCredentials(){
        return driverApplications;
    }
    
    public FileWriter getAdminCredentialsWriter(){
        return adminWriter;
    }
    
    public File getAdminCredentials(){
        return adminCredentials;
    }
}