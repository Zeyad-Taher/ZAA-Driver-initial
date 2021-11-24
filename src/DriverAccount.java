import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class DriverAccount extends Account implements Observer {
    private String nationalID;
    private String drivingLicense;
    private ArrayList<Area> favAreas;
    private double averageRating;

    public DriverAccount(String username,String password,String mobilePhone,String email,String nationalID,String drivingLicense) throws SQLException, ClassNotFoundException {
        super();
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        this.nationalID=nationalID;
        this.drivingLicense=drivingLicense;
        active = false;
        setType(this);
        favAreas = new ArrayList<>();
        averageRating = calculateAverageRating();
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
            try {
                area.registerObserver(this);
                Statement stat = Database.getInstance().createStatement();
                String sql = "INSERT OR IGNORE INTO fav_areas" +
                        "(driverUsername, areaName) VALUES ('"+getUsername()+"', '"+area.getName()+"')";
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

    public double getAverageRating() {
        return averageRating;
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

    public void getRatingList() // this function is inside the DriverAccount class which will iterate on the rating file to get the ratings and who rated it.
    {
        try {
            Statement stat = Database.getInstance().createStatement();
            String sql = "SELECT * FROM ratings WHERE driverUsername = '"+getUsername()+"'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("userUsername") + " - " + rs.getInt("rate") + " stars");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertAndUpdateAverageRating(Rating rating) // this function is inside the DriverAccount class which will update the averagerating attribute and the rating list file.
    {
        // add rating to the rating list and update the average Rating
        try {
            Statement stat = Database.getInstance().createStatement();
            String sql = "INSERT OR IGNORE INTO ratings" +
                    "(userUsername, rate, driverUsername) VALUES ('"+rating.getUsername()+"', '"+rating.getRate()+"', '"+getUsername()+"')";
            stat.executeUpdate(sql);
            averageRating = calculateAverageRating();
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Error: can't rate driver " + getUsername());
        }
    }

    public double calculateAverageRating() {
        // compute the average rating by adding all ratings in the file then divide them by their total
        try {
            Statement stat = Database.getInstance().createStatement();
            String sql = "SELECT rate FROM ratings WHERE driverUsername = '"+getUsername()+"'";
            double total = 0;
            int count = 0;
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                total += rs.getInt("rate");
                count++;
            }
            if(count == 0) {
                return 0;
            }
            return total / count;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}