import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class DriverAccount extends Account implements Observer{
    String username;
    String password;
    ArrayList<Area> favAreas;
    ArrayList<Notification> notifications;

    public DriverAccount(String username, String password) {
        this.username = username;
        this.password = password;
        favAreas = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Area> getFavAreas() {
        return favAreas;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFavArea(Area area) {
        if(favAreas.add(area)) {
            area.registerObserver(this);
        }
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
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
