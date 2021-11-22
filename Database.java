import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    private static Database uniqueInstance;
    private File userCredentials, driverCredentials, driverApplications, adminCredentials;
    private FileWriter userWriter, driverWriter, driverAppWriter, adminWriter;

    private Database () throws IOException{
        File databaseFolder=new File(System.getProperty("user.dir") + "\\Database");
        File dsersFolder=new File(System.getProperty("user.dir") + "\\Database\\Users");
        dsersFolder.mkdir();
        File driversFolder=new File(System.getProperty("user.dir") + "\\Database\\Drivers");
        driversFolder.mkdir();
        if(!databaseFolder.exists()){
            databaseFolder.mkdir();
        }
        userCredentials=new File(System.getProperty("user.dir") + "\\Database\\UserCredentials.txt");
        userWriter = new FileWriter(userCredentials, true);
        driverCredentials=new File(System.getProperty("user.dir") + "\\Database\\DriverCredentials.txt");
        driverWriter = new FileWriter(driverCredentials, true);
        driverApplications=new File(System.getProperty("user.dir") + "\\Database\\DriverApplications.txt");
        driverAppWriter = new FileWriter(driverApplications, true);
        adminCredentials=new File(System.getProperty("user.dir") + "\\Database\\AdminCredentials.txt");
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