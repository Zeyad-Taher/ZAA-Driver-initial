//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class DriverAccount extends Account implements Observer {
//    private String nationalID;
//    private String drivingLicense;
//    private FileWriter driverAppWriter;
//    private ArrayList<Area> favAreas;
//
//    public DriverAccount(String username,String password,String mobilePhone,String email,String nationalID,String drivingLicense) throws IOException{
//        super();
//        setUsername(username);
//        setPassword(password);
//        setMobilePhone(mobilePhone);
//        setEmail(email);
//        this.nationalID=nationalID;
//        this.drivingLicense=drivingLicense;
//        setActive(false);
//        setType(this);
//        favAreas = new ArrayList<>();
//    }
//
//    public void saveAccount(){
//        driverAppWriter=getSystem().getDriverAppWriter();
//        try {
//            driverAppWriter.write(getUsername()+" "+getPassword()+" "+getMobilePhone()+" "+getEmail()+" "+getNationalID()+" "+getDrivingLicense()+" "+getActive()+"\n");
//            driverAppWriter.flush();
//            File driver=new File(System.getProperty("user.dir") + "\\Database\\Drivers\\"+getUsername()+".txt");
//            FileWriter driverFile = new FileWriter(driver, true);
//        } catch (IOException ex) {
//            Logger.getLogger(DriverAccount.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void setNationalID(String nationalID){
//        this.nationalID=nationalID;
//    }
//
//    public void setDrivingLicense(String drivingLicense){
//        this.drivingLicense=drivingLicense;
//    }
//
//    public void addFavArea(Area area) {
//        if(favAreas.add(area)) {
//            area.registerObserver(this);
//        }
//    }
//
//    public String getNationalID(){
//        return nationalID;
//    }
//
//    public String getDrivingLicense(){
//        return drivingLicense;
//    }
//
//    public Offer makeOffer(long price, Ride ride) {
//        Offer offer = new Offer(price, ride, this);
//        ride.addOffer(offer);
//        return offer;
//    }
//
//    @Override
//    public void update(Object object) {
//        if(object instanceof Ride){
//            Ride r = (Ride) object;
//            Date date = new Date();
//            Timestamp now = new Timestamp(date.getTime());
//            addNotification(new Notification("new ride from " + r.getSource().getName(), now, (Notifiable) r));
//        }
//        if (object instanceof Offer) {
//            Offer offer = (Offer) object;
//            Date date = new Date();
//            Timestamp now = new Timestamp(date.getTime());
//            addNotification(new Notification(offer.getRide().getUser() + " accepted your offer", now, (Notifiable) offer));
//        }
//    }
//
//}