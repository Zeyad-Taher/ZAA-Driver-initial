package zaa;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Account {
    private String username;
    private String password;
    private String mobilePhone;
    private String email;
    private String type;
    private boolean active;
    private Database system;
    
    public Account() throws FileNotFoundException, IOException{
        setSystem();
    }
    public void setSystem() throws FileNotFoundException, IOException{
        system=Database.getInstance();
    }
    public Database getSystem(){
        return system;
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
    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
    }
    public String getMobilePhone(){
        return mobilePhone;
    }
    public void setEmail(String email){
        if(email.isBlank()){
            this.email=null;
        }
        else{
            this.email = email;
        }
    }
    public String getEmail(){
        return email;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public boolean getActive(){
        return active;
    }
    public void setType(Account account){
        if(account instanceof UserAccount){
            type="user";
        }
        else{
            type="driver";
        }
    }
    public String getType(){
        return type;
    }
}