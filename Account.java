import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Account {
    private String username;
    private String password;
    private String mobilePhone;
    private String email;
    private String type;
    private boolean active;
    private Database system;
    ArrayList<Notification> notifications;

    public Account() throws FileNotFoundException, IOException{
        notifications = new ArrayList<>();
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

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }


    public String getType(){
        return type;
    }
    public abstract void saveAccount();

    @Override
    public String toString() {
        return username;
    }
}