package zaa;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class UserAccount extends Account {
    private FileWriter userWriter;
    public UserAccount(String username,String password,String mobilePhone,String email) throws FileNotFoundException, IOException{
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        setActive(true);
        setType(this);
        userWriter=getSystem().getUserCredentialsWriter();
        userWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getActive()+"\n");
        userWriter.flush();
    }
}