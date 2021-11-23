import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class DriverAccount extends Account implements Observer {
    private String nationalID;
    private String drivingLicense;
    private ArrayList<Area> favAreas;

    public DriverAccount(String username,String password,String mobilePhone,String email,String nationalID,String drivingLicense) throws SQLException, ClassNotFoundException {
        super();
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        this.nationalID=nationalID;
        this.drivingLicense=drivingLicense;
        setActive(false);
        setType(this);
        favAreas = new ArrayList<>();
    }

    public void setNationalID(String nationalID){
        this.nationalID=nationalID;
    }

    public void setDrivingLicense(String drivingLicense){
        this.drivingLicense=drivingLicense;
    }

    public void setFavAreas(ArrayList<Area> favAreas) {
        this.favAreas = favAreas;
    }

    public void addFavArea(Area area) {
        if(favAreas.add(area)) {
            area.registerObserver(this);
            try {
                Statement stat = Database.getInstance().createStatement();
                String sql = "INSERT INTO fav_areas" +
                        "(areaName, driverUsername) VALUES ('"+getUsername()+"', '"+area.getName()+"')";
                stat.executeUpdate(sql);
            } catch (SQLException | ClassNotFoundException throwables) {
                System.out.println("Error: can't add fav area" + area);
            }
        }
    }

    public String getNationalID(){
        return nationalID;
    }

    public String getDrivingLicense(){
        return drivingLicense;
    }

    public Offer makeOffer(long price, Ride ride) {
        Offer offer = new Offer(price, ride, this);
        ride.addOffer(offer);
        return offer;
    }

    @Override
    public void update(Object object) {
        if(object instanceof Ride){
            Ride r = (Ride) object;
            Date date = new Date();
            Timestamp now = new Timestamp(date.getTime());
            addNotification(new Notification("new ride from " + r.getSource().getName(), now, (Notifiable) r));
        }
        if (object instanceof Offer) {
            Offer offer = (Offer) object;
            Date date = new Date();
            Timestamp now = new Timestamp(date.getTime());
            addNotification(new Notification(offer.getRide().getUser() + " accepted your offer", now, (Notifiable) offer));
        }
    }

}