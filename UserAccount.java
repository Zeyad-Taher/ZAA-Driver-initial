package zaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserAccount extends Account {
    private FileWriter userWriter;
    private File userCredentials;
    public UserAccount(String username,String password,String mobilePhone,String email) throws FileNotFoundException, IOException{
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        setActive(true);
        setType(this);
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
            userWriter=getSystem().getUserCredentialsWriter();
            userWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getActive()+"\n");
            userWriter.flush();
            File user=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\Users\\"+getUsername()+".txt");
            FileWriter userFile = new FileWriter(user, true);
            System.out.println("Welcome "+getUsername());
        }
        else{
            System.out.println("Username already registerd");
        }
    }
}