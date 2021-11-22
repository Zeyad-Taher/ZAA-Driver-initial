//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class UserAccount extends Account implements Observer {
//    private FileWriter userWriter;
//    public UserAccount(String username,String password,String mobilePhone,String email) throws IOException {
//        super();
//        setUsername(username);
//        setPassword(password);
//        setMobilePhone(mobilePhone);
//        setEmail(email);
//        setActive(true);
//        setType(this);
//    }
//
//    public Ride requestRide(Area source, Area destination) {
//        Ride ride = new Ride(source, destination, this);
//        return ride;
//    }
//
//    public void acceptOffer(Offer offer) {
//        offer.accept();
//    }
//
//    @Override
//    public void update(Object offer) {
//        Offer o = (Offer) offer;
//        java.util.Date date = new Date();
//        Timestamp now = new Timestamp(date.getTime());
//        notifications.add(new Notification(offer.toString(), now, o));
//    }
//
//    @Override
//    public void saveAccount(){
//        userWriter=getSystem().getUserCredentialsWriter();
//        try {
//            userWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getActive()+"\n");
//            userWriter.flush();
//            File user=new File(System.getProperty("user.dir") + "\\Database\\Users\\"+getUsername()+".txt");
//            FileWriter userFile = new FileWriter(user, true);
//        }
//        catch (IOException ex) {
//            Logger.getLogger(UserAccount.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}