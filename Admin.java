package zaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin {
    private String username;
    private String password;
    private Database system;
    private FileWriter adminWriter;
    
    public Admin(String username,String password) throws IOException{
        this.username=username;
        this.password=password;
        setSystem();
        system=getSystem();
        adminWriter=system.getAdminCredentialsWriter();
        adminWriter.write(getUsername()+" "+getPassword());
        adminWriter.close();
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    
    public void listPendingDrivers() throws FileNotFoundException{
        String pendingDrivers="";
        File driverApplications=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\DriverApplications.txt");
        Scanner myReader=new Scanner(driverApplications);
        while (myReader.hasNextLine()) {
            pendingDrivers += myReader.nextLine();
        }
        myReader.close();
        System.out.print(pendingDrivers);
    }
    
    public void verifyDriver(String username) throws FileNotFoundException, IOException{
        String newPendingDrivers="";
        String verifiedDriver="";
        File driverApplications=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\DriverApplications.txt");
        Scanner myReader=new Scanner(driverApplications);
        String driverLine="";
        while (myReader.hasNextLine()) {
            driverLine=myReader.nextLine();
            String[] driverInfo=driverLine.split(" ");
            if(driverInfo[0].equals(username)){
                verifiedDriver=driverLine;
                continue;
            }
            newPendingDrivers += driverLine+"\n";
        }
        FileWriter myWriter=new FileWriter("D:\\Java\\ZAA\\src\\zaa\\Database\\DriverApplications.txt");
        myWriter.write(newPendingDrivers);
        myReader.close();
        FileWriter driverWriter=system.getDriverCredentialsWriter();
        driverWriter.write(activateDriver(verifiedDriver)+"\n");
        driverWriter.close();
    }
    
    public void suspendPerson(String username) throws FileNotFoundException, IOException{
        String suspended="";
        File drivers=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\DriverCredentials.txt"); // search in drivers
        Scanner myReader=new Scanner(drivers);
        String newDrivers="";
        String driverLine="";
        while (myReader.hasNextLine()) {
            driverLine=myReader.nextLine();
            String[] driverInfo=driverLine.split(" ");
            if(driverInfo[0].equals(username)){
                suspended=driverLine;
                continue;
            }
            newDrivers+=driverLine+"\n";
        }
        if(!suspended.isEmpty()){
            newDrivers+=deActivatePerson(suspended);
            FileWriter driverWriter = new FileWriter(drivers);
            driverWriter.write(newDrivers);
            driverWriter.close();
        }
        else{ // search in users
            File users=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\UserCredentials.txt");
            myReader=new Scanner(users);
            String newUsers="";
            String userLine="";
            while (myReader.hasNextLine()) {
                userLine=myReader.nextLine();
                String[] userInfo=userLine.split(" ");
                if(userInfo[0].equals(username)){
                    suspended=userLine;
                    continue;
                }
                newUsers+=userLine+"\n";
            }
            if(!suspended.isEmpty()){
                newUsers+=deActivatePerson(suspended)+"\n";
                FileWriter userWriter = new FileWriter(users);
                userWriter.write(newUsers);
                userWriter.close();
            }
        }
        
        myReader.close();
    }
    
    public void setSystem() throws FileNotFoundException, IOException{
        system=Database.getInstance();
    }
    
    public Database getSystem(){
        return system;
    }
    
    public String activateDriver(String driver){
        String[] driverInfo=driver.split(" ");
        String newDriver="";
        for(int i=0;i<driverInfo.length-1;i++){
            newDriver+=driverInfo[i]+" ";
        }
        newDriver+="true";
        return newDriver;
    }
    
    public String deActivatePerson(String person){
        String[] driverInfo=person.split(" ");
        String newPerson="";
        for(int i=0;i<driverInfo.length-1;i++){
            newPerson+=driverInfo[i]+" ";
        }
        newPerson+="false";
        return newPerson;
    }
}
