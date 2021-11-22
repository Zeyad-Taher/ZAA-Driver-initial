package zaa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAccount extends Account {
    private FileWriter userWriter;
    public UserAccount(String username,String password,String mobilePhone,String email) throws IOException {
        super();
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        setActive(true);
        setType(this);
    }
    
    @Override
    public void saveAccount(){
        userWriter=getSystem().getUserCredentialsWriter();
        try {
            userWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getActive()+"\n");
            userWriter.flush();
            File user=new File("D:\\Java\\ZAA\\src\\zaa\\Database\\Users\\"+getUsername()+".txt");
            FileWriter userFile = new FileWriter(user, true);
        }
        catch (IOException ex) {
            Logger.getLogger(UserAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}